package com.github.dohyeon0608.usefulplugin

import com.github.dohyeon0608.usefulplugin.data.ConfigManager
import com.github.dohyeon0608.usefulplugin.data.DataManager.getFileFromPlayer
import com.github.dohyeon0608.usefulplugin.data.DataManager.loadPluginData
import net.kyori.adventure.text.Component.text
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

object Home  {

    private const val fileDir = "home"
    private const val homeDir = "$fileDir.homes"
    private const val homeList = "$fileDir.homeList"
    private const val locationPath = "loc"
    private const val iconPath = "icon"

    fun setHome(player: Player, name: String = ConfigManager.homeSetting.defaultHomeName){
        val maxHomeLetter = ConfigManager.homeSetting.maxNameLength
        val maxHomeCount = ConfigManager.homeSetting.maxHomeCount
        if(player.getHomeList().size >= maxHomeCount){
            player.sendMessage("${ChatColor.RED}최대 홈 보유 개수(${maxHomeCount}개)를 초과하였습니다.")
            return
        }
        if(name.length >= maxHomeLetter){
            player.sendMessage("${ChatColor.RED}홈의 이름은 최대 ${maxHomeLetter}자를 넘을 수 없습니다.")
            return
        }
        if(name.contains(".")){
            player.sendMessage("${ChatColor.RED}올바르지 않은 이름입니다! (\".\" 포함)")
            return
        }
        val data = player.loadPluginData()
        val icon = player.location.add(0.0, -1.0, 0.0).block.type
        val location = player.location
        data.set("$homeDir.$name.loc", location)
        data.set("$homeDir.$name.icon", ItemStack(
            if(!icon.isItem){
                when(icon){
                    Material.WATER -> Material.WATER_BUCKET
                    Material.LAVA -> Material.LAVA_BUCKET
                    else -> Material.BARRIER
                }
            } else if(icon == Material.AIR) {
                Material.BARRIER
            } else icon,
            1
        ).apply {
            this.itemMeta.apply {
                displayName(text("${ChatColor.GOLD}${ChatColor.BOLD}${name}"))
                lore( listOf(
                    text("${ChatColor.WHITE}world: ${player.world.name}"),
                    text("${ChatColor.WHITE}x: ${location.x.toInt()}, y: ${location.y.toInt()}, z: ${location.z.toInt()}")
                ))
            }.also { itemMeta = it }
        }
        )
        val list = player.getHomeList()
        if(!list.contains(name)) list.add(name)
        data.set(homeList, list)
        data.save(getFileFromPlayer(player))
        player.sendMessage("${ChatColor.GOLD}현재 위치를 홈 ${ChatColor.RESET}${name}${ChatColor.GOLD}의 위치로 설정하였습니다!")
    }

    fun goHome(player: Player, name: String = ConfigManager.homeSetting.defaultHomeName){
        homeRun(player, name) {
            val location = player.getHomeLocation(name)
            if(location != null) {
                player.run {
                    teleport(location)
                    sendMessage("${ChatColor.GOLD}홈 ${ChatColor.RESET}${name}${ChatColor.GOLD}(으)로 이동하였습니다!")
                }
            } else {
                player.sendMessage("${ChatColor.RED}이동 할 수 없습니다!")
            }
        }
    }

    fun removeHome(player: Player, name: String = ConfigManager.homeSetting.defaultHomeName){
        homeRun(player, name) {
            player.run {
                val data = player.loadPluginData()
                val list = this.getHomeList()
                if(list.contains(name)) list.remove(name)
                data.set("$homeDir.$name", null)
                data.set(homeList, list)
                data.save(getFileFromPlayer(player))
                sendMessage("${ChatColor.GOLD}홈 ${ChatColor.RESET}${name}${ChatColor.GOLD}을(를) 삭제했습니다!")
            }
        }
    }

    private fun Player.getHomeLocation(name: String) = this.loadPluginData().getLocation("$homeDir.$name.$locationPath")
    fun Player.getHomeIcon(name: String) = this.loadPluginData().getItemStack("$homeDir.$name.$iconPath")
    fun Player.getHomeList(): MutableList<String> = this.loadPluginData().getStringList(homeList)

    private fun homeRun(player: Player, name: String, run: () -> Unit){
        val data = player.loadPluginData()
        if(data.contains("$homeDir.$name")){
            run()
        } else {
            player.sendMessage("${ChatColor.RED}홈 ${ChatColor.RESET}${name}${ChatColor.RED}을(를) 찾을 수 없습니다")
        }
    }
}
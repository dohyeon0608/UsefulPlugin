package com.github.dohyeon0608.usefulplugin.usefulplugin

import com.github.dohyeon0608.usefulplugin.usefulplugin.data.DataManager
import com.github.dohyeon0608.usefulplugin.usefulplugin.data.DataManager.loadPluginData
import org.bukkit.ChatColor
import org.bukkit.entity.Player

object Back  {

    private const val fileDir = "back"
    const val canUse = "$fileDir.canUse"
    const val location = "$fileDir.location"

    fun back(player: Player) {
        val data = player.loadPluginData()
        val canUse = data.getBoolean(canUse)
        val location = data.getLocation(location)
        if(canUse){
            if(location != null){
                player.teleport(location)
                player.sendMessage("${ChatColor.GOLD}죽은 지점으로 되돌아갑니다!")
                data.set(this.canUse, false)
                data.save(DataManager.getFileFromPlayer(player))
            } else {
                player.sendMessage("${ChatColor.RED}이동할 수 없습니다!")
            }
        } else player.sendMessage("${ChatColor.RED}이미 이동했거나, 이동할 수 없는 상태입니다.")
    }

}
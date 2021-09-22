package com.github.dohyeon0608.usefulplugin

import com.github.dohyeon0608.usefulplugin.data.BackTimer
import com.github.dohyeon0608.usefulplugin.data.ConfigManager
import com.github.dohyeon0608.usefulplugin.data.DataManager
import com.github.dohyeon0608.usefulplugin.data.DataManager.loadPluginData
import com.github.dohyeon0608.usefulplugin.util.CommandUtil.getTimeTextSecond
import org.bukkit.ChatColor
import org.bukkit.entity.Player

object Back  {

    private const val fileDir = "back"
//    const val canUse = "$fileDir.canUse"
    const val location = "$fileDir.location"

    fun back(player: Player) {
        val data = player.loadPluginData()
//        val canUse = data.getBoolean(canUse)
        val location = data.getLocation(location)
        val time = BackTimer.getTime(player)
        if (time == 0) { // then the player can use 'back'
            if(location != null){
                player.teleport(location)
                player.sendMessage("${ChatColor.GOLD}죽은 지점으로 되돌아갑니다!")
                data.set(Back.location, null)
//                data.set(Back.canUse, false)
                data.save(DataManager.getFileFromPlayer(player))
                ConfigManager.backPlayerTimer[player] = -1
            } else {
                player.sendMessage("${ChatColor.RED}이동할 수 없습니다!")
            }
        } else if(time > 1){
            player.sendMessage("${ChatColor.RED}지금은 이동할 수 없습니다! ${getTimeTextSecond(time, ChatColor.RED)} 후에 다시 시도해주세요.")
        } else if (time < 0){
            player.sendMessage("${ChatColor.RED}이동할 수 없습니다!")
        }
    }

}
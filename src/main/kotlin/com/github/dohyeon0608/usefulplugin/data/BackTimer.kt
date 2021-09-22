package com.github.dohyeon0608.usefulplugin.data

import com.github.dohyeon0608.usefulplugin.UsefulPlugin
import net.kyori.adventure.text.Component
import org.bukkit.entity.Player

object BackTimer {

    private val timer = mutableMapOf<Player, Int>()

    init {
        UsefulPlugin.instance.run {
            server.scheduler.scheduleSyncRepeatingTask(this, {
                for(player in server.onlinePlayers.filter { getTime(it) > 0 }){
                    removeTime(player)
                }
            }, 0L, 1L)
        }
    }

    fun getTime(player: Player) : Int {
        if(!timer.containsKey(player)) timer[player] = 0
        return timer[player]!!
    }

    fun setTime(player: Player) {
        timer[player] = ConfigManager.backSetting.delayTime
    }

    private fun removeTime(player: Player) {
        timer[player] = getTime(player) - 1
    }

    override fun toString() = "BackTimer: $timer"

}
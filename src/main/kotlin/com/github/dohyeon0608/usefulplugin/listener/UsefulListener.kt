package com.github.dohyeon0608.usefulplugin.listener

import com.github.dohyeon0608.usefulplugin.data.DataManager.createData
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class UsefulListener : Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent){
        val player = event.player
        player.createData()
    }

}
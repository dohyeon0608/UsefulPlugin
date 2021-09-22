package com.github.dohyeon0608.usefulplugin.listener

import com.github.dohyeon0608.usefulplugin.Back
import com.github.dohyeon0608.usefulplugin.data.BackTimer
import com.github.dohyeon0608.usefulplugin.data.ConfigManager
import com.github.dohyeon0608.usefulplugin.data.DataManager
import com.github.dohyeon0608.usefulplugin.data.DataManager.loadPluginData
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerJoinEvent

class BackListener : Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent){
        val player = event.player
        if(!ConfigManager.backPlayerTimer.containsKey(player)) ConfigManager.backPlayerTimer[player] = 0
    }

    @EventHandler
    fun onDeath(e: PlayerDeathEvent){
        val player = e.entity
        val data = player.loadPluginData()
        data.set(Back.location, player.location)
        data.save(DataManager.getFileFromPlayer(player))

        BackTimer.setTime(player)
    }
}
package com.github.dohyeon0608.usefulplugin.usefulplugin.listener

import com.github.dohyeon0608.usefulplugin.usefulplugin.Back
import com.github.dohyeon0608.usefulplugin.usefulplugin.data.DataManager
import com.github.dohyeon0608.usefulplugin.usefulplugin.data.DataManager.loadPluginData
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

class BackListener : Listener {

    @EventHandler
    fun onDeath(e: PlayerDeathEvent){
        val player = e.entity
        val data = player.loadPluginData()
        data.set(Back.location, player.location)
        data.set(Back.canUse, true)
        data.save(DataManager.getFileFromPlayer(player))
    }
}
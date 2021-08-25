package com.github.dohyeon0608.usefulplugin.usefulplugin.listener

import com.github.dohyeon0608.usefulplugin.usefulplugin.data.DataManager.createData
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent

class UsefulListener : Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent){
        val player = event.player
        player.createData()
    }

    @EventHandler
    fun breakBlock(event: PlayerInteractEvent){
        val block = event.clickedBlock
        if(block != null && event.action == Action.LEFT_CLICK_BLOCK){
            block.type = Material.AIR
        }
    }
}
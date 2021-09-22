package com.github.dohyeon0608.usefulplugin.listener

import com.github.dohyeon0608.usefulplugin.Home
import com.github.dohyeon0608.usefulplugin.Home.getHomeList
import com.github.dohyeon0608.usefulplugin.HomeGUI
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

class HomeGUIListener : Listener {
    @EventHandler
    fun selectHome(e: InventoryClickEvent){
        val player = e.whoClicked as Player
        if(e.clickedInventory == HomeGUI.getHomeGUI(player)){
            e.isCancelled = true
            if(e.slot < 45){
                val name = player.getHomeList()[e.slot]
                Home.goHome(player, name)
                player.closeInventory()
            } else if(e.slot in 48..50){
                player.openInventory(HomeGUI.getRemoveHomeGUI(player))
            }
        } else if(e.clickedInventory == HomeGUI.getRemoveHomeGUI(player)){
            e.isCancelled = true
            if(e.slot < 45){
                val name = player.getHomeList()[e.slot]
                Home.removeHome(player, name)
                player.openInventory(HomeGUI.getRemoveHomeGUI(player))
            } else if(e.slot in 48..50){
                player.openInventory(HomeGUI.getHomeGUI(player))
            }
        }
    }
}
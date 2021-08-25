package com.github.dohyeon0608.usefulplugin.usefulplugin

import com.github.dohyeon0608.usefulplugin.usefulplugin.Home.getHomeIcon
import com.github.dohyeon0608.usefulplugin.usefulplugin.Home.getHomeList
import net.kyori.adventure.text.Component.text
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

object HomeGUI {

    private val playerHomeGUI = mutableMapOf<Player, Inventory>()
    private val playerRemoveHomeGUI = mutableMapOf<Player, Inventory>()

    fun getHomeGUI(player: Player): Inventory {
        if (playerHomeGUI[player]?.isEmpty != false) {
            playerHomeGUI[player] = Bukkit.createInventory(null, 54, text("${ChatColor.BOLD}홈 목록"))
        }
        playerHomeGUI[player]?.apply {
            clear()
            for (n in 0..45) {
                try {
                    val item = player.getHomeIcon(player.getHomeList()[n])?.apply {
                        itemMeta.apply {
                            val lore = lore()
                            lore?.add(text(""))
                            lore?.add(text("${ChatColor.GREEN}▶ 해당 홈으로 이동합니다."))
                            lore(lore)
                        }.also { itemMeta = it }
                    }
                    if (item != null) addItem(item) else break
                } catch (e: Exception) {
                    break
                }
            }
            for (n in 45..53) {
                setItem(n, if (n in 48..50) openDeleteHomeGUIItem else blankItem)
            }
        }
        return playerHomeGUI[player]!!
    }

    fun getRemoveHomeGUI(player: Player): Inventory {
        if (playerRemoveHomeGUI[player]?.isEmpty != false) {
            playerRemoveHomeGUI[player] = Bukkit.createInventory(null, 54, text("${ChatColor.BOLD}삭제할 홈을 선택해주세요!"))
        }
        playerRemoveHomeGUI[player]?.apply {
            clear()
            for (n in 0..44) {
                try {
                    val item = player.getHomeIcon(player.getHomeList()[n])?.apply {
                        itemMeta.apply {
                            val lore = lore()
                            lore?.add(text(""))
                            lore?.add(text("${ChatColor.RED}▶ 해당 홈을 삭제합니다."))
                            lore(lore)
                        }.also { itemMeta = it }
                    }
                    if (item != null) addItem(item) else break
                } catch (e: Exception) {
                    break
                }
            }
            for (n in 45..53) {
                setItem(n, if (n in 48..50) openDefaultHomeGUIItem else blankItem)
            }
        }
        return playerRemoveHomeGUI[player]!!
    }

    private val openDeleteHomeGUIItem = ItemStack(Material.RED_STAINED_GLASS_PANE, 1).apply {
        itemMeta.apply {
            displayName(text("${ChatColor.RED}홈 삭제 하기"))
        }.also { itemMeta = it }
    }

    private val openDefaultHomeGUIItem = ItemStack(Material.LIME_STAINED_GLASS_PANE, 1).apply {
        itemMeta.apply {
            displayName(text("${ChatColor.GREEN}홈 선택 하기"))
        }.also { itemMeta = it }
    }

    private val blankItem = ItemStack(Material.WHITE_STAINED_GLASS_PANE, 1).apply {
        itemMeta.apply {
            displayName(text(""))
        }.also { itemMeta = it }
    }
}
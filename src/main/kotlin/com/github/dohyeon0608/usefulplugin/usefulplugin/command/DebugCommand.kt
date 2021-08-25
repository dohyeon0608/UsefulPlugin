package com.github.dohyeon0608.usefulplugin.usefulplugin.command

import com.github.dohyeon0608.usefulplugin.usefulplugin.Home.getHomeIcon
import com.github.dohyeon0608.usefulplugin.usefulplugin.Home.getHomeList
import com.github.dohyeon0608.usefulplugin.usefulplugin.HomeGUI
import com.github.dohyeon0608.usefulplugin.usefulplugin.UsefulPlugin
import com.github.dohyeon0608.usefulplugin.usefulplugin.data.DataManager.loadPluginData
import io.github.monun.kommand.PluginKommand
import io.github.monun.kommand.getValue

object DebugCommand {

    private val plugin = UsefulPlugin.instance

    fun register(plugin: PluginKommand) {
        plugin.run {
            register("useful-debug") {
                requires { isPlayer }
                executes {
                    val data = player.loadPluginData()
                    for (key in data.getKeys(true)) {
                        player.sendMessage("${key}: ${data.get(key)}")
                    }
                }
                then("config") {
                    executes {
                        for (key in this@DebugCommand.plugin.config.getKeys(true)) {
                            player.sendMessage("${key}: ${this@DebugCommand.plugin.config.get(key)}")
                        }
                    }
                }
                then("homeitem") {
                    then("name" to string().apply {
                        suggests { context ->
                            suggest(context.source.player.getHomeList())
                        }
                    }) {
                        executes {
                            val name: String by it
                            player.run {
                                val item = this.getHomeIcon(name)
                                if (item != null) inventory.addItem(item)
                            }
                        }
                    }
                }
            }
        }
    }
}
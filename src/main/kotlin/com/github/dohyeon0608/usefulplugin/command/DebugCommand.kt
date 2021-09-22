package com.github.dohyeon0608.usefulplugin.command

import com.github.dohyeon0608.usefulplugin.Home.getHomeIcon
import com.github.dohyeon0608.usefulplugin.Home.getHomeList
import com.github.dohyeon0608.usefulplugin.UsefulPlugin
import com.github.dohyeon0608.usefulplugin.data.ConfigManager
import com.github.dohyeon0608.usefulplugin.data.DataManager.loadPluginData
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
                        for (key in DebugCommand.plugin.config.getKeys(true)) {
                            player.sendMessage("${key}: ${DebugCommand.plugin.config.get(key)}")
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
                then("configdata"){
                    executes {
                        sender.sendMessage("Home: ${ConfigManager.homeSetting}", "Back: ${ConfigManager.backSetting}")
                    }
                }
                then("reloadconfig"){
                    executes {
                        ConfigManager.loadConfig()
                        sender.sendMessage("로딩 완료!")
                    }
                }
                then("saveconfig"){
                    executes {
                        ConfigManager.saveData()
                        sender.sendMessage("저장 완료!")
                    }
                }
                then("timer"){
                    executes {
                        sender.sendMessage(ConfigManager.backPlayerTimer.toString())
                    }
                }
            }
        }
    }
}
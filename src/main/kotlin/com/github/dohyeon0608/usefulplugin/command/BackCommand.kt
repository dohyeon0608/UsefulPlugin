package com.github.dohyeon0608.usefulplugin.command

import com.github.dohyeon0608.usefulplugin.Back
import com.github.dohyeon0608.usefulplugin.data.ConfigManager
import com.github.dohyeon0608.usefulplugin.util.CommandMessage.Back.backDelayTimeMessage
import io.github.monun.kommand.PluginKommand
import io.github.monun.kommand.getValue
import org.bukkit.ChatColor

object BackCommand {

    fun register(plugin: PluginKommand){
        plugin.run{
            register("back"){
                requires { isPlayer }
                executes {
                    Back.back(player)
                }
            }

            register("backsetting"){

                requires { isOp }

                val maxValue = 1200.0 // tick

                then("delayTime"){
                    then("time" to double(0.0, 1200.0)){
                        then("ticks"){
                            executes {
                                val time : Double by it
                                sender.sendMessage(ConfigManager.setData(ConfigManager.Path.BACK_TIME, time.toInt()))
                            }
                        }
                        then("seconds"){
                            executes {
                                val time : Double by it
                                sender.sendMessage(ConfigManager.setData(ConfigManager.Path.BACK_TIME_SECOND, if(time < maxValue / 20) time else maxValue / 20))
                            }

                        }
                        executes {
                            val time : Double by it
                            sender.sendMessage(ConfigManager.setData(ConfigManager.Path.BACK_TIME_SECOND, if(time < maxValue / 20) time else maxValue / 20))

                        }
                    }
                    executes {
                        sender.sendMessage(backDelayTimeMessage)
                    }
                }

                executes {
                    sender.sendMessage("\n${ChatColor.YELLOW}${ChatColor.BOLD}===== Back 설정 =====")
                    sender.sendMessage(backDelayTimeMessage)
                }
            }
        }
    }
}
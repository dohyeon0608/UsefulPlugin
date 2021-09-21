package com.github.dohyeon0608.usefulplugin.usefulplugin.command

import com.github.dohyeon0608.usefulplugin.usefulplugin.Back
import com.github.dohyeon0608.usefulplugin.usefulplugin.data.ConfigManager
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
                val backDelayTimeMessage = "${ChatColor.GREEN}명령어 딜레이 시간: ${ChatColor.RESET}${ConfigManager.backSetting.delayTime}틱"

                then("delayTime"){
                    then("time" to int(0)){
                        then("ticks"){
                            executes {
                                val time : Int by it
                                sender.sendMessage(ConfigManager.setConfig(ConfigManager.Path.BACK_TIME, time))
                            }
                        }
                        then("seconds"){
                            executes {
                                val time : Int by it
                                sender.sendMessage(ConfigManager.setConfig(ConfigManager.Path.BACK_TIME, if(time * 20 > 0){
                                    time * 20
                                } else Int.MAX_VALUE))
                            }

                        }
                        executes {
                            val time : Int by it
                            sender.sendMessage(ConfigManager.setConfig(ConfigManager.Path.BACK_TIME, time))

                        }
                    }
                    executes {
                        sender.sendMessage(backDelayTimeMessage)
                    }
                }

                executes {
                    sender.sendMessage("${ChatColor.GREEN}${ChatColor.BOLD}Back 설정", backDelayTimeMessage)
                }
            }
        }
    }
}
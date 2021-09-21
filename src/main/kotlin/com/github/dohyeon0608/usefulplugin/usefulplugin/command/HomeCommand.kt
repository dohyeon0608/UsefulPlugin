package com.github.dohyeon0608.usefulplugin.usefulplugin.command

import com.github.dohyeon0608.usefulplugin.usefulplugin.Home
import com.github.dohyeon0608.usefulplugin.usefulplugin.Home.getHomeList
import com.github.dohyeon0608.usefulplugin.usefulplugin.HomeGUI
import com.github.dohyeon0608.usefulplugin.usefulplugin.data.ConfigManager
import io.github.monun.kommand.PluginKommand
import io.github.monun.kommand.getValue
import org.bukkit.ChatColor

object HomeCommand {
    fun register(plugin: PluginKommand) {
        plugin.run{
            register("sethome"){
                requires { isPlayer }
                then("name" to string()){
                    executes {
                        if(player.getHomeList().size < 45){
                            val name : String by it
                            Home.setHome(player, name.lowercase())
                        } else {
                            player.sendMessage("${ChatColor.RED}더 이상 홈을 추가할 수 없습니다!")
                        }
                    }
                }
                executes {
                    Home.setHome(player)
                }
            }

            register("home"){
                requires { isPlayer }
                then("name" to string().apply {
                    suggests { context ->
                        suggest(context.source.player.getHomeList())
                    }
                }){
                    executes {
                        val name : String by it
                        Home.goHome(player, name)
                    }
                }
                executes {
                    Home.goHome(player)
                }
            }

//            register("homeList"){
//                requires { isPlayer }
//                executes {
//                    player.run{
//                        val list = this.getHomeList()
//                        sendMessage("${ChatColor.GOLD}홈 목록: \n${ChatColor.WHITE}${list.joinToString(", ")}")
//                    }
//                }
//            }

            register("removehome", "deletehome"){
                requires { isPlayer }
                then("name" to string().apply {
                    suggests { context ->
                        suggest(context.source.player.getHomeList())
                    }
                }){
                    executes {
                        val name : String by it
                        Home.removeHome(player, name)
                    }
                }
                executes {
                    Home.removeHome(player)
                }
            }

            register("homegui"){
                requires { isPlayer }
                executes {
                    player.openInventory(HomeGUI.getHomeGUI(player))
                }
            }

            register("homesetting"){
                requires { isOp }
                val defaultNameMessage = "${ChatColor.GREEN}기본 홈 이름: ${ChatColor.RESET}${ConfigManager.homeSetting.defaultHomeName}"
                val maxNameLetterMessage = "${ChatColor.GREEN}최대 홈 이름 글자수: ${ChatColor.RESET}${ConfigManager.homeSetting.maxNameLetter}"
                val maxHomeCountMessage = "${ChatColor.GREEN}최대 홈 보유수: ${ChatColor.RESET}${ConfigManager.homeSetting.maxHomeCount}"

                executes {
                    sender.sendMessage("${ChatColor.GREEN}${ChatColor.BOLD}홈 설정", defaultNameMessage, maxNameLetterMessage, maxHomeCountMessage)
                }

                then("defaultHomeName"){
                    then("name" to string()){
                        executes {
                            val name : String by it
                            sender.sendMessage(ConfigManager.setConfig(ConfigManager.Path.DEFAULT_HOME, name))
                        }
                    }
                    executes {
                        sender.sendMessage(defaultNameMessage)
                    }
                }
                then("maxNameLetter"){
                    then("length" to int(1)){
                        executes {
                            val length : Int by it
                            sender.sendMessage(ConfigManager.setConfig(ConfigManager.Path.MAX_NAME_LETTER, length))
                        }
                    }
                    executes {
                        sender.sendMessage(maxNameLetterMessage)
                    }
                }
                then("maxHomeCount"){
                    then("count" to int(0, 45)){
                        executes {
                            val count : Int by it
                            sender.sendMessage(ConfigManager.setConfig(ConfigManager.Path.MAX_HOME_COUNT, count))
                        }
                    }
                    executes {
                        sender.sendMessage(maxHomeCountMessage)
                    }
                }
            }
        }
    }
}
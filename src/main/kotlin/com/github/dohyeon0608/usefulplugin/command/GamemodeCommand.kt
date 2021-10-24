package com.github.dohyeon0608.usefulplugin.command

import io.github.monun.kommand.PluginKommand
import io.github.monun.kommand.getValue
import org.bukkit.ChatColor
import org.bukkit.GameMode
import org.bukkit.entity.Player

object GamemodeCommand {

    fun register(plugin: PluginKommand){
        plugin.run{
            register("gm"){
                requires { isOp }
                val map = mapOf(
                    0 to GameMode.SURVIVAL,
                    1 to GameMode.CREATIVE,
                    2 to GameMode.ADVENTURE,
                    3 to GameMode.SPECTATOR
                )
                fun GameMode.name() = when(this){
                    GameMode.SURVIVAL -> "서바이벌"
                    GameMode.CREATIVE -> "크리에이티브"
                    GameMode.ADVENTURE -> "모험"
                    GameMode.SPECTATOR -> "관전자"
                    else -> "null"
                }
                then("mode" to int(0, 3)){
                    requires { isPlayer }
                    executes {
                        val mode : Int by it
                        val gameMode = map[mode]
                        if (gameMode != null) {
                            player.gameMode = gameMode
                            player.sendMessage("자신의 게임 모드를 ${gameMode.name()} 모드로 변경하였습니다")
                        } else {
                            player.sendMessage("${ChatColor.RED}정확한 값을 입력해주세요!")
                        }
                    }
                }

                then("moden" to int(0, 3), "players" to players()){
                    executes { context ->
                        val moden : Int by context
                        val players : Collection<Player> by context
                        val gameMode = map[moden]
                        if(gameMode != null) {
                            players.forEach {
                                it.gameMode = gameMode
                            }
                            sender.sendMessage(
                                if (players.size == 1) {
                                    "${players.first().name}님의 게임 모드를 ${gameMode.name()} 모드로 변경하였습니다"
                                } else {
                                    "${players.first().name}님 외 ${players.size - 1}명의 게임 모드를 ${gameMode.name()} 모드로 변경하였습니다"
                                }
                            )
                        } else{
                            player.sendMessage("${ChatColor.RED}정확한 값을 입력해주세요!")
                        }
                    }
                }
            }
        }
    }
}
package com.github.dohyeon0608.usefulplugin.usefulplugin.command

import com.github.dohyeon0608.usefulplugin.usefulplugin.Back
import io.github.monun.kommand.PluginKommand

object BackCommand {

    fun register(plugin: PluginKommand){
        plugin.run{
            register("back"){
                requires { isPlayer }
                executes {
                    Back.back(player)
                }
            }
        }
    }
}
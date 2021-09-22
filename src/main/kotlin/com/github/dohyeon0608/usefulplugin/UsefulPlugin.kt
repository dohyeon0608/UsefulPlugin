package com.github.dohyeon0608.usefulplugin

import com.github.dohyeon0608.usefulplugin.command.BackCommand
import com.github.dohyeon0608.usefulplugin.command.DebugCommand
import com.github.dohyeon0608.usefulplugin.command.GamemodeCommand
import com.github.dohyeon0608.usefulplugin.command.HomeCommand
import com.github.dohyeon0608.usefulplugin.data.BackTimer
import com.github.dohyeon0608.usefulplugin.data.ConfigManager
import com.github.dohyeon0608.usefulplugin.listener.BackListener
import com.github.dohyeon0608.usefulplugin.listener.HomeGUIListener
import com.github.dohyeon0608.usefulplugin.listener.UsefulListener
import io.github.monun.kommand.kommand
import org.bukkit.plugin.java.JavaPlugin

class UsefulPlugin : JavaPlugin() {

    private val enableDebugMod = false

    companion object {
        lateinit var instance: UsefulPlugin
        private set
    }

    override fun onEnable() {
        instance = this
//        ConfigManager.loadConfig()
        BackTimer
        server.pluginManager.run {
            registerEvents(UsefulListener(), this@UsefulPlugin)
            registerEvents(BackListener(), this@UsefulPlugin)
            registerEvents(HomeGUIListener(), this@UsefulPlugin)
        }
        kommand {
            HomeCommand.register(this)
            GamemodeCommand.register(this)
            BackCommand.register(this)
            if(enableDebugMod) DebugCommand.register(this)
        }
        saveDefaultConfig()
    }

    override fun onDisable() {
        ConfigManager.saveData()
    }

}
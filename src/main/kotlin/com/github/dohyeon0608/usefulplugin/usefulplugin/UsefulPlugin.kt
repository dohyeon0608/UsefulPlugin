package com.github.dohyeon0608.usefulplugin.usefulplugin

import com.github.dohyeon0608.usefulplugin.usefulplugin.command.BackCommand
import com.github.dohyeon0608.usefulplugin.usefulplugin.command.GamemodeCommand
import com.github.dohyeon0608.usefulplugin.usefulplugin.command.HomeCommand
import com.github.dohyeon0608.usefulplugin.usefulplugin.data.ConfigManager
import com.github.dohyeon0608.usefulplugin.usefulplugin.listener.BackListener
import com.github.dohyeon0608.usefulplugin.usefulplugin.listener.HomeGUIListener
import com.github.dohyeon0608.usefulplugin.usefulplugin.listener.UsefulListener
import io.github.monun.kommand.kommand
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class UsefulPlugin : JavaPlugin() {

    companion object {
        lateinit var instance: UsefulPlugin
        private set
    }

    override fun onEnable() {
        instance = this
        server.pluginManager.run {
            registerEvents(UsefulListener(), this@UsefulPlugin)
            registerEvents(BackListener(), this@UsefulPlugin)
            registerEvents(HomeGUIListener(), this@UsefulPlugin)
        }
        kommand {
            HomeCommand.register(this)
            GamemodeCommand.register(this)
            BackCommand.register(this)
        }
        saveDefaultConfig()
        ConfigManager.loadConfig()
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }

}
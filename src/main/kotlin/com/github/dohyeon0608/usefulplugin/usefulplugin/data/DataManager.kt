package com.github.dohyeon0608.usefulplugin.usefulplugin.data

import com.github.dohyeon0608.usefulplugin.usefulplugin.UsefulPlugin
import org.bukkit.configuration.InvalidConfigurationException
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import java.io.File
import java.io.IOException


object DataManager {

    private val plugin = UsefulPlugin.instance

    fun Player.createData(){
        val file = getFileFromPlayer(this)
        if (!file.exists()) {
            file.parentFile.mkdirs()
            file.createNewFile()
        }
        val data = YamlConfiguration.loadConfiguration(file)
        try {
            data.load(file)
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InvalidConfigurationException) {
            e.printStackTrace()
        }
    }

    fun Player.loadPluginData() : YamlConfiguration {
        val file = getFileFromPlayer(this)
        val data = YamlConfiguration.loadConfiguration(file)
        data.load(file)
        return data
    }

    fun Player.savePluginData(){
        val file = getFileFromPlayer(this)
        val data = YamlConfiguration.loadConfiguration(file)
        try{
            data.save(file)
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun getFileFromPlayer(player: Player) = File(plugin.dataFolder, "data" + File.separator + player.uniqueId.toString() + ".yml")

    fun getFileNameFromPlayer(player: Player) = "data" + File.separator + player.uniqueId.toString() + ".yml"
}
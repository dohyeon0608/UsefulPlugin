package com.github.dohyeon0608.usefulplugin.usefulplugin.data

import com.github.dohyeon0608.usefulplugin.usefulplugin.UsefulPlugin
import org.bukkit.ChatColor

object ConfigManager {

    val plugin = UsefulPlugin.instance
    private val config = plugin.config

    enum class Path(val path: String){

        DEFAULT_HOME("home.default-home-name"),
        MAX_NAME_LETTER("home.max-name-letter"),
        MAX_HOME_COUNT("home.max-home-count");

    }

    lateinit var homeSetting: Home

    init {
        loadConfig()
    }

    fun loadConfig(){
        val defaultHomeName = config.getString(Path.DEFAULT_HOME.path)
        val maxNameLetter = config.getInt(Path.MAX_NAME_LETTER.path)
        val maxHomeCount = config.getInt(Path.MAX_HOME_COUNT.path)
        homeSetting = Home(defaultHomeName ?: run{
            plugin.logger.severe("defaultHomeName을 불러오지 못했습니다. 기본값을 사용합니다. (이것은 플러그인 오류가 아닙니다!)")
            "default"
        }, maxNameLetter, maxHomeCount)
    }

    fun setConfig(path: Path, value: Any) = when (path) {
        Path.DEFAULT_HOME -> {
            if (value is String) {
                config.set(path.path, value)
                plugin.saveConfig()
                homeSetting.defaultHomeName = value
                "${ChatColor.GREEN}기본 홈 이름을 ${ChatColor.RESET}${value}${ChatColor.GREEN}(으)로 변경했습니다"
            } else "${ChatColor.RED}오류가 발생했습니다"
        }
        Path.MAX_HOME_COUNT -> {
            if (value is Int) {
                config.set(path.path, value)
                plugin.saveConfig()
                homeSetting.maxHomeCount = value
                "${ChatColor.GREEN}최대 홈 갯수를 ${ChatColor.RESET}${value}${ChatColor.GREEN}개로 변경했습니다"
            } else "${ChatColor.RED}오류가 발생했습니다"
        }
        Path.MAX_NAME_LETTER -> {
            if (value is Int) {
                config.set(path.path, value)
                plugin.saveConfig()
                homeSetting.maxNameLetter = value
                "${ChatColor.GREEN}최대 홈 글자수를 ${ChatColor.RESET}${value}${ChatColor.GREEN}자로 변경했습니다"
            } else "${ChatColor.RED}오류가 발생했습니다"
        }
    }

}

data class Home(var defaultHomeName: String, var maxNameLetter: Int, var maxHomeCount: Int)
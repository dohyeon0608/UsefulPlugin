package com.github.dohyeon0608.usefulplugin.data

import com.github.dohyeon0608.usefulplugin.UsefulPlugin
import com.github.dohyeon0608.usefulplugin.util.CommandUtil.getTimeTextSecond
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import kotlin.math.floor

object ConfigManager {

    val plugin = UsefulPlugin.instance
    private val config = plugin.config

    val backPlayerTimer = mutableMapOf<Player, Int>()

    enum class Path(val path: String){

        BACK_TIME("back.back-delay-time"),
        BACK_TIME_SECOND(BACK_TIME.path),
        DEFAULT_HOME("home.default-home-name"),
        MAX_NAME_LENGTH("home.max-name-length"),
        MAX_HOME_COUNT("home.max-home-count");

    }

    var homeSetting: Home = run{
        val defaultHomeName = config.getString(Path.DEFAULT_HOME.path)
        val maxNameLetter = config.getInt(Path.MAX_NAME_LENGTH.path)
        val maxHomeCount = config.getInt(Path.MAX_HOME_COUNT.path)
        Home(defaultHomeName ?: run{
            plugin.logger.severe("defaultHomeName을 불러오지 못했습니다. 기본값을 사용합니다. (이것은 플러그인 오류가 아닙니다!)")
            "default"
        }, maxNameLetter, maxHomeCount)
    }
    var backSetting: Back = run{
        val respawnTime = config.getInt(Path.BACK_TIME.path)
        Back(respawnTime)
    }

    private val errorMessage = "${ChatColor.RED}오류가 발생했습니다"

    fun loadConfig(){
        val defaultHomeName = config.getString(Path.DEFAULT_HOME.path)
        val maxNameLetter = config.getInt(Path.MAX_NAME_LENGTH.path)
        val maxHomeCount = config.getInt(Path.MAX_HOME_COUNT.path)
        val respawnTime = config.getInt(Path.BACK_TIME.path)
        homeSetting = Home(defaultHomeName ?: run{
            plugin.logger.severe("defaultHomeName을 불러오지 못했습니다. 기본값을 사용합니다. (이것은 플러그인 오류가 아닙니다!)")
            "default"
        }, maxNameLetter, maxHomeCount)
        backSetting = Back(respawnTime)
    }

    fun setData(path: Path, data: Any) = when (path) {
        Path.DEFAULT_HOME -> {
            if (data is String) {
                homeSetting.defaultHomeName = data
                "${ChatColor.GREEN}기본 홈 이름을 ${ChatColor.RESET}${data}${ChatColor.GREEN}(으)로 변경했습니다."
            } else errorMessage
        }
        Path.MAX_HOME_COUNT -> {
            if (data is Int) {
                homeSetting.maxHomeCount = data
                "${ChatColor.GREEN}최대 홈 갯수를 ${ChatColor.RESET}${data}${ChatColor.GREEN}개로 변경했습니다."
            } else errorMessage
        }
        Path.MAX_NAME_LENGTH -> {
            if (data is Int) {
                homeSetting.maxNameLength = data
                "${ChatColor.GREEN}최대 홈 글자수를 ${ChatColor.RESET}${data}${ChatColor.GREEN}자로 변경했습니다."
            } else errorMessage
        }
        Path.BACK_TIME -> {
            if (data is Int) {
                backSetting.delayTime = data
                "${ChatColor.GREEN}명령어 쿨타임 시간을 ${ChatColor.RESET}${data}${ChatColor.GREEN}틱(${getTimeTextSecond(data, ChatColor.GREEN)})으로 변경했습니다."
            } else errorMessage
        }
        Path.BACK_TIME_SECOND -> {
            if (data is Double) {
                backSetting.delayTime = ((floor(data * 100) / 100.0) * 20.0).toInt()
                "${ChatColor.GREEN}명령어 쿨타임 시간을 ${ChatColor.RESET}${floor(data * 100) / 100.0}${ChatColor.GREEN}초로 변경했습니다."
            } else errorMessage
        }
    }

    fun saveData(){
        plugin.config.run {
            set(Path.DEFAULT_HOME.path, homeSetting.defaultHomeName)
            set(Path.MAX_NAME_LENGTH.path, homeSetting.maxNameLength)
            set(Path.MAX_HOME_COUNT.path, homeSetting.maxHomeCount)
            set(Path.BACK_TIME.path, backSetting.delayTime)
        }
        plugin.saveConfig()
    }

}

data class Home(var defaultHomeName: String, var maxNameLength: Int, var maxHomeCount: Int)

data class Back(var delayTime: Int)
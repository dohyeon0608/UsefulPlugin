package com.github.dohyeon0608.usefulplugin.util

import com.github.dohyeon0608.usefulplugin.data.ConfigManager
import com.github.dohyeon0608.usefulplugin.util.CommandUtil.getTimeText
import com.github.dohyeon0608.usefulplugin.util.CommandUtil.getTimeTextSecond
import com.github.dohyeon0608.usefulplugin.util.CommandUtil.hoverMessage
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import org.bukkit.ChatColor

object CommandMessage {
    object Home {
        val defaultNameMessage : TextComponent
        get() = Component.text("${ChatColor.GREEN}기본 홈 이름: ${ChatColor.RESET}${ConfigManager.homeSetting.defaultHomeName}")
            .hoverEvent(HoverEvent.showText(hoverMessage("기본 홈 이름")))
            .clickEvent(ClickEvent.suggestCommand("/homesetting defaultHomeName "))

        val maxNameLetterMessage : TextComponent
        get() = Component.text("${ChatColor.GREEN}최대 홈 이름 글자수: ${ChatColor.RESET}${ConfigManager.homeSetting.maxNameLength}")
            .hoverEvent(HoverEvent.showText(hoverMessage("최대 홈 이름")))
            .clickEvent(ClickEvent.suggestCommand("/homesetting maxNameLength "))

        val maxHomeCountMessage : TextComponent
        get() = Component.text("${ChatColor.GREEN}최대 홈 보유수: ${ChatColor.RESET}${ConfigManager.homeSetting.maxHomeCount}")
            .hoverEvent(HoverEvent.showText(hoverMessage("최대 홈 보유수")))
            .clickEvent(ClickEvent.suggestCommand("/homesetting maxHomeCount "))
    }
    object Back {
        val backDelayTimeMessage : TextComponent
        get() = Component.text("${ChatColor.GREEN}명령어 딜레이 시간: ${ChatColor.RESET}${getTimeTextSecond(ConfigManager.backSetting.delayTime, ChatColor.GREEN)} ${ChatColor.RED}(최대 60초)")
            .hoverEvent(HoverEvent.showText(hoverMessage("명령어 딜레이 시간")))
            .clickEvent(ClickEvent.suggestCommand("/backsetting delayTime "))
    }
}
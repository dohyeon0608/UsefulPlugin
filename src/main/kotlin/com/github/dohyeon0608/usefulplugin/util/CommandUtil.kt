package com.github.dohyeon0608.usefulplugin.util

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import java.lang.StringBuilder

object CommandUtil {

    fun hoverMessage(name: String) = Component.text("${ChatColor.GREEN}${name}에 대해서 설정하려면 여기를 클릭해주세요!")

    fun CommandSender.sendMessage(vararg component: TextComponent) {
        component.forEach {
            this.sendMessage(it)
        }
    }

    fun getTimeText(time: Int, color: ChatColor = ChatColor.WHITE) : String {
        var left = time

        val second : Int = left / 20
        left %= 20

        val tick : Int = left

        val textBuilder = StringBuilder()

        if(second != 0) textBuilder.append("${ChatColor.WHITE}${second}${color}초")

        if(textBuilder.isNotEmpty() && tick != 0) textBuilder.append(" ")
        if(tick != 0) textBuilder.append("${ChatColor.WHITE}${tick}${color}틱")

        return textBuilder.toString()
    }

    fun getTimeTextSecond(time: Int, color: ChatColor = ChatColor.WHITE) : String {
        var left = time

        val second : Int = left / 20
        left %= 20

        val tick : Int = left

        return "${ChatColor.WHITE}${second.toDouble() + 0.05 * tick.toDouble()}${color}초"
    }
}
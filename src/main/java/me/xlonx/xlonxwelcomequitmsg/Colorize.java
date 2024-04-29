package me.xlonx.xlonxwelcomequitmsg;

import org.bukkit.ChatColor;

public class Colorize {
    public static String colorize(String text){
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}

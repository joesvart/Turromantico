package me.joesvart.turromantico.utils;

import net.md_5.bungee.api.ChatColor;

public class ColorHelper {

	public static String translate(String text) {
		return ChatColor.translateAlternateColorCodes('&', text);
	}
}

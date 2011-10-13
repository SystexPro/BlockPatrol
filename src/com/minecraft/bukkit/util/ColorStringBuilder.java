package com.minecraft.bukkit.util;

import org.bukkit.ChatColor;

public class ColorStringBuilder {

	private String message;
	private String bracketString;
	private ChatColor b1 = ChatColor.WHITE;
	private ChatColor b2 = ChatColor.WHITE;
	private ChatColor b3 = ChatColor.WHITE;
	private ChatColor b4 = ChatColor.WHITE;

	
	public ColorStringBuilder(String bracketText, String s) {
		message = s;//Store message\
		bracketString = bracketText;
	}
	
	public ColorStringBuilder setFirstBracketColor(ChatColor color) {
		b1 = color;
		return this;
	}
	
	public ColorStringBuilder setSecondBracketColor(ChatColor color) {
		b2 = color;
		return this;
	}
	
	/**
	 * Set the Bracket String Color
	 * @param color
	 * @return
	 */
	public ColorStringBuilder setBracketStringColor(ChatColor color) {
		b4 = color;
		return this;
	}
	
	/**
	 * Set the Color of the Message
	 * @param color
	 * @return
	 */
	public ColorStringBuilder setMessageColor(ChatColor color) {
		b3 = color;
		return this;
	}

	/**
	 * Return the message
	 * @return
	 */
	public String getMessage() {
		return b1 + "[" + b4 + bracketString + b2 + "] " + b3 + message;
	}
}

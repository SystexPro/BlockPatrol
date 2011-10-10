package com.bukkit.systexpro.blockpatrol.events;

public enum EventType {

	BLOCK_PLACED("placed");
	
	String w;
	
	EventType(String s) {
		w = s;
	}
	
	public String toString() {
		return w;
	}
}

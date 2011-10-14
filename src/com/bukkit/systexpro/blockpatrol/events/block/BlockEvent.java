package com.bukkit.systexpro.blockpatrol.events.block;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.bukkit.systexpro.blockpatrol.events.EventType;

public abstract class BlockEvent {
	
	public EventType type;
	
	public BlockEvent(EventType t) 
	{
		type = t;
	}

	/**
	 * Get the Type
	 * @return
	 */
	public EventType getType() {
		return type;
	}
	
	/**
	 * Abstracts
	 * @return
	 */
	public abstract Block getBlock();
	public abstract Player getPlayer();
}

package com.bukkit.systexpro.blockpatrol.events.block;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.bukkit.systexpro.blockpatrol.events.EventType;

public abstract class BlockEvent {

	public BlockEvent(EventType type) {
		
	}
	
	public abstract Block getBlock();
	public abstract Player getPlayer();
}

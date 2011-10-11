package com.bukkit.systexpro.blockpatrol.events.block;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.bukkit.systexpro.blockpatrol.events.EventType;

public class BlockPlaced extends BlockEvent {

	private Block block;
	private Player player;
	
	public BlockPlaced(Player p, Block b) {
		super(EventType.BLOCK_PLACED);
		block = b;
		player = p;
	}

	/**
	 * Get the Block
	 */
	public Block getBlock() {
		return block;
	}

	/**
	 * Get the Player
	 */
	public Player getPlayer() {
		return player;
	}
	
	


	
}

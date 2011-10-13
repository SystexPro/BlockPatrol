package com.bukkit.systexpro.blockpatrol.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.bukkit.systexpro.blockpatrol.BlockPatrolPlugin;
import com.bukkit.systexpro.blockpatrol.events.block.BlockPlaced;
import com.minecraft.bukkit.util.ColorStringBuilder;

public class BPBlockListener extends BlockListener {

	public BlockPatrolPlugin plugin;

	public BPBlockListener(BlockPatrolPlugin core) {
		plugin = core;
	}

	/**
	 * Handle Blocks Placed
	 */
	public void onBlockPlace(BlockPlaceEvent event) {
		Player p = event.getPlayer();
		Block b = event.getBlockPlaced();
		BlockPlaced bp = new BlockPlaced(p,b);
		plugin.getEventHandler().addBlockEvent(bp);
	}

}

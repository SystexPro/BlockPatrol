package com.bukkit.systexpro.blockpatrol.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerInteractEvent;
import com.bukkit.systexpro.blockpatrol.BlockPatrolPlugin;

public class BPPlayerListener extends PlayerListener {

	public BlockPatrolPlugin plugin;
	
	public BPPlayerListener(BlockPatrolPlugin core) {
		plugin = core;
	}
	
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		Block b = event.getClickedBlock();
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			plugin.getEventHandler().getBlock(b, p);
		}
	}
}

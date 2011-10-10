package com.bukkit.systexpro.blockpatrol.listeners;

import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerInteractEvent;
import com.bukkit.systexpro.blockpatrol.BlockPatrolPlugin;

public class BPPlayerListener extends PlayerListener {

	public BlockPatrolPlugin plugin;
	
	public BPPlayerListener(BlockPatrolPlugin core) {
		plugin = core;
	}
	
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		
	}
}

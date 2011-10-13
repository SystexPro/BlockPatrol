package com.bukkit.systexpro.blockpatrol.galert;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.bukkit.systexpro.blockpatrol.BlockPatrolPlugin;

public class BPGriefAlert {

	public BlockPatrolPlugin plugin;
	public boolean isInit;
	public String layout = ChatColor.RED + "[" + ChatColor.GRAY + "GriefAlert" + ChatColor.RED + "] " + ChatColor.GREEN;

	public BPGriefAlert(BlockPatrolPlugin core) {
		plugin = core;
		isInit = false;
	}

	/**
	 * Init the boolean
	 */
	public void init() {
		isInit = true;
	}

	/**
	 * Broadcast a Message
	 * @param message
	 */
	public void broadcastMessage(String message) {
		if(isInit) {
			plugin.getServer().broadcastMessage(layout + message);
		}	
	}

	/**
	 * Send a Message to Admins
	 * @param message
	 */
	public void sendMessageToAdmins(String message) {
		if(isInit) {
			Player[] online = plugin.getServer().getOnlinePlayers();
			for(int x = 0; x < online.length; x++) {
				Player p = plugin.getServer().getPlayer(online[x].getDisplayName());
				if(p.isOp()) {
					p.sendMessage(layout + message);
				}
			}
		}
	}

	/**
	 * Send a message back to the Player
	 * @param player
	 * @param message
	 */
	public void sendMessageToPlayer(Player player, String message) {
		if(isInit) {
			player.sendMessage(layout + message);
		}
	}
}

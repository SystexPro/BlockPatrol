package com.bukkit.systexpro.blockpatrol.events;

import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import com.bukkit.systexpro.blockpatrol.BlockPatrolPlugin;
import com.bukkit.systexpro.blockpatrol.events.block.BlockEvent;
import com.minecraft.bukkit.util.SQLTools;
import com.minecraft.bukkit.util.Time;

public class EventHandler {

	public BlockPatrolPlugin plugin;

	public EventHandler(BlockPatrolPlugin core) {
		plugin = core;
	}

	/**
	 * Add a Block to the SQL
	 * @param event
	 */
	public void addBlockEvent(BlockEvent event) {
		String query = "INSERT INTO blocks (owner, x, y, z, rb, type, block, time) VALUES ('" +event.getPlayer()+ "', '" +event.getBlock().getX()+ "', '" + event.getBlock().getY()+ "', '" + event.getBlock().getZ()+ "', '" + 0 +"', '" + event.getType().toString()+ "', '" +event.getBlock().getType().name()+ "', '" +Time.getFullDateTime()+ "');";
		//		if(plugin.settings.fileSaveType.equalsIgnoreCase("SQL")) {
		plugin.settings.sqlCore.insertQuery(query);
		System.out.println("Added Event Block: " + event.getBlock().getType().name());
		//		} else {
		//			try {
		//				plugin.settings.mysqlCore.insertQuery(query);
		//			} catch (MalformedURLException e) {
		//				e.printStackTrace();
		//			} catch (InstantiationException e) {
		//				e.printStackTrace();
		//			} catch (IllegalAccessException e) {
		//				e.printStackTrace();
		//			}
		//		}
	}
	
	/**
	 * Get Block
	 * @param block
	 * @param p
	 */
	public void getBlock(Block block, Player p) {
		String query2 = "SELECT * FROM blocks WHERE x = " + block.getX() + " AND y = " + block.getY() + " AND z = " + block.getZ() + ";";
		ResultSet result = null;
		result = this.plugin.settings.sqlCore.sqlQuery(query2);
		try {
			while(result != null && result.next()) {
				String owner = result.getString("owner");
				String type = result.getString("type");
				int rb = result.getInt("rb");
				String time = result.getString("time");
				String[] split = time.split("@");
				Material m = null;
				if(rb == 0) {
					int blockId = result.getInt("block");
					m = Material.getMaterial(blockId);
					p.sendMessage(ChatColor.WHITE + split[0] + "@" + ChatColor.GRAY + split[1]+ " - " + ChatColor.YELLOW + owner + ChatColor.WHITE + " " + type + " block " + m.name().toUpperCase());
				} 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Get all the Events for the Location
	 * @param loc
	 */
	public void getEventForBlock(Location loc, Player p) {
		int x = loc.getBlockX();
		int y = loc.getBlockY();
		int z = loc.getBlockZ();
		String query = "SELECT * FROM blocks WHERE x = " + x + " AND y = " + y + " AND z = " + z + ";";		
		ResultSet result = null;
		result = plugin.settings.sqlCore.sqlQuery(query);
		try {
			while(result != null && result.next()) {
				String owner = result.getString("owner");
				String type = result.getString("type");
				int rb = result.getInt("rb");
				String time = result.getString("time");
				int blockId = result.getInt("block");
				String[] split = time.split("@");
				Material m;
				if(rb == 0) {
					m = Material.getMaterial(blockId);
					p.sendMessage(getTime(time.split("@")) + " - " + "Block placed by " + owner);
				}
			}
		} catch (SQLException e) {
			try {
				result.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public String getTime(String[] split) {
		return ChatColor.WHITE + split[0] + "@" + ChatColor.GRAY + split[1];
	}
}

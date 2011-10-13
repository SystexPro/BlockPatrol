package com.minecraft.bukkit.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Location;

import com.bukkit.systexpro.blockpatrol.BlockPatrolPlugin;

public class SQLTools {

	public BlockPatrolPlugin plugin;			

	public SQLTools(BlockPatrolPlugin core) {
		plugin = core;
	}

	public int getCountFromSQL(Location loc) throws SQLException {
		int x = 0;
		String query = "SELECT x,y,z FROM blocks WHERE x='"+ loc.getBlockX() + "' AND y='"+loc.getBlockY()+"' AND z='" +loc.getBlockZ() + "'";
		ResultSet set = plugin.settings.sqlCore.sqlQuery(query);
		while(set != null && set.next()) {
			x = set.getRow();
		}
		return x;
	}
}

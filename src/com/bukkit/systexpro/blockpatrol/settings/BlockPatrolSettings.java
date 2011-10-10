package com.bukkit.systexpro.blockpatrol.settings;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.logging.Logger;

import com.bukkit.systexpro.blockpatrol.BlockPatrolPlugin;
import com.bukkit.systexpro.blockpatrol.config.Configuration;
import com.alta189.sqlLibrary.MySQL.*;
import com.alta189.sqlLibrary.SQLite.*;

public class BlockPatrolSettings {

	public BlockPatrolPlugin plugin;
	public Configuration config;
	public String fileSaveType = "SQL";
	public String hostname = "localhost";
	public int hostport = 3306;
	public String username = "minecraft";
	public String password = "";
	public String prefix = "bp_";
	public String database_name_mysql = "blockpatrol";
	public String database_name_sql = "blockpatrol";
	//SQL Shtuff
	public File dbFolder = new File("plugins" + File.separator + "BlockPatrol");
	public String logPrefix = "[BlockPatrol]";
	public Logger log = Logger.getLogger("Minecraft");
	//Cores
	public mysqlCore mysqlCore;

	public BlockPatrolSettings(BlockPatrolPlugin core) {
		config = new Configuration(new File("plugins/BlockPatrol/config.yml"));
		plugin = core;
	}

	/**
	 * Load Configuration File
	 */
	public void loadConfig() {
		config.load();
		fileSaveType = config.getString("connection.Save Type", fileSaveType);
		database_name_sql = config.getString("connection.sql.SQL Database", database_name_sql);
		hostname = config.getString("connection.mysql.MySQL Hostname", hostname);
		hostport = config.getInt("connection.mysql.MySQL Port", hostport);
		username = config.getString("connection.mysql.MySQL Username", username);
		password = config.getString("connection.mysql.MySQL Password", password);
		database_name_mysql = config.getString("connection.mysql.MySQL Databse", database_name_mysql);
		prefix  = config.getString("connection.mysql.MySQL Database Prefix", prefix);
		config.save();
	}

	public String getAuthors() {
		String s = "Bukkit, ";
		Iterator<String> itr = plugin.getDescription().getAuthors().iterator(); 
		while(itr.hasNext()) {
			Object element = itr.next(); 
			s += element;
		} 
		return s;
	}

	public void createSQLDatabase() {

	}

	public void createMySQLDatabase() throws MalformedURLException, InstantiationException, IllegalAccessException {
		mysqlCore = new mysqlCore(log, logPrefix, hostname, database_name_mysql, username, password);
		mysqlCore.initialize();
		if(mysqlCore.checkConnection()) {
			if(mysqlCore.checkTable("blocks")) {
				String query = "";
			} else if(mysqlCore.checkTable("chat")) {

			} else if(mysqlCore.checkTable("teleports")) {

			} else if(mysqlCore.checkTable("connect")) {
				
			}  
		} else {
			plugin.sendConsoleMessage("Error connecting to MySQL Server. Please check your settings.");
		}
	}
}

package com.bukkit.systexpro.blockpatrol.settings;

import java.awt.List;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.logging.Logger;

import org.bukkit.util.config.Configuration;

import com.bukkit.systexpro.blockpatrol.BlockPatrolPlugin;
import com.minecraft.bukkit.util.SQLTools;

import com.alta189.sqlLibrary.MySQL.*;
import com.alta189.sqlLibrary.SQLite.*;

public class BlockPatrolSettings {

	public BlockPatrolPlugin plugin;
	@SuppressWarnings("deprecation")
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
	//Blacklist
	public String bannedBlocks = "TNT, BEDROCK";
	//Nerfs :p
	public boolean fireNerfed = false;
	public boolean tntNerfed = false;
	public boolean fireSpreadNerfed = false;
	public boolean liquidSpreadNerfed = false;
	//Cores
	public mysqlCore mysqlCore;
	public sqlCore sqlCore;
	//Extensions
	public SQLTools tools = new SQLTools(plugin);
	//etc
	public String build = "#1240";

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
		bannedBlocks = config.getString("blacklist.banneditems", bannedBlocks);
		loadBannedBlocks();
		config.save();
	}
	
	public void setupFileSaver() {
		if(this.fileSaveType.equalsIgnoreCase("SQL")) {
			this.createSQLDatabase();
		} else if(fileSaveType.equalsIgnoreCase("MySql")) {
			try {
				this.createMySQLDatabase();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		} else {
			plugin.sendConsoleMessage("Error!!! Can't find out save type for: " + this.fileSaveType);
			plugin.getPluginLoader().disablePlugin(plugin);
		}
	}

	/**
	 * Send the list to the BannedBlocks Class
	 */
	private void loadBannedBlocks() {
		BannedBlocks bb = new BannedBlocks();
		bb.setItemsToHandle(bannedBlocks);
	}

	public String getAuthors() {
		String s = "";
		Iterator<String> itr = plugin.getDescription().getAuthors().iterator(); 
		while(itr.hasNext()) {
			if(!itr.hasNext()) {
				s += ".";
			} else {
				Object element = itr.next(); 
				s += element + ", ";
			}
		} 
		return s;
	}

	/**
	 * Create SQL
	 */
	public void createSQLDatabase() {
		sqlCore = new sqlCore(log, logPrefix, database_name_sql, dbFolder.getAbsolutePath());
		sqlCore.initialize();
		if(!sqlCore.checkTable("blocks")) {
			String query = "CREATE TABLE blocks (id INT AUTO_INCREMENT PRIMARY_KEY, owner VARCHAR(255), x INT, y INT, z INT, rb INT, type VARCHAR(255), block VARCHAR(255), time TEXT);";
			sqlCore.createTable(query);
			plugin.sendConsoleMessage("SQL Table: blocks, has been created.");
		}
	}

	/**
	 * Create MySQL
	 * @throws MalformedURLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
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

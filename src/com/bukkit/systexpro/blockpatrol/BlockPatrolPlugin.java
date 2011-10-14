package com.bukkit.systexpro.blockpatrol;

import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.bukkit.systexpro.blockpatrol.commands.CommandHandler;
import com.bukkit.systexpro.blockpatrol.commands.HelpCommand;
import com.bukkit.systexpro.blockpatrol.commands.RollbackCommand;
import com.bukkit.systexpro.blockpatrol.commands.WandCommand;
import com.bukkit.systexpro.blockpatrol.events.EventHandler;
import com.bukkit.systexpro.blockpatrol.galert.BPGriefAlert;
import com.bukkit.systexpro.blockpatrol.listeners.BPBlockListener;
import com.bukkit.systexpro.blockpatrol.listeners.BPPlayerListener;
import com.bukkit.systexpro.blockpatrol.settings.BlockPatrolSettings;

public class BlockPatrolPlugin extends JavaPlugin {

	public BlockPatrolSettings settings;
	public PlayerListener pListener = new BPPlayerListener(this);
	public BlockListener bListener = new BPBlockListener(this);
	public BPGriefAlert griefAlert = new BPGriefAlert(this);
	public EventHandler eventHandler = new EventHandler(this);
	
	public void onDisable() {
		sendConsoleMessage("Disabled");	
	}
	
	@Override
	public void onEnable() {
		//Load Settings/Make Folder
		getDataFolder().mkdirs();
		settings = new BlockPatrolSettings(this);
		settings.loadConfig();
		
		//Start SQL
//		settings.setupFileSaver();
		settings.createSQLDatabase();
		
		//Send enable message
		sendConsoleMessage("Enabled Version: " + this.getDescription().getVersion() + " by " + settings.getAuthors());
		
		//Register Events
		registerEvents();
		
		//Start GriefAlert
		griefAlert.init();
		
		//Register Commands
		CommandHandler cmd = new CommandHandler(this);
		cmd.registerExecutor("rollback", new RollbackCommand());
		cmd.registerExecutor("wand", new WandCommand(this));
		cmd.registerExecutor("help", new HelpCommand());
		cmd.registerExecutor("?", new HelpCommand());
		getCommand("bp").setExecutor(cmd);
	}
	
	/**
	 * Register the Events
	 */
	private void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvent(Type.PLAYER_INTERACT, pListener, Priority.Normal, this);
		pm.registerEvent(Type.BLOCK_PLACE, bListener, Priority.Normal, this);
	}
	
	public EventHandler getEventHandler() {
		return eventHandler;
	}
	
	/**
	 * Send a Console Message
	 * @param arg0
	 */
	public void sendConsoleMessage(String arg0) {
		System.out.println("[BlockPatrol] " + arg0);
	}

}

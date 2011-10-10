package com.bukkit.systexpro.blockpatrol;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.bukkit.systexpro.blockpatrol.commands.CommandHandler;
import com.bukkit.systexpro.blockpatrol.commands.HelpCommand;
import com.bukkit.systexpro.blockpatrol.commands.RollbackCommand;
import com.bukkit.systexpro.blockpatrol.commands.WandCommand;
import com.bukkit.systexpro.blockpatrol.settings.BlockPatrolSettings;

public class BlockPatrolPlugin extends JavaPlugin {

	public BlockPatrolSettings settings;
	
	@Override
	public void onDisable() {
		sendConsoleMessage("Disabled");
		
	}

	@Override
	public void onEnable() {
		//Load Settings/Make Folder
		getDataFolder().mkdirs();
		settings = new BlockPatrolSettings(this);
		settings.loadConfig();
		
		sendConsoleMessage("Enabled Version: " + this.getDescription().getVersion() + " by " + settings.getAuthors());
		
		//Register Events
		registerEvents();
		
		//Register Commands
		CommandHandler cmd = new CommandHandler();
		cmd.registerExecutor("rollback", new RollbackCommand());
		cmd.registerExecutor("help", new HelpCommand());
		cmd.registerExecutor("?", new HelpCommand());
		cmd.registerExecutor("wand", new WandCommand(this));
		getCommand("bp").setExecutor(cmd);
	}
	
	private void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
//		pm.registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Priority.Normal, this);

	}
	
	public void sendConsoleMessage(String arg0) {
		System.out.println("[BlockPatrol] " + arg0);
	}

}

package com.bukkit.systexpro.blockpatrol.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.bukkit.systexpro.blockpatrol.BlockPatrolPlugin;
import com.bukkit.systexpro.blockpatrol.commands.tools.Tool;
import com.bukkit.systexpro.blockpatrol.commands.tools.WandTool;

public class WandCommand implements CommandExecutor {

	public BlockPatrolPlugin plugin;

	public WandCommand(BlockPatrolPlugin core) {
		plugin = core;
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		ItemStack stickTool = new WandTool((Player) arg0).getToolType();
		if(arg3.length == 1) {
			Player p = (Player) arg0;
			p.setItemInHand(stickTool);
			return true;
		} 
		return true;
	}



}

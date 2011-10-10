package com.bukkit.systexpro.blockpatrol.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {

	private HashMap<String, CommandExecutor> executors = new HashMap<String, CommandExecutor>();

	public CommandHandler() {

	}

	public void registerExecutor(String subcmd, CommandExecutor cmd) {
		executors.put(subcmd.toLowerCase(), cmd);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		String commandName = command.getName().toLowerCase();
		args = groupArgs(args);
		if (sender instanceof Player) {
			if (commandName.equals("bp")) {
				if (args.length == 0)
					return help(sender);
				String subcommandName = args[0].toLowerCase();
				if (!executors.containsKey(subcommandName))
					return false;
				return executors.get(subcommandName).onCommand(sender, command, commandLabel, args);
			}
		} else if (sender instanceof ConsoleCommandSender) {
			if (commandName.equals("bp")) {
				ConsoleCommandSender console = (ConsoleCommandSender) sender;
				if (args.length == 0) {
					return false;
				} else if (args[0].equalsIgnoreCase("version")) {
					console.sendMessage("Running");
				} 
				return true;
			}
			return false;
		}
		return false;
	}

	private boolean help(CommandSender sender) {
		Player p = (Player) sender;
		p.sendMessage("[===Block Patrol===]");
		p.sendMessage("/bp wand - Gives BlockPatrol Wand(Set in Config)");
		return true;
	}

	public static String[] groupArgs(String[] preargs) {
		List<String> args = new ArrayList<String>();
		String currentArg = "";
		boolean inQuotes = false;
		for (String arg : preargs) {
			if (inQuotes) {
				currentArg += " " + arg;
				if (arg.endsWith("\"")) {
					args.add(currentArg.substring(0, currentArg.lastIndexOf("\"")));
					inQuotes = false;
				}
			} else {
				if (arg.startsWith("\"")) {
					inQuotes = true;
					currentArg = arg.substring(1, arg.length());
				} else {
					args.add(arg);
				}
			}
		}
		String[] gargs = new String[args.size()];
		return args.toArray(gargs);
	}

}

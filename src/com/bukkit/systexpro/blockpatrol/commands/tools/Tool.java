package com.bukkit.systexpro.blockpatrol.commands.tools;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public abstract class Tool {
	
	public Tool() {
		
	}
	
	public abstract ItemStack getToolType();
	
	public abstract ItemStack getItemInHand();
	
	public abstract Block getBlockHit();

}

package com.bukkit.systexpro.blockpatrol.commands.tools;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class WandTool extends Tool{

	public WandTool(Player arg0) {
		
	}

	@Override
	public ItemStack getToolType() {
		return new ItemStack(Material.BONE, 1); 
	}

	@Override
	public ItemStack getItemInHand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Block getBlockHit() {
		// TODO Auto-generated method stub
		return null;
	}

}

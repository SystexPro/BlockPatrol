package com.bukkit.systexpro.blockpatrol.settings;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;

public class BannedBlocks {

	private String list;
	private ArrayList<String> bannedBlocks = new ArrayList<String>();

	public BannedBlocks() {
		list = null;
	}

	public void setItemsToHandle(String s) {
		list = s;
		handleItems();
	}

	private void handleItems() {
		String[] items = list.split(", ");
		for(int x = 0; x < items.length; x++) {
			bannedBlocks.add(items[x]);
		}
	}

	/**
	 * Get Banned Blocks
	 * @return
	 */
	public ArrayList getList() {
		return bannedBlocks;
	}

	public String getStringList() {
		return list;
	}
}

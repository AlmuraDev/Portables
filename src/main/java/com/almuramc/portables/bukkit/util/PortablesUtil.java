/*
 * This file is part of Portables.
 *
 * Copyright (c) 2012, AlmuraDev <http://www.almuramc.com/>
 * Portables is licensed under the Almura Development License.
 *
 * Portables is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Portables is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License. If not,
 * see <http://www.gnu.org/licenses/> for the GNU General Public License.
 */
package com.almuramc.portables.bukkit.util;

import com.almuramc.portables.bukkit.PortablesPlugin;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PortablesUtil {
	private static Economy econ = PortablesPlugin.getHooks().getEconomy();
	private static Permission permission = PortablesPlugin.getHooks().getPermissions();

	public static void openEnchantmentTable(Player player) {
		//has to have the use permission
		if (!permission.has(player.getWorld(), player.getName(), "portables.enchant.use")) {
			return;
		}
		//has to be holding the material
		if (permission.has(player.getWorld(), player.getName(), "portables.enchant.has")) {
			//Doesn't have it
			if (!hasMaterial(Material.ENCHANTMENT_TABLE, player.getInventory().getContents())) {
				return;
			}
		}
		//has to pay for it
		if (PortablesPlugin.getCached().useEconomy() && permission.has(player.getWorld(), player.getName(), "portables.enchant.cost")) {
			if (!econ.has(player.getName(), PortablesPlugin.getCached().getWorkbenchCost())) {
				player.sendMessage(ChatColor.GREEN + "[Portables] " + ChatColor.RED + " Insufficient Funds to open requested item.");
				return;
			}
			double cost = PortablesPlugin.getCached().getEnchantCost();
			econ.withdrawPlayer(player.getName(), cost);
			player.sendMessage(ChatColor.GREEN + "[Portables] " + ChatColor.WHITE + " Account deducted: " + econ.format(cost) + ".");
		}
		player.openEnchanting(null, true);
	}

	public static void openWorkbench(Player player) {
		//has to have the use permission
		if (!permission.has(player.getWorld(), player.getName(), "portables.workbench.use")) {
			return;
		}
		//has to be holding the material
		if (permission.has(player.getWorld(), player.getName(), "portables.workbench.has")) {
			//Doesn't have it
			if (!hasMaterial(Material.WORKBENCH, player.getInventory().getContents())) {
				return;
			}
		}
		//has to pay for it
		if (PortablesPlugin.getCached().useEconomy() && permission.has(player.getWorld(), player.getName(), "portables.workbench.cost")) {
			if (!econ.has(player.getName(), PortablesPlugin.getCached().getWorkbenchCost())) {
				player.sendMessage(ChatColor.GREEN + "[Portables] " + ChatColor.RED + " Insufficient Funds to open requested item.");
				return;
			}
			double cost = PortablesPlugin.getCached().getWorkbenchCost();
			econ.withdrawPlayer(player.getName(), cost);
			player.sendMessage(ChatColor.GREEN + "[Portables] " + ChatColor.WHITE + " Account deducted: " + econ.format(cost) + ".");
		}
		player.openWorkbench(null, true);
	}

	private static boolean hasMaterial(Material toFind, ItemStack[] contents) {
		for (ItemStack is : contents) {
			if (is == null) {
				continue;
			}
			if (is.getType().equals(toFind)) {
				return true;
			}
		}
		return false;
	}
}

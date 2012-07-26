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
 * As an exception, all classes which do not reference GPL licensed code
 * are hereby licensed under the GNU Lesser Public License, as described
 * in Almura Development License.
 *
 * Portables is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License,
 * the GNU Lesser Public License (for classes that fulfill the exception)
 * and the Almura Development License along with this program. If not, see
 * <http://www.gnu.org/licenses/> for the GNU General Public License and
 * the GNU Lesser Public License.
 */
package com.almuramc.portables.bukkit.util;

import com.almuramc.portables.bukkit.PortablesPlugin;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PortablesUtil {
	private static Economy econ = PortablesPlugin.getHooks().getEconomy();
	private static Permission permission = PortablesPlugin.getHooks().getPermissions();

	public static void openEnchantmentTable(Player player) {
		//has to have the use permission
		if (!permission.has(player.getWorld(), player.getName(), "portables.enchantmenttable.use")) {
			return;
		}
		//has to be holding the material
		if (permission.has(player.getWorld(), player.getName(), "portables.enchantmenttable.has")) {
			//Doesn't have it
			if (!hasMaterial(Material.ENCHANTMENT_TABLE, player.getInventory().getContents())) {
				return;
			}
		}
		//has to pay for it
		if (PortablesPlugin.getCached().useEconomy() && permission.has(player.getWorld(), player.getName(), "portables.enchantmenttable.cost")) {
			if (econ.withdrawPlayer(player.getName(), PortablesPlugin.getCached().getEnchantmentTableCost()).equals(EconomyResponse.ResponseType.SUCCESS)) {
				//TODO Dockter, send a pretty success message that they were charged.
			} else {
				//TODO Dockter, send a pretty success message that they don't have enough jack.
				return;
			}
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
			if (!hasMaterial(Material.ENCHANTMENT_TABLE, player.getInventory().getContents())) {
				return;
			}
		}
		//has to pay for it
		if (PortablesPlugin.getCached().useEconomy() && permission.has(player.getWorld(), player.getName(), "portables.workbench.cost")) {
			if (econ.withdrawPlayer(player.getName(), PortablesPlugin.getCached().getWorkbenchCost()).equals(EconomyResponse.ResponseType.SUCCESS)) {
				//TODO Dockter, send a pretty success message that they were charged.
			} else {
				//TODO Dockter, send a pretty success message that they don't have enough jack.
				return;
			}
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

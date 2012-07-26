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
			if (is.getType().equals(toFind)) {
				return true;
			}
		}
		return false;
	}
}

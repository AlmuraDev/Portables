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

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;

public class Dependency {
	private final Plugin plugin;
	private final PluginManager manager;
	private Permission permHook;
	private Economy econHook;

	public Dependency(Plugin plugin) {
		this.plugin = plugin;
		manager = plugin.getServer().getPluginManager();
	}

	public boolean hasSpoutPlugin() {
		return manager.getPlugin("Spout") != null;
	}

	public boolean hasVaultPlugin() {
		return manager.getPlugin("Vault") != null;
	}

	public boolean isSpoutPluginEnabled() {
		return manager.isPluginEnabled("Spout");
	}

	public boolean isVaultPluginEnabled() {
		return hasVaultPlugin() && manager.isPluginEnabled("Vault");
	}

	public Permission getPermissions() {
		if (permHook == null) {
			throw new NullPointerException("Permissions was called but hasn't been setup!");
		}
		return permHook;
	}

	public Economy getEconomy() {
		if (econHook == null) {
			throw new NullPointerException("Economy was called but hasn't been setup!");
		}
		return econHook;
	}

	public void setupVaultEconomy() {
		if (!hasVaultPlugin() || !manager.isPluginEnabled("Vault") || econHook != null) {
			return;
		}
		RegisteredServiceProvider<Economy> economyProvider = plugin.getServer().getServicesManager().getRegistration(Economy.class);
		if (economyProvider != null) {
			econHook = economyProvider.getProvider();
			plugin.getLogger().info("Successfully hooked into Vault for economy transactions");
		}
	}

	public void setupVaultPermissions() {
		if (!hasVaultPlugin() || !manager.isPluginEnabled("Vault") || permHook != null) {
			return;
		}
		RegisteredServiceProvider<Permission> rsp = plugin.getServer().getServicesManager().getRegistration(Permission.class);
		if (rsp != null) {
			permHook = rsp.getProvider();
			plugin.getLogger().info("Successfully hooked into Vault for permissions");
		}
	}

	public void setupResidence() {
		if (manager.isPluginEnabled("Residence")) {
			plugin.getLogger().info("Successfully hooked into Residence");
		}
	}

	public boolean isResidenceEnabled() {
		return manager.isPluginEnabled("Residence");
	}
}

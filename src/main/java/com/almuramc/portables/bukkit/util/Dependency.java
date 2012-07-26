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
		return hasSpoutPlugin() && manager.isPluginEnabled("Spout");
	}

	public boolean isVaultPluginEnabled() {
		return hasVaultPlugin() && manager.isPluginEnabled("Vault");
	}

	public Permission getPermissions(){
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
}

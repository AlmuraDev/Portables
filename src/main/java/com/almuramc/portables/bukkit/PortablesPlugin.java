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
package com.almuramc.portables.bukkit;

import com.almuramc.portables.bukkit.command.PortablesCommands;
import com.almuramc.portables.bukkit.configuration.PortablesConfiguration;
import com.almuramc.portables.bukkit.util.Dependency;
import com.almuramc.portables.bukkit.util.SpoutSafeBindings;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PortablesPlugin extends JavaPlugin {
	private static Dependency hooks;
	private static PortablesConfiguration cached;
	private static PortablesPlugin instance;

	@Override
	public void onLoad() {
		cached = new PortablesConfiguration(this);
		cached.load();
		hooks = new Dependency(this);
	}

	@Override
	public void onEnable() {
		instance = this;
		hooks.setupVaultEconomy();
		hooks.setupVaultPermissions();
		hooks.setupResidence();
		getCommand("portables").setExecutor(new PortablesCommands(this));
		//Classloader has SpoutPlugin, Admin wants to use Spout features, and SpoutPlugin has been enabled. Overkill but trying to nail out the issue
		if (cached.useSpout() && hooks.isSpoutPluginEnabled()) {
			SpoutSafeBindings.registerSpoutBindings();
			Bukkit.getPluginManager().registerEvents(new PortablesSpoutListener(), this);
		}
	}

	public static PortablesPlugin getInstance() {
		return instance;
	}

	public static Dependency getHooks() {
		return hooks;
	}

	public static PortablesConfiguration getCached() {
		return cached;
	}
}
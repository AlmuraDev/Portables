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
package com.almuramc.portables.bukkit.configuration;

import java.io.File;

import com.almuramc.portables.bukkit.PortablesPlugin;

import org.bukkit.configuration.file.YamlConfiguration;

public final class PortablesConfiguration {
	private final PortablesPlugin plugin;
	private YamlConfiguration configLoader;

	public PortablesConfiguration(PortablesPlugin plugin) {
		this.plugin = plugin;
	}

	public boolean useSpout() {
		return configLoader.getBoolean("general.use-spoutplugin");
	}

	public boolean useEconomy() {
		return configLoader.getBoolean("general.use-economy");
	}

	public double getEnchantCost() {
		return configLoader.getDouble("enchant.cost", 0.0);
	}

	public String getEnchantHotkey() {
		return configLoader.getString("enchant.hotkey");
	}

	public String getWorkbenchHotkey() {
		return configLoader.getString("workbench.hotkey");
	}

	public double getWorkbenchCost() {
		return configLoader.getDouble("workbench.cost", 0.0);
	}

	public void load() {
		if (!new File(plugin.getDataFolder(), "config.yml").exists()) {
			plugin.saveDefaultConfig();
		}
		configLoader = (YamlConfiguration) plugin.getConfig();
	}

	public void reload() {
		if (!new File(plugin.getDataFolder(), "config.yml").exists()) {
			plugin.saveDefaultConfig();
		}
		plugin.reloadConfig();
		configLoader = (YamlConfiguration) plugin.getConfig();
	}
}

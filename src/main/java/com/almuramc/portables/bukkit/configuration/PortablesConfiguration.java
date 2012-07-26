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

	public double getEnchantmentTableCost() {
		return configLoader.getDouble("enchantmenttable.cost", 0.0);
	}

	public String getEnchantmentTableHotkey() {
		return configLoader.getString("enchantmenttable.hotkey");
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

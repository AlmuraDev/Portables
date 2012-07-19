/*
 * This file is part of Workbench.
 *
 * Copyright (c) 2012, AlmuraDev <http://www.almuramc.com/>
 * Workbench is licensed under the Almura Development License.
 *
 * Workbench is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * As an exception, all classes which do not reference GPL licensed code
 * are hereby licensed under the GNU Lesser Public License, as described
 * in Almura Development License.
 *
 * Workbench is distributed in the hope that it will be useful,
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
/*
 * This file is part of Backpack.
 *
 * Copyright (c) 2012, AlmuraDev <http://www.almuramc.com/>
 * Backpack is licensed under the Almura Development License version 1.
 *
 * Backpack is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * As an exception, all classes which do not reference GPL licensed code
 * are hereby licensed under the GNU Lesser Public License, as described
 * in Almura Development License version 1.
 *
 * Backpack is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License,
 * the GNU Lesser Public License (for classes that fulfill the exception)
 * and the Almura Development License version 1 along with this program. If not, see
 * <http://www.gnu.org/licenses/> for the GNU General Public License and
 * the GNU Lesser Public License.
 */
package com.almuramc.workbench.bukkit.configuration;

import java.io.File;

import com.almuramc.workbench.bukkit.WorkbenchPlugin;

import org.bukkit.configuration.file.YamlConfiguration;

public final class WorkbenchConfiguration {
	private final WorkbenchPlugin plugin;
	private YamlConfiguration configLoader;

	public WorkbenchConfiguration(WorkbenchPlugin plugin) {
		this.plugin = plugin;
	}

	public boolean useSpout() {
		return configLoader.getBoolean("general.use-spout");
	}

	public String getWorkbenchHotkey() {
		return configLoader.getString("workbench.hotkey");
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
		} else {
			plugin.reloadConfig();
			configLoader = (YamlConfiguration) plugin.getConfig();
		}
	}
}

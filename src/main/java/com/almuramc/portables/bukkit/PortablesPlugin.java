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
package com.almuramc.portables.bukkit;

import com.almuramc.portables.bukkit.command.PortablesCommands;
import com.almuramc.portables.bukkit.configuration.PortablesConfiguration;
import com.almuramc.portables.bukkit.input.PortablesWorkbenchDelegate;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.keyboard.Keyboard;

import org.bukkit.plugin.java.JavaPlugin;

public class PortablesPlugin extends JavaPlugin {
	private PortablesConfiguration cached;

	@Override
	public void onLoad() {
		cached = new PortablesConfiguration(this);
		cached.load();
		if (cached.useSpout() && getServer().getPluginManager().getPlugin("Spout") != null) {
			SpoutManager.getKeyBindingManager().registerBinding("Workbench", Keyboard.valueOf(cached.getWorkbenchHotkey()), "Opens the portable workbench", new PortablesWorkbenchDelegate(), this);
		}
	}

	@Override
	public void onEnable() {
		getCommand("portables").setExecutor(new PortablesCommands(this));
	}
}
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
import com.almuramc.portables.bukkit.input.PortablesEnchantmentTableDelegate;
import com.almuramc.portables.bukkit.input.PortablesWorkbenchDelegate;

import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.keyboard.Keyboard;

/**
 * Safely handles registering SpoutPlugin's bindings when SpoutPlugin may not be existant.
 */
public class SpoutSafeBindings {
	public static void registerSpoutBindings() {
		PortablesPlugin.getInstance().getLogger().info("Successfully hooked into SpoutPlugin for keybindings");
		SpoutManager.getKeyBindingManager().registerBinding("Enchantment Table", Keyboard.valueOf(PortablesPlugin.getCached().getEnchantmentTableHotkey()), "Opens the portable enchantment table", new PortablesEnchantmentTableDelegate(), PortablesPlugin.getInstance());
		SpoutManager.getKeyBindingManager().registerBinding("Workbench", Keyboard.valueOf(PortablesPlugin.getCached().getWorkbenchHotkey()), "Opens the portable workbench", new PortablesWorkbenchDelegate(), PortablesPlugin.getInstance());
	}
}

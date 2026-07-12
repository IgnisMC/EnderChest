/*
 * Copyright (c) 2026 ItzLoghotXD
 *
 * This file is part of "EnderChest" Plugin.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation, version 3 of the License.
 */

package me.itzloghotxd.enderchest.storage;

import org.bukkit.inventory.ItemStack;

public class StoragePage {

    private final ItemStack[] contents;
    public static final int SIZE = 45;

    public StoragePage() {
        contents = new ItemStack[SIZE];
    }

    public ItemStack[] getContents() {
        return contents;
    }
}

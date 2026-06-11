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

import java.util.UUID;

public class PlayerStorage {
    private final UUID playerId;
    private final ItemStack[] items;

    public PlayerStorage(UUID playerId) {
        this.playerId = playerId;
        items = new ItemStack[45];
    }

    public ItemStack[] getItems() {
        return items;
    }

    public UUID getPlayerId() {
        return playerId;
    }
}

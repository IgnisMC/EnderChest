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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerStorage {
    private final UUID playerId;
    private final List<StoragePage> pages = new ArrayList<>();

    public PlayerStorage(UUID playerId) {
        this.playerId = playerId;

        for (int i = 0; i < 9; i++) {
            pages.add(i, new StoragePage());
        }
    }

    public UUID getPlayerId() {
        return playerId;
    }

    public StoragePage getPage(int page) {
        return pages.get(page);
    }

    public int getPageCount() {
        return pages.size();
    }
}

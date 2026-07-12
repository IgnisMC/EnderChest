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

import java.util.UUID;

public interface StorageProvider {
    PlayerStorage loadPlayerStorage(UUID uuid);

    void savePlayerStorage(UUID uuid);

    void saveAll();

    PlayerStorage getPlayerStorage(UUID uuid);

    void unloadPlayerStorage(UUID uuid);
}

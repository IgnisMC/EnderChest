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

import me.itzloghotxd.enderchest.EnderChestPlugin;
import me.itzloghotxd.pdk.config.ConfigHandler;
import me.itzloghotxd.pdk.config.ConfigManager;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.*;

public class StorageManager {
    private final ConfigManager configManager;
    private final Map<UUID, PlayerStorage> storages = new HashMap<>();

    public StorageManager() {
        configManager = EnderChestPlugin.getPlugin().getConfigManager();
    }

    public PlayerStorage getStorage(UUID uuid) {
        return storages.computeIfAbsent(uuid, this::loadStorage);
    }

    public void saveStorage(UUID uuid) {
        String file = "data/" + uuid;
        if (!configManager.has(file)) {
            configManager.register(new ConfigHandler(EnderChestPlugin.getPlugin(), file));
        }

        PlayerStorage storage = getStorage(uuid);
        configManager.getConfig(file).set("items", Arrays.asList(storage.getItems()));
        configManager.save(file);
    }

    private PlayerStorage loadStorage(UUID uuid) {
        String file = "data/" + uuid;
        PlayerStorage storage = new PlayerStorage(uuid);

        File playerFile = new File(EnderChestPlugin.getPlugin().getDataFolder(), file + ".yml");
        if (!playerFile.exists()) return storage;

        if (!configManager.has(file)) {
            configManager.register(new ConfigHandler(EnderChestPlugin.getPlugin(), file));
            configManager.reload(file);
        }

        List<?> list = configManager.getConfig(file).getList("items");
        if (list != null) {
            for (int i = 0; i < Math.min(45, list.size()); i++) {
                storage.getItems()[i] = (ItemStack) list.get(i);
            }
        }

        return storage;
    }

    public void save() {
        for (UUID uuid : storages.keySet()) {
            saveStorage(uuid);
        }
    }

    public void save(UUID... uuids) {
        for (UUID uuid : uuids) {
            saveStorage(uuid);
        }
    }

    public void unloadStorage(UUID uuid) {
        storages.remove(uuid);
    }
}

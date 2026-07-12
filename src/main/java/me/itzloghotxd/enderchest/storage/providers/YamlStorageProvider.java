/*
 * Copyright (c) 2026 ItzLoghotXD
 *
 * This file is part of "EnderChest" Plugin.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation, version 3 of the License.
 */

package me.itzloghotxd.enderchest.storage.providers;

import me.itzloghotxd.enderchest.EnderChestPlugin;
import me.itzloghotxd.enderchest.storage.PlayerStorage;
import me.itzloghotxd.enderchest.storage.StoragePage;
import me.itzloghotxd.enderchest.storage.StorageProvider;
import me.itzloghotxd.pdk.config.ConfigHandler;
import me.itzloghotxd.pdk.config.ConfigManager;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.*;

public class YamlStorageProvider implements StorageProvider {
    private final Map<UUID, PlayerStorage> storages = new HashMap<>();
    private final ConfigManager configManager;

    public YamlStorageProvider() {
        configManager = EnderChestPlugin.getPlugin().getConfigManager();
    }

    @Override
    public PlayerStorage loadPlayerStorage(UUID uuid) {
        String file = "data/" + uuid;
        PlayerStorage storage = new PlayerStorage(uuid);

        File playerFile = new File(EnderChestPlugin.getPlugin().getDataFolder(), file + ".yml");
        if (!playerFile.exists()) return storage;

        if (!configManager.has(file)) {
            configManager.register(new ConfigHandler(EnderChestPlugin.getPlugin(), file));
            configManager.reload(file);
        }

        for (int i = 0; i < storage.getPageCount() ; i++) {
            List<?> list = configManager.getConfig(file).getList("pages."+i);
            if (list == null) continue;

            for (int j = 0; j < Math.min(StoragePage.SIZE, list.size()); j++) {
                storage.getPage(i).getContents()[j] = (ItemStack) list.get(j);
            }
        }

        return storage;
    }

    @Override
    public void savePlayerStorage(UUID uuid) {
        String file = "data/" + uuid;
        if (!configManager.has(file)) {
            configManager.register(new ConfigHandler(EnderChestPlugin.getPlugin(), file));
        }

        PlayerStorage storage = getPlayerStorage(uuid);
        for (int i = 0; i < storage.getPageCount() ; i++) {
            configManager.getConfig(file).set("pages."+i, Arrays.asList(storage.getPage(i).getContents()));
        }

        configManager.save(file);
    }

    @Override
    public void saveAll() {
        for (UUID uuid : storages.keySet()) {
            savePlayerStorage(uuid);
        }
    }

    @Override
    public PlayerStorage getPlayerStorage(UUID uuid) {
        return storages.computeIfAbsent(uuid, this::loadPlayerStorage);
    }

    @Override
    public void unloadPlayerStorage(UUID uuid) {
        storages.remove(uuid);
    }
}

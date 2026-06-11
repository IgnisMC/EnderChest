/*
 * Copyright (c) 2026 ItzLoghotXD
 *
 * This file is part of "EnderChest" Plugin.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation, version 3 of the License.
 */

package me.itzloghotxd.enderchest;

import me.itzloghotxd.enderchest.command.StorageCommand;
import me.itzloghotxd.enderchest.storage.StorageManager;
import me.itzloghotxd.pdk.command.CommandHandler;
import me.itzloghotxd.pdk.command.CommandManager;
import me.itzloghotxd.pdk.config.ConfigManager;
import me.itzloghotxd.pdk.gui.inventory.InventoryListener;
import org.bstats.bukkit.Metrics;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.UUID;

public final class EnderChestPlugin extends JavaPlugin {
    private static EnderChestPlugin plugin;
    private ConfigManager configManager;
    private StorageManager storageManager;
    private static final int BSTATS_ID = 24501;

    @Override
    public void onEnable() {
        long start = System.currentTimeMillis();

        getLogger().info("");
        getLogger().info(getDescription().getName());
        getLogger().info("Version " + getDescription().getVersion());
        getLogger().info("Made with <3 by ItzLoghotXD");
        getLogger().info(getDescription().getWebsite());
        getLogger().info("");

        plugin = this;

        new Metrics(this, BSTATS_ID);

        setupDataFolder();

        registerConfig();
        storageManager = new StorageManager();
        registerCommand();
        registerEvents();

        getLogger().info("");
        getLogger().info("Successfully loaded in " + (System.currentTimeMillis() - start) + "ms!");
    }

    @Override
    public void onDisable() {
        storageManager.save();
    }

    private void registerConfig() {
        configManager = new ConfigManager(this);
    }

    private void registerCommand() {
        CommandManager commandManager = new CommandManager(this);
        new CommandHandler(this, "storage", commandManager, new StorageCommand());
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        getServer().getPluginManager().registerEvents(new Listener() {
            @EventHandler
            public void onPlayerQuit(PlayerQuitEvent event) {
                UUID uuid = event.getPlayer().getUniqueId();

                storageManager.saveStorage(uuid);
                storageManager.unloadStorage(uuid);
            }
        }, this);
    }

    private void setupDataFolder() {
        File dataFolder = new File(getDataFolder(), "data");
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
    }

    public static EnderChestPlugin getPlugin() {
        return plugin;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public StorageManager getStorageManager() {
        return storageManager;
    }
}

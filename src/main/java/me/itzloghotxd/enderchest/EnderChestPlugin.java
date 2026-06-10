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
import me.itzloghotxd.pdk.command.CommandHandler;
import me.itzloghotxd.pdk.command.CommandManager;
import me.itzloghotxd.pdk.gui.inventory.InventoryListener;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public final class EnderChestPlugin extends JavaPlugin {
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

        new Metrics(this, BSTATS_ID);

        registerCommand();
        registerEvents();

        getLogger().info("");
        getLogger().info("Successfully loaded in " + (System.currentTimeMillis() - start) + "ms!");
    }

    private void registerCommand() {
        CommandManager commandManager = new CommandManager(this);
        new CommandHandler(this, "storage", commandManager, new StorageCommand());
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
    }
}

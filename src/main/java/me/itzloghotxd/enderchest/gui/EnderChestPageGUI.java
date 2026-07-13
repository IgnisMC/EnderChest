/*
 * Copyright (c) 2026 ItzLoghotXD
 *
 * This file is part of "EnderChest" Plugin.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published
 * by the Free Software Foundation, version 3 of the License.
 */

package me.itzloghotxd.enderchest.gui;

import me.itzloghotxd.enderchest.EnderChestPlugin;
import me.itzloghotxd.enderchest.storage.PlayerStorage;
import me.itzloghotxd.enderchest.storage.StoragePage;
import me.itzloghotxd.pdk.gui.inventory.AbstractInventory;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class EnderChestPageGUI extends AbstractInventory {
    private final UUID uuid;
    private final int pageIndex;

    public EnderChestPageGUI(Player player, int page) {
        super(player);
        this.uuid = player.getUniqueId();
        pageIndex = page;
    }

    @Override
    public Component getTitle() {
        return Component.text("Ender Chest (Page " + (pageIndex+1) + ")");
    }

    @Override
    public Row getRows() {
        return Row.SIX;
    }

    @Override
    public void handleClick(InventoryClickEvent event) {
        if (event.getClickedInventory() == event.getView().getTopInventory() && event.getSlot() <= 8) event.setCancelled(true);

        ItemStack item = event.getCurrentItem();
        if (item == null) return;

        switch (item.getType()) {
            case ARROW -> new StorageGUI(player).open();
            case BARRIER -> player.closeInventory();
        }
    }

    @Override
    public void setItems() {
        ItemStack back = new ItemStack(Material.ARROW);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.displayName(Component.text("Back").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false));
        back.setItemMeta(backMeta);
        inventory.setItem(1, back);

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.displayName(Component.text("Close").color(NamedTextColor.RED).decoration(TextDecoration.ITALIC, false));
        close.setItemMeta(closeMeta);
        inventory.setItem(0, close);

        ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta fillerMeta = filler.getItemMeta();
        fillerMeta.displayName(Component.empty());
        filler.setItemMeta(fillerMeta);
        for (int i = 0; i < 9; i++) {
            ItemStack item = inventory.getItem(i);
            if (item == null || item.getType() == Material.AIR) inventory.setItem(i, filler);
        }

        PlayerStorage storage = EnderChestPlugin.getPlugin().getStorageProvider().getPlayerStorage(uuid);
        ItemStack[] contents = storage.getPage(pageIndex).getContents();
        for (int i = 0; i < contents.length; i++) {
            inventory.setItem(i + 9, contents[i]);
        }
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1.0F, 1.0F);
    }

    @Override
    public void onClose(InventoryCloseEvent event) {
        PlayerStorage storage = EnderChestPlugin.getPlugin().getStorageProvider().getPlayerStorage(uuid);
        for (int i = 0; i < StoragePage.SIZE; i++) {
            storage.getPage(pageIndex).getContents()[i] = inventory.getItem(i + 9);
        }
        EnderChestPlugin.getPlugin().getStorageProvider().savePlayerStorage(uuid);
    }
}

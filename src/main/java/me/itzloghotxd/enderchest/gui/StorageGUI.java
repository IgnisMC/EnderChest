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

import me.itzloghotxd.pdk.gui.inventory.AbstractInventory;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class StorageGUI extends AbstractInventory {
    @Override
    public Component getTitle() {
        return Component.text("Storage");
    }

    @Override
    public Row getRows() {
        return Row.THREE;
    }

    @Override
    public void handleClick(InventoryClickEvent event) {
        event.setCancelled(true);

        ItemStack item = event.getCurrentItem();
        if (item == null) return;

        switch (item.getType()) {
            case PURPLE_STAINED_GLASS_PANE -> {
                Player player = (Player) event.getWhoClicked();
                new EnderChestPageGUI(player).open(player);
            }
            case BARRIER -> event.getWhoClicked().closeInventory();
        }
    }

    @Override
    public void setItems() {
        ItemStack enderchest = new ItemStack(Material.ENDER_CHEST);
        ItemMeta enderchestMeta = enderchest.getItemMeta();
        enderchestMeta.displayName(Component.text("Ender Chest").color(NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
        enderchestMeta.lore(List.of(
                Component.text("Store global items you can").color(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false),
                Component.text("access anywhere in your ender").color(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false),
                Component.text("chest.").color(NamedTextColor.GRAY).decoration(TextDecoration.ITALIC, false))
        );
        enderchest.setItemMeta(enderchestMeta);
        inventory.setItem(4, enderchest);

        for (int i = 0; i < 9; i++) {
            ItemStack page = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
            ItemMeta pageMeta = page.getItemMeta();
            pageMeta.lore(List.of(
                    Component.text("Click to open!").color(NamedTextColor.YELLOW).decoration(TextDecoration.ITALIC, false)
            ));
            pageMeta.displayName(Component.text("Ender Chest Page " + (i+1)).color(NamedTextColor.GREEN).decoration(TextDecoration.ITALIC, false));
            page.setItemMeta(pageMeta);
            inventory.setItem(i+9, page);
        }

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closeMeta = close.getItemMeta();
        closeMeta.displayName(Component.text("Close").color(NamedTextColor.RED).decoration(TextDecoration.ITALIC, false));
        close.setItemMeta(closeMeta);
        inventory.setItem(22, close);
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        Player player = (Player) event.getPlayer();
        player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1.0F, 1.0F);
    }
}

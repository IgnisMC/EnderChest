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

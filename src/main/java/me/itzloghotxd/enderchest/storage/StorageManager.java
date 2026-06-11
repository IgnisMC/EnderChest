package me.itzloghotxd.enderchest.storage;

import me.itzloghotxd.enderchest.EnderChestPlugin;
import me.itzloghotxd.pdk.config.ConfigHandler;
import me.itzloghotxd.pdk.config.ConfigManager;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class StorageManager {
    private final ConfigManager configManager;
    private final Map<UUID, PlayerStorage> storages = new HashMap<>();

    public StorageManager() {
        configManager = EnderChestPlugin.getPlugin().getConfigManager();
    }

    public PlayerStorage getStorage(UUID uuid) {
        return storages.computeIfAbsent(uuid, PlayerStorage::new);
    }

    public void createPlayerFile(Player player) {
        UUID uuid = player.getUniqueId();
        if (configManager.has("data/" + uuid)) return;
        configManager.register(new ConfigHandler(EnderChestPlugin.getPlugin(), "data/" + uuid + ".yml"));
        configManager.save();
        configManager.reload();
    }
}

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

public class EnderChestPageGUI extends AbstractInventory {
    @Override
    public Component getTitle() {
        return Component.text("Ender Chest");
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
            case ARROW -> {
                Player player = (Player) event.getWhoClicked();
                player.closeInventory();
                new StorageGUI().open(player);
            }
            case BARRIER -> event.getWhoClicked().closeInventory();
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
            if (inventory.getItem(i) == null || inventory.getItem(i).getType() == Material.AIR) inventory.setItem(i, filler);
        }
    }

    @Override
    public void onOpen(InventoryOpenEvent event) {
        Player player = (Player) event.getPlayer();
        player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 1.0F, 1.0F);
    }
}

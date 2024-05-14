package org.capitalism.ItemManager;

import jdk.tools.jlink.plugin.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.capitalism.Capitalism;

import java.util.Random;
import java.util.stream.IntStream;

public class UsableItem {

    private Interaction interaction;
    private ItemDisplay itemDisplay;
    private ItemStack itemStack;
    private Location position;

    private Capitalism plugin;

    public UsableItem(Location position, Capitalism plugin) {
        this.interaction = null;
        this.itemDisplay = null;
        this.itemStack = null;
        this.position = position;
        this.plugin = plugin;
    }

    public void itemCreation() {
        this.interaction = (Interaction) position.getWorld().spawnEntity(position, EntityType.INTERACTION);
        this.interaction.setInteractionWidth(0.5f);
        this.interaction.setInteractionHeight(0.5f);

        this.itemDisplay = (ItemDisplay) position.getWorld().spawnEntity(position, EntityType.ITEM_DISPLAY);
        this.itemStack = new ItemStack(Material.WOODEN_HOE);
        ItemMeta meta = this.itemStack.getItemMeta();
        Random random = new Random();
        meta.setCustomModelData(random.nextInt(1,3));
        this.itemStack.setItemMeta(meta);



        this.itemDisplay.setItemDisplayTransform(ItemDisplay.ItemDisplayTransform.GROUND);
        this.itemDisplay.setItemStack(this.itemStack);
        this.itemDisplay.setDisplayWidth(0.5f);
        this.itemDisplay.setDisplayHeight(0.5f);

    }

    public ItemDisplay getItemDisplay() {
        return this.itemDisplay;
    }

    public Interaction getInteraction() {
        return this.interaction;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public void setItemDisplay(ItemDisplay itemDisplay) {
        this.itemDisplay = itemDisplay;
    }
}

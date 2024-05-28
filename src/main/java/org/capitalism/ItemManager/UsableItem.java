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
import org.capitalism.Weapons.Pistol.J7;
import org.capitalism.Weapons.Shotgun.Brimstone;
import org.capitalism.Weapons.Sniper.Eagle50;
import org.capitalism.Weapons.Weapon;

import java.util.Random;
import java.util.stream.IntStream;

public class UsableItem extends Container {

    private Interaction interaction;
    private ItemDisplay itemDisplay;
    private ItemStack itemStack;
    private Location position;
    private Capitalism plugin;
    private Boolean owned;
    private double price;
    private Weapon weapon;

    public UsableItem(Location position, Capitalism plugin, Boolean owned) {
        super(null);
        this.itemDisplay = null;
        this.itemStack = null;
        this.position = position;
        this.plugin = plugin;
        this.owned = owned;
        this.price = 0.00;
        this.itemCreation();
    }

    public void itemCreation() {
        this.interaction = (Interaction) position.getWorld().spawnEntity(position, EntityType.INTERACTION);
        this.interaction.setInteractionWidth(0.5f);
        this.interaction.setInteractionHeight(0.5f);

        this.itemDisplay = (ItemDisplay) position.getWorld().spawnEntity(position, EntityType.ITEM_DISPLAY);
        this.itemStack = new ItemStack(Material.WOODEN_HOE);
        ItemMeta meta = this.itemStack.getItemMeta();
        Random random = new Random();
        int modelData = random.nextInt(1,3);
        meta.setCustomModelData(modelData);
        this.itemStack.setItemMeta(meta);

        this.itemDisplay.setItemDisplayTransform(ItemDisplay.ItemDisplayTransform.GROUND);
        this.itemDisplay.setItemStack(this.itemStack);
        this.itemDisplay.setDisplayWidth(0.5f);
        this.itemDisplay.setDisplayHeight(0.5f);

        switch(modelData) {
            case 1:
                this.weapon = new J7(null);
                break;
            case 2:
                this.weapon = new Eagle50(null, plugin);
                break;
            default:
               this.weapon = new Brimstone(null);
        }


        this.price = this.weapon.getPrice();
        //Bukkit.broadcastMessage(String.valueOf(price));

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

    public Boolean getOwned() {
        return owned;
    }

    public void setOwned(Boolean owned) {
        this.owned = owned;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}

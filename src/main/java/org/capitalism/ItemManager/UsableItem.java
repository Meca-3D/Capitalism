package org.capitalism.ItemManager;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class UsableItem {

    private Interaction interaction;
    private ItemDisplay itemDisplay;
    private Location position;

    public UsableItem(Location position) {
        this.interaction = null;
        this.itemDisplay = null;
        this.position = position;
    }

    public void itemCreation() {
        Interaction interactionItem = (Interaction) position.getWorld().spawnEntity(position.add(new Vector(0, 1, 0)), EntityType.INTERACTION);
        interactionItem.setInteractionWidth(0.5f);
        interactionItem.setInteractionHeight(0.5f);

        ItemDisplay displayItem = (ItemDisplay) position.getWorld().spawnEntity(position, EntityType.ITEM_DISPLAY);
        ItemStack arme = new ItemStack(Material.WOODEN_HOE);
        displayItem.setItemDisplayTransform(ItemDisplay.ItemDisplayTransform.GROUND);
        displayItem.setItemStack(arme);
        displayItem.setDisplayWidth(0.5f);
        displayItem.setDisplayHeight(0.5f);
    }

    public void animation() {

    }

}

package org.capitalism.ItemManager;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Random;

public class LootChest {

    private Interaction interaction;
    private ItemDisplay itemDisplay;
    private Location position;

   public LootChest(Interaction interaction, ItemDisplay itemDisplay, Location position) {
       this.interaction = interaction;
       this.itemDisplay = itemDisplay;
       this.position = position;

   }

   public void remove() {
       interaction.remove();
       itemDisplay.remove();
   }

   public void removeInteractions() {
       interaction.remove();
   }

    public void openChest() {
        Random random = new Random();

        UsableItem item = new UsableItem(position);
        item.itemCreation();
    }

    public Interaction getInteraction() {
       return this.interaction;
    }

    public void setInteraction(Interaction interaction) {
        this.interaction = interaction;
    }

    public ItemDisplay getItemDisplay() {
        return itemDisplay;
    }

    public void setItemDisplay(ItemDisplay itemDisplay) {
        this.itemDisplay = itemDisplay;
    }
}

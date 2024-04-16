package org.capitalism.Chest;

import org.bukkit.entity.Interaction;
import org.bukkit.entity.ItemDisplay;

public class LootChest {

    private Interaction interaction;
    private ItemDisplay itemDisplay;

   public LootChest(Interaction interaction, ItemDisplay itemDisplay) {
       this.interaction = interaction;
       this.itemDisplay = itemDisplay;
   }

   public void remove() {
       interaction.remove();
       itemDisplay.remove();
   }

    public Interaction getInteraction() {
        return interaction;
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

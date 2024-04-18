package org.capitalism.ItemManager;

import org.bukkit.entity.Interaction;
import org.bukkit.entity.ItemDisplay;

import java.util.ArrayList;

public class LootChest {

    private Interaction interaction1;
    private Interaction interaction2;
    private ItemDisplay itemDisplay;

   public LootChest(Interaction interaction1, Interaction interaction2, ItemDisplay itemDisplay) {
       this.interaction1 = interaction1;
       this.interaction2 = interaction2;
       this.itemDisplay = itemDisplay;

   }

   public void remove() {
       interaction1.remove();
       interaction2.remove();
       itemDisplay.remove();
   }

    public ArrayList<Interaction> getInteraction() {
        ArrayList<Interaction> arr = new ArrayList<Interaction>();
        arr.add(interaction1);
        arr.add(interaction2);
        return arr;
    }

    public void setInteraction(Interaction interaction1, Interaction interaction2) {
        this.interaction1 = interaction1;
        this.interaction2 = interaction2;
    }

    public ItemDisplay getItemDisplay() {
        return itemDisplay;
    }

    public void setItemDisplay(ItemDisplay itemDisplay) {
        this.itemDisplay = itemDisplay;
    }
}

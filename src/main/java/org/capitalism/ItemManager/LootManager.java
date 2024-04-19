package org.capitalism.ItemManager;

import java.util.ArrayList;

public class LootManager {
    private ArrayList<LootChest> lootChests;
    private ArrayList<UsableItem> items;

    public ArrayList<UsableItem> getItems() {
        return items;
    }
    public void addItem(UsableItem item) {
        this.items.add(item);
    }

    public LootManager() {
        this.lootChests = new ArrayList<>();
    }
    public void addChest(LootChest chest) {
        this.lootChests.add(chest);
    }
    public void removeChest(LootChest chest) {
        this.lootChests.remove(chest);
    }
    public ArrayList<LootChest> getLootChests() {
        return lootChests;
    }


}

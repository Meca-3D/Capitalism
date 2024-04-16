package org.capitalism.Chest;

import java.util.ArrayList;

public class LootManager {
    private ArrayList<LootChest> lootChests;
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

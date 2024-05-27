package org.capitalism.ItemManager;

import java.util.ArrayList;

public class LootManager {
    private ArrayList<Container> loots;
    private ArrayList<UsableItem> items;

    public ArrayList<UsableItem> getItems() {
        return items;
    }
    public void addItem(UsableItem item) {
        this.items.add(item);
    }

    public LootManager() {
        this.loots = new ArrayList<>();
    }
    public void add(Container container) {
        this.loots.add(container);
    }
    public void removeChest(LootChest chest) {
        this.loots.remove(chest);
    }
    public ArrayList<Container> getLoots() {
        return loots;
    }


}

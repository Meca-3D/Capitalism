package org.capitalism.ItemManager;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.entity.Display;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TextDisplay;
import org.bukkit.util.Vector;
import org.capitalism.Capitalism;
import org.capitalism.ListenerClass;
import org.capitalism.Missions.Mission;
import org.capitalism.Prospectors.Prospector;

import java.util.ArrayList;

public class LootManager {
    private ArrayList<Container> loots;
    private ArrayList<UsableItem> items;
    private ListenerClass listenerClass;
    private Capitalism plugin;

    public ArrayList<UsableItem> getItems() {
        return items;
    }
    public void addItem(UsableItem item) {
        this.items.add(item);
    }

    public LootManager(ListenerClass listenerClass, Capitalism plugin) {
        this.loots = new ArrayList<>();
        this.listenerClass = listenerClass;
        this.plugin = plugin;
        displayPrice();
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

    public void setListenerClass(ListenerClass listenerClass) {
        this.listenerClass = listenerClass;
    }

    public void displayPrice() {
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            for (Prospector prospector : listenerClass.getProspectors()) {
                if (prospector.getTargetEntity() == null) {
                    continue;
                }
                for (Container shop : loots) {
                    if (shop instanceof ShopDisplay) {
                        if (prospector.getTargetEntity() == null) {
                            continue;
                        }
                        UsableItem item = ((ShopDisplay) shop).getItem();
                        if (prospector.getTargetEntity() == item.getInteraction()) {
                            if (!item.getOwned()) {
                                TextDisplay textPrice = (TextDisplay) prospector.getPlayer().getWorld().spawnEntity(item.getInteraction().getLocation().add(new Vector(0, 0.5f, 0)), EntityType.TEXT_DISPLAY);
                                textPrice.setText(item.getPrice() + "0 $");
                                textPrice.setBillboard(Display.Billboard.CENTER);
                                textPrice.setCustomNameVisible(false);
                                textPrice.setBackgroundColor(Color.fromARGB(255, 0, 0, 0));
                                textPrice.setPersistent(false);
                                Bukkit.getScheduler().runTaskLater(plugin, textPrice::remove, 3L);
                            }
                        }
                    }
                }
            }
        }, 0L, 2L);
    }

}

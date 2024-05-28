package org.capitalism.ItemManager;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.capitalism.Capitalism;
import org.capitalism.Prospectors.Prospector;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ShopDisplay extends Container {
    private ItemDisplay itemDisplay;
    private Location position;
    private UsableItem item;
    private Capitalism plugin;
    public ShopDisplay(ItemDisplay itemDisplay, Location position, Capitalism plugin) {
        super(null);
        this.itemDisplay = itemDisplay;
        this.position = position;
        this.plugin = plugin;
        this.item = new UsableItem(position.add(new Vector(0, 0.5f, 0)), plugin, false);
        this.item.getItemDisplay().setRotation(0f, -67.5f);
        //this.item.getItemDisplay().setTransformation(new Transformation(new Vector3f(), new AxisAngle4f(22.5f, 1f, 0f, 0f), new Vector3f(1f, 1f, 1f), new AxisAngle4f()));
    }

    public void remove() {
        interaction.remove();
        itemDisplay.remove();
    }

    public void removeInteractions() {
        interaction.remove();
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

    public UsableItem getItem() {
        return item;
    }

    public void setItem(UsableItem item) {
        this.item = item;
    }
}

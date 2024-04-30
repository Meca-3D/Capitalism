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
import org.capitalism.Capitalism;
import org.capitalism.Prospectors.Prospector;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class LootChest {

    private Interaction interaction;
    private ItemDisplay itemDisplay;
    private Location position;

    private ItemDisplay leftDoor;
    private ItemDisplay rightDoor;

    private Capitalism plugin;

   public LootChest(Interaction interaction, ItemDisplay itemDisplay, ItemDisplay leftDoor, ItemDisplay rightDoor, Location position, Capitalism plugin) {
       this.interaction = interaction;
       this.itemDisplay = itemDisplay;
       this.leftDoor = leftDoor;
       this.rightDoor = rightDoor;
       this.position = position;
       this.plugin = plugin;
   }

   public void remove() {
       interaction.remove();
       itemDisplay.remove();
   }

   public void removeInteractions() {
       interaction.remove();
   }
    private float duration;
    public void openChest() {
        Random random = new Random();
        int counter = 0;
        UsableItem item = new UsableItem(position, plugin);
        item.itemCreation();
        animDoor(item);
    }

    public void animDoor(UsableItem item) {
        Transformation transformationL = this.leftDoor.getTransformation();
        Transformation transformationR = this.rightDoor.getTransformation();
        Vector3f vectorL = new Vector3f(0.03f, 0, 0);
        Vector3f vectorR = new Vector3f(-0.03f, 0, 0);
        duration = 27f;
        new BukkitRunnable() {
            @Override
            public void run() {
                if(duration > 0){
                    transformationL.getTranslation().add(vectorL);
                    transformationR.getTranslation().add(vectorR);
                    leftDoor.setTransformation(transformationL);
                    rightDoor.setTransformation(transformationR);
                    duration--;
                } else {
                    animWeapon(item);
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    public void animWeapon(UsableItem item) {
        Transformation transformation = item.getItemDisplay().getTransformation();
        Vector3f vector = new Vector3f(0, 0.05f, 0);
        duration = 27f;
        new BukkitRunnable() {
            @Override
            public void run() {
                if(duration > 0){
                    if (vector.y > 0.01)
                        vector.y-=0.0005f;
                    transformation.getTranslation().add(vector);
                    item.getItemDisplay().setTransformation(transformation);
                    duration--;
                } else {
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0L, 1L);
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

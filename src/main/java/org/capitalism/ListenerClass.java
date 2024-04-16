package org.capitalism;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;
import org.capitalism.Chest.LootChest;
import org.capitalism.Chest.LootManager;
import org.capitalism.Prospectors.Prospector;
import sun.security.util.Debug;

import java.util.ArrayList;

public class ListenerClass implements Listener {
    private ArrayList<Prospector> prospectors;
    private LootManager lootManager;
    public ListenerClass(LootManager lootManager) {
        this.prospectors = new ArrayList<>();
        this.lootManager = lootManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Bukkit.broadcastMessage("fzeg");
        event.getPlayer().getInventory().clear();
        prospectors.add(new Prospector(event.getPlayer()));
        ItemStack J7 = new ItemStack(Material.WOODEN_HOE);
        ItemMeta meta = J7.getItemMeta();
        meta.setDisplayName("Eagle .50");
        J7.setItemMeta(meta);
        event.getPlayer().getInventory().addItem(J7);
    }

    @EventHandler
    public void onPlayerQuit (PlayerQuitEvent event) {
        prospectors.remove(getProspector(event.getPlayer()));
    }


    @EventHandler
    public void onEntityInteraction(PlayerInteractAtEntityEvent event){
        if(event.getRightClicked().getType() == EntityType.INTERACTION || event.getRightClicked().getType() == EntityType.ITEM_DISPLAY ){
            event.getPlayer().sendMessage("You just right clicked on a interaction entity");
            for (LootChest chest :lootManager.getLootChests()) {
                if (chest.getInteraction() == event.getRightClicked()) {
                    chest.remove();
                }
            }

        }
    }
    @EventHandler
    public void onArrowHit(ProjectileHitEvent event){
        if(event.getEntityType() == EntityType.ARROW){
            Location location = event.getEntity().getLocation();
            double x = location.getX();
            double y = location.getY();
            double z = location.getZ();
            event.getEntity().getWorld().spawnParticle(Particle.SONIC_BOOM, x,y+0.1,z,1,0,0.1,0,0.1);
            event.getEntity().remove();
        }
    }

    @EventHandler
    public void onInteractItem(PlayerInteractEvent event){
        Prospector prospector = getProspector(event.getPlayer());
        if (event.getItem() != null) {
            if (event.getItem().getType() == Material.WOODEN_HOE && event.getItem().getItemMeta().getDisplayName().equals("J-7") || event.getItem().getItemMeta().getDisplayName().equals("Eagle .50")) {
                if (prospector != null) {
                    prospector.getSlot1().shoot();

                    Location location = event.getPlayer().getLocation();
                    Interaction interaction = (Interaction) event.getPlayer().getWorld().spawnEntity(location, EntityType.INTERACTION);
                    interaction.setInteractionHeight(1);
                    interaction.setInteractionWidth(2);
                    ItemDisplay model = (ItemDisplay) event.getPlayer().getWorld().spawnEntity(location.add(new Vector(0,0.5,0)), EntityType.ITEM_DISPLAY);
                    ItemStack chest = new ItemStack(Material.CHEST);
                    model.setItemStack(chest);
                    model.setDisplayWidth(2);
                    lootManager.addChest(new LootChest(interaction, model));

                } else {
                    event.getPlayer().sendMessage("Prospector not found");
                }
            }
        }

    }

    /*@EventHandler
        public void onRightClick(PlayerInteractEvent event) {
            Prospector prospector = getProspector(event.getPlayer());
            Action action = event.getAction();

            if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {

            }
        }*/

    public Prospector getProspector(Player player) {
        for(Prospector prospector : prospectors){
            if(player.getName().equals(prospector.getName())){
                return prospector;
            }
        }
        return null;
    }



}

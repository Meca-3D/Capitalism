package org.capitalism;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.capitalism.Prospectors.Prospector;

import java.util.ArrayList;

public class ListenerClass implements Listener {
    private ArrayList<Prospector> prospectors = new ArrayList<>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        event.getPlayer().getInventory().clear();
        prospectors.add(new Prospector(event.getPlayer()));
        ItemStack J7 = new ItemStack(Material.WOODEN_HOE);
        ItemMeta meta = J7.getItemMeta();
        meta.setDisplayName("J-7");
        J7.setItemMeta(meta);
        event.getPlayer().getInventory().addItem(J7);
    }

    public void onPlayerQuit (PlayerQuitEvent event) {
        prospectors.remove(getProspector(event.getPlayer()));
    }

    @EventHandler
    public void onArrowHit(ProjectileHitEvent event){
        if(event.getEntityType() == EntityType.ARROW){
            Location location = event.getEntity().getLocation();
            double x = location.getX();
            double y = location.getY();
            double z = location.getZ();
            event.getEntity().getWorld().spawnParticle(Particle.CLOUD, x,y+0.1,z,10,0,0.1,0,0.1);
            event.getEntity().remove();
        }
    }

    @EventHandler
    public void onInteractItem(PlayerInteractEvent event){
        Prospector prospector = getProspector(event.getPlayer());
        if (event.getItem() != null) {
            if (event.getItem().getType() == Material.WOODEN_HOE && event.getItem().getItemMeta().getDisplayName().equals("J-7")) {
                event.getPlayer().sendMessage("Interacted with J-7");
                if (prospector != null) {
                    prospector.getSlot1().shoot();
                } else {
                    event.getPlayer().sendMessage("Prospector not found");
                }
            }
        }
    }

    public Prospector getProspector(Player player) {
        for(Prospector prospector : prospectors){
            if(player.getName().equals(prospector.getName())){
                return prospector;
            }
        }
        return null;
    }

}

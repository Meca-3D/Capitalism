package org.capitalism;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.capitalism.Prospectors.Prospector;

import java.util.ArrayList;

public class ListenerClass implements Listener {
    private Capitalism plugin;
    private ArrayList<Prospector> prospectors = new ArrayList<>();

    public ListenerClass(Capitalism plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Bukkit.broadcastMessage("fzeg");
        event.getPlayer().getInventory().clear();
        Location location = event.getPlayer().getLocation();
        Interaction interaction = (Interaction) event.getPlayer().getWorld().spawnEntity(location, EntityType.INTERACTION);
        prospectors.add(new Prospector(event.getPlayer(), plugin));
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
    public void onArrowHit(ProjectileHitEvent event){
        if(event.getEntityType() == EntityType.ARROW){
            Location location = event.getEntity().getLocation();
            double x = location.getX();
            double y = location.getY();
            double z = location.getZ();
            event.getEntity().getWorld().spawnParticle(Particle.BLOCK_CRACK, x,y+0.1,z,1,0,0.1,0,0.1, Material.REDSTONE_BLOCK.createBlockData());
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
                } else {
                    event.getPlayer().sendMessage("Prospector not found");
                }
            }
        }
    }

    @EventHandler
    public void onEntityInteraction(PlayerInteractAtEntityEvent event){
        if(event.getRightClicked().getType() == EntityType.INTERACTION && event.getRightClicked().getCustomName().equals("chest")){
            event.getPlayer().sendMessage("You just right clicked on a interaction entity");
            event.getRightClicked().remove();
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

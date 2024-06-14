package org.capitalism;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;
import org.capitalism.Arenas.AreneManager;
import org.capitalism.ItemManager.*;
import org.capitalism.Prospectors.Prospector;
import org.capitalism.Missions.AreaMission;

import java.util.ArrayList;
import java.util.Random;


public class ListenerClass implements Listener {
    private ArrayList<Prospector> prospectors;
    private LootManager lootManager;
    private Capitalism plugin;
    private AreneManager areneManager;
    public ListenerClass(LootManager lootManager, AreneManager areneManager, Capitalism plugin) {
        this.prospectors = new ArrayList<>();
        this.lootManager = lootManager;
        this.plugin = plugin;
        this.areneManager = areneManager;
    }

    public ListenerClass(Capitalism plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        if (prospectors.isEmpty()) {
            for (Entity e : event.getPlayer().getWorld().getEntities()) {
                e.remove();
            }
        }
        event.getPlayer().getInventory().clear();
        prospectors.add(new Prospector(event.getPlayer(), plugin));
        ItemStack J7 = new ItemStack(Material.WOODEN_HOE);
        ItemMeta meta = J7.getItemMeta();
        meta.setDisplayName("Eagle .50");
        J7.setItemMeta(meta);
        event.getPlayer().getInventory().addItem(J7);

        //prospectors.get(prospectors.size()-1).addMission(new AreaMission("Gold Farmer", 30, 30, new Location(event.getPlayer().getWorld(), 0, 0, 0), 35, 1, "available", prospectors.get(prospectors.size()-1), new Location(event.getPlayer().getWorld(), 50, 150, 50), new Location(event.getPlayer().getWorld(), -50, -150, -50)));
        //prospectors.get(prospectors.size()-1).addMission(new Mission("Gold Farmer", 30, 30, new Location(event.getPlayer().getWorld(), 0, 0, 0), 35, 1, "available", prospectors.get(prospectors.size()-1)));
        //getProspector(event.getPlayer()).addMission(new AreaMission(plugin, "Gold Farmer", 30, new Location(event.getPlayer().getWorld(), 0, 72, 0), 35, 1, getProspector(event.getPlayer()), new Location(event.getPlayer().getWorld(), 50, 150, 50), new Location(event.getPlayer().getWorld(), -50, -150, -50)));

    }

    @EventHandler
    public void onPlayerQuit (PlayerQuitEvent event) {
        prospectors.remove(getProspector(event.getPlayer()));
    }

    @EventHandler
    public void onMobKilled (EntityDamageByEntityEvent event) {
        if (event != null && event.getEntity() instanceof LivingEntity) {
            if (((LivingEntity) event.getEntity()).getHealth() - event.getDamage() <= 0) {
                if (event.getDamager() instanceof Player) {
                    Player p = (Player) event.getDamager();
                    getProspector(p).addMoney(new Random().nextInt(2, 6));
                }
            }
        }
    }

    @EventHandler
    public void onEntityInteraction(PlayerInteractAtEntityEvent event){
        if(event.getRightClicked().getType() == EntityType.INTERACTION){
            event.getPlayer().sendMessage("You just right clicked on a interaction entity");

            for (Container loot : lootManager.getLoots()) {

                UsableItem item = null;

                if (loot instanceof LootChest) {
                    LootChest chest = (LootChest) loot;
                    item = chest.getItem();
                    if (chest.getInteraction() == event.getRightClicked()) {
                        chest.openChest();
                        chest.removeInteractions();
                    }
                }

                if (loot instanceof ShopDisplay) {
                    item = ((ShopDisplay) loot).getItem();
                }

                if (item != null) {
                    if (item.getInteraction() == event.getRightClicked()) {
                        ItemStack hand = null;
                        if (event.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) {
                            hand = event.getPlayer().getInventory().getItemInMainHand();
                        }

                        int slot = event.getPlayer().getInventory().getHeldItemSlot();
                        event.getPlayer().getInventory().setItem(slot, item.getItemStack());

                        if (hand != null) {
                            item.setItemStack(hand);
                            item.getItemDisplay().setItemStack(hand);
                            item.setOwned(true);
                        } else {
                            item.getItemDisplay().setItemStack(null);
                            item.setItemStack(null);
                            item.setOwned(true);
                            //lootManager.removeChest(chest);
                        }
                    }
                }


            }

            if (event.getRightClicked() == areneManager.getActualGame().getEndInteraction()) {
                areneManager.getActualGame().end(getProspector(event.getPlayer()));
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
            event.getEntity().getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, x,y+0.1,z,1,0,0.1,0,0.1, Material.REDSTONE_BLOCK.createBlockData());
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
    public void onPlayerAttack(EntityDamageByEntityEvent event)
    {
        if (event != null) {
            if (event.getEntity() instanceof LivingEntity) {
                if(!(event.getEntity() instanceof Player) && event.getDamager() instanceof Player) {
                    if (event.getEntity() instanceof Zombie) {
                        ((Zombie) event.getEntity()).setTarget((LivingEntity) event.getDamager());
                    }
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

    public ArrayList<Prospector> getProspectors() {
        return prospectors;
    }
}

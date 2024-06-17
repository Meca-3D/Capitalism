package org.capitalism.Weapons.Sniper;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;
import org.capitalism.Capitalism;
import org.capitalism.ListenerClass;
import org.capitalism.Weapons.Weapon;
import org.capitalism.Weapons.WeaponInterface;
import org.bukkit.ChatColor;



public class Eagle50 extends Weapon implements WeaponInterface {



    private ListenerClass listenerClass;

    public Eagle50(Player player, Capitalism plugin) {
        super(5, 20, 20, 40, "Eagle .50", player, 2, 450.00d, plugin);
        this.plugin = plugin;
        this.listenerClass = plugin.getListenerClass();
    }

    @Override
    public void shoot() {
        if(((System.currentTimeMillis() - lastShot) > fireRate) && ammo > 0){
            lastShot = System.currentTimeMillis();
            
            ammo--;
            player.sendMessage("§e§l" + ammo + "/" + maxAmmo);
            if(ammo == 0){
                reload();
            }
            new BukkitRunnable() {
                final Vector dir = player.getLocation().getDirection().normalize();
                final Location loc = player.getLocation();
                double t = 0;
                public void run() {
                    t += 1;
                    double x = dir.getX() * t;
                    double y = dir.getY() * t +1.5;
                    double z = dir.getZ() * t;
                    loc.add(x, y, z);
                    player.getWorld().spawnParticle(Particle.BLOCK_CRACK, loc, 1,0.5,0,0,0, Material.STONE.createBlockData());
                    RayTraceResult result = player.getWorld().rayTraceEntities(loc, dir,3);

                    for(Entity e : loc.getWorld().getEntities()){
                        if(result != null){
                            if(result.getHitEntity() != null){
                                if(!result.getHitEntity().equals(player)){
                                    if(result.getHitEntity() instanceof Player && listenerClass.getProspector(((Player) result.getHitEntity()).getPlayer()) != null){
                                        ((Player) result.getHitEntity()).getPlayer().damage(5);
                                        player.sendMessage(listenerClass.getProspector(((Player) result.getHitEntity()).getPlayer()).getHealth() + " health left");
                                    } else if (result.getHitEntity() instanceof LivingEntity) {
                                        ((LivingEntity) result.getHitEntity()).damage(5);
                                    }
                                    result = null;
                                    this.cancel();
                                }
                            }
                        }
                    }

                    loc.subtract(x, y, z);
                    if(t > 40){
                        this.cancel();
                    }
        }

            }.runTaskTimer(plugin,0,1);
        }
    }

    @Override
    public void reload() {
        new BukkitRunnable() {
            @Override
            public void run() {
                ammo = maxAmmo;
            }

        }.runTaskLater(plugin, reloadTime);
    }

    @Override
    public void aim() {

    }
    }
package org.capitalism.Mob;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.capitalism.Capitalism;
import org.capitalism.Prospectors.Prospector;

public class GRABDOUL extends CustomMob {

    public Zombie mob;


    public GRABDOUL(Capitalism plugin, Entity target, Prospector prospector, Location loc) {
        super(plugin, target);

        this.mob = (Zombie) prospector.getPlayer().getWorld().spawnEntity(loc, EntityType.ZOMBIE);

        init();
        update();

    }

    @Override
    public void init() {

        this.mob.setCustomName("GRABDOUL");
        this.mob.setCustomNameVisible(true);
        this.mob.setHealth(20);
        this.mob.setBaby();

        mob.setTarget((LivingEntity) target);

        this.mob.getEquipment().setHelmet(new ItemStack(Material.TNT, 1));

    }

    @Override
    public void update() {

        new BukkitRunnable() {

            @Override
            public void run() {

                if (mob.isDead()) {
                    return;
                }

                if (mob.getTarget() == null) {
                    mob.setTarget((LivingEntity) target);
                }

                mob.getLocation().getWorld().spawnEntity(mob.getTarget().getLocation(), EntityType.LIGHTNING);

            }

        }.runTaskTimer(plugin, 100L, 100L);


    }
}

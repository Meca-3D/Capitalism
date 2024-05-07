package org.capitalism.mission;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Creature;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class GRABDOULFOU extends CustomMob {
    public GRABDOULFOU(String name, int health, int speed, int damage, boolean baby, Creature mob) {
        super(baby, name, health, speed, damage, mob);
        mob.getEquipment().setHelmet(new ItemStack(Material.TNT, 1));

    }

    @Override
    public void update() {
        if (mob.isDead()) {
            Location loc = mob.getLocation();
            mob.getWorld().spawnEntity(loc, EntityType.PRIMED_TNT);
        }
    }
}

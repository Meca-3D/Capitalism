package org.capitalism.Mob;

import org.bukkit.entity.Creature;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Zombie;
import org.capitalism.Capitalism;
import org.capitalism.Prospectors.Prospector;

public class CustomMob {

    protected Capitalism plugin;
    protected Entity target;

    public CustomMob(Capitalism plugin, Entity target) {
        this.plugin = plugin;
        this.target = target;
    }

    public Prospector prospector;

    public void init() {

    }

    public void update() {

    }
}







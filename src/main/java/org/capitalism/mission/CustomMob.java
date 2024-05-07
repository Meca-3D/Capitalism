package org.capitalism.mission;

import org.bukkit.entity.Creature;
import org.bukkit.entity.Zombie;

public class CustomMob {
    public String name;
    public int health;
    public int speed;
    public int damage;
    public Zombie mob;
    public boolean baby;

    public CustomMob(boolean baby, String name, int health, int speed, int damage, Creature mob) {
        this.name = name;
        this.health = health;
        this.speed = speed;
        this.damage = damage;
        this.mob = (Zombie) mob;

        setVariables();

    }
    private void setVariables() {
        this.mob.setCustomName(this.name);
        this.mob.setCustomNameVisible(true);
        this.mob.setHealth(this.health);
        //SPEED AF
        //DAMAGE AF
        if (this.baby)
            this.mob.setBaby();


    }

    public void update() {

    }
}







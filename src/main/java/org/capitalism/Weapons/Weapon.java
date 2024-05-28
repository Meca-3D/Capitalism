package org.capitalism.Weapons;

import org.bukkit.entity.Player;

public class Weapon {

    protected String name;
    protected int ammo;
    protected int maxAmmo;
    protected int damage;
    protected double fireRate;
    protected int reloadTime;
    protected double lastShot;
    protected Player player;
    protected int customModelData;
    protected double price;

    public Weapon(int maxAmmo, int damage, double fireRate, int reloadTime, String name, Player player, int customModelData, double price) {
        this.name = name;
        this.maxAmmo = maxAmmo;
        this.ammo = maxAmmo;
        this.damage = damage;
        this.fireRate = fireRate;
        this.reloadTime = reloadTime;
        this.player = player;
        this.lastShot = System.currentTimeMillis();
        this.customModelData = customModelData;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}

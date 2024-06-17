package org.capitalism.Weapons;

import org.bukkit.entity.Player;
import org.capitalism.Capitalism;

public class Weapon {

    protected String name;
    protected int ammo;
    protected int maxAmmo;
    protected int damage;
    protected double fireRate;
    protected long reloadTime;
    protected double lastShot;
    protected Player player;
    protected int customModelData;
    protected double price;
    protected Capitalism plugin;

    public Weapon(int maxAmmo, int damage, double fireRate, long reloadTime, String name, Player player, int customModelData, double price, Capitalism plugin) {
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
        this.plugin = plugin;
    }

    public double getPrice() {
        return price;
    }
}

package org.capitalism.Weapons;

import org.bukkit.entity.Player;

public class Weapon {

    protected String name;
    protected int ammo;
    protected int maxAmmo;
    protected int damage;
    protected int fireRate;
    protected int reloadTime;
    protected double lastShot;
    protected Player player;

    public Weapon(int maxAmmo, int damage, int fireRate, int reloadTime, String name, Player player) {
        this.name = name;
        this.maxAmmo = maxAmmo;
        this.ammo = maxAmmo;
        this.damage = damage;
        this.fireRate = fireRate;
        this.reloadTime = reloadTime;
        this.player = player;
    }
}

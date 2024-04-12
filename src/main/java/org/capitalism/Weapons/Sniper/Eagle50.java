package org.capitalism.Weapons.Sniper;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.capitalism.Weapons.Weapon;
import org.capitalism.Weapons.WeaponInterface;
public class Eagle50 extends Weapon implements WeaponInterface {

    public Eagle50(Player player) {
        super(5, 20, 3000, 4, "Eagle .50", player);
    }

    @Override
    public void shoot() {
        if(((System.currentTimeMillis() - lastShot) > fireRate) && ammo > 0){
            lastShot = System.currentTimeMillis();
            player.launchProjectile(Arrow.class, player.getLocation().getDirection().multiply(2));
            ammo--;
            player.sendMessage(ammo + " / " + maxAmmo);
        }
        if(ammo == 0){
            reload();
        }
    }

    @Override
    public void reload() {
        ammo = maxAmmo;
    }

    @Override
    public void aim() {

    }
    }
package org.capitalism.Weapons.Shotgun;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.capitalism.Weapons.Weapon;
import org.capitalism.Weapons.WeaponInterface;

public class Brimstone extends Weapon implements WeaponInterface {

    public Brimstone(Player player) {
        super(5, 20, 1500, 4, "Brimstone", player, 3, 300.00d);
    }

    @Override
    public void shoot() {
        //player.launchProjectile(Arrow.class, player.getLocation().getDirection().multiply(2));
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

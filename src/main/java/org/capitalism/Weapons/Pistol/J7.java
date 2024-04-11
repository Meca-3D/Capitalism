package org.capitalism.Weapons.Pistol;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.capitalism.Weapons.Weapon;
import org.capitalism.Weapons.WeaponInterface;

public class J7 extends Weapon implements WeaponInterface {

    public J7(Player player) {
        super(7, 10, 10, 1, "J-7", player);
    }

    @Override
    public void shoot() {
        //player.launchProjectile(Arrow.class, player.getLocation().getDirection().multiply(2));
        if((System.currentTimeMillis() - lastShot > (double) 1000 / fireRate) && ammo > 0){
            lastShot = System.currentTimeMillis();
            player.launchProjectile(Arrow.class, player.getLocation().getDirection().multiply(2));
            player.sendMessage(ammo + " / c " + maxAmmo);
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

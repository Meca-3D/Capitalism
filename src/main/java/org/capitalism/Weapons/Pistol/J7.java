package org.capitalism.Weapons.Pistol;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.capitalism.Weapons.Weapon;
import org.capitalism.Weapons.WeaponInterface;

public class J7 extends Weapon implements WeaponInterface {

    public J7(Player player) {
        super(7, 10, 600, 1, "J-7", player, 1, 120.00d);
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

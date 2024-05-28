package org.capitalism.Weapons.Pistol;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.capitalism.Weapons.Weapon;
import org.capitalism.Weapons.WeaponInterface;
public class Operator extends Weapon implements WeaponInterface {

    public Operator(Player player) {
        super(5, 20, 0.33, 4, "Operator", player, 10000, 10000.00d);
    }

    @Override
    public void shoot() {
        player.launchProjectile(Arrow.class, player.getLocation().getDirection().multiply(2));
        if(System.currentTimeMillis() - lastShot > (double) 1000 /fireRate && ammo > 0){
            lastShot = System.currentTimeMillis();
            player.launchProjectile(Arrow.class, player.getLocation().getDirection().multiply(2));
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

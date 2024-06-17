package org.capitalism.Weapons.Pistol;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.capitalism.Capitalism;
import org.capitalism.Weapons.Weapon;
import org.capitalism.Weapons.WeaponInterface;



public class J7 extends Weapon implements WeaponInterface {

    private Capitalism plugin;

    public J7(Player player, Capitalism plugin) {
        super(7, 10, 600, 10, "J-7", player, 1, 120.00d, plugin);
    }

    @Override
    public void shoot() {
        //player.launchProjectile(Arrow.class, player.getLocation().getDirection().multiply(2));
        if(((System.currentTimeMillis() - lastShot) > fireRate) && ammo > 0){
            lastShot = System.currentTimeMillis();
            player.launchProjectile(Arrow.class, player.getLocation().getDirection().multiply(2));
            ammo--;
            player.sendMessage("§e§l" + ammo + "/" + maxAmmo);
        }
        if(ammo == 0){
            reload();
        }
    }

    @Override
    public void reload() {
        new BukkitRunnable() {
            @Override
            public void run() {
                ammo = maxAmmo;
            }

        }.runTaskLater(plugin, reloadTime);
    }

    @Override
    public void aim() {

    }
}

package org.capitalism.Prospectors;

import org.bukkit.entity.Player;
import org.capitalism.Capitalism;
import org.capitalism.Weapons.Sniper.Eagle50;
import org.capitalism.Weapons.WeaponInterface;


public class Prospector {
    private String name;
    private Player player;
    private WeaponInterface slot1;
    private int health = 100;
    private boolean isDead = false;
    private Capitalism plugin;

    public Prospector(Player player, Capitalism plugin){
        this.name = player.getName();
        this.player = player.getPlayer();
        this.plugin = plugin;
        this.slot1 = new Eagle50(player, plugin);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getPlayer() {
        return player;
    }

    public void setUuid(Player player) {
        this.player = player;
    }

    public WeaponInterface getSlot1() {
        return slot1;
    }

    public void setSlot1(WeaponInterface slot1) {
        this.slot1 = slot1;
    }

    public int getHealth() {
        return health;
    }

    public void takeDamage(int damage){
        if(getHealth() - damage <= 0 && !isDead){
            this.health = 0;
            player.sendMessage("You have died");
            isDead = true;
            player.setGameMode(org.bukkit.GameMode.SPECTATOR);
        }else {
            this.health = getHealth() - damage;
        }

    }
}

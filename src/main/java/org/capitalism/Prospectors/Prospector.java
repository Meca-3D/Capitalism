package org.capitalism.Prospectors;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.capitalism.Capitalism;
import org.capitalism.Weapons.Sniper.Eagle50;
import org.capitalism.Weapons.WeaponInterface;
import org.capitalism.Missions.Mission;
import java.util.ArrayList;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;


public class Prospector {
    private String name;
    private Player player;
    private WeaponInterface slot1;
    private int health = 100;
    private boolean isDead = false;
    private Capitalism plugin;
    private ArrayList<Mission> missions;
    private int money;
    private static final int MAX_DISTANCE = 3;

    public Prospector(Player player, Capitalism plugin){
        this.name = player.getName();
        this.player = player.getPlayer();
        this.plugin = plugin;
        this.slot1 = new Eagle50(player, plugin);
        this.missions = new ArrayList<>();
        this.money = 0;
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int amount) {
        this.money += amount;
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

    public void addMission(Mission mission) {
        this.missions.add(mission);
    }

    public ArrayList<Mission> getMissions() {
        return missions;
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

    public Entity getTargetEntity() {
        Location eyeLocation = player.getEyeLocation();
        Vector direction = eyeLocation.getDirection().normalize();
        for (int i = 0; i < MAX_DISTANCE; i++) {
            Location checkLocation = eyeLocation.add(direction);
            for (Entity entity : player.getWorld().getNearbyEntities(checkLocation, 0.3, 0.3, 0.3)) {
                if (entity != player) {
                    return entity;
                }
            }
        }
        return null;
    }



}

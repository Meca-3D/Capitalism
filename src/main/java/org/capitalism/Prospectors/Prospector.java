package org.capitalism.Prospectors;

import org.bukkit.entity.Player;
import org.capitalism.Weapons.Sniper.Eagle50;
import org.capitalism.Weapons.WeaponInterface;

import java.util.UUID;

public class Prospector {
    private String name;
    private UUID uuid;
    private WeaponInterface slot1;

    public Prospector(Player player){
        this.name = player.getName();
        this.uuid = player.getUniqueId();
        this.slot1 = new Eagle50(player);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public WeaponInterface getSlot1() {
        return slot1;
    }

    public void setSlot1(WeaponInterface slot1) {
        this.slot1 = slot1;
    }
}

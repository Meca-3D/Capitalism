package org.capitalism.Mission;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.capitalism.Capitalism;
import org.capitalism.Mob.GRABDOULFOU;
import org.capitalism.Prospectors.Prospector;
import java.util.Objects;

public class AreaMission extends Mission {

    private Location location1;
    private Location location2;
    private Entity target;

    public AreaMission(Capitalism plugin, String name, int timer, Location location, int profit, int level, String state, Prospector prospector, Location location1, Location location2) {
        super(plugin, name, timer, location, profit, level, state, prospector);
        this.location1 = location1;
        this.location2 = location2;

        this.target = prospector.getPlayer().getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
    }

    @Override
    public boolean update() {
        if (isInArea(prospector, location1, location2)) {
            if (Objects.equals(state, "avaliable")) {
                state = "in progress";
            } else {
                progression += 0.5;
                if (progression == 100D) {
                    prospector.addMoney(this.profit);
                    return false;
                }
                if ((progression % 5) == 0) {
                    for (int i = 0; i < (Math.round(Math.random() * (50)) + 10); i++) {
                        World w = prospector.getPlayer().getLocation().getWorld();
                        double k = Math.round(Math.random() * (359)) + 1;
                        double x = location.getX() + Math.cos(Math.toRadians(k)) * 20;
                        double z = location.getZ() + Math.sin(Math.toRadians(k)) * 20;
                        Location loc = new Location(w, x, w.getHighestBlockYAt((int) x, (int) z), z);
                        //w.spawnEntity(loc, EntityType.ZOMBIE);
//                        Creature c = (Creature) w.spawnEntity(loc, EntityType.ZOMBIE);
//                        GRABDOUL abdel = new GRABDOUL("abdel",20,0, 0, true, c);
                        new GRABDOULFOU(plugin, target, prospector, loc);


                    }
                }
            }
        } else if (Objects.equals(state, "avaliable")) {
            updateTimer();
        }
        return true;
    }

    public boolean isInArea(Prospector player, Location location1, Location location2) {
        Location playerLocation = player.getPlayer().getLocation();

        double x1 = Math.min(location1.getX(), location2.getX());
        double y1 = Math.min(location1.getY(), location2.getY());
        double z1 = Math.min(location1.getZ(), location2.getZ());

        double x2 = Math.max(location1.getX(), location2.getX());
        double y2 = Math.max(location1.getY(), location2.getY());
        double z2 = Math.max(location1.getZ(), location2.getZ());

        double px = playerLocation.getX();
        double py = playerLocation.getY();
        double pz = playerLocation.getZ();

        return (px >= x1 && px <= x2) && (py >= y1 && py <= y2) && (pz >= z1 && pz <= z2);
    }

}

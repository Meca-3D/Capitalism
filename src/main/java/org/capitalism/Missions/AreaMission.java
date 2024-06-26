package org.capitalism.Missions;

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

    public AreaMission(Capitalism plugin, String name, int timer, Location location, int profit, int level, Prospector prospector, Location location1, Location location2) {
        super(plugin, name, timer, location, profit, level, prospector);
        this.location1 = location1;
        this.location2 = location2;

        this.target = prospector.getPlayer().getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        this.target.setVisibleByDefault(false);
        this.target.setInvulnerable(true);
    }

    @Override
    public boolean update() {
        if (currentTimer <= 0) {
            return false;
        }
        if (isInArea()) {
            if (Objects.equals(state, "available")) {
                state = "in progress";
            } else {
                progression += 1;
                if (progression >= 100D) {
                    //state = "finished";
                    prospector.addMoney(this.profit);
                    return false;
                }
                if ((progression % 20) == 0) {
                    for (int i = 0; i < (Math.round(Math.random() * (30)) + 10); i++) {
                        World w = prospector.getPlayer().getLocation().getWorld();
                        double k = Math.round(Math.random() * (359)) + 1;
                        double x = location.getX() + Math.cos(Math.toRadians(k)) * 20;
                        double z = location.getZ() + Math.sin(Math.toRadians(k)) * 20;
                        Location loc = new Location(w, x, w.getHighestBlockYAt((int) x, (int) z), z);

                        new GRABDOULFOU(plugin, target, prospector, loc);

                    }
                }
            }
        }
        if (!isInArea()) {
            updateTimer();
        }
        return true;
    }

    public boolean isInArea() {

        Location playerLocation = prospector.getPlayer().getLocation();

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

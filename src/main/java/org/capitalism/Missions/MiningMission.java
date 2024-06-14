package org.capitalism.Missions;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.capitalism.Capitalism;
import org.capitalism.Prospectors.Prospector;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class MiningMission extends Mission {

    private Location location1;
    private Location location2;
    private ArrayList<Location> blocksLocations;
    private ArrayList<Location> missionLocations;

    public MiningMission(Capitalism plugin, String name, int timer, Location location, int profit, int level, Prospector prospector) {

        super(plugin, name, timer, location, profit, level, prospector);
        this.missionLocations = new ArrayList<>(Arrays.asList(new Location(prospector.getPlayer().getWorld(), 77, 63, 149), new Location(prospector.getPlayer().getWorld(), 29, 63, 210)));
        Random random = new Random();
        this.location = this.missionLocations.get(random.nextInt(0, this.missionLocations.size()));
        this.location1 = this.location.clone();
        this.location1 = this.location1.add(new Vector(16, 150, 16));
        this.location2 = this.location.clone();
        this.location2 = this.location2.add(new Vector(-16, -150, -16));
        this.blocksLocations = new ArrayList<>();
        this.blocksLocations.add(this.location);
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
                for (Location loc : blocksLocations) {
                    if (loc.getBlock().getType() == Material.AIR) {
                        progression += 10;
                        loc.getBlock().setType(Material.STONE);
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                loc.getBlock().setType(Material.GOLD_ORE);
                            }

                        }.runTaskLater(plugin, 400L);
                    }
                }
                if (progression == 100D) {
                    //state = "finished";
                    prospector.addMoney(this.profit);
                    return false;
                }
            }
        }
        if (!isInArea()) {
            updateTimer();
        }
        Bukkit.broadcastMessage(location1.toString());
        Bukkit.broadcastMessage(location2.toString());
        Bukkit.broadcastMessage(isInArea() + "");
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

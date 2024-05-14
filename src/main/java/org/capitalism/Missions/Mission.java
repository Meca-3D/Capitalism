package org.capitalism.Missions;

import org.bukkit.Location;
import org.capitalism.Capitalism;
import org.capitalism.Prospectors.Prospector;

import java.util.HashMap;
import java.util.Map;

public class Mission {

    protected int timer;
    protected int currentTimer;
    protected String name;
    protected Location location;
    protected int profit;
    protected int level;
    protected String state;
    protected Prospector prospector;
    protected Double progression;

    protected Capitalism plugin;

    public Mission(Capitalism plugin, String name, int timer, Location location, int profit, int level, String state, Prospector prospector) {
        this.name = name;
        this.timer = timer;
        this.location = location;
        this.profit = profit;
        this.level = level;
        this.currentTimer = timer;
        this.state = state;
        this.prospector = prospector;
        this.progression = 0D;
        this.plugin = plugin;
    }

    private String getDirectionFromYaw(float yaw) {
        if (yaw < 0) {
            yaw += 360;
        }

        if ((yaw >= 337.5 && yaw <= 360) || (yaw >= 0 && yaw < 22.5)) {
            return "SUD";
        } else if (yaw >= 22.5 && yaw < 67.5) {
            return "SUD-OUEST";
        } else if (yaw >= 67.5 && yaw < 112.5) {
            return "OUEST";
        } else if (yaw >= 112.5 && yaw < 157.5) {
            return "NORD-OUEST";
        } else if (yaw >= 157.5 && yaw < 202.5) {
            return "NORD";
        } else if (yaw >= 202.5 && yaw < 247.5) {
            return "NORD-EST";
        } else if (yaw >= 247.5 && yaw < 292.5) {
            return "EST";
        } else if (yaw >= 292.5 && yaw < 337.5) {
            return "SUD-EST";
        }
        return null;
    }

    public String getDirection() {
        Location playerLocation = prospector.getPlayer().getLocation();
        Location targetLocation = location;

        double x = targetLocation.getX() - playerLocation.getX();
        double z = targetLocation.getZ() - playerLocation.getZ();

        double angle = Math.atan2(z, x);

        double yaw = Math.toDegrees(angle);//prospector.getPlayer().getLocation().getYaw();

        if (yaw < 0) {
            yaw += 360;
        }

        Map<String, String> FACING = new HashMap<>();

        FACING.put("SUD1", "↖");
        FACING.put("SUD2", "↑");
        FACING.put("SUD3", "↗");
        FACING.put("SUD4", "→");
        FACING.put("SUD5", "↘");
        FACING.put("SUD6", "↓");
        FACING.put("SUD7", "↙");
        FACING.put("SUD8", "←");

        FACING.put("SUD-EST1", "↑");
        FACING.put("SUD-EST2", "↗");
        FACING.put("SUD-EST3", "→");
        FACING.put("SUD-EST4", "↘");
        FACING.put("SUD-EST5", "↓");
        FACING.put("SUD-EST6", "↙");
        FACING.put("SUD-EST7", "←");
        FACING.put("SUD-EST8", "↖");

        FACING.put("SUD-OUEST1", "←");
        FACING.put("SUD-OUEST2", "↖");
        FACING.put("SUD-OUEST3", "↑");
        FACING.put("SUD-OUEST4", "↗");
        FACING.put("SUD-OUEST5", "→");
        FACING.put("SUD-OUEST6", "↘");
        FACING.put("SUD-OUEST7", "↓");
        FACING.put("SUD-OUEST8", "↙");

        FACING.put("NORD1", "↘");
        FACING.put("NORD2", "↓");
        FACING.put("NORD3", "↙");
        FACING.put("NORD4", "←");
        FACING.put("NORD5", "↖");
        FACING.put("NORD6", "↑");
        FACING.put("NORD7", "↗");
        FACING.put("NORD8", "→");

        FACING.put("NORD-EST1", "→");
        FACING.put("NORD-EST2", "↘");
        FACING.put("NORD-EST3", "↓");
        FACING.put("NORD-EST4", "↙");
        FACING.put("NORD-EST5", "←");
        FACING.put("NORD-EST6", "↖");
        FACING.put("NORD-EST7", "↑");
        FACING.put("NORD-EST8", "↗");

        FACING.put("NORD-OUEST1", "↓");
        FACING.put("NORD-OUEST2", "↙");
        FACING.put("NORD-OUEST3", "←");
        FACING.put("NORD-OUEST4", "↖");
        FACING.put("NORD-OUEST5", "↑");
        FACING.put("NORD-OUEST6", "↗");
        FACING.put("NORD-OUEST7", "→");
        FACING.put("NORD-OUEST8", "↘");

        FACING.put("OUEST1", "↙");
        FACING.put("OUEST2", "←");
        FACING.put("OUEST3", "↖");
        FACING.put("OUEST4", "↑");
        FACING.put("OUEST5", "↗");
        FACING.put("OUEST6", "→");
        FACING.put("OUEST7", "↘");
        FACING.put("OUEST8", "↓");

        FACING.put("EST1", "↗");
        FACING.put("EST2", "→");
        FACING.put("EST3", "↘");
        FACING.put("EST4", "↓");
        FACING.put("EST5", "↙");
        FACING.put("EST6", "←");
        FACING.put("EST7", "↖");
        FACING.put("EST8", "↑");

        String arrow = null;
        String facing = getDirectionFromYaw(prospector.getPlayer().getLocation().getYaw());

        if (yaw >= 22.5 && yaw < 67.5) {
            return FACING.get(facing + "1");
        } else if (yaw >= 67.5 && yaw < 112.5) {
            return FACING.get(facing + "2");
        } else if (yaw >= 112.5 && yaw < 157.5) {
            return FACING.get(facing + "3");
        } else if (yaw >= 157.5 && yaw < 202.5) {
            return FACING.get(facing + "4");
        } else if (yaw >= 202.5 && yaw < 247.5) {
            return FACING.get(facing + "5");
        } else if (yaw >= 247.5 && yaw < 292.5) {
            return FACING.get(facing + "6");
        } else if (yaw >= 292.5 && yaw < 337.5) {
            return FACING.get(facing + "7");
        } else {
            return FACING.get(facing + "8");
        }
    }

    public long getDistance() {
        return  Math.round(Math.sqrt(Math.pow((location.getX() - prospector.getPlayer().getLocation().getX()), 2) + Math.pow((location.getZ() - prospector.getPlayer().getLocation().getZ()), 2)) );
    }


    public Prospector getProspector() {
        return prospector;
    }

    public Double getProgression() {
        return progression;
    }

    public boolean update() {
        return false;
    }

    public void updateTimer() {
        currentTimer -= 1;
        location.setY(prospector.getPlayer().getLocation().getY());
    }

    public String getState() {
        return state;
    }

    public int getTimer() {
        return timer;
    }

    public int getCurrentTimer() {
        return currentTimer;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public int getProfit() {
        return profit;
    }

    public int getLevel() {
        return level;
    }
}

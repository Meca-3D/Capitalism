package org.capitalism.Arenas;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.capitalism.Capitalism;
import org.capitalism.Missions.AreaMission;
import org.capitalism.Missions.MiningMission;
import org.capitalism.Missions.Mission;
import org.capitalism.Prospectors.Prospector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Arena {
    
    private ArrayList<Prospector> prospectorsList;
    private int id;
    private int gameDuration;
    private boolean isRunning = false;
    private Capitalism capitalism;
    private ArrayList<ArrayList<Double>> spawnCoordinates;

    public Arena(int time, int id,Capitalism capitalism) {
       this.gameDuration = time;
       this.id = id;
       this.prospectorsList = new ArrayList<>();
       this.capitalism = capitalism;
       this.spawnCoordinates = new ArrayList<>();
       this.spawnCoordinates.add(new ArrayList<>(Arrays.asList(63.5, 64d, 121.5)));
       this.spawnCoordinates.add(new ArrayList<>(Arrays.asList(67.5, 64d, 121.5)));
       this.spawnCoordinates.add(new ArrayList<>(Arrays.asList(71.5, 64d, 121.5)));
    }

    public void join(Prospector prospector) {
        this.prospectorsList.add(prospector);
    }

    public void leave(Prospector prospector) {
        this.prospectorsList.remove(prospector);
    }

    public void start() {
        if(!isRunning) {
            isRunning = true;
            placeBlocks(Material.BARRIER);
            teleportPlayer();
            timerBeforeStart();
            missionManager();
        }
    }

    public int getGameDuration() {
        return gameDuration;
    }

    public ArrayList<Prospector> getProspectorsList() {
        return prospectorsList;
    }

    public void timer(){
        new BukkitRunnable(){
            @Override
            public void run() {
                 if (gameDuration == 360) {
                    Bukkit.getWorld("world").setStorm(true);
                } else if (gameDuration == 240) {
                    Bukkit.getWorld("world").setThundering(true);
                } else if (gameDuration == 201) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.playSound(p.getLocation(), "cptlm:endgame",1,1);
                    } 
                }
                if(gameDuration > 0){
                    gameDuration--;
                    for(Prospector prospector : prospectorsList){
                        prospector.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("Time left " + (gameDuration/60) + " " + (gameDuration%60)));
                    }
                } else {
                    isRunning = false;
                    cancel();
                }
            }
        }.runTaskTimer(capitalism, 0, 20);
    }

    public void teleportPlayer() {
        for (int i = 0; i < prospectorsList.size(); i++) {
            Location pos = new Location(prospectorsList.get(i).getPlayer().getWorld() ,spawnCoordinates.get(i).get(0),spawnCoordinates.get(i).get(1),spawnCoordinates.get(i).get(2));
            prospectorsList.get(i).getPlayer().teleport(pos);
        }
    }

    public void timerBeforeStart() {
        new BukkitRunnable(){
            int i = 3;
            @Override
            public void run() {
                if (i == 0) {
                    for (Prospector prospector : prospectorsList) {
                        prospector.getPlayer().sendTitle(ChatColor.AQUA + "§lSTART", "", 8, 20, 8);
                        prospector.getPlayer().playSound(prospector.getPlayer(), Sound.ENTITY_ENDER_DRAGON_AMBIENT, 10, 1);

                    }
                    placeBlocks(Material.AIR);
                    timer();

                    cancel();
                    return ;
                }
                for (Prospector prospector : prospectorsList) {
                    prospector.getPlayer().sendTitle(ChatColor.AQUA + String.valueOf(i), "", 8, 20, 0);
                    prospector.getPlayer().playSound(prospector.getPlayer(), Sound.BLOCK_NOTE_BLOCK_PLING, 10, 1);
                }
                i--;
            }
        }.runTaskTimer(capitalism, 0, 20);

    }

    public void placeBlocks(Material material) {
        for (int i = 0; i < prospectorsList.size(); i++) {
            new Location(prospectorsList.get(i).getPlayer().getWorld() ,Math.floor(spawnCoordinates.get(i).get(0)),Math.floor(spawnCoordinates.get(i).get(1)),Math.floor(spawnCoordinates.get(i).get(2))).add(new Vector(1, 0, 0)).getBlock().setType(material);
            new Location(prospectorsList.get(i).getPlayer().getWorld() ,Math.floor(spawnCoordinates.get(i).get(0)),Math.floor(spawnCoordinates.get(i).get(1)),Math.floor(spawnCoordinates.get(i).get(2))).add(new Vector(-1, 0, 0)).getBlock().setType(material);
            new Location(prospectorsList.get(i).getPlayer().getWorld() ,Math.floor(spawnCoordinates.get(i).get(0)),Math.floor(spawnCoordinates.get(i).get(1)),Math.floor(spawnCoordinates.get(i).get(2))).add(new Vector(0, 0, 1)).getBlock().setType(material);
            new Location(prospectorsList.get(i).getPlayer().getWorld() ,Math.floor(spawnCoordinates.get(i).get(0)),Math.floor(spawnCoordinates.get(i).get(1)),Math.floor(spawnCoordinates.get(i).get(2))).add(new Vector(0, 0, -1)).getBlock().setType(material);

            new Location(prospectorsList.get(i).getPlayer().getWorld() ,Math.floor(spawnCoordinates.get(i).get(0)),Math.floor(spawnCoordinates.get(i).get(1)),Math.floor(spawnCoordinates.get(i).get(2))).add(new Vector(1, 1, 0)).getBlock().setType(material);
            new Location(prospectorsList.get(i).getPlayer().getWorld() ,Math.floor(spawnCoordinates.get(i).get(0)),Math.floor(spawnCoordinates.get(i).get(1)),Math.floor(spawnCoordinates.get(i).get(2))).add(new Vector(-1, 1, 0)).getBlock().setType(material);
            new Location(prospectorsList.get(i).getPlayer().getWorld() ,Math.floor(spawnCoordinates.get(i).get(0)),Math.floor(spawnCoordinates.get(i).get(1)),Math.floor(spawnCoordinates.get(i).get(2))).add(new Vector(0, 1, 1)).getBlock().setType(material);
            new Location(prospectorsList.get(i).getPlayer().getWorld() ,Math.floor(spawnCoordinates.get(i).get(0)),Math.floor(spawnCoordinates.get(i).get(1)),Math.floor(spawnCoordinates.get(i).get(2))).add(new Vector(0, 1, -1)).getBlock().setType(material);

            new Location(prospectorsList.get(i).getPlayer().getWorld() ,Math.floor(spawnCoordinates.get(i).get(0)),Math.floor(spawnCoordinates.get(i).get(1)),Math.floor(spawnCoordinates.get(i).get(2))).add(new Vector(0, -1, 0)).getBlock().setType(material);
            new Location(prospectorsList.get(i).getPlayer().getWorld() ,Math.floor(spawnCoordinates.get(i).get(0)),Math.floor(spawnCoordinates.get(i).get(1)),Math.floor(spawnCoordinates.get(i).get(2))).add(new Vector(0, 2, 0)).getBlock().setType(material);
        }
    }

    public void missionManager() {

        Bukkit.getScheduler().runTaskTimer(capitalism, () -> {
            for (Prospector prospector : prospectorsList) {
                capitalism.updateHUD(prospector);
            }
        }, 0L, 2L);

        Bukkit.getScheduler().runTaskTimer(capitalism, () -> {
            for (Prospector prospector : prospectorsList) {
                for (int i = 0; i < prospector.getMissions().size(); i++) {
                    Mission mission = prospector.getMissions().get(i);
                    if (!mission.update()) {
                        mission = null;
                        prospector.getMissions().remove(i);
                        if (prospector.getMissions().size() < 2) {
                            addRandomMission(prospector);
                        }
                    }
                }
            }
        }, 0L, 20L);

        Bukkit.getScheduler().runTaskTimer(capitalism, () -> {
            for (Prospector prospector : prospectorsList) {
                if (prospector.getMissions().size() < 4) {
                    addRandomMission(prospector);
                }
            }
        }, 0L, 3000L);

    }

    public void addRandomMission(Prospector prospector) {
        Random random = new Random();
        int missionID = random.nextInt(0, 2);
        switch (missionID) {
            case 0:
                prospector.addMission(new AreaMission(capitalism, "Gold Farmer", 30, new Location(prospector.getPlayer().getWorld(), 0, 72, 0), 35, 1, prospector, new Location(prospector.getPlayer().getWorld(), 50, 150, 50), new Location(prospector.getPlayer().getWorld(), -50, -150, -50)));
            case 1:
                prospector.addMission(new MiningMission(capitalism, "Mining", 30, null, 35, 1, prospector));
        }
    }

    public int getId() {
        return id;
    }
}

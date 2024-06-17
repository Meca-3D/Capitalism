package org.capitalism.Arenas;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
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
    private int maxTime;
    private boolean isRunning = false;
    private Capitalism capitalism;
    private ArrayList<ArrayList<Double>> spawnCoordinates;

    private ArrayList<Location> miningMissionLocations;

    private ArrayList<Location> areaMissionLocations;

    private Interaction endInteraction;
    private ItemDisplay endShipModel;

    public Arena(int time, int id,Capitalism capitalism) {
       this.maxTime = time;
       this.gameDuration = maxTime;
       this.id = id;
       this.prospectorsList = new ArrayList<>();
       this.capitalism = capitalism;
       this.spawnCoordinates = new ArrayList<>();
       this.spawnCoordinates.add(new ArrayList<>(Arrays.asList(48.5, 72d, 212.5)));
       this.spawnCoordinates.add(new ArrayList<>(Arrays.asList(-103.5, 74d, 181.5)));
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
            gameDuration = maxTime;
            miningMissionLocations = new ArrayList<>(Arrays.asList(new Location(prospectorsList.get(0).getPlayer().getWorld(), 45, 67, 113).clone(), new Location(prospectorsList.get(0).getPlayer().getWorld(), 96, 70, 60).clone()));
            areaMissionLocations = new ArrayList<>(Arrays.asList(new Location(prospectorsList.get(0).getPlayer().getWorld(), -92, 74, 147).clone()));
            isRunning = true;
            placeBlocks(Material.BARRIER);
            teleportPlayer();
            timerBeforeStart();
            missionManager();
        }
    }

    public void end(Prospector winner) {
        for (Prospector prospector : prospectorsList) {
            prospector.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 70, 4, true));
            prospector.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 70, 1, true));
            gameDuration = 0;
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Prospector prospector : prospectorsList) {
                    prospector.getPlayer().teleport(new Location(prospectorsList.get(0).getPlayer().getWorld(), 0, 120, 0));
                }
            }
        }.runTaskLater(capitalism,50L);

        prospectorsList.remove(winner);
        new BukkitRunnable() {
            @Override
            public void run() {
                winner.getPlayer().sendTitle(ChatColor.AQUA + "§lWIN", "", 8, 20, 8);
                winner.getPlayer().playSound(winner.getPlayer(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 10, 1);
                for (Prospector prospector : prospectorsList) {
                    prospector.getPlayer().sendTitle(ChatColor.RED + "§lLOSE", "", 8, 20, 8);
                    prospector.getPlayer().playSound(prospector.getPlayer(), Sound.ENTITY_ENDER_DRAGON_DEATH, 10, 1);
                }
            }
        }.runTaskLater(capitalism,80L);

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
                }else if (gameDuration == 150) {
                     endInteraction = (Interaction) prospectorsList.get(0).getPlayer().getWorld().spawnEntity(new Location(prospectorsList.get(0).getPlayer().getWorld(), 0, 80, 0), EntityType.INTERACTION);
                     endInteraction.setInteractionWidth(0.5f);
                     endInteraction.setInteractionHeight(0.5f);
                 }
                if(gameDuration > 0){
                    gameDuration--;
                    for(Prospector prospector : prospectorsList){
                        prospector.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("Time left " + (gameDuration/60) + " : " + (gameDuration%60)));
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
                        if (mission instanceof MiningMission) {
                            miningMissionLocations.add(mission.getLocation().clone());
                        }else if (mission instanceof AreaMission) {
                            areaMissionLocations.add(mission.getLocation().clone());
                        }
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
        Location location;
        int index;
        switch (missionID) {
            case 0:
                if (areaMissionLocations.isEmpty()) {
                    return;
                }
                index = random.nextInt(0, areaMissionLocations.size());
                location = areaMissionLocations.get(index).clone();
                areaMissionLocations.remove(index);
                prospector.addMission(new AreaMission(capitalism, "Gold Farmer", 30, location, 35, 1, prospector, location.clone().add(new Vector(50, 150, 50)), location.clone().add(new Vector(-50, -150, -50))));
            case 1:
                if (miningMissionLocations.isEmpty()) {
                    return;
                }
                index = random.nextInt(0, miningMissionLocations.size());
                location = miningMissionLocations.get(index).clone();
                miningMissionLocations.remove(index);
                prospector.addMission(new MiningMission(capitalism, "Mining", 30, location, 35, 1, prospector));
        }
    }

    public int getId() {
        return id;
    }

    public Interaction getEndInteraction() {
        return endInteraction;
    }
}

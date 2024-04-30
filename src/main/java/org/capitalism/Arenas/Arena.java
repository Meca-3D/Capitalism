package org.capitalism.Arenas;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.capitalism.Capitalism;
import org.capitalism.Prospectors.Prospector;

import java.util.ArrayList;

public class Arena {
    
    private ArrayList<Prospector> prospectorsList;
    private int id;
    private int gameDuration;
    private boolean isRunning = false;
    private Capitalism capitalism;

    public Arena(int time, int id,Capitalism capitalism) {
       this.gameDuration = time;
       this.id = id;
       this.prospectorsList = new ArrayList<>();
       this.capitalism = capitalism;
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
            timer();
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
                    Bukkit.getworld("world").setThunder(true);
                } else if (gameduration == 201) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.playSound(p.getlocation, "cptlm:endgame",1,1);
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

    public int getId() {
        return id;
    }
}

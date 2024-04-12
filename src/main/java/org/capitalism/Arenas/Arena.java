package org.capitalism.Arenas;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.scheduler.BukkitRunnable;
import org.capitalism.Prospectors.Prospector;

import java.util.ArrayList;

public class Arena {
    
    private ArrayList<Prospector> prospectorsList;
    private int id;
    private int gameDuration;
    private boolean isRunning = false;

    public Arena(int time, int id) {
       this.gameDuration = time;
       this.id = id;
       this.prospectorsList = new ArrayList<>();
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
                if(gameDuration > 0){
                    gameDuration--;
                    for(Prospector prospector : prospectorsList){
                        prospector.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("Time left: " + gameDuration));
                    }
                } else {
                    isRunning = false;
                    cancel();
                }
            }
        };
    }

    public int getId() {
        return id;
    }
}

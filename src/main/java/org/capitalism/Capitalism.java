package org.capitalism;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import org.bukkit.Bukkit;

import org.capitalism.Arenas.Arena;
import org.capitalism.Arenas.AreneManager;
import org.capitalism.Command.CommandClass;
import org.capitalism.Prospectors.Prospector;
import org.capitalism.mission.Mission;

public final class Capitalism extends JavaPlugin {
    private AreneManager areneManager;
    private ListenerClass listenerClass;

    @Override
    public void onEnable() {
        listenerClass = new ListenerClass();
        areneManager = new AreneManager(this);
        areneManager.addArena(new Arena(1200, 0,this));
        CommandClass commands = new CommandClass(areneManager, listenerClass);

        getCommand("join").setExecutor(commands);
        getCommand("start").setExecutor(commands);
        getServer().getPluginManager().registerEvents(listenerClass, this);

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (Prospector player : listenerClass.getProspectors()) {
                updateHUD(player);
            }
        }, 0L, 20L);
    }

    @Override
    public void onDisable() {
        System.out.println("Capitalism plugin disabled");
    }

    public void updateHUD(Prospector player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("hud", "dummy", ChatColor.WHITE + "MISSIONS");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        objective.getScore(ChatColor.WHITE + "Money : " + player.getMoney()).setScore(1);

        for (Mission mission : player.getMissions()) {
            //player.getPlayer().sendMessage(mission.getName() + " : " + mission.getState() + " | " + mission.update());
            if (!mission.update()) {
                player.getMissions().remove(mission);
                mission = null;
            }
            ChatColor color = ChatColor.GREEN;
            if (mission.getCurrentTimer() <= (mission.getTimer()/3))
                color = ChatColor.RED;
            else if (mission.getCurrentTimer() <= ((mission.getTimer()/3)*2))
                color = ChatColor.YELLOW;

            if (mission.getProgression() == 0D)
                objective.getScore(color + mission.getName() + " | " + mission.getDirection() + " " + mission.getDistance() + " | " + mission.getCurrentTimer() ).setScore(0);
            else
                objective.getScore(color + mission.getName() + " | " + mission.getProgression() + "% | " + mission.getCurrentTimer() ).setScore(0);
        }

        player.getPlayer().setScoreboard(scoreboard);



    }


}

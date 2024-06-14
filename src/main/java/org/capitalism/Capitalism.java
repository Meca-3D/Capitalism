package org.capitalism;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import org.bukkit.Bukkit;

import org.capitalism.Arenas.Arena;
import org.capitalism.Arenas.AreneManager;
import org.capitalism.ItemManager.LootManager;
import org.capitalism.Command.CommandClass;
import org.capitalism.Missions.AreaMission;
import org.capitalism.Missions.MiningMission;
import org.capitalism.Prospectors.Prospector;
import org.capitalism.Missions.Mission;

public final class Capitalism extends JavaPlugin {
    private AreneManager areneManager;
    private ListenerClass listenerClass;
    private LootManager lootManager;

    @Override
    public void onEnable() {
        lootManager = new LootManager(null, this);
        listenerClass = new ListenerClass(lootManager, this);
        lootManager.setListenerClass(listenerClass);
        areneManager = new AreneManager(this);
        areneManager.addArena(new Arena(1200, 0,this));
        CommandClass commands = new CommandClass(areneManager, listenerClass, this, lootManager);

        getCommand("join").setExecutor(commands);
        getCommand("start").setExecutor(commands);
        getCommand("shop").setExecutor(commands);
        getCommand("chest").setExecutor(commands);

        getServer().getPluginManager().registerEvents(listenerClass, this);

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


            ChatColor color = ChatColor.WHITE;
            if (mission instanceof AreaMission ) {
                if (((AreaMission) mission).isInArea())
                    objective.getScore(color + mission.getName() + " | " + mission.getProgression() + "% " ).setScore(0);
                else {
                    color = ChatColor.GREEN;
                    if (mission.getCurrentTimer() <= (mission.getTimer()/3))
                        color = ChatColor.RED;
                    else if (mission.getCurrentTimer() <= ((mission.getTimer()/3)*2))
                        color = ChatColor.YELLOW;
                    objective.getScore(color + mission.getName() + " | " + mission.getDirection() + " " + mission.getDistance() + " | " + mission.getCurrentTimer()).setScore(0);

                }
            }
            if (mission instanceof MiningMission) {
                if (((MiningMission) mission).isInArea())
                    objective.getScore(color + mission.getName() + " | " + mission.getProgression() + "% " ).setScore(0);
                else {
                    color = ChatColor.GREEN;
                    if (mission.getCurrentTimer() <= (mission.getTimer()/3))
                        color = ChatColor.RED;
                    else if (mission.getCurrentTimer() <= ((mission.getTimer()/3)*2))
                        color = ChatColor.YELLOW;
                    objective.getScore(color + mission.getName() + " | " + mission.getDirection() + " " + mission.getDistance() + " | " + mission.getCurrentTimer()).setScore(0);

                }
            }
        }

        player.getPlayer().setScoreboard(scoreboard);



    }



    public ListenerClass getListenerClass() {
        return listenerClass;
    }
}

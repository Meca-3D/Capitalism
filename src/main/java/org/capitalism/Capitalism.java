package org.capitalism;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.capitalism.Arenas.Arena;
import org.capitalism.Arenas.AreneManager;
import org.capitalism.Command.CommandClass;

public final class Capitalism extends JavaPlugin {

    private Arena arena;
    private AreneManager areneManager;

    @Override
    public void onEnable() {
        CommandClass commands = new CommandClass();

        getCommand("join").setExecutor(commands);
        getCommand("start").setExecutor(commands);
        getCommand("tir").setExecutor(new TirCommand());

        areneManager.addArena(new Arena(1200, 0));
        for(Player p : Bukkit.getOnlinePlayers()) {


            getServer().getPluginManager().registerEvents(new ListenerClass(),this);
        }
    }

    @Override
    public void onDisable() {
        System.out.println("Capitalism plugin disabled");
    }
}

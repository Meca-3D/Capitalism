package org.capitalism;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.capitalism.Arenas.Arena;
import org.capitalism.Arenas.AreneManager;
import org.capitalism.Command.CommandClass;

public final class Capitalism extends JavaPlugin {
    private AreneManager areneManager;
    private ListenerClass listenerClass;

    @Override
    public void onEnable() {
        listenerClass = new ListenerClass();
        areneManager = new AreneManager(this);
        areneManager.addArena(new Arena(1200, 0));
        CommandClass commands = new CommandClass(areneManager, listenerClass);

        getCommand("join").setExecutor(commands);
        getCommand("start").setExecutor(commands);
        getCommand("tir").setExecutor(new TirCommand());


        for(Player p : Bukkit.getOnlinePlayers()) {
            getServer().getPluginManager().registerEvents(listenerClass, this);
        }
    }

    @Override
    public void onDisable() {
        System.out.println("Capitalism plugin disabled");
    }
}

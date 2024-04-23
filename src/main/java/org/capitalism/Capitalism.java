package org.capitalism;

import org.bukkit.plugin.java.JavaPlugin;
import org.capitalism.Arenas.Arena;
import org.capitalism.Arenas.AreneManager;
import org.capitalism.Command.CommandClass;

public final class Capitalism extends JavaPlugin {
    private AreneManager areneManager;
    private ListenerClass listenerClass;

    @Override
    public void onEnable() {
        listenerClass = new ListenerClass(this);
        areneManager = new AreneManager(this);
        areneManager.addArena(new Arena(1200, 0,this));
        CommandClass commands = new CommandClass(areneManager, listenerClass);

        getCommand("join").setExecutor(commands);
        getCommand("start").setExecutor(commands);
        getServer().getPluginManager().registerEvents(listenerClass, this);
    }

    @Override
    public void onDisable() {
        System.out.println("Capitalism plugin disabled");
    }

    public ListenerClass getListenerClass() {
        return listenerClass;
    }
}

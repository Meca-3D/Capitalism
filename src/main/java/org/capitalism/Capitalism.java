package org.capitalism;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Capitalism extends JavaPlugin {

    @Override
    public void onEnable() {
        for(Player p : Bukkit.getOnlinePlayers()) {
            getCommand("tir").setExecutor(new TirCommand());
            getServer().getPluginManager().registerEvents(new ListenerClass(),this);
        }
    }

    @Override
    public void onDisable() {
        System.out.println("Capitalism plugin disabled");
    }
}

package org.capitalism.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.capitalism.Arenas.Arena;
import org.capitalism.Arenas.AreneManager;
import org.capitalism.ListenerClass;

public class CommandClass implements CommandExecutor {

    private Arena arena;
    private AreneManager areneManager;
    private ListenerClass listenerClass;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("UR NOT A PLAYER");
            return false;
        }

        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("join")) {
            areneManager.getActualGame().join(listenerClass.getProspector(player));
            player.sendMessage("§e§l(!) §eYou Just Join the Arena " + areneManager.getActualGame().getId());
        }

        if (cmd.getName().equalsIgnoreCase("start")) {
            areneManager.getActualGame().start();
            player.sendMessage("§e§l(!) §eYou Just Start the Game in the Arena " + areneManager.getActualGame().getId());
        }



        return true;
    }

}

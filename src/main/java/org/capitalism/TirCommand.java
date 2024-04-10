package org.capitalism;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;

public class TirCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            player.sendMessage("PAN");
            player.launchProjectile(Arrow.class, player.getLocation().getDirection().multiply(2));

        }
        return false;
    }
}

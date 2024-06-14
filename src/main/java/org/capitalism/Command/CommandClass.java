package org.capitalism.Command;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Interaction;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;
import org.capitalism.Arenas.AreneManager;
import org.capitalism.Capitalism;
import org.capitalism.ItemManager.LootChest;
import org.capitalism.ItemManager.LootManager;
import org.capitalism.ItemManager.ShopDisplay;
import org.capitalism.ListenerClass;

public class CommandClass implements CommandExecutor {

    private final AreneManager areneManager;
    private final ListenerClass listenerClass;
    private final LootManager lootManager;

    private final Capitalism plugin;
    public CommandClass(AreneManager areneManager, ListenerClass listenerClass, Capitalism plugin, LootManager lootManager){
        this.listenerClass = listenerClass;
        this.lootManager = lootManager;
        this.areneManager = areneManager;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§c§lUR NOT A PLAYER");
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

        if (cmd.getName().equalsIgnoreCase("shop")) {

            Location location = player.getLocation();

            // Shop

            ItemDisplay chestModel = (ItemDisplay) player.getWorld().spawnEntity(location.add(new Vector(0,0.5,0)), EntityType.ITEM_DISPLAY);
            ItemStack chest = new ItemStack(Material.PAPER);
            ItemMeta chestMeta = chest.getItemMeta();
            chestMeta.setCustomModelData(4);
            chest.setItemMeta(chestMeta);
            chestModel.setItemStack(chest);
            chestModel.setDisplayWidth(2);

            lootManager.add(new ShopDisplay(chestModel, location, plugin));

            player.sendMessage("§e§l(!) §eYou Just Spawn Something");
        }

        if (cmd.getName().equalsIgnoreCase("chest")) {

            Location location = player.getLocation();

            // Chest

            Interaction interaction1 = (Interaction) player.getWorld().spawnEntity(location.add(new Vector(-1.5, 0, 0)), EntityType.INTERACTION);
            interaction1.setInteractionHeight(1);
            interaction1.setInteractionWidth(3);

            ItemDisplay chestModel = (ItemDisplay) player.getWorld().spawnEntity(location.add(new Vector(0,0.5,0)), EntityType.ITEM_DISPLAY);
            ItemStack chest = new ItemStack(Material.PAPER);
            ItemMeta chestMeta = chest.getItemMeta();
            chestMeta.setCustomModelData(1);
            chest.setItemMeta(chestMeta);
            chestModel.setItemStack(chest);
            chestModel.setDisplayWidth(2);

            ItemDisplay rightChestDoorModel = (ItemDisplay) player.getWorld().spawnEntity(chestModel.getLocation().add(new Vector(-0.62, 0.812, 0)), EntityType.ITEM_DISPLAY);
            ItemStack rightChestDoor = new ItemStack(Material.PAPER);
            ItemMeta rightDoorMeta = rightChestDoor.getItemMeta();
            rightDoorMeta.setCustomModelData(3);
            rightChestDoor.setItemMeta(rightDoorMeta);
            rightChestDoorModel.setItemStack(rightChestDoor);
            rightChestDoorModel.setDisplayWidth(2);

            ItemDisplay leftChestDoorModel = (ItemDisplay) player.getWorld().spawnEntity(chestModel.getLocation().add(new Vector(0.62, 0.812, 0)), EntityType.ITEM_DISPLAY);
            ItemStack leftChestDoor = new ItemStack(Material.PAPER);
            ItemMeta leftDoorMeta = leftChestDoor.getItemMeta();
            leftDoorMeta.setCustomModelData(2);
            leftChestDoor.setItemMeta(leftDoorMeta);
            leftChestDoorModel.setItemStack(leftChestDoor);
            leftChestDoorModel.setDisplayWidth(2);

            lootManager.add(new LootChest(interaction1, chestModel, leftChestDoorModel, rightChestDoorModel, location, plugin));

        }



        return true;
    }

}

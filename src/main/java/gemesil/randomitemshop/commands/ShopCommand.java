package gemesil.randomitemshop.commands;

import gemesil.randomitemshop.ChatLogger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShopCommand extends BaseCommand {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!isPlayer(sender)) {
            ChatLogger.sendNoPermsMsg((Player) sender);
            return true;
        }
        else
            ChatLogger.sendAlert("You can't use this command from the console. Run it from in-game!");

        // grab todays items and display shop gui
        // check if player has the item's cost in inventory
        // deduct it from them if they click on an item
        // put the new item into the player's inventory

        return false;
    }
}

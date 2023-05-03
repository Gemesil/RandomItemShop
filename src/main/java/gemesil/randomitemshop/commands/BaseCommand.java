package gemesil.randomitemshop.commands;

import gemesil.randomitemshop.ChatLogger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BaseCommand implements CommandExecutor {

//
//    ChatLogger.sendNoPermsMsg((Player) player);

    public boolean isPlayer(CommandSender player) {
        return player instanceof Player;
    }

    public boolean hasPerms(CommandSender player, String perm) {
        return player.hasPermission(perm);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }
}

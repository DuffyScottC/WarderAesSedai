package me.braekpo1nt.warderaessedai.commands.subcommands;

import me.braekpo1nt.warderaessedai.Main;
import me.braekpo1nt.warderaessedai.commands.interfaces.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * A temporary debug command used for testing Aes Sedai functionality
 */
public class AddWarderSubCommand implements SubCommand {

    private final Main plugin;
    
    public AddWarderSubCommand(Main plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage("Please provide a player names: /addwarder <warder>");
            return false;
        }
        
        Player warder = Bukkit.getServer().getPlayerExact(args[0]);
        if (warder == null) {
            sender.sendMessage("Can't make player \""+ args[0] +"\" a warder, they are not online");
            return false;
        }
        
        plugin.setWarder(warder);
        
        sender.sendMessage(args[0] + " is now a warder.");
        
        return true;
    }
    
}

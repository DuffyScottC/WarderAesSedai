package me.braekpo1nt.warderaessedai.commands.subcommands;

import me.braekpo1nt.warderaessedai.Main;
import me.braekpo1nt.warderaessedai.commands.interfaces.SubCommand;
import me.braekpo1nt.warderaessedai.compass.CompassBossBar;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * A temporary debug command used for testing Aes Sedai functionality
 */
public class AddWarderSubCommand implements SubCommand {
    
    private Main plugin;
    private CompassBossBar bar;
    
    public AddWarderSubCommand(Main plugin) {
        bar = new CompassBossBar(plugin);
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
        bar.setPlayer(warder);
        bar.setTargetLocation(new Location(warder.getWorld(), 48, 69, -309));
        
        sender.sendMessage(args[0] + " is now a warder.");
        
        return true;
    }
    
}

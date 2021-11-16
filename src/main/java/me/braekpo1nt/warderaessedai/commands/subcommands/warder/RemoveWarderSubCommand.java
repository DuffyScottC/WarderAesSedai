package me.braekpo1nt.warderaessedai.commands.subcommands.warder;

import me.braekpo1nt.warderaessedai.Main;
import me.braekpo1nt.warderaessedai.commands.interfaces.SubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class RemoveWarderSubCommand implements SubCommand {
    
    private final Main plugin;
    
    public RemoveWarderSubCommand(Main plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if (plugin.getWarder() == null) {
            sender.sendMessage("A warder was not set.");
            return false;
        }
        
        String warderName = plugin.getWarder().getName();
        plugin.removeWarder();
        plugin.getConfig().set(Main.WARDER_NAME, null);
        plugin.saveConfig();

        sender.sendMessage(warderName + " is no longer a warder.");
        
        return true;
    }
    
}

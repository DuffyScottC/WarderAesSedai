package me.braekpo1nt.warderaessedai.commands.subcommands;


import me.braekpo1nt.warderaessedai.Main;
import me.braekpo1nt.warderaessedai.commands.interfaces.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BondCommand implements SubCommand {
    
    private Main plugin;
    
    public BondCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            sender.sendMessage("Please provide two player names: /bond <warder> <aes sedai>");
            return false;
        }
        
        Player warder = Bukkit.getServer().getPlayerExact(args[0]);
        if (warder == null) {
            sender.sendMessage("Can't make player \""+ args[0] +"\" a warder, they are not online");
            return false;
        }
        
        Player aesSedai = Bukkit.getServer().getPlayerExact(args[1]);
        if (aesSedai == null) {
            sender.sendMessage("Can't make player \""+ args[1] +"\" an Aes Sedai, they are not online");
            return false;
        }
        
        if (warder.equals(aesSedai)) {
            sender.sendMessage("Warder and Aes Sedai can't be the same player");
            return false;
        }
        
        plugin.setWarder(warder);
        plugin.setAesSedai(aesSedai);
        
        sender.sendMessage(args[1] + " bonded " + args[0]);
        
        return true;
    }
}
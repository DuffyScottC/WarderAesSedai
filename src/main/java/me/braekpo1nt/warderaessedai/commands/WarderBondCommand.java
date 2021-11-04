package me.braekpo1nt.warderaessedai.commands;


import me.braekpo1nt.warderaessedai.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WarderBondCommand implements CommandExecutor {

    public WarderBondCommand(Main plugin) {
        plugin.getCommand("bond").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }
}
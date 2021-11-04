package me.braekpo1nt.warderaessedai;

import me.braekpo1nt.warderaessedai.commands.WarderBondCommand;
import me.braekpo1nt.warderaessedai.listeners.AesSedaiListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        new WarderBondCommand(this);

        Bukkit.getLogger().info("listener created");
        new AesSedaiListener(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

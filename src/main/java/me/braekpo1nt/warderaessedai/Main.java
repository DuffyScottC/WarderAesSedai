package me.braekpo1nt.warderaessedai;

import me.braekpo1nt.warderaessedai.commands.subcommands.BondCommand;
import me.braekpo1nt.warderaessedai.listeners.AesSedaiListener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    
    private Player warder;
    private Player aesSedai;
    
    @Override
    public void onEnable() {
        new BondCommand(this);

        new AesSedaiListener(this);
    }


    public Player getWarder() {
        return warder;
    }

    public void setWarder(Player warder) {
        this.warder = warder;
    }

    public Player getAesSedai() {
        return aesSedai;
    }

    public void setAesSedai(Player aesSedai) {
        this.aesSedai = aesSedai;
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

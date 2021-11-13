package me.braekpo1nt.warderaessedai;

import me.braekpo1nt.warderaessedai.commands.WBCommandManager;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    
    private Player warder;
    private Player aesSedai;
    
    @Override
    public void onEnable() {
        new WBCommandManager(this);
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

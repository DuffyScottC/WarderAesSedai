package me.braekpo1nt.warderaessedai;

import me.braekpo1nt.warderaessedai.commands.subcommands.BondSubCommand;
import me.braekpo1nt.warderaessedai.compass.Bar;
import me.braekpo1nt.warderaessedai.listeners.AesSedaiListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {
    
    private Player warder;
    private Player aesSedai;
    public Bar bar;
    
    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
        bar = new Bar(this);
        bar.createBar();
        
        if (Bukkit.getOnlinePlayers().size() > 0) {
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                bar.addPlayer(onlinePlayer);
            }
        }
        
        new BondSubCommand(this);

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
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (!bar.getBar().getPlayers().contains(event.getPlayer())) {
            bar.addPlayer((event.getPlayer()));
        }
    }
}

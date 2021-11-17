package me.braekpo1nt.warderaessedai.listeners;

import me.braekpo1nt.warderaessedai.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class AesSedaiListener implements Listener {

    private final Main plugin;
    
    public AesSedaiListener(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onPlayerDamageEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager().equals(plugin.getAesSedai())) {
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onAesSedaiRegainHealth(EntityRegainHealthEvent event) {
        Player warder = plugin.getWarder();
        Player aesSedai = plugin.getAesSedai();
        if (warder == null || aesSedai == null) {
            return;
        }
        if (event.getEntity().equals(aesSedai)) {
            if (warder.getHealth() < 20) {
                event.setCancelled(true);
            }
        }
    }
}

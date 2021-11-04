package me.braekpo1nt.warderaessedai.listeners;

import me.braekpo1nt.warderaessedai.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class AesSedaiListener implements Listener {
    public AesSedaiListener(Main plugin) {
        Bukkit.getLogger().info("listener registered");
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    @EventHandler
    public void onPlayerDamageEntity(EntityDamageByEntityEvent event) {
        Bukkit.getLogger().info("event cancelled");
        event.setCancelled(true);
    }
}

package me.braekpo1nt.warderaessedai.listeners;

import me.braekpo1nt.warderaessedai.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class WarderListener implements Listener {
    
    private final Main plugin;
    
    public WarderListener(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }
    
    @EventHandler
    public void warderBreakBlock(BlockBreakEvent event) {
        if (event.getPlayer().equals(plugin.getWarder())) {
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void warderPlaceBlock(BlockPlaceEvent event) {
        if (event.getPlayer().equals(plugin.getWarder())) {
            event.setCancelled(true);
        }
    }
    
}

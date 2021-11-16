package me.braekpo1nt.warderaessedai.listeners;

import me.braekpo1nt.warderaessedai.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;

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

    /**
     * If a player logs in and matches the warder, set the warder to them
     * @param event
     */
    @EventHandler
    public void warderLogOn(PlayerJoinEvent event) {
        String warderName = plugin.getConfig().getString(Main.WARDER_NAME);
        if (warderName != null && event.getPlayer().getName().equals(warderName)) {
            plugin.setWarder(event.getPlayer());
        }
    }
    
}

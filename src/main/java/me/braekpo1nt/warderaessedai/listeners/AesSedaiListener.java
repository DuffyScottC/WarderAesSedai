package me.braekpo1nt.warderaessedai.listeners;

import me.braekpo1nt.warderaessedai.Main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Iterator;
import java.util.List;

public class AesSedaiListener implements Listener {

    private final Main plugin;
    
    public AesSedaiListener(Main plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onPlayerDamageEntity(EntityDamageByEntityEvent event) {
        Player aesSedai = plugin.getAesSedai();
        if (aesSedai == null) {
            return;
        }
        if (event.getDamager().equals(aesSedai)) {
            event.setCancelled(true);
        }
    }
    
    /**
     * If a player logs in and matches the warder, set the warder to them
     * @param event
     */
    @EventHandler
    public void aesSedaiJoin(PlayerJoinEvent event) {
        String aesSedaiName = plugin.getConfig().getString(Main.AES_SEDAI_NAME);
        if (aesSedaiName != null && event.getPlayer().getName().equals(aesSedaiName)) {
            plugin.setAesSedai(event.getPlayer());
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
    
    @EventHandler
    public void onAesSedaiRespawn(PlayerRespawnEvent event) {
        Player aesSedai = plugin.getAesSedai();
        if (aesSedai != null && event.getPlayer().equals(aesSedai)) {
            plugin.giveAesSedaiCompass();
        }
    }
    
    @EventHandler
    public void onAesSedaiDeath(PlayerDeathEvent event) {
        Player aesSedai = plugin.getAesSedai();
        if (aesSedai != null && event.getEntity().equals(aesSedai)) {
            Iterator<ItemStack> iterator = event.getDrops().iterator();
            while (iterator.hasNext()) {
                if (iterator.next().getType().equals(Material.COMPASS)) {
                    iterator.remove();
                }
            }
        }
    }
}

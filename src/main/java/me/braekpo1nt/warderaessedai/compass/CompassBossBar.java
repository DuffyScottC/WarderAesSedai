package me.braekpo1nt.warderaessedai.compass;

import me.braekpo1nt.warderaessedai.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Iterator;

/**
 * Indicates a location relative to the player who sees the bar
 */
public class CompassBossBar {
    
    private final Main plugin;
    private BossBar bossBar;
    private Player player;
    private Location targetLocation;
    
    public CompassBossBar(Main plugin) {
        for (Iterator<KeyedBossBar> it = Bukkit.getBossBars(); it.hasNext(); ) {
            BossBar bar = it.next();
            bar.setVisible(false);
        }
        this.plugin = plugin;
        bossBar = Bukkit.createBossBar("Compass", BarColor.RED, BarStyle.SOLID);
        bossBar.setVisible(true);
        cast();
    }
    
    private void cast() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                if (player == null || targetLocation == null) {
                    return;
                }
                bossBar.setTitle(ChatColor.translateAlternateColorCodes('&', "&c" + String.format("%.2f - %.2f", calculateProgress(), calculateProgress2())));
                player.sendTitle("","",0,0,0);
            }
        }, 0, 20/*every tick*/);
    }
    
    /**
     * Calculates the boss bar progress from the relative location of the player and the location. 
     * @return a double on [0.0,1.0] indicating direction of location relative to player's facing direction
     */
    private double calculateProgress() {
        double playerYaw = player.getLocation().getYaw();
        double targetYaw = targetLocation.clone().subtract(player.getLocation()).getYaw();
        return targetYaw - playerYaw;
    }
    
    private double calculateProgress2() {
        double targetYaw = targetLocation.clone().subtract(player.getLocation()).getYaw();
        Bukkit.getLogger().info("targetLocation" + targetLocation);
        Bukkit.getLogger().info("difference" + targetLocation.clone().subtract(player.getLocation()));
        return targetYaw;
    }
    
    /**
     * The player who can see the boss bar
     * @param player The player who can see the boss bar
     */
    public void setPlayer(Player player) {
        this.player = player;
        bossBar.addPlayer(player);
    }
    
    /**
     * The location of the target
     * @param targetLocation The location of the target
     */
    public void setTargetLocation(Location targetLocation) {
        this.targetLocation = targetLocation;
    }
    
}

package me.braekpo1nt.warderaessedai.compass;

import me.braekpo1nt.warderaessedai.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 * Indicates a location relative to the player who sees the bar
 */
public class CompassBossBar {
    
    private final Main plugin;
    private BossBar bossBar;
    private Player player;
    private Location targetLocation;
    
    public CompassBossBar(Main plugin) {
        this.plugin = plugin;
        bossBar = Bukkit.createBossBar("Compass", BarColor.RED, BarStyle.SOLID);
        bossBar.setVisible(true);
    }
    
    private void cast() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                bossBar.setTitle("" + calculateProgress());
            }
        }, 0, 1/*every tick*/);
    }

    /**
     * Calculates the boss bar progress from the relative location of the player and the location. 
     * @return a double on [0.0,1.0] indicating direction of location relative to player's facing direction
     */
    private double calculateProgress() {
        Vector dir = targetLocation.getDirection().subtract(player.getLocation().getDirection());
        Vector dir2D = new Vector(dir.getX(), dir.getY(), 0);
        return dir2D.angle(new Vector(1, 0, 0));
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

package me.braekpo1nt.warderaessedai.commands;

import me.braekpo1nt.warderaessedai.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WarderManager {
    
    public WarderManager(Main plugin) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                Player warder = plugin.getWarder();
                if (warder == null) {
                    return;
                }
                warder.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 3*20, 0, false, false));
                warder.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3*20, 0, false, false));
            }
        }, 0, 20);
    }
    
}

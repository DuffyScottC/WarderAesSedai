package me.braekpo1nt.warderaessedai;

import me.braekpo1nt.warderaessedai.commands.WBCommandManager;
import me.braekpo1nt.warderaessedai.listeners.AesSedaiListener;
import me.braekpo1nt.warderaessedai.listeners.WarderListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class Main extends JavaPlugin {
    
    public static final String WARDER_NAME = "warder";
    public static final String AES_SEDAI_NAME = "aes_sedai";
    
    private Player warder;
    private Player aesSedai;
    
    private ItemStack warderCompass;
    private ItemStack aesSedaiCompass;
    
    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        
        String warderName = this.getConfig().getString(Main.WARDER_NAME);
        if (warderName != null) {
            Player warderPlayer = Bukkit.getServer().getPlayerExact(warderName);
            if (warderPlayer != null) {
                setWarder(warderPlayer);
            }
        }
        String aesSedaiName = this.getConfig().getString(Main.AES_SEDAI_NAME);
        if (aesSedaiName != null) {
            Player aesSedaiPlayer = Bukkit.getServer().getPlayerExact(aesSedaiName);
            if (aesSedaiPlayer != null) {
                setAesSedai(aesSedaiPlayer);
            }
        }
        
        new WBCommandManager(this);
        
        new AesSedaiListener(this);
        new WarderListener(this);
        
        cast();
    }
    
    public Player getWarder() {
        return this.warder;
    }
    
    public void setWarder(Player warder) {
        this.warder = warder;
    }
    
    public Player getAesSedai() {
        return this.aesSedai;
    }
    
    public void setAesSedai(Player aesSedai) {
        this.aesSedai = aesSedai;
    }
    
    private void cast() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                Player warder = getWarder();
                if (warder == null) {
                    return;
                }
                warder.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 3*20, 0, false, false));
                warder.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 3*20, 0, false, false));
            }
        }, 0, 20);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                Bukkit.getLogger().info("running scheduled task");
                if (warder == null || aesSedai == null) {
                    return;
                }
                
                if (warderCompass == null) {
                    giveWarderCompass();
                }
                
                if (aesSedaiCompass == null) {
                    giveAesSedaiCompass();
                }
                
                CompassMeta warderCompassMeta = (CompassMeta) warderCompass.getItemMeta();
                CompassMeta aesSedaiCompassMeta = (CompassMeta) aesSedaiCompass.getItemMeta();
                
                warderCompassMeta.setLodestone(aesSedai.getLocation());
                aesSedaiCompassMeta.setLodestone(warder.getLocation());
                
                warderCompass.setItemMeta(warderCompassMeta);
                aesSedaiCompass.setItemMeta(aesSedaiCompassMeta);
                
            }
        }, 0, 20);
    }
    
    public void giveWarderCompass() {
        if (warder == null) {
            return;
        }
        warder.getInventory().remove(Material.COMPASS);
        warderCompass = new ItemStack(Material.COMPASS);
        CompassMeta warderCompassMeta = (CompassMeta) warderCompass.getItemMeta();
        warderCompassMeta.setLodestoneTracked(false);
        if (aesSedai != null) {
            warderCompassMeta.setLodestone(aesSedai.getLocation());
        }
        warder.getInventory().addItem(warderCompass);
        warderCompass.setItemMeta(warderCompassMeta);
    }
    
    public void giveAesSedaiCompass() {
        if (aesSedai == null) {
            return;
        }
        aesSedai.getInventory().remove(Material.COMPASS);
        aesSedaiCompass = new ItemStack(Material.COMPASS);
        CompassMeta aesSedaiCompassMeta = (CompassMeta) aesSedaiCompass.getItemMeta();
        aesSedaiCompassMeta.setLodestoneTracked(false);
        if (warder != null) {
            aesSedaiCompassMeta.setLodestone(warder.getLocation());
        }
        aesSedai.getInventory().addItem(aesSedaiCompass);
        aesSedaiCompass.setItemMeta(aesSedaiCompassMeta);
    }
    
    @Override
    public void onDisable() {
        if (warder != null) {
            this.getConfig().set(Main.WARDER_NAME, warder.getName());
        }
    }
}

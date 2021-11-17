package me.braekpo1nt.warderaessedai;

import me.braekpo1nt.warderaessedai.commands.WBCommandManager;
import me.braekpo1nt.warderaessedai.listeners.AesSedaiListener;
import me.braekpo1nt.warderaessedai.listeners.WarderListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public final class Main extends JavaPlugin {
    
    public static final String WARDER_NAME = "warder";
    public static final String AES_SEDAI_NAME = "aes_sedai";
    
    private Player warder;
    private Player aesSedai;
    
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
    }
    
    
    public Player getWarder() {
        return this.warder;
    }
    
    public void removeWarder() {
//        this.warder.removePotionEffect(PotionEffectType.REGENERATION);
        this.warder.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
        this.warder = null;
    }
    
    public void setWarder(Player warder) {
        this.warder = warder;
//        this.warder.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200000, 0, false, false));
        this.warder.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 200000, 0, false, false));
    }
    
    public Player getAesSedai() {
        return this.aesSedai;
    }
    
    public void setAesSedai(Player aesSedai) {
        this.aesSedai = aesSedai;
    }
    
    public void removeAesSedai() {
        this.aesSedai = null;
    }
    
    
    @Override
    public void onDisable() {
        if (warder != null) {
            this.getConfig().set(Main.WARDER_NAME, warder.getName());
        }
    }
}

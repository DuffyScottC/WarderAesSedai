package me.braekpo1nt.warderaessedai;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

public class HealthScoreboardManager {

    private Scoreboard warderBoard;
    private Scoreboard aesSedaiBoard;
    
    public HealthScoreboardManager() {
        
        warderBoard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective o = warderBoard.getObjective("aessedaihealth");
        if (o == null) {
            o = warderBoard.registerNewObjective("aessedaihealth", "dummy", ChatColor.RED + "❤");
        }
        o.setDisplayName(ChatColor.RED + "❤");
        o.setDisplaySlot(DisplaySlot.BELOW_NAME);
    }
    
}

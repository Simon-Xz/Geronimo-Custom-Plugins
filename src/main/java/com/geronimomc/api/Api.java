package com.geronimomc.api;

import com.geronimomc.Main;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class Api {

    public static int getRank(Player p) {
        return getRank(Bukkit.getOfflinePlayer(p.getUniqueId()));
    }
    public static int getRank(OfflinePlayer p) {
        try {
            return Main.cfgm.getPlayers().getInt("Players." + p.getUniqueId().toString() + ".Rank");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }



}

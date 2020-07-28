package com.geronimomc.files;


import com.geronimomc.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class PlayerManager implements Listener {

    Plugin plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void createConfig(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        addPlayerConfig(p);
        return;
    }

    public void addPlayerConfig(Player p) {
        if (!Main.cfgm.getPlayers().contains("Players." + p.getUniqueId().toString() + ".Name")) {
            Main.cfgm.getPlayers().set("Players." + p.getUniqueId().toString() + ".Name", p.getName());
            Main.cfgm.savePlayers();
        }
        if (!Main.cfgm.getPlayers().contains("Players." + p.getUniqueId().toString() + ".Rank")) {
            Main.cfgm.getPlayers().set("Players." + p.getUniqueId().toString() + ".Rank", 1);
            Main.cfgm.savePlayers();
        }
        if (!Main.cfgm.getPlayers().contains("Players." + p.getUniqueId().toString() + ".Prestige")) {
            Main.cfgm.getPlayers().set("Players." + p.getUniqueId().toString() + ".Prestige", 0);
            Main.cfgm.savePlayers();
        }
/**
 * Crystals coming soon as needed for Custom enchants
 *
 * Coming next update
 */
//            if (!Main.cfgm.getPlayers().contains("Players." + p.getUniqueId().toString() + ".Crystals")) {
//            Main.cfgm.getPlayers().set("Players." + p.getUniqueId().toString() + ".Crystals", 0);
//            Main.cfgm.savePlayers();
//        }
    }
}
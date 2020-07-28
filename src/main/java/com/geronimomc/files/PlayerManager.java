package com.geronimomc.files;

import com.geronimomc.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class PlayerManager implements Listener {
    Plugin plugin = (Plugin)Main.getPlugin(Main.class);

    @EventHandler
    public void createConfig(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        addPlayerConfig(p);
    }

    public void addPlayerConfig(Player p) {
        if (!Main.cfgm.getPlayers().contains("Players." + p.getUniqueId().toString() + ".Name")) {
            Main.cfgm.getPlayers().set("Players." + p.getUniqueId().toString() + ".Name", p.getName());
            Main.cfgm.savePlayers();
        }
        if (!Main.cfgm.getPlayers().contains("Players." + p.getUniqueId().toString() + ".Rank")) {
            Main.cfgm.getPlayers().set("Players." + p.getUniqueId().toString() + ".Rank", Integer.valueOf(1));
            Main.cfgm.savePlayers();
        }
        if (!Main.cfgm.getPlayers().contains("Players." + p.getUniqueId().toString() + ".Prestige")) {
            Main.cfgm.getPlayers().set("Players." + p.getUniqueId().toString() + ".Prestige", Integer.valueOf(0));
            Main.cfgm.savePlayers();
        }
    }
}

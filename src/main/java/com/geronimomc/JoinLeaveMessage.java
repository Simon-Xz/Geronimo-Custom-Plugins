package com.geronimomc;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinLeaveMessage implements Listener {
    @EventHandler
    public void onPlayerConnect(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (p.hasPermission("core.login.message"))
            e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', "&a[+] " + p.getDisplayName()));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if (p.hasPermission("core.quit.message"))
            e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', "&c[-] " + p.getDisplayName()));
    }
}

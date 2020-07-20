package com.geronimomc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import javax.xml.bind.Marshaller;

public class LoginMessage implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(p.hasPermission("core.login.default")) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l&m--------------------------------------------------"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &8>> &fWelcome back, &6" + p.getDisplayName() + " &fto the &6&lGeronimoMC &fserver"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &8>> &fWebsite: &6forums.geronimomc.com"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &8>> &fStore: &6store.geronimomc.com"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &8>> &fThere are currently &6" + Bukkit.getOnlinePlayers().size() + "&e/&6" + Bukkit.getMaxPlayers() + "&f players online!"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&l&m--------------------------------------------------"));
        }
    }
}

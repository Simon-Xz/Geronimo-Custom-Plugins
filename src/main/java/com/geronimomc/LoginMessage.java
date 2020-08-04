package com.geronimomc;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LoginMessage implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (p.hasPermission("core.login.default")) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m&l--------------------------------------------------"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &8>> &fWelcome back, &6" + p.getDisplayName() + " &fto the &6&lGeronimoMC &fserver"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &8>> &fWebsite: &6forums.geronimomc.com"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &8>> &fStore: &6store.geronimomc.com"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "  &8>> &fThere are currently &6" + Bukkit.getOnlinePlayers().size() + "&e/&6" + Bukkit.getMaxPlayers() + "&f players online!"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m&l--------------------------------------------------"));
        }
        if (!p.getInventory().contains(new ItemStack(Material.DIAMOND_PICKAXE))) {
            if(p.hasPermission("core.rank.default")) {
                ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
                ItemMeta pickaxeMeta = pickaxe.getItemMeta();
                pickaxeMeta.setDisplayName("Default");

                pickaxe.setItemMeta(pickaxeMeta);

                p.getInventory().setItem(1, pickaxe);
            }
            if(p.hasPermission("core.rank.merchant")) {
                ItemStack merchant = new ItemStack(Material.DIAMOND_PICKAXE);
                ItemMeta merchantMeta = merchant.getItemMeta();
                merchantMeta.setDisplayName("Merchant");

                merchant.setItemMeta(merchantMeta);
                p.getInventory().setItem(1, merchant);
            }
            if(p.hasPermission("core.rank.warrior")) {

            }
            if(p.hasPermission("core.rank.noble")) {

            }
            if(p.hasPermission("core.rank.earl")) {

            }
            if(p.hasPermission("core.rank.titan")) {

            }
            if(p.hasPermission("core.rank.divine")) {

            }
            if(p.hasPermission("core.rank.geronimo")) {
                
            }
        }
    }
}

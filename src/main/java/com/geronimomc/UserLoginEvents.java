package com.geronimomc;

import com.geronimomc.files.CustomConfig;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class UserLoginEvents implements Listener {
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
        if (!p.getInventory().contains(Material.DIAMOND_PICKAXE)) {
            if(p.hasPermission("core.rank.default")) {
                ItemStack pickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
                ItemMeta pickaxeMeta = pickaxe.getItemMeta();

                // SETTING PICKAXE NAME AND ENCHANTS
                pickaxeMeta.setDisplayName(CustomConfig.get().getString("pickname-name").replace('&', '§'));
                pickaxeMeta.addEnchant(Enchantment.DIG_SPEED,15, true);
                pickaxeMeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS,15, true);
                pickaxeMeta.addEnchant(Enchantment.DURABILITY, 15, true);
                pickaxeMeta.setUnbreakable(true);
                pickaxeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                pickaxeMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                pickaxeMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

                // SETTING PICKAXE LORE
                ArrayList<String> pickaxeLore = new ArrayList<String>();
                pickaxeLore.add(ChatColor.translateAlternateColorCodes('&', "&6Item Info:"));
                pickaxeLore.add(ChatColor.RED + " Omnitool");
                pickaxeLore.add(ChatColor.RED + " Unbreakable");
                pickaxeLore.add(" ");
                pickaxeLore.add(ChatColor.GOLD + "Enchants:");
                pickaxeLore.add(ChatColor.translateAlternateColorCodes('&', " &7Efficiency " + pickaxeMeta.getEnchantLevel(Enchantment.DIG_SPEED)));
                pickaxeLore.add(ChatColor.translateAlternateColorCodes('&', " &7Fortune " + pickaxeMeta.getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS)));
                pickaxeMeta.setLore(pickaxeLore);

                pickaxe.setItemMeta(pickaxeMeta);

                p.getInventory().setItem(1, pickaxe);
            }
        }
    }

}

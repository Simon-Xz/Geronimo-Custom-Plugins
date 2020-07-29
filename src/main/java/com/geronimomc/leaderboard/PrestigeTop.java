package com.geronimomc.leaderboard;

import com.geronimomc.Main;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.TreeType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import sun.reflect.generics.tree.Tree;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class PrestigeTop  implements CommandExecutor, Listener {

    Main plugin = Main.getPlugin(Main.class);
    Plugin geronimoprisoncore = Bukkit.getPluginManager().getPlugin("GeronimoPrisonCore");

    public static void main(Player p) {
        Plugin geronimoprisoncore = Bukkit.getPluginManager().getPlugin("GeronimoPrisonCore");

        TreeMap<Integer, String> map = new TreeMap<Integer, String>();
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            Integer prestige = Main.cfgm.getPlayers().getInt("Players." + onlinePlayer.getUniqueId().toString() + ".Prestige");
            if (Main.cfgm.getPlayers().contains("Players." + onlinePlayer.getUniqueId().toString() + ".Prestige"))
                map.put(prestige, onlinePlayer.getName());
        }
        for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
            if (offlinePlayer != null && offlinePlayer.getName() != null) {
                Integer prestige = Main.cfgm.getPlayers().getInt("Players." + offlinePlayer.getUniqueId().toString() + ".Prestige");
                if (Main.cfgm.getPlayers().contains("Players." + offlinePlayer.getUniqueId().toString() + ".Prestige"))
                    map.put(prestige, offlinePlayer.getName());
            }
        }
        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lTOP 5 PRESTIGE"));
        int n = 1;

        NavigableMap<Integer, String> nmap = map.descendingMap();
        for (NavigableMap.Entry<Integer, String> entry : nmap.entrySet()) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e#"+n+". &f" + entry.getValue() + " &8-&b Prestige " + entry.getKey()));
            n++;
            if (n == 6) {
                break;
            }
        }

    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player)sender;
            if (args.length == 0) {
                main(p);
            }
        }
        return true;
    }
}

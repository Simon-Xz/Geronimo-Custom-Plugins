package com.geronimomc.commands.rankup.list;

import com.geronimomc.api.Api;
import com.geronimomc.commands.rankup.Prestige;
import com.geronimomc.commands.rankup.Rankup;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.text.NumberFormat;


public class Ranks implements CommandExecutor, Listener {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player)sender;
            if (args.length == 0) {
                p.sendMessage("");
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&l&nRank List"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&oDoes not show updated prices for prestige(s)"));
                for(int i = 1; i < 26; ++i){
                    String ranklist = Rankup.rankName(p, i);
                    Long rankcost = Rankup.rankCost(p, i);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', String.format("&6" + ranklist + " &e$" + NumberFormat.getNumberInstance().format(rankcost))));
                }
                p.sendMessage("");
            }
        }
        return true;
    }

}

package com.geronimomc.commands.rankup.list;

import com.geronimomc.api.Api;
import com.geronimomc.commands.rankup.Rankup;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;


public class Ranks implements CommandExecutor, Listener {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player)sender;
            if (args.length == 0) {
                for(int i = 1; i < 10; ++i){
                    String ranklist = Rankup.rankName(p, i);
                    Long rankcost = Rankup.rankCost(p, i);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6" + ranklist + " &e$" + rankcost));
                }
            }
        }
        return true;
    }

}

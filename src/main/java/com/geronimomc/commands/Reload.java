package com.geronimomc.commands;

import com.geronimomc.files.CustomConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reload implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;
        CustomConfig.reload();
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aConfig reloaded with no issues!"));
        return true;
    }
}

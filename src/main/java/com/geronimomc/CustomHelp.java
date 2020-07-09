package com.geronimomc;

import jdk.nashorn.internal.runtime.regexp.joni.encoding.CharacterType;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.awt.*;

public class CustomHelp implements Listener, CommandExecutor {


    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lGeronimoMC"));
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eClick to select a help option..."));
            p.sendMessage(bug);
            p.sendMessage(rules);
            p.sendMessage(payment);

        }

        return true;
    }



}

package com.geronimomc.commands;

import com.geronimomc.files.CustomConfig;
import jdk.nashorn.internal.runtime.regexp.joni.encoding.CharacterType;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.awt.*;

public class CustomHelp implements Listener, CommandExecutor {

    private Plugin plugin;



    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            p.sendMessage(CustomConfig.get().getString("help-command-header").replace('&', '§'));

        }

        return true;
    }



}

package com.geronimomc.commands;

import com.geronimomc.files.CustomConfig;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.awt.*;


public class CustomHelp implements Listener, CommandExecutor {



    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            p.sendMessage(CustomConfig.get().getString("help-line-one").replace('&', '§'));
            p.sendMessage(CustomConfig.get().getString("help-line-two").replace('&', '§'));
            p.sendMessage(CustomConfig.get().getString("help-line-three").replace('&', '§'));


            String help1 = CustomConfig.get().getString("help-line-four").replace('&', '§');
            String help1hover = CustomConfig.get().getString("help-line-four-hover").replace('&', '§');
            String help1click = CustomConfig.get().getString("help-line-four-clickevent").replace('&', '§');

            //String help2 = CustomConfig.get().getString("help-line-four");
            //String help2hover = CustomConfig.get().getString("help-line-four-hover");
            //String help2click = CustomConfig.get().getString("help-line-four-clickevent");

            //String help3 = CustomConfig.get().getString("help-line-four");
            //String help3hover = CustomConfig.get().getString("help-line-four-hover");
            //String help3click = CustomConfig.get().getString("help-line-four-clickevent");

            //String help4 = CustomConfig.get().getString("help-line-four");
            //String help4hover = CustomConfig.get().getString("help-line-four-hover");
            //String help4click = CustomConfig.get().getString("help-line-four-clickevent");

            TextComponent help1message = new TextComponent(help1);
            help1message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, help1click));
            help1message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(help1hover).create()));

            p.spigot().sendMessage(help1message);


        }

        return true;
    }



}

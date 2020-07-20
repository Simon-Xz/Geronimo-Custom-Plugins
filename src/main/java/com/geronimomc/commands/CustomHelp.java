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

            String help2 = CustomConfig.get().getString("help-line-five").replace('&', '§');
            String help2hover = CustomConfig.get().getString("help-line-five-hover").replace('&', '§');
            String help2click = CustomConfig.get().getString("help-line-five-clickevent").replace('&', '§');

            String help3 = CustomConfig.get().getString("help-line-six").replace('&', '§');
            String help3hover = CustomConfig.get().getString("help-line-six-hover").replace('&', '§');
            String help3click = CustomConfig.get().getString("help-line-six-clickevent").replace('&', '§');

            String help4 = CustomConfig.get().getString("help-line-eight").replace('&', '§');
            String help4hover = CustomConfig.get().getString("help-line-eight-hover").replace('&', '§');
            String help4click = CustomConfig.get().getString("help-line-eight-clickevent").replace('&', '§');

            TextComponent help1message = new TextComponent(help1);
            help1message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, help1click));
            help1message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(help1hover).create()));

            TextComponent help2message = new TextComponent(help2);
            help2message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, help2click));
            help2message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(help2hover).create()));

            TextComponent help3message = new TextComponent(help3);
            help3message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, help3click));
            help3message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(help3hover).create()));

            TextComponent help4message = new TextComponent(help4);
            help4message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, help4click));
            help4message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(help4hover).create()));



            p.spigot().sendMessage(help1message);
            p.spigot().sendMessage(help2message);
            p.spigot().sendMessage(help3message);
            p.sendMessage(CustomConfig.get().getString("help-line-seven").replace('&', '§'));
            p.spigot().sendMessage(help4message);


        }

        return true;
    }



}

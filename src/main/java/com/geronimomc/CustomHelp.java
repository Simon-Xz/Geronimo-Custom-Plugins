package com.geronimomc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomHelp extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("");
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        getLogger().info("");
        // Plugin shutdown logic
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

    }
}

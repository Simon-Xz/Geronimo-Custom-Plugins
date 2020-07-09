package com.geronimomc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("");
        this.getCommand("help").setExecutor(new CustomHelp());
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        getLogger().info("");
        // Plugin shutdown logic
    }
}

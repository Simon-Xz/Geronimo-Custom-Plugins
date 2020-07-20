package com.geronimomc;

import com.geronimomc.commands.CustomHelp;
import com.geronimomc.commands.Reload;
import com.geronimomc.files.CustomConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Main extends JavaPlugin {


    @Override
    public void onEnable() {
        //Custom Commands
        this.getCommand("help").setExecutor(new CustomHelp());
        this.getCommand("grcore").setExecutor(new Reload());
        Bukkit.getServer().getPluginManager().registerEvents(new LoginMessage(), this);

        getConfig().options().copyDefaults();
        saveDefaultConfig();


        //Settings up config
        CustomConfig.setup();
        CustomConfig.get().addDefault("help-line-one", "&6&lGeronimoMC");
        CustomConfig.get().addDefault("help-line-two", "&eClick to select a help option...");
        CustomConfig.get().addDefault("help-line-three", "&r");
        CustomConfig.get().addDefault("help-line-four", "&6* &eFound a server issue/bug");
        CustomConfig.get().addDefault("help-line-four-hover", "&6Click to select!");
        CustomConfig.get().addDefault("help-line-four-clickevent", "https://discord.geronimomc.com");
        CustomConfig.get().addDefault("help-line-five", "&6* &eReport rule breakers");
        CustomConfig.get().addDefault("help-line-five-hover", "&6Click to select!");
        CustomConfig.get().addDefault("help-line-five-clickevent", "https://discord.geronimomc.com");
        CustomConfig.get().addDefault("help-line-six", "&6* &eAsk a payment question");
        CustomConfig.get().addDefault("help-line-six-hover", "&6Click to select!");
        CustomConfig.get().addDefault("help-line-six-clickevent", "https://discord.geronimomc.com");
        CustomConfig.get().addDefault("help-line-seven", "");
        CustomConfig.get().addDefault("help-line-eight", "&eNeed more help? Vist &6our forums.");
        CustomConfig.get().addDefault("help-line-eight-hover", "&6Click to select!");
        CustomConfig.get().addDefault("help-line-eight-clickevent", "https://forums.geronimomc.com");


        CustomConfig.get().options().copyDefaults(true);
        CustomConfig.save();

    }

    @Override
    public void onDisable() {
        getLogger().info("");
        // Plugin shutdown logic
    }

}

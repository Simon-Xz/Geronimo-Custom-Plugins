package com.geronimomc;

import com.geronimomc.commands.CustomHelp;
import com.geronimomc.commands.Reload;
import com.geronimomc.files.CustomConfig;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class Main extends JavaPlugin {


    @Override
    public void onEnable() {
        //Custom Commands
        this.getCommand("help").setExecutor(new CustomHelp());
        this.getCommand("hreload").setExecutor(new Reload());

        getConfig().options().copyDefaults();
        saveDefaultConfig();


        //Settings up config
        CustomConfig.setup();
        CustomConfig.get().addDefault("help-line-one", "&6&lGeronimoMC");
        CustomConfig.get().addDefault("help-line-two", "&eClick to select a help option");
        CustomConfig.get().addDefault("help-line-three", "");
        CustomConfig.get().addDefault("help-line-four", "");
        CustomConfig.get().addDefault("help-line-four-hover", "");
        CustomConfig.get().addDefault("help-line-four-clickevent", "");
        CustomConfig.get().addDefault("help-line-five", "");
        CustomConfig.get().addDefault("help-line-five-hover", "");
        CustomConfig.get().addDefault("help-line-five-clickevent", "");
        CustomConfig.get().addDefault("help-line-six", "");
        CustomConfig.get().addDefault("help-line-six-hover", "");
        CustomConfig.get().addDefault("help-line-six-clickevent", "");
        CustomConfig.get().addDefault("hrlp-line-seven", "");
        CustomConfig.get().addDefault("help-line-eight", "");
        CustomConfig.get().addDefault("help-line-eight-hover", "");
        CustomConfig.get().addDefault("help-line-eight-clickevent", "");


        CustomConfig.get().options().copyDefaults(true);
        CustomConfig.save();

    }

    @Override
    public void onDisable() {
        getLogger().info("");
        // Plugin shutdown logic
    }

}

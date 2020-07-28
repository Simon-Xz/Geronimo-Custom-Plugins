package com.geronimomc;

import com.geronimomc.commands.CustomHelp;
import com.geronimomc.commands.rankup.Prestige;
import com.geronimomc.commands.rankup.Rankup;
import com.geronimomc.commands.Reload;
import com.geronimomc.files.ConfigManager;
import com.geronimomc.files.CustomConfig;
import com.geronimomc.files.PlayerManager;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.security.Permission;

public final class Main extends JavaPlugin implements Listener {

    private static Economy econ = null;
    private static Chat chat = null;
    public static ConfigManager cfgm;
    private static Permission perms = null;

    @Override
    public void onEnable() {
        //Custom Commands
        this.getCommand("help").setExecutor(new CustomHelp());
        this.getCommand("grcore").setExecutor(new Reload());
        this.getCommand("rankup").setExecutor(new Rankup());
        this.getCommand("prestige").setExecutor(new Prestige());

        //Join Leave messages ect
        Bukkit.getServer().getPluginManager().registerEvents(new LoginMessage(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new JoinLeaveMessage(), this);
        Bukkit.getServer().getPluginManager().registerEvents(this,this);
        Bukkit.getServer().getPluginManager().registerEvents(new Rankup(), this);
        getServer().getPluginManager().registerEvents(new PlayerManager(), this);

        //Setup Econ
        if (!setupEconomy() ) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        //sorting config stuff out
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        loadConfigManager();


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

    // Player config creator
    public void loadConfigManager() {
        cfgm = new ConfigManager();
        cfgm.setupPlayers();
    }

    //Vault API
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

}

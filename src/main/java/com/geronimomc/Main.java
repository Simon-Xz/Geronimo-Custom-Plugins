// https://tryitands.ee/
package com.geronimomc;

import com.geronimomc.commands.CustomHelp;
import com.geronimomc.commands.Reload;
import com.geronimomc.commands.rankup.Prestige;
import com.geronimomc.commands.rankup.Rankup;
import com.geronimomc.files.ConfigManager;
import com.geronimomc.files.CustomConfig;
import com.geronimomc.files.PlayerManager;

import java.lang.reflect.Field;
import java.security.Permission;
import java.util.HashMap;
import java.util.logging.Logger;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin implements Listener {

    private static final Logger log = Logger.getLogger("Minecraft");
    public static ConfigManager cfgm;
    private static Economy econ = null;
    private static Permission perms = null;
    private static Main plugin;


    public void onEnable() {


        getCommand("help").setExecutor(new CustomHelp());
        getCommand("grcore").setExecutor(new Reload());
        getCommand("rankup").setExecutor(new Rankup());
        getCommand("prestige").setExecutor(new Prestige());

        Bukkit.getServer().getPluginManager().registerEvents(new CustomHelp(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new LoginMessage(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new JoinLeaveMessage(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Rankup(), this);
        getServer().getPluginManager().registerEvents(new PlayerManager(), this);
        getServer().getPluginManager().registerEvents(this, this);

        if (!setupEconomy()) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", new Object[] { getDescription().getName() }));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getConfig().options().copyDefaults();
        saveDefaultConfig();
        loadConfigManager();
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

    public void loadConfigManager() {
        cfgm = new ConfigManager();
        cfgm.setupPlayers();

    }


    public void onDisable() {

    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null)
            return false;
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null)
            return false;
        econ = rsp.getProvider();
        return (econ != null);
    }

    public static Economy getEconomy() {
        return econ;
    }

    public static Main getPlugin(){
        return plugin;
    }




}

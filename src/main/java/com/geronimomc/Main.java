// https://tryitands.ee/
package com.geronimomc;

import com.geronimomc.api.Api;
import com.geronimomc.commands.CustomHelp;
import com.geronimomc.commands.Reload;
import com.geronimomc.commands.rankup.Prestige;
import com.geronimomc.commands.rankup.Rankup;
import com.geronimomc.commands.rankup.list.Ranks;
import com.geronimomc.files.ConfigManager;
import com.geronimomc.files.CustomConfig;
import com.geronimomc.files.PlayerManager;

import java.math.RoundingMode;
import java.security.Permission;
import java.text.DecimalFormat;
import java.util.logging.Logger;

import com.geronimomc.leaderboard.PrestigeTop;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderHook;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
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
        getCommand("ranks").setExecutor(new Ranks());
        getCommand("prestigetop").setExecutor(new PrestigeTop());

        getServer().getPluginManager().registerEvents(new CustomHelp(), this);
        getServer().getPluginManager().registerEvents(new UserLoginEvents(), this);
        getServer().getPluginManager().registerEvents(new JoinLeaveMessage(), this);
        getServer().getPluginManager().registerEvents(new Rankup(), this);
        getServer().getPluginManager().registerEvents(new PlayerManager(), this);
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new Rankup(), this);
        getServer().getPluginManager().registerEvents(new PrestigeTop(), this);

        if (!setupEconomy()) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", new Object[] { getDescription().getName() }));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.getPluginManager().registerEvents(this, this);
        } else {
            throw new RuntimeException("Could not find PlaceholderAPI!! Plugin can not work without it!");
        }

        registerPlaceholders();

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
        CustomConfig.get().addDefault("pickaxe-name", "&&6&lOmnitool");
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

    //PLACEHOLDER API SUPPORT

    public void registerPlaceholders() {
        PlaceholderAPI.registerPlaceholderHook("prisoncore", new PlaceholderHook() {
            @Override
            public String onRequest(OfflinePlayer p, String params) {
                if(p != null && p.isOnline()) {
                    return onPlaceholderRequest(p.getPlayer(), params);
                }
                return null;
            }
            @Override
            public String onPlaceholderRequest(Player p, String params) {
                if (p == null) {
                    return null;
                }
                if (params.equalsIgnoreCase("prestige")) {
                    String prestige = cfgm.getPlayers().getString("Players." + p.getUniqueId().toString() + ".Prestige");
                    return prestige;
                }
                if (params.equalsIgnoreCase("rank")) {
                    int rank = Api.getRank(p);
                    String a = Rankup.rankName(p, rank);
                    return a;
                }
                if (params.equalsIgnoreCase("rankbar")) {
                    int rank = Api.getRank(p);
                    int prestige = Integer.parseInt(cfgm.getPlayers().getString("Players." + p.getUniqueId().toString() + ".Prestige") + 1);
                    double p_bal = econ.getBalance(p);
                    long rankcost = 0;
                    if (prestige < 1) {
                        rankcost = Rankup.rankCost(p, rank);
                    } else {
                        rankcost = Rankup.rankCost(p, rank) * prestige;
                    }
                    double percentage = (p_bal / rankcost) * 100;
                    if (percentage >= 100) {
                        percentage = 100.00;
                    }
                    DecimalFormat round = new DecimalFormat("#.##");
                    round.setRoundingMode(RoundingMode.HALF_DOWN);

                    String bar = "";
                    if(percentage >= 100) { bar = "&a||||||||||"; }
                    else if(percentage >= 90 && percentage < 100) { bar = "&a|||||||||&7|"; }
                    else if(percentage >= 80 && percentage < 90) { bar = "&a||||||||&7||"; }
                    else if(percentage >= 70 && percentage < 80) { bar = "&a|||||||&7|||"; }
                    else if(percentage >= 60 && percentage < 70) { bar = "&a|||||||&7|||"; }
                    else if(percentage >= 50 && percentage < 60) { bar = "&a||||||&7||||"; }
                    else if(percentage >= 40 && percentage < 50) { bar = "&a|||||&7|||||"; }
                    else if(percentage >= 30 && percentage < 40) { bar = "&a||||&7||||||"; }
                    else if(percentage >= 20 && percentage < 30) { bar = "&a|||&7|||||||"; }
                    else if(percentage >= 10 && percentage < 20) { bar = "&a||&7||||||||"; }
                    else if(percentage >= 0 && percentage < 10) { bar = "&a|&7|||||||||"; }

                    return bar + " " + round.format(percentage) + "%";

                }

                return null;
            }

        });
    }
}

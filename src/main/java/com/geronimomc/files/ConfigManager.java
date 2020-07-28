package com.geronimomc.files;

import com.geronimomc.Main;
import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {
    private Main plugin = (Main)Main.getPlugin(Main.class);

    public FileConfiguration playerscfg;

    public File playersfile;

    public void setupPlayers() {
        if (!this.plugin.getDataFolder().exists());
        this.plugin.getDataFolder().mkdir();
        this.playersfile = new File(this.plugin.getDataFolder(), "players.yml");
        if (!this.playersfile.exists())
            try {
                this.playersfile.createNewFile();
            } catch (IOException e) {
                Bukkit.getServer().getConsoleSender()
                        .sendMessage(ChatColor.RED + "Couldn't create players.yml file.");
            }
        this.playerscfg = (FileConfiguration)YamlConfiguration.loadConfiguration(this.playersfile);
        Bukkit.getServer().getConsoleSender()
                .sendMessage(ChatColor.GREEN + "players.yml file has been created");
    }

    public FileConfiguration getPlayers() {
        return this.playerscfg;
    }

    public void savePlayers() {
        try {
            this.playerscfg.save(this.playersfile);
        } catch (IOException e) {
            Bukkit.getServer().getConsoleSender()
                    .sendMessage(ChatColor.RED + "Couldn't save players.yml file.");
        }
    }

    public void reloadPlayers() {
        this.playerscfg = (FileConfiguration)YamlConfiguration.loadConfiguration(this.playersfile);
    }
}

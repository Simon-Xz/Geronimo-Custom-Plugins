package com.geronimomc.files;

import com.geronimomc.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CustomConfig {

    private Main plugin = Main.getPlugin(Main.class);


    private static File file;
    private static FileConfiguration customFile;



    //gens custom config
    public static void setup(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("CustomHelp").getDataFolder(), "config.yml");

        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e) {
                //owww
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);

    }

    public static FileConfiguration get(){
        return customFile;
    }

    public static void save(){
        try {
            customFile.save(file);
        } catch (IOException e){
            System.out.println("Couldn't save file");
        }
    }

}

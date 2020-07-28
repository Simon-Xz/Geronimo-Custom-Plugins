package com.geronimomc.commands.rankup;

import com.geronimomc.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Prestige implements CommandExecutor {

    Plugin plugin = Main.getPlugin(Main.class);

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(args.length == 0) {
                confirmPrestige(p);
            }
        }
        return true;
    }

    public void confirmPrestige(Player p) {
        int rank = Main.cfgm.getPlayers().getInt("Players." + p.getUniqueId().toString() + ".Rank");
        int prestige = Main.cfgm.getPlayers().getInt("Players." + p.getUniqueId().toString() + ".Prestige");
        long new_prestige = prestige + 1;

        if(rank == 10) {

            Main.cfgm.getPlayers().set("Players." + p.getUniqueId().toString() + ".Prestige", new_prestige);
            Main.cfgm.getPlayers().set("Players." + p.getUniqueId().toString() +  ".Rank", 1);
            Main.cfgm.savePlayers();
            Location loc = p.getLocation();
            p.getWorld().spawnParticle(Particle.FLAME, loc, 40, .5, .5, .5, 0);
            p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You were promoted to &f&lPrestige " + new_prestige + "&7!"));

        }
        else {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lError&8: &7You must have the rank &c&lX &7to prestige."));
        }
    }
}
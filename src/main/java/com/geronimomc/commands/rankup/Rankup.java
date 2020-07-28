package com.geronimomc.commands.rankup;

import com.geronimomc.Main;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.NumberFormat;

public class Rankup implements CommandExecutor, Listener {

    Plugin plugin = Main.getPlugin(Main.class);

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){

        if(cmd.getName().equalsIgnoreCase("rankup") && sender instanceof Player){

            Player p = (Player) sender;

            if(args.length == 0) {
                doRankup(p);
            }else {
                if(args[0].equals("max")) {
                    int rank = Main.cfgm.getPlayers().getInt("Players." + p.getUniqueId().toString() +  ".Rank");
                    int ranksleft = 10 - rank;
                    if(ranksleft > 0) {
                        new BukkitRunnable() {
                            int loop = ranksleft;
                            @Override
                            public void run() {
                                doRankup(p);
                                loop--;
                                if(loop==0) this.cancel();
                            }
                        }.runTaskTimer(plugin, 0L, 2L).getTaskId();
                    }
                    else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lYou're top rank"));
                    }
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lIncorrect arguments: &f/rankup [max/ranks]"));
                }
            }

        }
        return true;
    }

    public void doRankup(Player p) {
        //Stats
        int rank = Main.cfgm.getPlayers().getInt("Players." + p.getUniqueId().toString() +  ".Rank");
        long cost = rankCost(p, rank);
        int prestige = Main.cfgm.getPlayers().getInt("Players." + p.getUniqueId().toString() + ".Prestige");
        int new_rank = rank + 1;

        // Multiply rank up cost by prestige
        long multiplier = prestige + 1;
        long charge = 0;
        if(multiplier >= 1) {
            charge = cost * multiplier;
        }else {
            charge = cost;
        }

        if(rank >= 1 && rank < 10) {
            EconomyResponse r = Main.getEconomy().withdrawPlayer(p, charge);
            if(r.transactionSuccess()) {

                Main.cfgm.getPlayers().set("Players." + p.getUniqueId().toString() + ".Rank", new_rank);
                Main.cfgm.savePlayers();

                //Messages
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You have ranked up to &f&l" + rankName(p, new_rank) + " &7( &c- &l$&c"+ NumberFormat.getNumberInstance().format(charge)+" &7)"));

                Location loc = p.getLocation();
                p.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, loc, 40, .5, .5, .5, 0);
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
            }else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lError&8: &7You require &c$" + NumberFormat.getNumberInstance().format(charge) + "&7 to rankup to &f&l" + rankName(p, new_rank) + "&7!"));
            }
        }else {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lError&8: &7You're at top rank. How about you try &c&l/prestige&7?"));
        }
    }

    public static String rankName(Player p, int rank) {
        String a = null;
        //Select next rank
        if(rank == 1) {
            a = "I";
        } else if(rank == 2) {
            a = "II";
        } else if(rank == 3) {
            a = "III";
        } else if(rank == 4) {
            a = "IV";
        } else if(rank == 5) {
            a = "V";
        } else if(rank == 6) {
            a = "VI";
        } else if(rank == 7) {
            a = "VII";
        } else if(rank == 8) {
            a = "VIII";
        } else if(rank == 9) {
            a = "IX";
        } else if(rank == 10) {
            a = "X";
        }
        return a;
    }

    public static long rankCost(Player p, int rank) {
        long a = 0;
        if(rank == 1) {
            a = 50000;
        } else if(rank == 2) {
            a = 100000;
        } else if(rank == 3) {
            a = 250000;
        } else if(rank == 4) {
            a = 500000;
        } else if(rank == 5) {
            a = 2000000;
        } else if(rank == 6) {
            a = 10000000;
        } else if(rank == 7) {
            a = 25000000;
        } else if(rank == 8) {
            a = 80000000;
        } else if(rank == 9) {
            a = 300000000;
        } else if(rank == 10) {
            a = 800000000;
        }
        return a;
    }

}
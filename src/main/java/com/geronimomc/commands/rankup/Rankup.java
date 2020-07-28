package com.geronimomc.commands.rankup;

import com.geronimomc.Main;
import java.text.NumberFormat;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Rankup implements Listener, CommandExecutor {
    Plugin plugin = (Plugin)Main.getPlugin(Main.class);

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (args.length == 0) {
                doRankup(p);
            } else if (args[0].equals("max")) {
                int rank = Main.cfgm.getPlayers().getInt("Players." + p.getUniqueId().toString() + ".Rank");
                final int ranksleft = 10 - rank;
                if (ranksleft > 0) {
                    (new BukkitRunnable() {
                        int loop = ranksleft;

                        public void run() {
                            Rankup.this.doRankup(p);
                            this.loop--;
                            if (this.loop == 0)
                                cancel();
                        }
                    }).runTaskTimer(this.plugin, 0L, 2L).getTaskId();
                } else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lYou're top rank"));
                }
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lIncorrect arguments: &f/rankup [max/ranks]"));
            }
        }
        return true;
    }

    public void doRankup(Player p) {
        int rank = Main.cfgm.getPlayers().getInt("Players." + p.getUniqueId().toString() + ".Rank");
        long cost = rankCost(p, rank);
        int prestige = Main.cfgm.getPlayers().getInt("Players." + p.getUniqueId().toString() + ".Prestige");
        int new_rank = rank + 1;
        long multiplier = (prestige + 1);
        long charge = 0L;
        if (multiplier >= 1L) {
            charge = cost * multiplier;
        } else {
            charge = cost;
        }
        if (rank >= 1 && rank < 10) {
            EconomyResponse r = Main.getEconomy().withdrawPlayer((OfflinePlayer)p, charge);
            if (r.transactionSuccess()) {
                Main.cfgm.getPlayers().set("Players." + p.getUniqueId().toString() + ".Rank", Integer.valueOf(new_rank));
                Main.cfgm.savePlayers();
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You have ranked up to &f&l" + rankName(p, new_rank) + " &7( &c- &l$&c" + NumberFormat.getNumberInstance().format(charge) + " &7)"));
                Location loc = p.getLocation();
                p.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, loc, 40, 0.5D, 0.5D, 0.5D, 0.0D);
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10.0F, 1.0F);
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lError&8: &7You require &c$" + NumberFormat.getNumberInstance().format(charge) + "&7 to rankup to &f&l" + rankName(p, new_rank) + "&7!"));
            }
        } else {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lError&8: &7You're at top rank. How about you try &c&l/prestige&7?"));
        }
    }

    public static String rankName(Player p, int rank) {
        String a = null;
        if (rank == 1) {
            a = "I";
        } else if (rank == 2) {
            a = "II";
        } else if (rank == 3) {
            a = "III";
        } else if (rank == 4) {
            a = "IV";
        } else if (rank == 5) {
            a = "V";
        } else if (rank == 6) {
            a = "VI";
        } else if (rank == 7) {
            a = "VII";
        } else if (rank == 8) {
            a = "VIII";
        } else if (rank == 9) {
            a = "IX";
        } else if (rank == 10) {
            a = "X";
        }
        return a;
    }

    public static long rankCost(Player p, int rank) {
        long a = 0L;
        if (rank == 1) {
            a = 50000L;
        } else if (rank == 2) {
            a = 100000L;
        } else if (rank == 3) {
            a = 250000L;
        } else if (rank == 4) {
            a = 500000L;
        } else if (rank == 5) {
            a = 2000000L;
        } else if (rank == 6) {
            a = 10000000L;
        } else if (rank == 7) {
            a = 25000000L;
        } else if (rank == 8) {
            a = 80000000L;
        } else if (rank == 9) {
            a = 300000000L;
        } else if (rank == 10) {
            a = 800000000L;
        }
        return a;
    }
}

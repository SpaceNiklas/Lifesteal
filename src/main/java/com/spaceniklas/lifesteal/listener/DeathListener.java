package com.spaceniklas.lifesteal.listener;

import com.spaceniklas.lifesteal.Lifesteal;
import com.spaceniklas.lifesteal.managers.HeartManager;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.util.Vector;

import java.nio.Buffer;

public class DeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(EntityDeathEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            Player k = p.getKiller();
            if (k != null && Lifesteal.config.getBoolean("RemoveHeartsWhenKilled")) {
                HeartManager.addHearts(2, k);
                HeartManager.removeHearts(2, p);
            }
            if (k == null && Lifesteal.config.getBoolean("RemoveHeartOnNaturalDeath")) {
                HeartManager.removeHearts(2, p);
            }

            if (Lifesteal.hearts.get(p.getUniqueId().toString()).equals(0)) {
                if (Lifesteal.config.getBoolean("enabled")) {
                    Lifesteal.banlist.add(p.getUniqueId().toString());
                    Location loc = new Location(Bukkit.getWorld(Lifesteal.config.getString("world")), Lifesteal.config.getDouble("x"), Lifesteal.config.getDouble("y"), Lifesteal.config.getDouble("x"));
                    if (Bukkit.getWorld(Lifesteal.config.getString("world")) != null) {
                        p.teleport(loc);
                        p.sendTitle(ChatColor.RED + "You died!", "");
                    }
                } else {
                    Bukkit.getBanList(BanList.Type.NAME).addBan(p.getName(), ChatColor.RED + "You died!", null, null);
                    p.kickPlayer(ChatColor.RED + "You died!");
                }
            }

        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        if (Lifesteal.hearts.get(p.getUniqueId().toString()).equals(0)) {
            if (Lifesteal.config.getBoolean("enabled")) {
                Location loc = new Location(Bukkit.getWorld(Lifesteal.config.getString("world")), Lifesteal.config.getDouble("x"), Lifesteal.config.getDouble("y"), Lifesteal.config.getDouble("x"));
                Bukkit.getScheduler().runTaskLater(Lifesteal.instance, () -> {
                    p.teleport(loc);
                }, 5);
            } else {
                Bukkit.getBanList(BanList.Type.NAME).addBan(p.getName(), ChatColor.RED + "You died!", null, null);
                p.kickPlayer(ChatColor.RED + "You died!");
            }
        }
    }
}

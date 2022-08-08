package com.spaceniklas.lifesteal.listener;

import com.spaceniklas.lifesteal.Lifesteal;
import com.spaceniklas.lifesteal.managers.HeartManager;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.checkerframework.checker.units.qual.Luminance;

import java.nio.Buffer;

public class DeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(EntityDeathEvent e){
        if(e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();
            Player k = p.getKiller();
            if(k != null && Lifesteal.config.getBoolean("RemoveHeartsWhenKilled")){
                HeartManager.addHearts(2, k);
                HeartManager.removeHearts(2, p);
            }
            if(k == null && Lifesteal.config.getBoolean("RemoveHeartOnNaturalDeath")){
                HeartManager.removeHearts(2, p);
            }

            if(Lifesteal.hearts.get(p.getUniqueId().toString()).equals(0)){
                Bukkit.getBannedPlayers().add(p);
                Bukkit.getBanList(BanList.Type.NAME).addBan(p.getName(), ChatColor.RED + "You died!", null, null);
                p.kickPlayer(ChatColor.RED + "You died!");
            }

        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e){
        Player p = e.getPlayer();
        if(Lifesteal.hearts.get(p.getUniqueId().toString()).equals(0)){
            Bukkit.getBannedPlayers().add(p);
            Bukkit.getBanList(BanList.Type.NAME).addBan(p.getName(), ChatColor.RED + "You died!", null, null);
            p.kickPlayer(ChatColor.RED + "You died!");
        }
    }
}

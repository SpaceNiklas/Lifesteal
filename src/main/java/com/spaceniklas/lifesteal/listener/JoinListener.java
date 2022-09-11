package com.spaceniklas.lifesteal.listener;

import com.spaceniklas.lifesteal.Lifesteal;
import com.spaceniklas.lifesteal.managers.HeartManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;
import java.util.UUID;


public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){

        Player p = e.getPlayer();
        UUID pu = p.getUniqueId();

        if(!Lifesteal.hearts.contains(pu.toString())) {
            Lifesteal.hearts.set(pu.toString(), 20);
            try {
                Lifesteal.hearts.save(Lifesteal.heartsfile);
            } catch (IOException ex) {
                Bukkit.getLogger().info("[Error] Can't save hearts.yml file!");
                ex.printStackTrace();
            }
        }

    }
    @EventHandler
    public void onReviveJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        try {
            if(Lifesteal.banlist.contains(e.getPlayer().getUniqueId().toString()) && Lifesteal.config.getList("worlds").contains(e.getPlayer().getWorld().getName()) && Lifesteal.config.getBoolean("enabled")) {
                Location loc = new Location(Bukkit.getWorld(Lifesteal.config.getString("world")), Lifesteal.config.getDouble("x"), Lifesteal.config.getDouble("y"), Lifesteal.config.getDouble("x"));
                if (Bukkit.getWorld(Lifesteal.config.getString("world")) != null) {
                    p.teleport(loc);
                    p.sendTitle(ChatColor.RED + "You died!", "");
                }
            }

            if (Lifesteal.revive.get(e.getPlayer().getUniqueId().toString()).equals(1)) {
                HeartManager.setHearts(8, e.getPlayer());
                Lifesteal.revive.set(e.getPlayer().getUniqueId().toString(), 0);
                try {
                    Lifesteal.revive.save(Lifesteal.revivefile);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }catch (NullPointerException x){

        }
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent e) {
        Player p = e.getPlayer();
        if(!Lifesteal.config.getList("worlds").contains(p.getWorld().getName())){
            p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
            p.setHealth(20);
            return;
        }
        if(Lifesteal.config.getList("worlds").contains(p.getWorld().getName()) && Lifesteal.banlist.contains(p.getUniqueId().toString())){
            Location loc = new Location(Bukkit.getWorld(Lifesteal.config.getString("world")), Lifesteal.config.getDouble("x"), Lifesteal.config.getDouble("y"), Lifesteal.config.getDouble("x"));
            p.teleport(loc);
            return;
        }
        if(Lifesteal.config.getList("worlds").contains(p.getWorld().getName())){
            HeartManager.setHearts(Lifesteal.hearts.getInt(p.getUniqueId().toString()), p);
            if (Lifesteal.revive.get(e.getPlayer().getUniqueId().toString()).equals(1) && Lifesteal.revive.get(e.getPlayer().getUniqueId().toString()) != null) {
                HeartManager.setHearts(8, e.getPlayer());
                Lifesteal.revive.set(e.getPlayer().getUniqueId().toString(), 0);
                try {
                    Lifesteal.revive.save(Lifesteal.revivefile);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

}

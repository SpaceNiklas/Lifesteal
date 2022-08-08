package com.spaceniklas.lifesteal.listener;

import com.spaceniklas.lifesteal.Lifesteal;
import com.spaceniklas.lifesteal.managers.HeartManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
        try {
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

}

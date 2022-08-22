package com.spaceniklas.lifesteal.listener;

import com.spaceniklas.lifesteal.Lifesteal;
import com.spaceniklas.lifesteal.managers.HeartManager;
import com.spaceniklas.lifesteal.menus.ReviveMenu;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ClickListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){

        Player p = e.getPlayer();

        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && !e.getClickedBlock().getType().equals(Material.CRAFTING_TABLE)){
            if(!p.getInventory().getItemInMainHand().equals(null)){
                if(p.getInventory().getItemInMainHand().hasItemMeta()){
                    if(p.getInventory().getItemInMainHand().getType().equals(Material.NETHER_STAR) && Lifesteal.config.getBoolean("EnableHearts")) {
                        if(!Lifesteal.config.getList("worlds").contains(p.getWorld().getName())&& Lifesteal.config.getBoolean("enabled")){
                            return;
                        }
                        HeartManager.addHearts(2, p);
                        p.sendMessage(ChatColor.GREEN + "You've received one heart!");
                        p.playNote(p.getLocation(), Instrument.PLING , Note.natural(1, Note.Tone.D));
                        p.getInventory().getItemInMainHand().setAmount(p.getInventory().getItemInMainHand().getAmount() - 1);
                    }
                }
            }
        }

        if(e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && !e.getClickedBlock().getType().equals(Material.CRAFTING_TABLE)){
            if(!p.getInventory().getItemInMainHand().equals(null)){
                if(p.getInventory().getItemInMainHand().hasItemMeta()){
                    if(p.getInventory().getItemInMainHand().getType().equals(Material.TOTEM_OF_UNDYING) && Lifesteal.config.getBoolean("EnableTotemOfRevival")) {
                        ReviveMenu.openReviveMenu(e.getPlayer());
                    }
                }
            }
        }




    }


}

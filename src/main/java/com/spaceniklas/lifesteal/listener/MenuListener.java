package com.spaceniklas.lifesteal.listener;

import com.spaceniklas.lifesteal.managers.HeartManager;
import com.spaceniklas.lifesteal.menus.DecisionMenu;
import com.spaceniklas.lifesteal.menus.ReviveMenu;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuListener implements Listener {

    public static String name;

    @EventHandler
    public void onReviveInvClick(InventoryClickEvent e){
        if(ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).equals(ChatColor.BLACK.toString() + "Revive Menu") && e.getCurrentItem() != null){

            Player p = (Player) e.getWhoClicked();

            if(e.getRawSlot() >9 && e.getRawSlot() < 44){
                e.setCancelled(true);
                p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1 ,1);
                if(!e.getCurrentItem().getItemMeta().getDisplayName().equals(null))
                    name = e.getCurrentItem().getItemMeta().getDisplayName();
                DecisionMenu.openDecisisonMenu(p);
            }else if(e.getRawSlot() == 0){
                p.closeInventory();
            }
            p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1 ,1);

            e.setCancelled(true);

        }

    }
    @EventHandler
    public void onDecisionInvClick(InventoryClickEvent e){
        try {
            if (ChatColor.translateAlternateColorCodes('&', e.getView().getTitle()).equals(ChatColor.BLACK.toString() + "Are you sure?") && e.getCurrentItem() != null) {

                Player p = (Player) e.getWhoClicked();

                if (e.getRawSlot() == 20) {
                    Bukkit.getBanList(BanList.Type.NAME).pardon(name);
                    HeartManager.playerRevive(Bukkit.getOfflinePlayer(name));
                    p.getInventory().getItemInMainHand().setAmount(0);
                    p.closeInventory();
                } else if (e.getRawSlot() == 0) {
                    p.closeInventory();
                } else if (e.getRawSlot() == 24) {
                    ReviveMenu.openReviveMenu(p);
                }
                p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
                e.setCancelled(true);

            }
        }catch (IllegalArgumentException x){

        }

    }


}

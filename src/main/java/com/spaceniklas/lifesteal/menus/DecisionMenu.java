package com.spaceniklas.lifesteal.menus;

import com.spaceniklas.lifesteal.listener.MenuListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DecisionMenu {

    public static void openDecisisonMenu(Player p){
        try {
            Inventory decisioninv = Bukkit.createInventory(p, 36, ChatColor.BLACK.toString() + "Are you sure?");

            //SKULL
            ItemStack skull = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
            skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer(MenuListener.name));
            skullMeta.setDisplayName(MenuListener.name);
            skull.setItemMeta(skullMeta);

            decisioninv.setItem(4, skull);

            //ARE YOU SURE?
            ItemStack sure = new ItemStack(Material.NAME_TAG);
            ItemMeta suremeta = sure.getItemMeta();
            suremeta.setDisplayName(ChatColor.WHITE + "Are you sure you want to revive " + ChatColor.YELLOW + MenuListener.name + "?");
            sure.setItemMeta(suremeta);

            decisioninv.setItem(13, sure);

            //YES
            ItemStack yes = new ItemStack(Material.GREEN_WOOL);
            ItemMeta yesmeta = yes.getItemMeta();
            yesmeta.setDisplayName(ChatColor.GREEN + "Yes!");
            yes.setItemMeta(yesmeta);

            decisioninv.setItem(20, yes);

            //NO
            ItemStack no = new ItemStack(Material.RED_WOOL);
            ItemMeta nometa = no.getItemMeta();
            nometa.setDisplayName(ChatColor.RED + "No!");
            no.setItemMeta(nometa);

            decisioninv.setItem(24, no);

            //CLOSE

            ItemStack close = new ItemStack(Material.BARRIER);
            ItemMeta closemeta = close.getItemMeta();
            closemeta.setDisplayName(ChatColor.RED + "Close");
            close.setItemMeta(closemeta);

            decisioninv.setItem(0, close);

            //FRAME

            ItemStack frame = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);

            for (int i : new int[]{1, 2, 3, 5, 6, 7, 8, 9, 17, 18, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35}) {
                decisioninv.setItem(i, frame);
            }

            p.openInventory(decisioninv);
        }catch (IllegalArgumentException x){

        }
    }
}

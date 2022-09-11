package com.spaceniklas.lifesteal.menus;

import com.spaceniklas.lifesteal.Lifesteal;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

public class ReviveMenu {

    public static void openReviveMenu(Player p) {
        Inventory reviveinv = Bukkit.createInventory(p, 45, ChatColor.BLACK.toString() + "Revive Menu");

        //TOTEM OF REVIVAL
        ItemStack totem = new ItemStack(Material.TOTEM_OF_UNDYING);
        totem.addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);
        ItemMeta totemmeta = totem.getItemMeta();
        totemmeta.setDisplayName(ChatColor.GOLD + "Totem of Revival");
        totemmeta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Right-Click to revive any banned player!"));
        totemmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        totem.setItemMeta(totemmeta);

        reviveinv.setItem(4, totem);

        int index = 0;
        List<Integer> intlist = new LinkedList<Integer>(Arrays.asList(10, 11, 12, 13, 14, 15, 16, 19, 20, 21, 22, 23, 24, 25, 28, 29, 30, 31, 32, 33, 34, 37, 38, 39, 40, 41, 42, 43));
        if (!Lifesteal.config.getBoolean("enabled")) {
            for (OfflinePlayer offlinePlayer : Bukkit.getBannedPlayers()) {
                if (Bukkit.getBanList(BanList.Type.NAME).getBanEntry(offlinePlayer.getName()).getReason().equals(ChatColor.RED + "You died!")) {
                    ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
                    SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
                    skullMeta.setOwningPlayer(offlinePlayer);
                    skullMeta.setDisplayName(offlinePlayer.getName());
                    skull.setItemMeta(skullMeta);

                    reviveinv.setItem(intlist.get(index), skull);
                    index++;
                }
            }
        }
        else if (Lifesteal.config.getBoolean("enabled")) {
                for (String str : Lifesteal.banlist) {
                    OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(UUID.fromString(str));
                    ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
                    SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
                    skullMeta.setOwningPlayer(offlinePlayer);
                    skullMeta.setDisplayName(offlinePlayer.getName());
                    skull.setItemMeta(skullMeta);

                    reviveinv.setItem(intlist.get(index), skull);
                    index++;
                }

            }

            //CLOSE

            ItemStack close = new ItemStack(Material.BARRIER);
            ItemMeta closemeta = close.getItemMeta();
            closemeta.setDisplayName(ChatColor.RED + "Close");
            close.setItemMeta(closemeta);

            reviveinv.setItem(0, close);

            //FRAME

            ItemStack frame = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);

            for (int i : new int[]{1, 2, 3, 5, 6, 7, 8, 9, 17, 18, 26, 27, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44}) {
                reviveinv.setItem(i, frame);
            }

            p.openInventory(reviveinv);
        }


    }

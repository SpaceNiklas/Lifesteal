package com.spaceniklas.lifesteal.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class GetCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 1 && args[0].equals("lifesteal:heart") && sender.hasPermission("lifesteal.get")){
            try {
                ItemStack heart = new ItemStack(Material.NETHER_STAR, 1);
                ItemMeta heartmeta = heart.getItemMeta();
                heartmeta.setDisplayName(ChatColor.RED + "Heart");
                heartmeta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Use 4 of these to craft a Totem of Revival!"));
                heart.setItemMeta(heartmeta);

                if (sender instanceof Player) {
                    ((Player) sender).getInventory().addItem(heart);
                }
            }catch (NullPointerException x){
                sender.sendMessage(ChatColor.RED + "Invalid Usage! Usage: /get <item> <amount> <player>");
            }
        }else
        if(args.length == 1 && args[0].equals("lifesteal:totem_of_revival")&& sender.hasPermission("lifesteal.get")){
            try {
                ItemStack revive = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
                revive.addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);
                ItemMeta revivemeta = revive.getItemMeta();
                revivemeta.setDisplayName(ChatColor.GOLD + "Totem of Revival");
                revivemeta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Right-Click to revive any banned player!"));
                revivemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                revive.setItemMeta(revivemeta);
                if (sender instanceof Player) {
                    ((Player) sender).getInventory().addItem(revive);
                }
            }catch (NullPointerException x){
                sender.sendMessage(ChatColor.RED + "Invalid Usage! Usage: /get <item> <amount> <player>");
            }
        }else
        if(args.length == 2 && args[0].equals("lifesteal:heart")&& sender.hasPermission("lifesteal.get")){
            try {
                ItemStack heart = new ItemStack(Material.NETHER_STAR, 1);
                ItemMeta heartmeta = heart.getItemMeta();
                heartmeta.setDisplayName(ChatColor.RED + "Heart");
                heartmeta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Use 4 of these to craft a Totem of Revival!"));
                heart.setItemMeta(heartmeta);
                if(sender instanceof Player) {
                    ((Player) sender).getInventory().addItem(heart);
                    ((Player) sender).getInventory().getItemInMainHand().setAmount(Integer.parseInt(args[1]));
                }
            }catch (NullPointerException x){
                sender.sendMessage(ChatColor.RED + "Invalid Usage! Usage: /get <item> <amount> <player>");
            }
        }else
        if(args.length == 2 && args[0].equals("lifesteal:totem_of_revival")&& sender.hasPermission("lifesteal.get")){
            try {
                ItemStack revive = new ItemStack(Material.TOTEM_OF_UNDYING, Integer.parseInt(args[1]));
                revive.addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);
                ItemMeta revivemeta = revive.getItemMeta();
                revivemeta.setDisplayName(ChatColor.GOLD + "Totem of Revival");
                revivemeta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Right-Click to revive any banned player!"));
                revivemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                revive.setItemMeta(revivemeta);
                if(sender instanceof Player) {
                    ((Player) sender).getInventory().addItem(revive);
                    ((Player) sender).getInventory().getItemInMainHand().setAmount(Integer.parseInt(args[1]));
                }
            }catch (NullPointerException x){
                sender.sendMessage(ChatColor.RED + "Invalid Usage! Usage: /get <item> <amount> <player>");
            }
        }else
        if(args.length == 3 && args[0].equals("lifesteal:heart")&& sender.hasPermission("lifesteal.get")){
            try {
                ItemStack heart = new ItemStack(Material.NETHER_STAR, 1);
                ItemMeta heartmeta = heart.getItemMeta();
                heartmeta.setDisplayName(ChatColor.RED + "Heart");
                heartmeta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Use 4 of these to craft a Totem of Revival!"));
                heart.setItemMeta(heartmeta);
                if(sender instanceof Player) {
                    Bukkit.getPlayer(args[2]).getInventory().addItem(heart);
                    Bukkit.getPlayer(args[2]).getInventory().getItemInMainHand().setAmount(Integer.parseInt(args[1]));

                }
            }catch (NullPointerException x){
                sender.sendMessage(ChatColor.RED + "Invalid Usage! Usage: /get <item> <amount> <player>");
            }
        }else
        if(args.length == 3 && args[0].equals("lifesteal:totem_of_revival")&& sender.hasPermission("lifesteal.get")){
            try {
                ItemStack revive = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
                revive.addUnsafeEnchantment(Enchantment.WATER_WORKER, 1);
                ItemMeta revivemeta = revive.getItemMeta();
                revivemeta.setDisplayName(ChatColor.GOLD + "Totem of Revival");
                revivemeta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Right-Click to revive any banned player!"));
                revivemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                revive.setItemMeta(revivemeta);
                if(sender instanceof Player) {
                    Bukkit.getPlayer(args[2]).getInventory().addItem(revive);
                    Bukkit.getPlayer(args[2]).getInventory().getItemInMainHand().setAmount(Integer.parseInt(args[1]));
                }
            }catch (NullPointerException x){
                sender.sendMessage(ChatColor.RED + "Invalid Usage! Usage: /get <item> <amount> <player>");
            }
        }


        return false;
    }
}

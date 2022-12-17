package com.spaceniklas.lifesteal.commands;

import com.spaceniklas.lifesteal.Lifesteal;
import com.spaceniklas.lifesteal.managers.HeartManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class WithdrawCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //   /withdraw 10
        if(args.length == 1 && sender instanceof Player && Lifesteal.config.getBoolean("EnableWithdrawCommand")) {
            if(args[0].equalsIgnoreCase("2147483647")){
                sender.sendMessage(ChatColor.RED + "You can't withdraw more hearts than you have!");
                return false;
            }
            Player p = (Player) sender;
            if(!Lifesteal.config.getList("worlds").contains(p.getWorld().getName())&& Lifesteal.config.getBoolean("enabled")){
                return false;
            }
            int amount = Integer.parseInt(args[0]);

            ItemStack heart = Lifesteal.heart;

            if(amount*2 < (int) Lifesteal.hearts.get(p.getUniqueId().toString()) && amount > 0) {
                p.getInventory().addItem(heart);

                HeartManager.removeHearts(amount * 2, p);
                if (amount == 1) {
                    p.sendMessage(ChatColor.GOLD + "Successfully withdrawn heart");
                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
                }
                if (amount != 1) {
                    p.sendMessage(ChatColor.GOLD + "Successfully withdrawn hearts");
                    p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 1);
                }
            }else {
                p.sendMessage(ChatColor.RED + "You can't withdraw more hearts than you have!");
            }

        }else if(Lifesteal.config.getBoolean("EnableWithdrawCommand")){
            sender.sendMessage(ChatColor.RED + "Invalid usage! /withdraw <amount>");
        }else {
            sender.sendMessage(ChatColor.RED + "This command is disabled!");
        }
        return false;
    }
}

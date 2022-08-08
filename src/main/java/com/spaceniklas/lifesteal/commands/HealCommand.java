package com.spaceniklas.lifesteal.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 2 && sender.hasPermission("lifesteal.heal")){
            Bukkit.getPlayer(args[0]).setHealth(Integer.parseInt(args[1]));
            if(sender instanceof Player)
                ((Player) sender).playSound(((Player) sender).getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 1);
            sender.sendMessage(ChatColor.GREEN + args[0] + " was successfully healed!");
        }else if(args.length == 0 && sender instanceof Player){
            ((Player) sender).setHealth(((Player) sender).getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
            if(sender instanceof Player)
                ((Player) sender).playSound(((Player) sender).getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 1);
            sender.sendMessage(ChatColor.GREEN + "You were successfully healed!");
        }else {
            sender.sendMessage(ChatColor.RED + "Invalid Usage! Do /heal <username> <amount>!");
        }



        return false;
    }
}

package com.spaceniklas.lifesteal.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class HealCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 2 && sender.hasPermission("lifesteal.heal")){
            if(Bukkit.getPlayer(args[0]) != null && Objects.requireNonNull(Bukkit.getPlayer(args[0])).isOnline()) {
                Bukkit.getPlayer(args[0]).setHealth(Integer.parseInt(args[1]));
                if (sender instanceof Player)
                    ((Player) sender).playSound(((Player) sender).getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 1);
                sender.sendMessage(ChatColor.GREEN + args[0] + " was successfully healed!");
            }else{
                sender.sendMessage(ChatColor.RED + "Given player is not online!");
            }
        }else if(args.length == 0 && sender instanceof Player && sender.hasPermission("lifesteal.heal")){
            ((Player) sender).setHealth(((Player) sender).getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
            if(sender instanceof Player)
                ((Player) sender).playSound(((Player) sender).getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 1, 1);
            sender.sendMessage(ChatColor.GREEN + "You were successfully healed!");
        }else if(sender.hasPermission("lifesteal.heal")){
            sender.sendMessage(ChatColor.RED + "Invalid Usage! Do /heal <username> <amount>!");
        }else{
            sender.sendMessage(ChatColor.RED + "You do not have the permission to execute this command!");
        }



        return false;
    }
}

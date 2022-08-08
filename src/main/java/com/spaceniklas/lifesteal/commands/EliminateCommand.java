package com.spaceniklas.lifesteal.commands;

import com.spaceniklas.lifesteal.managers.HeartManager;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EliminateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length == 1 && sender.hasPermission("lifesteal.eliminate")){
            try{
                Player p = Bukkit.getPlayer(args[0]);
                HeartManager.setHearts(0, p);
                Bukkit.getBanList(BanList.Type.NAME).addBan(p.getName(), ChatColor.RED + "You died!", null, null);
                p.kickPlayer(ChatColor.RED + "You died!");
                sender.sendMessage(ChatColor.GREEN + p.getName() +" was eliminated!");
            }catch (NullPointerException x) {
                OfflinePlayer p = Bukkit.getOfflinePlayer(args[0]);
                Bukkit.getBanList(BanList.Type.NAME).addBan(p.getName(), ChatColor.RED + "You died!", null, null);
                sender.sendMessage(ChatColor.GREEN + p.getName() + " was eliminated!");
            }
        }
        return false;
    }
}

package com.spaceniklas.lifesteal.commands;

import com.spaceniklas.lifesteal.managers.HeartManager;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReviveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //      /revive <username>
        if(args.length == 1 && sender.hasPermission("lifesteal.revive")){
            try{
                Bukkit.getBanList(BanList.Type.NAME).pardon(args[0]);
                HeartManager.playerRevive(Bukkit.getOfflinePlayer(args[0]));
                sender.sendMessage(ChatColor.GREEN + Bukkit.getPlayer(args[0]).getName() +" was revived!");
            }catch(NullPointerException x){
                sender.sendMessage(ChatColor.GREEN + args[0] +" was revived!");
            }
        }

        return false;
    }
}

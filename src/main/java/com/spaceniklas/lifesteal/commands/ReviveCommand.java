package com.spaceniklas.lifesteal.commands;

import com.spaceniklas.lifesteal.Lifesteal;
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
                if(!Lifesteal.config.getBoolean("enabled")) {
                    Bukkit.getBanList(BanList.Type.NAME).pardon(Bukkit.getOfflinePlayer(args[0]).getName());
                }else{
                    Lifesteal.banlist.remove(Bukkit.getPlayer(args[0]).getUniqueId().toString());
                }
                HeartManager.playerRevive(Bukkit.getOfflinePlayer(args[0]));
                sender.sendMessage(ChatColor.GREEN + Bukkit.getPlayer(args[0]).getName() +" was revived!");
            }catch(NullPointerException x){
                sender.sendMessage(ChatColor.GREEN + args[0] +" was revived!");
            }
        }

        return false;
    }
}

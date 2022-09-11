package com.spaceniklas.lifesteal.commands;

import com.spaceniklas.lifesteal.Lifesteal;
import com.spaceniklas.lifesteal.managers.HeartManager;
import org.bukkit.*;
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

                if(!Lifesteal.config.getBoolean("enabled")) {
                    p.kickPlayer(ChatColor.RED + "You died!");
                    Bukkit.getBanList(BanList.Type.NAME).addBan(p.getName(), ChatColor.RED + "You died!", null, null);
                }else{
                    Lifesteal.banlist.add(p.getUniqueId().toString());
                    Location loc = new Location(Bukkit.getWorld(Lifesteal.config.getString("world")), Lifesteal.config.getDouble("x"), Lifesteal.config.getDouble("y"), Lifesteal.config.getDouble("x"));
                    if(Bukkit.getWorld(Lifesteal.config.getString("world")) != null)
                        p.teleport(loc);
                        p.sendTitle(ChatColor.RED + "You died!", "");
                    }

                sender.sendMessage(ChatColor.GREEN + p.getName() +" was eliminated!");
            }catch (NullPointerException x) {
                OfflinePlayer p = Bukkit.getOfflinePlayer(args[0]);
                if(!Lifesteal.config.getBoolean("enabled")) {
                    Bukkit.getBanList(BanList.Type.NAME).addBan(p.getName(), ChatColor.RED + "You died!", null, null);
                }else{
                    Lifesteal.banlist.add(p.getUniqueId().toString());
                    for(World w : Bukkit.getWorlds()){
                        if(!Lifesteal.worlds.contains(w.getName())){
                            p.getBedSpawnLocation().setWorld(w);
                            break;
                        }
                    }
                }
                sender.sendMessage(ChatColor.GREEN + p.getName() + " was eliminated!");
            }
        }
        return false;
    }
}

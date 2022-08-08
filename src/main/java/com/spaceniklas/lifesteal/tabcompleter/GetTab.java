package com.spaceniklas.lifesteal.tabcompleter;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GetTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        List<String> results = new ArrayList<>();

        if(args.length == 1) {
            results.add("lifesteal:heart");
            results.add("lifesteal:totem_of_revival");
        }else
        if(args.length == 2){
            results.add("[<count>]");
        }else
        if(args.length == 3){
            for(Player p : Bukkit.getOnlinePlayers()){
                results.add(p.getName());
            }
        }

        return results;
    }
}

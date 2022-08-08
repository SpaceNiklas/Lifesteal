package com.spaceniklas.lifesteal.tabcompleter;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class ReviveTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        List<String> result = new ArrayList<>();

        if(args.length == 1){
            for (OfflinePlayer op : Bukkit.getBannedPlayers()){
                result.add(op.getName());
            }
        }

        return result;
    }
}

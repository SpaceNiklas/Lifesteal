package com.spaceniklas.lifesteal.tabcompleter;

import com.spaceniklas.lifesteal.Lifesteal;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReviveTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        List<String> result = new ArrayList<>();

        if(args.length == 1 && !Lifesteal.config.getBoolean("enabled")){
            for (OfflinePlayer op : Bukkit.getBannedPlayers()){
                result.add(op.getName());
            }
        }else if(args.length == 1 && Lifesteal.config.getBoolean("enabled")){
            for (String str : Lifesteal.banlist){
                result.add(Bukkit.getOfflinePlayer(UUID.fromString(str)).getName());
            }
        }

        return result;
    }
}

package com.spaceniklas.lifesteal.tabcompleter;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class EliminateTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {

        List<String> result = new ArrayList<>();

        if(args.length == 1 && sender.isOp()){
            for (OfflinePlayer op : Bukkit.getOfflinePlayers()){
                result.add(op.getName());
            }
            for (Player p : Bukkit.getOnlinePlayers()){
                result.add(p.getName());
            }
        }

        return null;
    }
}

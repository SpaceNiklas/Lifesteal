package com.spaceniklas.lifesteal.commands;

import com.spaceniklas.lifesteal.managers.HeartManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        //      /set <username> <amount>
        if(args.length == 2 && sender instanceof Player && sender.hasPermission("lifesteal.set")){
            Player target = Bukkit.getPlayer(args[0]);
            Player p = (Player) sender;
            int amount = Integer.parseInt(args[1]);

            HeartManager.setHearts(amount, target);
            p.sendMessage(ChatColor.GREEN + "Successfully set " + target.getName() + " hearts to " + amount + "!");
            p.playNote(p.getLocation(), Instrument.PLING , Note.natural(1, Note.Tone.D));

        }else if(args.length == 1 && sender instanceof Player && sender.isOp()){
            try {
                Player p = (Player) sender;
                int amount = Integer.parseInt(args[0]);

                HeartManager.setHearts(amount, p);
                p.sendMessage(ChatColor.GREEN + "Successfully set your hearts to " + amount + "!");
                p.playNote(p.getLocation(), Instrument.PLING, Note.natural(1, Note.Tone.D));
            }catch (NumberFormatException x){
                sender.sendMessage(ChatColor.RED + "You entered an invalid number!");
            }
        }


        return false;
    }
}

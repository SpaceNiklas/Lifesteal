package com.spaceniklas.lifesteal.managers;

import com.spaceniklas.lifesteal.Lifesteal;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import java.io.IOException;

public class HeartManager {


    public static void removeHearts(int amount, Player target){

    if(Lifesteal.config.getList("worlds").contains(target.getWorld().getName())) {
        if(!Lifesteal.config.getList("worlds").contains(target.getWorld().getName()) && Lifesteal.config.getBoolean("enabled")){
            return;
        }
        int famount = (int) Lifesteal.hearts.get(target.getUniqueId().toString()) - amount;

        Lifesteal.hearts.set(target.getUniqueId().toString(), famount);
        try {
            Lifesteal.hearts.save(Lifesteal.heartsfile);
        } catch (IOException e) {
            e.printStackTrace();
            Bukkit.getLogger().info("[Error] Can't save hearts.yml file!");
        }

        target.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(famount);
    }
    }

    public static void addHearts(int amount, Player target){
        if(Lifesteal.config.getList("worlds").contains(target.getWorld().getName())) {
            if(!Lifesteal.config.getList("worlds").contains(target.getWorld().getName()) && Lifesteal.config.getBoolean("enabled")){
                return;
            }
            int famount = (int) Lifesteal.hearts.get(target.getUniqueId().toString()) + amount;

            Lifesteal.hearts.set(target.getUniqueId().toString(), famount);
            try {
                Lifesteal.hearts.save(Lifesteal.heartsfile);
            } catch (IOException e) {
                e.printStackTrace();
                Bukkit.getLogger().info("[Error] Can't save hearts.yml file!");
            }


            target.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(famount);
        }
    }

    public static void setHearts(int amount, Player target){
        if(!Lifesteal.config.getList("worlds").contains(target.getWorld().getName())&& Lifesteal.config.getBoolean("enabled")){
            return;
        }
        if(Lifesteal.config.getList("worlds").contains(target.getWorld().getName())) {
            Lifesteal.hearts.set(target.getUniqueId().toString(), amount);
            try {
                Lifesteal.hearts.save(Lifesteal.heartsfile);
            } catch (IOException e) {
                e.printStackTrace();
                Bukkit.getLogger().info("[Error] Can't save hearts.yml file!");
            }


            target.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(amount);
        }
    }

    public static void playerRevive(OfflinePlayer target){
        Lifesteal.revive.set(target.getUniqueId().toString(), 1);
        Lifesteal.banlist.remove(target.getUniqueId().toString());
        try {
            Lifesteal.revive.save(Lifesteal.revivefile);
        } catch (IOException e) {
            e.printStackTrace();
            Bukkit.getLogger().info("[Error] Can't save revive.yml file!");
        }
    }




}

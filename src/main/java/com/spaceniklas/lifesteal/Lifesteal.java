package com.spaceniklas.lifesteal;

import com.spaceniklas.lifesteal.commands.*;
import com.spaceniklas.lifesteal.listener.ClickListener;
import com.spaceniklas.lifesteal.listener.DeathListener;
import com.spaceniklas.lifesteal.listener.JoinListener;
import com.spaceniklas.lifesteal.listener.MenuListener;
import com.spaceniklas.lifesteal.tabcompleter.EliminateTab;
import com.spaceniklas.lifesteal.tabcompleter.GetTab;
import com.spaceniklas.lifesteal.tabcompleter.ReviveTab;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Lifesteal extends JavaPlugin {

    public static YamlConfiguration hearts;
    public static File heartsfile;
    public static YamlConfiguration revive;
    public static File revivefile;
    public static FileConfiguration config;

    @Override
    public void onEnable() {

        Bukkit.getLogger().info("[Lifesteal] Plugin is up!");

        config = this.getConfig();
        this.saveDefaultConfig();

        craftingRecipe();

        /*
         * REGISTER
         */

        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new MenuListener(), this);
        Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new ClickListener(), this);

        this.getCommand("withdraw").setExecutor(new WithdrawCommand());
        this.getCommand("revive").setExecutor(new ReviveCommand());
        this.getCommand("revive").setTabCompleter(new ReviveTab());
        this.getCommand("set").setExecutor(new SetCommand());
        this.getCommand("heal").setExecutor(new HealCommand());
        this.getCommand("eliminate").setExecutor(new EliminateCommand());
        this.getCommand("eliminate").setTabCompleter(new EliminateTab());
        this.getCommand("get").setExecutor(new GetCommand());
        this.getCommand("get").setTabCompleter(new GetTab());



        /*
         * FILE
         */
        heartsfile = new File(getDataFolder(), "hearts.yml");

        if(!heartsfile.exists()){
            try {
                heartsfile.createNewFile();
            } catch (IOException e) {
                Bukkit.getLogger().info("[Error] Can't load hearts.yml file!");
                e.printStackTrace();
                return;
            }
        }

        Lifesteal.hearts = YamlConfiguration.loadConfiguration(heartsfile);

        revivefile = new File(getDataFolder(), "revive.yml");

        if(!revivefile.exists()){
            try {
                revivefile.createNewFile();
            } catch (IOException e) {
                Bukkit.getLogger().info("[Error] Can't load revive.yml file!");
                e.printStackTrace();
                return;
            }
        }

        Lifesteal.revive = YamlConfiguration.loadConfiguration(revivefile);
    }

    public void craftingRecipe(){

        ItemStack heart = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta heartmeta = heart.getItemMeta();
        heartmeta.setDisplayName(ChatColor.RED + "Heart");
        heartmeta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Use 4 of these to craft a Totem of Revival!"));
        heart.setItemMeta(heartmeta);

        ShapedRecipe heartrecipe = new ShapedRecipe(new NamespacedKey(this, "heart"), heart);
        heartrecipe.shape(
                "DOD",
                "OTO",
                "DOD");

        heartrecipe.setIngredient('D', Material.DIAMOND_BLOCK);
        heartrecipe.setIngredient('O', Material.OBSIDIAN);
        heartrecipe.setIngredient('T', Material.TOTEM_OF_UNDYING);
        heartrecipe.setGroup("Lifesteal");
        if(config.getBoolean("EnableHeartCrafting"))
            Bukkit.addRecipe(heartrecipe);

        ItemStack revive = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
        revive.addUnsafeEnchantment(Enchantment.WATER_WORKER , 1);
        ItemMeta revivemeta = revive.getItemMeta();
        revivemeta.setDisplayName(ChatColor.GOLD + "Totem of Revival");
        revivemeta.setLore(Arrays.asList(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "Right-Click to revive any banned player!"));
        revivemeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        revive.setItemMeta(revivemeta);

        ShapedRecipe reviverecipe = new ShapedRecipe(new NamespacedKey(this, "revive"), revive);
        reviverecipe.shape(
                "DHD",
                "HTH",
                "DHD");

        reviverecipe.setIngredient('D', Material.DIAMOND_BLOCK);
        reviverecipe.setIngredient('H', new RecipeChoice.ExactChoice(heart));// part not working
        reviverecipe.setIngredient('T', Material.TOTEM_OF_UNDYING);
        reviverecipe.setGroup("Lifesteal");

        if(config.getBoolean("EnableTotemOfRevivalCrafting"))
            Bukkit.addRecipe(reviverecipe);


    }

}

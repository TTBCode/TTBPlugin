package org.TTB.TTBUtils;

import java.util.logging.Logger;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

import org.TTB.TTBUtils.Commands.clearinv;
import org.TTB.TTBUtils.Commands.help;
import org.TTB.TTBUtils.Commands.ipinfo;
import org.TTB.TTBUtils.Commands.kick;
import org.TTB.TTBUtils.Commands.pinfo;
import org.TTB.TTBUtils.Commands.rankup;
import org.TTB.TTBUtils.Listeners.PlayerListener;
import org.TTB.TTBUtils.Util.ChatUtils;
import org.TTB.TTBUtils.Util.PlayerFreeze;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class TTBUtils extends JavaPlugin
{

    /*
    *
    *Copyright 2015 Davin J Luirette
    */

    private static final Logger log = Logger.getLogger("Minecraft");
    public Economy econ = null;
    public Permission perms = null;
    public ChatUtils chatutils;
    public PlayerFreeze pf;
    
    
    @Override
    public void onDisable()
    {
        final PluginDescriptionFile pdfFile = this.getDescription();
        log.info(pdfFile.getName() + " Version: " + pdfFile.getVersion() + " Has Been Disabled !");
   
        
        }

    @Override
    public void onEnable()
    {
        
        final PluginDescriptionFile pdfFile = this.getDescription();
        log.info(pdfFile.getName() + " Version: " + pdfFile.getVersion() + " Has Been Enabled !");
    
        
        Init();
        setupCommands();
        
    }
    
    public void setupCommands()
    {
        
        getCommand("help").setExecutor(new help(this));
        getCommand("rankup").setExecutor(new rankup(this));
        getCommand("pinfo").setExecutor(new pinfo(this));
        getCommand("clearinv").setExecutor(new clearinv(this));
        getCommand("kick").setExecutor(new kick(this));
        getCommand("ipinfo").setExecutor(new ipinfo(this));
        
    }
    
    //Initialize The Plugin Features
    public void Init() 
    {
        
        //Setup Vault Economy Hookin
        if (!setupEconomy() ) 
        {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        
        //Setup Vault Permissions Hookin
        setupPermissions();
        
        //Initiate the Player Freeze
        pf = new PlayerFreeze(this);
        
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
    }

    private boolean setupEconomy()
    {
        if (getServer().getPluginManager().getPlugin("Vault") == null)
        {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null)
        {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    
    private boolean setupPermissions() 
    {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }


    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args)
    {
        if(!(sender instanceof Player))
        {
            log.info("Only players are supported for TTBUtils, but you should not do this!!!");
            return true;
        }

        return false;

    }


}

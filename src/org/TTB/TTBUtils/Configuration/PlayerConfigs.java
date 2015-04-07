package org.TTB.TTBUtils.Configuration;

import java.io.File;
import java.io.IOException;

import org.TTB.TTBUtils.TTBUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class PlayerConfigs
{

    public TTBUtils pl;

    public PlayerConfigs()
    {
    }
    
    public static PlayerConfigs instance = new PlayerConfigs();

    public static PlayerConfigs getInstance()
    {
        return instance;
    }

    public FileConfiguration data;
    public File dfile;

    public FileConfiguration getPlayerFile(final Player p)
    {
        final File playersDir = new File(pl.getDataFolder() + File.separator+ "players");
        if (!playersDir.exists())
        {
            playersDir.mkdir();
        }
        dfile = new File(pl.getDataFolder() + File.separator + "players", p.getName() + ".yml");

        if (!dfile.exists())
        {
            try
            {
                dfile.createNewFile();
            } catch (final IOException e)
            {
                Bukkit.getServer() .getLogger() .severe(ChatColor.RED + "Could not create "+ p.getName() + ".yml!");
                e.printStackTrace();
            }
        }

        data = YamlConfiguration.loadConfiguration(dfile);
        return data;
    }

    public void createFile(final Player p)
    {
        dfile = new File(pl.getDataFolder() + File.separator + "players",  p.getName() + ".yml");
        if (!dfile.exists())
        {
            try
            {
                dfile.createNewFile();
            } catch (final IOException e)
            {
                Bukkit.getServer().getLogger() .severe(ChatColor.RED + "Could not create "+ p.getName() + ".yml!");
                e.printStackTrace();
            }
        }
    }

    public void savePlayerFile(final Player p)
    {
        dfile = new File(pl.getDataFolder() + File.separator + "players", p.getName() + ".yml");
        try
        {
            data.save(dfile);
        } catch (final IOException e)
        {
            Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not SAVE " + p.getName()+ ".yml!");
            e.printStackTrace();
        }
    }

}

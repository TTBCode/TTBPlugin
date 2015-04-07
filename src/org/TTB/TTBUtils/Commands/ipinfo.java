package org.TTB.TTBUtils.Commands;


import org.TTB.TTBUtils.TTBUtils;
import org.TTB.TTBUtils.Util.IPUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ipinfo implements CommandExecutor
{

    public static TTBUtils plugin;
    
    public ipinfo(TTBUtils ttbUtils)
    {
        plugin = ttbUtils;
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command,  final String label, final String[] args)
    {
        
        final Player player = (Player) sender;
        
       if(label.equalsIgnoreCase("ipinfo") && plugin.perms.playerHas(player, "ttbutils.cmd.staff.ipinfo"))
       {
           if(args.length == 0)
           {
               String p = player.getAddress().toString();
               player.sendMessage(plugin.chatutils.header + "Time: " + IPUtils.ipToTime(p));
               player.sendMessage(plugin.chatutils.header + "City: " + IPUtils.getCityName(p));
               player.sendMessage(plugin.chatutils.header + "State: " + IPUtils.getStateName(p));
               player.sendMessage(plugin.chatutils.header + "Country: " + IPUtils.getCountryName(p));
               player.sendMessage(plugin.chatutils.header + "Country Code: " + IPUtils.getCountryCode(p));
               player.sendMessage(plugin.chatutils.header + "Weather: " + IPUtils.getWeather(p));
               
               return true;
           }else
               if(args.length == 1)
               {
                   final Player tPlayer = player.getServer().getPlayer(args[0]);
                   String p = tPlayer.getAddress().toString();
                   player.sendMessage(plugin.chatutils.header + "Time: " + IPUtils.ipToTime(p));
                   player.sendMessage(plugin.chatutils.header + "City: " + IPUtils.getCityName(p));
                   player.sendMessage(plugin.chatutils.header + "State: " + IPUtils.getStateName(p));
                   player.sendMessage(plugin.chatutils.header + "Country: " + IPUtils.getCountryName(p));
                   player.sendMessage(plugin.chatutils.header + "Country Code: " + IPUtils.getCountryCode(p));
                   player.sendMessage(plugin.chatutils.header + "Weather: " + IPUtils.getWeather(p));
                   return true;
               }
       }else
       {
           player.sendMessage(plugin.chatutils.noperm);
       }
        
        return false;
    }
    
}

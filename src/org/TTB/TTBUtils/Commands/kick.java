package org.TTB.TTBUtils.Commands;


import org.TTB.TTBUtils.TTBUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class kick implements CommandExecutor
{

    public static TTBUtils plugin;
    
    public kick(TTBUtils ttbUtils)
    {
        plugin = ttbUtils;
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command,  final String label, final String[] args)
    {
        
        final Player player = (Player) sender;
        
       if(label.equalsIgnoreCase("kick") && plugin.perms.playerHas(player, "ttbutils.cmd.staff.kick"))
       {
           if(args.length == 0)
           {
               player.sendMessage(plugin.chatutils.header + "Please Use /kick <playername>");
               return true;
           }else
               if(args.length == 1)
               {
                   final Player tPlayer = player.getServer().getPlayer(args[0]);
                   tPlayer.kickPlayer(plugin.chatutils.kick + player.getDisplayName());
                   player.sendMessage(plugin.chatutils.header + "You Have Just Kicked " + tPlayer.getDisplayName());
                   return true;
               }
       }else
       {
           player.sendMessage(plugin.chatutils.noperm);
       }
        
        return false;
    }
    
}

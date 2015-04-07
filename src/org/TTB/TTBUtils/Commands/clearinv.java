package org.TTB.TTBUtils.Commands;


import org.TTB.TTBUtils.TTBUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class clearinv implements CommandExecutor
{

    public static TTBUtils plugin;
    
    public clearinv(TTBUtils ttbUtils)
    {
        plugin = ttbUtils;
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command,  final String label, final String[] args)
    {
        
        final Player player = (Player) sender;
        
       if(label.equalsIgnoreCase("clearinv") && plugin.perms.playerHas(player, "ttbutils.cmd.staff.clearinv"))
       {
           if(args.length == 0)
           {
               player.sendMessage(plugin.chatutils.header + "Your Inventory Has Been Cleared!");
               player.getInventory().clear();
               player.getInventory().setArmorContents(null);
               return true;
           }else
               if(args.length == 1)
               {
                   final Player tPlayer = player.getServer().getPlayer(args[0]);
                   player.sendMessage(plugin.chatutils.header + tPlayer + "'s Inventory Has Been Cleared!");
                   tPlayer.sendMessage(plugin.chatutils.header + "Your Inventory Has Been Cleared!");
                   tPlayer.getInventory().clear();
                   tPlayer.getInventory().setArmorContents(null);
                   return true;
               }
       }else
       {
           player.sendMessage(plugin.chatutils.noperm);
       }
        
        return false;
    }
    
}

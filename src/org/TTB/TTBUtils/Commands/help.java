package org.TTB.TTBUtils.Commands;

import org.TTB.TTBUtils.TTBUtils;
import org.TTB.TTBUtils.Util.HelpScreen;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class help implements CommandExecutor
{

    public static TTBUtils plugin;
    public HelpScreen hs = new HelpScreen("TTB Help");
    
    public help(TTBUtils ttbUtils)
    {
        plugin = ttbUtils;
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command,  final String label, final String[] args)
    {
        
        final Player player = (Player) sender;
        
        hs.setHeader(ChatColor.WHITE + "[" + ChatColor.AQUA + "<name> Page <page> of <maxpage>");
        hs.setFormat("<name>: <desc>");
        hs.setFlipColor(ChatColor.GRAY, ChatColor.DARK_GRAY);
        
        hs.setEntry("/ttbhelp", "Shows This Help Screen");
        hs.setEntry("/rankup <rank>", "Pay To Rank Up");
        
        if(plugin.perms.playerHas(player, "ttb.staff"))
        {
            hs.setEntry("/pinfo","Show Player Info");
            hs.setEntry("/ipinfo", "Show a players irl info");
            hs.setEntry("/clearinv", "Clear Inventory");
            hs.setEntry("/kick", "Kick a player from the server");
        }
        
        if(command.getName().equalsIgnoreCase("ttbhelp"))
        {
            if(args.length == 0)
            {
                hs.sendTo(sender, 1, 5);
            }else
                if(args.length == 1)
                {
                    hs.sendTo(sender, Integer.parseInt(args[0]), 5);
                }
            return true;
        }
        
        return false;
    }
    
}

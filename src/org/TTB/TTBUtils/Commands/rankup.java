package org.TTB.TTBUtils.Commands;

import net.milkbowl.vault.economy.EconomyResponse;

import org.TTB.TTBUtils.TTBUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class rankup implements CommandExecutor
{

    public static TTBUtils plugin;

    public rankup(TTBUtils ttbUtils)
    {
        plugin = ttbUtils;
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command command,  final String label, final String[] args)
    {

        Player player = (Player) sender;

        if(command.getLabel().equals("rankup")) 
        {


            if(args.length == 0)
            {
                sender.sendMessage(plugin.chatutils.header + "Rank Up Choices");
                sender.sendMessage(plugin.chatutils.header + "Merchant - Pay 2500 for Merchant");
                return true;
            }

            if(args.length == 1)
            {
                String cmd=args[0];
                if(cmd.equalsIgnoreCase("merchant"))
                {
                    if(plugin.perms.has(player, "ttbutils.cmd.user.merchant"))
                    {
                        double amt = 2500.00;
                        EconomyResponse er = plugin.econ.withdrawPlayer(player ,amt);
                        if(er.transactionSuccess()) 
                        {
                            sender.sendMessage(plugin.chatutils.header+" You are now a Merchant! It Costed You " +plugin. econ.format(er.amount) );
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "pex user " + player.getName() +" group set merchant");
                        } else
                        {
                            sender.sendMessage(String.format("An error occured: %s", er.errorMessage));
                        }
                        return true;
                    }else
                    {
                        player.sendMessage(plugin.chatutils.noperm);
                    }
                }else
                    {
                        return false;
                    }
            }
        }

        return false;
    }

}

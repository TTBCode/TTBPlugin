package org.TTB.TTBUtils.Commands;


import org.TTB.TTBUtils.TTBUtils;
import org.TTB.TTBUtils.Util.UUIDApi;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class pinfo implements CommandExecutor
{

    public static TTBUtils plugin;

    public pinfo(final TTBUtils tu)
    {
        plugin = tu;
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd,
            final String label, final String[] args)
    {
        final Player player = (Player) sender;

        if (label.equalsIgnoreCase("pinfo"))
        {
            if (player.hasPermission("ttbutils.cmd.staff.pinfo"))
            {
                if(args.length == 0)
                {
                    player.sendMessage(ChatColor.DARK_RED + "Usage: /pinfo <Username>");
                    return true;
                }else
                if(args.length == 1)
                {
                    Player targetPlayer = player.getServer().getPlayer(args[0]);
                    
                    String tuuid = UUIDApi.getUUID(args[0]);
                    
                    player.sendMessage(plugin.chatutils.header + "Username: " + targetPlayer.getName());
                    player.sendMessage(plugin.chatutils.header + "UUID: " + tuuid);
                    player.sendMessage(plugin.chatutils.header + "IP: " + targetPlayer.getAddress()); 
                    player.sendMessage(plugin.chatutils.header + "Gamemode: " + targetPlayer.getGameMode());
                    player.sendMessage(plugin.chatutils.header + "Flying: " + targetPlayer.isFlying());
                    player.sendMessage(plugin.chatutils.header + "Health: " + targetPlayer.getHealth()+ " | Food: " + targetPlayer.getFoodLevel());
                    player.sendMessage(plugin.chatutils.header + "X: " + targetPlayer.getLocation().getBlockX()  + " | Y: " + targetPlayer.getLocation().getBlockY()+ " | Z: " + targetPlayer.getLocation().getBlockZ());
                    player.sendMessage(plugin.chatutils.header + "Pitch: " + (int)targetPlayer.getLocation().getPitch()+ " | Yaw: " + (int)targetPlayer.getLocation().getYaw());
                    player.sendMessage(plugin.chatutils.header+ "Direction Facing: " + getDirection(targetPlayer));
                    player.sendMessage(plugin.chatutils.header + "Level: " + targetPlayer.getLevel() + "  (Exp: " + (int)targetPlayer.getExp() + ")");
                    player.sendMessage(plugin.chatutils.header + "Online: " + targetPlayer.isOnline());
                    player.sendMessage(plugin.chatutils.header + "Op: " + targetPlayer.isOp());
                    player.sendMessage(plugin.chatutils.header + "Whitelisted: " + targetPlayer.isWhitelisted());
                    return true;
                }
            }else
            {
                player.sendMessage(plugin.chatutils.noperm);
            }
        }

        return false;
    }
    
    private String getDirection(Player player)
    {
        int x = getF(player);
        switch(x)
        {
            case -4: return "South";
            case -3: return "West";
            case -2: return "North";
            case -1: return "East";
            case 0: return "South";
            default: return "Unknown Direction";
        }
    }

    private int getF(Player player)
    {
        double d = (player.getLocation().getYaw() / 90) + 0.5;
        int i = (int)d;
        return d < i ? i - 1 : i;
    }
   

}

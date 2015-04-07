package org.TTB.TTBUtils.Listeners;

import java.util.Collection;

import org.TTB.TTBUtils.Configuration.PlayerConfigs;
import org.TTB.TTBUtils.Util.UUIDApi;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener
{

    public static PlayerConfigs pc = new PlayerConfigs();
    
    @EventHandler
    private final void onPlayerChatTabComplete(final PlayerChatTabCompleteEvent event)
    {
        final String token = event.getLastToken();
        if (token.startsWith("@"))
        {
            final Collection<String> autoCompletions = event.getTabCompletions();
            autoCompletions.clear();
            final String begin = token.replaceAll("@", "").toLowerCase();
            for (final Player player : Bukkit.getOnlinePlayers())
            {
                final String playerName = player.getName();
                if (playerName.toLowerCase().startsWith(begin))
                {
                    autoCompletions.add("@" + playerName);
                }
            }
        }
    }

    @EventHandler
    private final void onAsyncPlayerChat(final AsyncPlayerChatEvent event)
    {
        for (final Player player : Bukkit.getOnlinePlayers())
        {
            final String message = event.getMessage();
            final String playerName = "@" + player.getName();
            if (StringUtils.containsIgnoreCase(message, playerName))
            {
                event.setMessage(message.replaceAll(playerName, ChatColor.GOLD+ playerName));
                player.playNote(player.getLocation(), Instrument.PIANO, Note.natural(1, Note.Tone.A));
            }
        }
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        Player player = e.getPlayer();
        String UUID = UUIDApi.getUUID(player.getDisplayName());
        String pName = UUIDApi.getName(UUID);
        pc.getPlayerFile(player).set(UUID + "\n" + pName, true);
        pc.savePlayerFile(player);
    }

}

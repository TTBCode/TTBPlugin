package org.TTB.TTBUtils.Util;


import java.util.ArrayList;
import java.util.List;
import org.TTB.TTBUtils.TTBUtils;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerFreeze 
{

    private final List<String> frozenplayers = new ArrayList<String>();

    /**
     *
     *Funtions:
     *freeze(player);
     *unfreeze(player);
     *isfrozen(player);
     *frozenloc(player);
     *
     */
    public PlayerFreeze(final TTBUtils plugin)
    {
    }
    
    /**
     *Freeze the player
     *
     *Use like:
     *freeze(player);
     *
     */
    public void freeze(final Player player)
    {
        player.getLocation();
        frozenplayers.add(player.getName());
        try
        {
            player.setWalkSpeed(0.0f);
        } catch (final Exception e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     *Unfreeze the player
     *
     *Use like:
     *unfreeze(player);
     *
     */
    public void unfreeze(final Player player)
    {
        final Location location = player.getLocation();
        location.toString();
        frozenplayers.remove(player.getName());
        player.setWalkSpeed(0.2f);

    }
    
    /**
     *Return if the player is currently frozen
     *
     *Use like:
     *isfrozen(player);
     *
     */
    public boolean isFrozen(Player p)
    {
        return frozenplayers.contains(p.getName());
    }
    
    /**
     *Return location where the frozen player
     *is currently <u>not</u> where they were frozen
     *
     *Use Like:
     *frozenloc(player);
     *
     */
    public String frozenloc(final Player player)
    {
        if(frozenplayers.contains(player.getName()))
        {
            final String location = player.getLocation().toString();
            return location;
        }
        return null;
    }
}

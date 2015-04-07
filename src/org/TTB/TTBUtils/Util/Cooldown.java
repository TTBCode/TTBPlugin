package org.TTB.TTBUtils.Util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Cooldown
{

    /*
     *  //Checks if the player is in a cooldown
     *  if(!Cooldown.isinCooldown(<uuid>, <name>)){
     *  //If isn't, start it
     *  Cooldown c = new Cooldown(<uuid>, <name>, <time>);
     *  c.start();
     *  }
     * 
     *  // Time Left In Cooldown
     * int timeLeft = Cooldown.getTimeLeft(<uuid>, <name>);
     * 
     */
    
    private static Map<String, Cooldown> cooldowns = new HashMap<String, Cooldown>();
    private long start;
    private final int timeInSeconds;
    private final UUID id;
    private final String cooldownName;

    public Cooldown(final UUID id, final String cooldownName, final int timeInSeconds)
    {
        this.id = id;
        this.cooldownName = cooldownName;
        this.timeInSeconds = timeInSeconds;
    }

    public static boolean isInCooldown(final UUID id, final String cooldownName)
    {
        if (getTimeLeft(id, cooldownName) >= 1)
        {
            return true;
        } else
        {
            stop(id, cooldownName);
            return false;
        }
    }

    private static void stop(final UUID id, final String cooldownName)
    {
        Cooldown.cooldowns.remove(id + cooldownName);
    }

    private static Cooldown getCooldown(final UUID id, final String cooldownName)
    {
        return cooldowns.get(id.toString() + cooldownName);
    }

    public static int getTimeLeft(final UUID id, final String cooldownName)
    {
        final Cooldown cooldown = getCooldown(id, cooldownName);
        int f = -1;
        if (cooldown != null)
        {
            final long now = System.currentTimeMillis();
            final long cooldownTime = cooldown.start;
            final int totalTime = cooldown.timeInSeconds;
            final int r = (int) (now - cooldownTime) / 1000;
            f = (r - totalTime) * (-1);
        }
        return f;
    }

    public void start()
    {
        this.start = System.currentTimeMillis();
        cooldowns.put(this.id.toString() + this.cooldownName, this);
    }

}

package org.TTB.TTBUtils.Util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.UUID;

public class UUIDApi
{
/*
 * 
 * String UUID = UUIDApi.getUUID("PlayerName");
 * player.sendMessage("UUID: "+ UUID);
 *
 * String Name = UUIDApi.getName(UUID);
 * player.sendMessage("Name - > "+Name);
 * 
 * 
 * 
 */
    public static String getName(String UUID)
    {
        try
        {
            URL url = new URL("[url]https://api.mojang.com/user/profiles/[/url]"+ UUID.replaceAll("-", "") + "/names");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = in.readLine();
            line = line.replace("[\"", "");
            line = line.replace("\"]", "");
            return line;
        } catch (Exception ex) 
        {
            ex.printStackTrace();
        }
        return null;
    }

    public static String getUUIDs(String player)
    {
        try 
        {
            URL url = new URL("[url]https://api.mojang.com/users/profiles/minecraft/[/url]" + player);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String Line;
            while ((Line = in.readLine()) != null) 
            {
                String uuid = Line.substring(7, 39);
                return uuid.substring(0, 8) + "-" + uuid.substring(8, 12) + "-"
                + uuid.substring(12, 16) + "-" + uuid.substring(16, 20)
                + "-" + uuid.substring(20, 32);
            }
            in.close();
        } catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public static String getName(UUID uuid)
    {
        return getName(uuid.toString());
    }

    public static String getUUID(String player) 
    {
        String strUUID = getUUIDs(player);
        return strUUID;
    }

}
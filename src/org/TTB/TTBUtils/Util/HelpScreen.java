package org.TTB.TTBUtils.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permissible;

public class HelpScreen
{

    /**
     * How to use the API
     * Follow Below
     */

//
//    helpscreen = new HelpScreen("MyHelp"); // Set the help screen
//    helpscreen.setHeader(ChatColor.BLUE + "<name> Page <page> of <maxpage>:"); // The msg at the top of the page
//    helpscreen.setFormat("<name> >> <desc>"); // How the commands are put
//    helpscreen.setFlipColor(ChatColor.GREEN, ChatColor.DARK_GREEN); // Set flip msg colo
//
//                  Command Input System
//    helpscreen.setEntry("/MyHelp", "Shows this helpscreen");
//    helpscreen.setEntry("/op", "You need to have a Permission").setPerms("perm.op");
//    helpscreen.setEntry("/test", "This is a test command");
//    helpscreen.setEntry("/fun", "Its a funny command");
//    helpscreen.setEntry("/opfun", "Fun only for the permission test.fun or test.funny").setPerms("test.fun", "test.funny");
//    helpscreen.setEntry("/food", "Free food");
//
//                      How to use the help command
//    @Override
//    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
//        if(cmd.getName().equalsIgnoreCase("myHelp"))
//            if(args.length == 0)
//                helpscreen.sendTo(sender, 1, 5);
//            else if(args.length == 1)
//                helpscreen.sendTo(sender, Integer.parseInt(args[0]), 5);
//            return true;
//    }
//    

    private final HashMap<String, HelpScreenEntry> entries;
    private final String NAME;
    private String header = "<name> Help (<page> / <maxpage>)";
    private String format = "<name> -> <desc>";
    private ChatColor c1 = ChatColor.GRAY, c2 = ChatColor.GRAY;

    public HelpScreen(final String name)
    {
        NAME = name;
        entries = new HashMap<>();
    }

    /**
     * Allowed parameters:
     * <name> = The entries name
     * <desc> = The entries description
     */
    public void setFormat(String format)
    {
        if (format == null)
        {
            format = "";
        }
        this.format = format;
    }

    /**
     * Set the color that the entries will have
     * The color will change for each entry
     * 
     * @param c1 The first Color
     * @param c2 The second Color
     */
    public void setFlipColor(final ChatColor c1, final ChatColor c2)
    {
        this.c1 = c1 == null ? ChatColor.GRAY : c1;
        this.c2 = c2 == null ? ChatColor.GRAY : c2;
    }

    /**
     * Set the color that the entries will have
     * The colorwill stay the same for all entries
     * 
     * @param c
     */
    public void setSimpleColor(final ChatColor c)
    {
        setFlipColor(c, c);
    }

    /**
     * Allowed parameters:
     * <name> = The screens name
     * <page> = The current page
     * <maxpage> = The amount of pages that are aviable
     */
    public void setHeader(String header)
    {
        if (header == null)
        {
            header = "";
        }
        this.header = header;
    }

    /**
     * @param name The name that will be displayed
     * @param description The description
     */
    public HelpScreenEntry setEntry(final String name, final String description)
    {
        HelpScreenEntry e;
        entries.put(name.toLowerCase(), e = new HelpScreenEntry(name,
                description));
        return e;
    }

    public HelpScreenEntry getEntry(final String name)
    {
        return entries.get(name.toLowerCase());
    }

    /**
     * @param p The user
     * @return The HelpEntries that the user is allowed to see
     */
    public List<HelpScreenEntry> toSend(final Permissible p)
    {
        final ArrayList<HelpScreenEntry> toSend = new ArrayList<>();

        for (final HelpScreenEntry e : entries.values())
        {
            if (e.isPermitted(p))
            {
                toSend.add(e);
            }
        }

        return toSend;
    }

    /**
     * @param s The reciver of the helpscreen
     * @param page The page to send
     * @param perPage The amount of entries per Page
     */
    public void sendTo(final CommandSender s, final int page, final int perPage)
    {
        List<HelpScreenEntry> toSend = toSend(s);

        final int maxpage = (int) ((toSend.size() / (float) perPage) + 0.999);

        int from = (page - 1) * perPage;
        int to = from + perPage;

        if (from >= toSend.size())
        {
            from = to = 0;
        }
        if (to > toSend.size())
        {
            to = toSend.size();
        }

        toSend = (from == to) || (toSend.size() == 0) ? new ArrayList<HelpScreenEntry>()
                : toSend.subList(from, to);

        final String[] msg = new String[toSend.size() + 1];

        msg[0] = header.replaceAll("<name>", NAME)
                .replaceAll("<page>", page + "")
                .replaceAll("<maxpage>", maxpage + "");

        int i = 1;

        boolean col = false;
        for (final HelpScreenEntry e : toSend)
        {
            msg[i++] = ((col = !col) ? c1 : c2) + e.fromFormat(format);
        }

        s.sendMessage(msg);

    }

    public class HelpScreenEntry
    {

        private final String NAME;
        private final String DESC;

        private String[] perms;

        public HelpScreenEntry(final String name, final String description)
        {
            this.NAME = name;
            this.DESC = description;
            this.perms = new String[0];
        }

        /**
         * Set the permission needed to see the entry
         * Only one of the permissions is needed
         * 
         * @param perms The permissions
         */
        public void setPerms(final String... perms)
        {
            this.perms = perms;
        }

        /**
         * @param p The user
         * @return If the user is allowed to see the entry
         */
        public boolean isPermitted(final Permissible p)
        {
            boolean b = true;
            for (final String s : perms)
            {
                if (p.hasPermission(s))
                {
                    b = true;
                    break;
                } else
                {
                    b = false;
                }
            }
            return b;
        }

        /**
         * @param format The format to use
         * @return The phrased format
         */
        public String fromFormat(final String format)
        {
            return format.replaceAll("<name>", NAME).replaceAll("<desc>", DESC);
        }

        @Override
        public String toString()
        {
            return NAME + " -> " + DESC;
        }
    }

}

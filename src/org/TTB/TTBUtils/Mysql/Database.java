package org.TTB.TTBUtils.Mysql;

import java.sql.Connection;

import org.bukkit.plugin.Plugin;


/**
 * ---[ This goes in your main class ]---
 * MySQL mysql = new MySQL("host.name", port, "user", "pass");
 * Connection c = null;
 * ]--------------------------------------[
 * 
 * Add this to your onEnable()
 * c = MySQL.open();
 */


//--Getting values--
//
//To get values from the database, you need to set up a few more things. Statements.
//Statements can be declared like this,
//
//Statement statement = c.createStatement();
//
//'c' being the Connection.
//
//
//Now we get to retrieving values.
//
//ResultSet res = statement.executeQuery("SELECT * FROM tokens WHERE PlayerName = '" + name + "';");
//res.next();
//
//
//This is an example I have used from one of my other plugins, It retrieves information from the db tokens, where the Table 'PlayerName' == my 'name' variable.
//
//Then I can get the information using
//
//if(res.getString("PlayerName") == null) 
//{
//tokens = 0;
//} else 
//{
//tokens = res.getInt("tokens");
//}
//
//
//--Setting information--
//
//To set information in the database, we use Statements again, but we use a different method within the Statement.
//
//Some example code;
//
//statement.executeUpdate("INSERT INTO tokens (`PlayerName`, `tokens`) VALUES ('" + name + "', '0');");
//System.out.println("Inserted info");
//
//
//Which will insert into the table.
//
//That's all from my tutorials, if you need more help, Please leave a comment, I'm happy to help.
//
//--Prepared Statements--
//
//
//To handle prepared statements, Do something like this!
//
//PreparedStatement ps = connection.prepareStatement("INSERT INTO `yourTable`(X, Y, Z) VALUES (?, ?, ?);");
//ps.setString(1, "This is the first question mark");
//ps.setString(2, "second question mark.");
//ps.setString(3, " I think you get it now.");
//ps.executeUpdate();
//
//
//Thanks to @evilmidget38 for supplying the code for this!
//
//P.S : If you're having problems getting a NullPointer when calling
//Statement statement = c.createStatement();
//
//Try putting
//Statement s = MySQL.open().createStatement();


public abstract class Database
{

    /**
     * Plugin instance, use for plugin.getDataFolder() and plugin.getLogger()
     */
    protected Plugin plugin;

    /**
     * Creates a new Database
     * 
     * @param plugin
     *            Plugin instance
     */
    protected Database(final Plugin plugin)
    {
        this.plugin = plugin;
    }

    /**
     * Opens a connection with the database
     * 
     * @return Connection opened
     */
    public abstract Connection openConnection();

    /**
     * Checks if a connection is open with the database
     * 
     * @return true if a connection is open
     */
    public abstract boolean checkConnection();

    /**
     * Gets the connection with the database
     * 
     * @return Connection with the database, null if none
     */
    public abstract Connection getConnection();

    /**
     * Closes the connection with the database
     */
    public abstract void closeConnection();
}

package com.michielo;


import com.michielo.bukkit.command.CommandHandler;
import com.michielo.bukkit.listener.ClickListener;
import com.michielo.bukkit.listener.InvMoveListener;
import com.michielo.file.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    /*
        Modified singleton implementation
     */
    private static Main instance;
    public static Main getInstance() {
        return instance; // not initiating instance because onEnable() **always** has to run on plugin startup
    }

    private DataManager dataManager;

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Starting...");

        // setting instance
        instance = this;

        // load files
        this.saveDefaultConfig();
        this.dataManager = new DataManager(this);

        // load commandhandler
        this.getCommand("lockedfurnaces").setExecutor(new CommandHandler());

        // load listeners
        this.getServer().getPluginManager().registerEvents(new ClickListener(), this);
        this.getServer().getPluginManager().registerEvents(new InvMoveListener(), this);

        Bukkit.getLogger().info("Started");
    }

    @Override
    public void onDisable() {

    }

    public DataManager getData() {
        return this.dataManager;
    }

}
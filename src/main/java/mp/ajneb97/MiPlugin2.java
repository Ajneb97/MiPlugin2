package mp.ajneb97;

import mp.ajneb97.commands.*;
import mp.ajneb97.config.MainConfigManager;
import mp.ajneb97.config.PlayersConfigManager;
import mp.ajneb97.listeners.InventoryListener;
import mp.ajneb97.listeners.PlayerListener;
import mp.ajneb97.managers.MenuInventoryManager;
import mp.ajneb97.managers.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class MiPlugin2 extends JavaPlugin {

    public static String prefix = "&8[&c&lMiPlugin&b²&8] ";
    private String version = getDescription().getVersion();
    private MainConfigManager mainConfigManager;
    private PlayerDataManager playerDataManager;
    private PlayersConfigManager playersConfigManager;
    private MenuInventoryManager menuInventoryManager;

    public void onEnable(){
        registerCommands();
        registerEvents();
        mainConfigManager = new MainConfigManager(this);
        menuInventoryManager = new MenuInventoryManager();
        playerDataManager = new PlayerDataManager();
        playersConfigManager = new PlayersConfigManager(this,"players");

        Bukkit.getConsoleSender().sendMessage(
                ChatColor.translateAlternateColorCodes('&',prefix+"&eHa sido activado! &fVersion: "+version));
        Bukkit.getConsoleSender().sendMessage(
                ChatColor.translateAlternateColorCodes('&',prefix+"&eGracias por usar mi plugin ;)"));
    }

    public void onDisable(){
        playersConfigManager.saveConfigs();
        Bukkit.getConsoleSender().sendMessage(
                ChatColor.translateAlternateColorCodes('&',prefix+"&eHa sido desactivado! &fVersion: "+version));
        Bukkit.getConsoleSender().sendMessage(
                ChatColor.translateAlternateColorCodes('&',prefix+"&eGracias por usar mi plugin ;)"));
    }

    public void registerCommands(){
        this.getCommand("miplugin").setExecutor(new MainCommand(this));
        this.getCommand("experiencia").setExecutor(new XpCommand(this));
        this.getCommand("volar").setExecutor(new FlyCommand(this));
        this.getCommand("menu").setExecutor(new MenuCommand(this));
        this.getCommand("scorps").setExecutor(new ScorpsCommand(this));
    }

    public void registerEvents(){
        getServer().getPluginManager().registerEvents(new PlayerListener(this),this);
        getServer().getPluginManager().registerEvents(new InventoryListener(this),this);
    }

    public MainConfigManager getMainConfigManager() {
        return mainConfigManager;
    }

    public MenuInventoryManager getMenuInventoryManager() {
        return menuInventoryManager;
    }

    public PlayersConfigManager getPlayersConfigManager() {
        return playersConfigManager;
    }

    public PlayerDataManager getPlayerDataManager() {
        return playerDataManager;
    }
}

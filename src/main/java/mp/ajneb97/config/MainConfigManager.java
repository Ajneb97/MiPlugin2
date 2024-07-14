package mp.ajneb97.config;

import mp.ajneb97.MiPlugin2;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class MainConfigManager {

    private CustomConfig configFile;
    private MiPlugin2 plugin;

    private String preventBlockBreak;
    private boolean welcomeMessageEnabled;
    private List<String> welcomeMessageMessage;

    public MainConfigManager(MiPlugin2 plugin){
        this.plugin = plugin;
        configFile = new CustomConfig("config.yml",null,plugin,false);
        configFile.registerConfig();
        loadConfig();
    }

    public void loadConfig(){
        FileConfiguration config = configFile.getConfig();
        preventBlockBreak = config.getString("messages.prevent_block_break");
        welcomeMessageEnabled = config.getBoolean("config.welcome_message.enabled");
        welcomeMessageMessage = config.getStringList("config.welcome_message.message");
    }

    public void reloadConfig(){
        configFile.reloadConfig();
        loadConfig();
    }

    public String getPreventBlockBreak() {
        return preventBlockBreak;
    }

    public boolean isWelcomeMessageEnabled() {
        return welcomeMessageEnabled;
    }

    public List<String> getWelcomeMessageMessage() {
        return welcomeMessageMessage;
    }
}

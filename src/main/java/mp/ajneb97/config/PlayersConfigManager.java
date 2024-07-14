package mp.ajneb97.config;

import mp.ajneb97.MiPlugin2;
import mp.ajneb97.model.PlayerData;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayersConfigManager extends DataFolderConfigManager{

    public PlayersConfigManager(MiPlugin2 plugin, String folderName) {
        super(plugin, folderName);
    }

    @Override
    public void loadConfigs() {
        Map<UUID, PlayerData> players = new HashMap<>();
        for(CustomConfig customConfig : configFiles){
            FileConfiguration config = customConfig.getConfig();

            UUID uuid = UUID.fromString(customConfig.getPath().replace(".yml",""));
            String name = config.getString("name");
            int scorps = config.getInt("scorps");

            PlayerData playerData = new PlayerData(uuid,name,scorps);
            players.put(uuid,playerData);
        }

        plugin.getPlayerDataManager().setPlayers(players);
    }

    @Override
    public void saveConfigs() {
        Map<UUID, PlayerData> players = plugin.getPlayerDataManager().getPlayers();
        for(Map.Entry<UUID,PlayerData> entry : players.entrySet()){
            PlayerData playerData = entry.getValue();
            String pathName = playerData.getUuid().toString()+".yml";
            CustomConfig customConfig = getConfigFile(pathName);
            if(customConfig == null){
                //Hay que crearlo
                customConfig = registerConfigFile(pathName);
            }

            FileConfiguration config = customConfig.getConfig();
            config.set("name",playerData.getName());
            config.set("scorps",playerData.getScorps());
        }
        saveConfigFiles();
    }
}

package mp.ajneb97.managers;

import mp.ajneb97.model.PlayerData;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerDataManager {
    private Map<UUID, PlayerData> players;
    private Map<String,UUID> playerNames;

    public PlayerDataManager(){
        players = new HashMap<>();
        playerNames = new HashMap<>();
    }

    public Map<UUID, PlayerData> getPlayers() {
        return players;
    }

    public void setPlayers(Map<UUID, PlayerData> players) {
        this.players = players;
        for(Map.Entry<UUID,PlayerData> entry : players.entrySet()){
            playerNames.put(entry.getValue().getName(),entry.getKey());
        }
    }

    public void addPlayer(PlayerData p){
        players.put(p.getUuid(),p);
        playerNames.put(p.getName(),p.getUuid());
    }

    public PlayerData getPlayer(Player player, boolean create){
        PlayerData playerData = players.get(player.getUniqueId());
        if(playerData == null && create){
            playerData = new PlayerData(player.getUniqueId(),player.getName(),0);
            addPlayer(playerData);
        }
        return playerData;
    }

    public PlayerData getPlayerByName(String playerName){
        UUID uuid = playerNames.get(playerName);
        return players.get(uuid);
    }

    public void addScorps(Player player,int amount){
        PlayerData playerData = getPlayer(player,true);
        playerData.setScorps(playerData.getScorps()+amount);
    }

    public int getScorpsByPlayer(Player player){
        PlayerData playerData = getPlayer(player,false);
        if(playerData != null){
            return playerData.getScorps();
        }
        return 0;
    }

    public int getScorpsByName(String playerName){
        PlayerData playerData = getPlayerByName(playerName);
        if(playerData != null){
            return playerData.getScorps();
        }
        return 0;
    }

    public void updateName(Player player){
        PlayerData playerData = getPlayer(player,false);
        if(playerData != null){
            String newName = player.getName();
            String oldName = playerData.getName();
            if(!newName.equals(oldName)){
                playerNames.remove(oldName);
                playerNames.put(newName,player.getUniqueId());
                playerData.setName(newName);
            }
        }
    }
}

package mp.ajneb97.model;

import java.util.UUID;

public class PlayerData {
    private UUID uuid;
    private String name;
    private int scorps;

    public PlayerData(UUID uuid, String name, int scorps) {
        this.uuid = uuid;
        this.name = name;
        this.scorps = scorps;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScorps() {
        return scorps;
    }

    public void setScorps(int scorps) {
        this.scorps = scorps;
    }
}

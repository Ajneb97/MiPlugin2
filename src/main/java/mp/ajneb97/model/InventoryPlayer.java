package mp.ajneb97.model;

import org.bukkit.entity.Player;

public class InventoryPlayer {
    private Player player;
    private InventorySection section;

    public InventoryPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public InventorySection getSection() {
        return section;
    }

    public void setSection(InventorySection section) {
        this.section = section;
    }
}

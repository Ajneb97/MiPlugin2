package mp.ajneb97.listeners;

import mp.ajneb97.MiPlugin2;
import mp.ajneb97.model.InventoryPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryListener implements Listener {

    private MiPlugin2 plugin;
    public InventoryListener(MiPlugin2 plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        Player player = (Player)event.getWhoClicked();
        InventoryPlayer inventoryPlayer = plugin.getMenuInventoryManager().getInventoryPlayer(player);
        if(inventoryPlayer != null){
            //Significa que el jugador esta dentro de uno de los inventarios (MiPlugin2)
            event.setCancelled(true);
            if(event.getCurrentItem() != null && event.getClickedInventory().equals(player.getOpenInventory().getTopInventory())){
                plugin.getMenuInventoryManager().inventoryClick(inventoryPlayer,event.getSlot(),event.getClick());
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){
        Player player = (Player)event.getPlayer();
        plugin.getMenuInventoryManager().removePlayer(player);
    }
}

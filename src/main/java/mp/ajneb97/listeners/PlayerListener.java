package mp.ajneb97.listeners;

import mp.ajneb97.MiPlugin2;
import mp.ajneb97.config.MainConfigManager;
import mp.ajneb97.utils.ItemUtils;
import mp.ajneb97.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Random;

public class PlayerListener implements Listener {

    private MiPlugin2 plugin;
    public PlayerListener(MiPlugin2 plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        String message = event.getMessage();

        if(message.toLowerCase().contains("aternos")){
            event.setCancelled(true);
            player.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix+"&c¡No escribas palabras feas en el chat!"));
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        // 56.5 68 266.5 90 0 spawn
        Player player = event.getPlayer();
        plugin.getPlayerDataManager().updateName(player);

        MainConfigManager mainConfigManager = plugin.getMainConfigManager();
        if(mainConfigManager.isWelcomeMessageEnabled()){
            List<String> message = mainConfigManager.getWelcomeMessageMessage();
            for(String m : message){
                player.sendMessage(MessageUtils.getColoredMessage(m.replace("%player%",player.getName())));
            }
        }

        Location location = new Location(Bukkit.getWorld("spawn"),56.5,68,266.5,90,0);
        player.teleport(location);

        location.getWorld().playSound(location, Sound.BLOCK_ANVIL_LAND, 5, 1F);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Player player = event.getPlayer();

        if(player.getWorld().getName().equals("spawn") && !player.hasPermission("miplugin.admin")){
            event.setCancelled(true);
            player.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix+plugin.getMainConfigManager().getPreventBlockBreak()));
        }

        Block block = event.getBlock();
        if(block.getType().equals(Material.EMERALD_ORE)){
            int num = new Random().nextInt(10);
            if(num >= 6){
                ItemStack item = ItemUtils.generateEmeraldItem(1);
                block.getWorld().dropItemNaturally(block.getLocation(),item);
            }
        }
    }

    @EventHandler
    public void onZombieKill(EntityDeathEvent event){
        if(!event.getEntity().getType().equals(EntityType.ZOMBIE)){
            return;
        }

        Player player = event.getEntity().getKiller();
        if(player != null){
            int num = new Random().nextInt(10)+1;
            plugin.getPlayerDataManager().addScorps(player,num);
            player.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix+" &7Has recibido &a"+num+" &7Scorps."));
        }
    }
}

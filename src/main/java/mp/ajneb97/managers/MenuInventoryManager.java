package mp.ajneb97.managers;

import mp.ajneb97.MiPlugin2;
import mp.ajneb97.model.InventoryPlayer;
import mp.ajneb97.model.InventorySection;
import mp.ajneb97.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class MenuInventoryManager {

    private ArrayList<InventoryPlayer> players;

    public MenuInventoryManager(){
        this.players = new ArrayList<>();
    }

    public InventoryPlayer getInventoryPlayer(Player player){
        for(InventoryPlayer inventoryPlayer : players){
            if(inventoryPlayer.getPlayer().equals(player)){
                return inventoryPlayer;
            }
        }
        return null;
    }

    public void removePlayer(Player player){
        players.removeIf(inventoryPlayer -> inventoryPlayer.getPlayer().equals(player));
    }

    public void openMainInventory(InventoryPlayer inventoryPlayer){
        inventoryPlayer.setSection(InventorySection.MENU_MAIN);
        Player player = inventoryPlayer.getPlayer();
        Inventory inv = Bukkit.createInventory(null,54, MessageUtils.getColoredMessage("&4Menu del Servidor"));

        // Items decorativos
        ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);
        for(int i=45;i<=53;i++){
            inv.setItem(i,item);
        }

        // Item informativo
        item = new ItemStack(Material.PAPER);
        meta = item.getItemMeta();
        meta.setDisplayName(MessageUtils.getColoredMessage("&4Información del Usuario"));
        List<String> lore = new ArrayList<>();
        lore.add(MessageUtils.getColoredMessage("&7Nivel: &a"+player.getLevel()));
        lore.add(MessageUtils.getColoredMessage("&7XP: &a"+player.getTotalExperience()));
        lore.add(MessageUtils.getColoredMessage("&7Ping: &a"+player.getPing()));
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(20,item);

        // Acceder a sub-inventario pociones
        item = new ItemStack(Material.POTION);
        meta = item.getItemMeta();
        meta.setDisplayName(MessageUtils.getColoredMessage("&4Efectos de Poción"));
        lore = new ArrayList<>();
        lore.add(MessageUtils.getColoredMessage("&7Activate algunos efectos de poción para"));
        lore.add(MessageUtils.getColoredMessage("&7usar en el Lobby. Solo para VIPs."));
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        item.setItemMeta(meta);
        inv.setItem(24,item);


        player.openInventory(inv);
        players.add(inventoryPlayer);
    }

    public void openEffectsInventory(InventoryPlayer inventoryPlayer) {
        inventoryPlayer.setSection(InventorySection.MENU_EFFECTS);
        Player player = inventoryPlayer.getPlayer();
        Inventory inv = Bukkit.createInventory(null, 27, MessageUtils.getColoredMessage("&4Menu del Servidor"));

        // Items decorativos
        ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(" ");
        item.setItemMeta(meta);
        for (int i = 19; i <= 26; i++) {
            inv.setItem(i, item);
        }

        //Items de Poción
        item = new ItemStack(Material.POTION);
        PotionMeta metaPotion = (PotionMeta)item.getItemMeta();
        metaPotion.setDisplayName(MessageUtils.getColoredMessage("&4Efecto de Poción de Salto"));
        List<String> lore = new ArrayList<>();
        lore.add(MessageUtils.getColoredMessage("&7Click para recibir el siguiente efecto:"));
        lore.add(MessageUtils.getColoredMessage("   &bSalto I &7(10 segundos)"));
        metaPotion.setLore(lore);
        metaPotion.setColor(Color.fromRGB( 7, 180, 255 ));
        metaPotion.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        item.setItemMeta(metaPotion);
        inv.setItem(11,item);

        item = new ItemStack(Material.POTION);
        metaPotion = (PotionMeta)item.getItemMeta();
        metaPotion.setDisplayName(MessageUtils.getColoredMessage("&4Efecto de Poción Poderoso"));
        lore = new ArrayList<>();
        lore.add(MessageUtils.getColoredMessage("&7Click para recibir los siguientes efectos:"));
        lore.add(MessageUtils.getColoredMessage("   &bFuerza III &7(30 segundos)"));
        lore.add(MessageUtils.getColoredMessage("   &bRegeneración III &7(30 segundos)"));
        lore.add(MessageUtils.getColoredMessage("   &bVisión Nocturna I &7(30 segundos)"));
        metaPotion.setLore(lore);
        metaPotion.setColor(Color.fromRGB(  236, 255, 7 ));
        metaPotion.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        item.setItemMeta(metaPotion);
        inv.setItem(15,item);

        //Atras
        item = new ItemStack(Material.ARROW);
        meta = item.getItemMeta();
        meta.setDisplayName(MessageUtils.getColoredMessage("&7Atras"));
        item.setItemMeta(meta);
        inv.setItem(18,item);

        player.openInventory(inv);
        players.add(inventoryPlayer);
    }

    public void inventoryClick(InventoryPlayer inventoryPlayer, int slot, ClickType clickType){
        Player player = inventoryPlayer.getPlayer();
        InventorySection section = inventoryPlayer.getSection();
        if(section.equals(InventorySection.MENU_MAIN)){
            if(slot == 24){
                if(!player.hasPermission("miplugin.inventario.efectos")){
                    player.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix+"&cSolo los VIPs pueden acceder a este menu."));
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 0.1F);
                    return;
                }
                openEffectsInventory(inventoryPlayer);
            }
        }else if(section.equals(InventorySection.MENU_EFFECTS)){
            if(slot == 18){
                openMainInventory(inventoryPlayer);
            }else if(slot == 11){
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP,200,0,false,true,true));
                player.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix+"&aHas recibido el efecto de poción de salto."));

                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2.0F);
            }else if(slot == 15){
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,600,2,false,true,true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,600,2,false,true,true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,600,0,false,true,true));
                player.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix+"&aHas recibido el efecto de poción poderoso."));

                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 0.1F, 2.0F);
            }
        }
    }
}

package mp.ajneb97.utils;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

public class ItemUtils {

    public static ItemStack generateEmeraldItem(int amount){
        ItemStack item = new ItemStack(Material.EMERALD,amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(MessageUtils.getColoredMessage("&aEsmeralda Exótica"));

        List<String> lore = new ArrayList<>();
        lore.add(MessageUtils.getColoredMessage("&7Esta esmeralda puede ser utilizada para"));
        lore.add(MessageUtils.getColoredMessage("&7intercambiar items poderosos."));
        lore.add("");
        lore.add(MessageUtils.getColoredMessage("&fNivel: &a5"));
        meta.setLore(lore);

        meta.addEnchant(Enchantment.DAMAGE_ALL,10,true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        item.setItemMeta(meta);

        return item;
    }

    public static ItemStack generatePotionItem(int amount){
        ItemStack item = new ItemStack(Material.POTION,amount);
        PotionMeta meta = (PotionMeta)item.getItemMeta();
        meta.setDisplayName(MessageUtils.getColoredMessage("&aPoción de las Sombras"));

        List<String> lore = new ArrayList<>();
        lore.add(MessageUtils.getColoredMessage("&7Una poción de tinieblas, muy poderosa."));
        meta.setLore(lore);

        meta.setColor(Color.fromRGB( 100, 8, 0 ));
        meta.addCustomEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE,600,2,false,true,true),true);
        meta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION,600,2,false,true,true),true);
        meta.addCustomEffect(new PotionEffect(PotionEffectType.NIGHT_VISION,600,0,false,true,true),true);

        item.setItemMeta(meta);

        return item;
    }
}

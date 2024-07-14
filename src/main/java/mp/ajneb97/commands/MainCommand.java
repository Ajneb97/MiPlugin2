package mp.ajneb97.commands;

import mp.ajneb97.MiPlugin2;
import mp.ajneb97.utils.ItemUtils;
import mp.ajneb97.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainCommand implements CommandExecutor {

    private MiPlugin2 plugin;
    public MainCommand(MiPlugin2 plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        if(!(sender instanceof Player)){
            //Consola
            sender.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix+"&cSolamente puedes usar este comando desde un jugador."));
            return true;
        }

        //Jugador
        Player player = (Player) sender;

        // /miplugin args[0] args[1] args[2]
        if(args.length >= 1){
            if(args[0].equalsIgnoreCase("bienvenida")){
                // /miplugin bienvenida
                sender.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix+"&7Bienvenido &a"+player.getName()));
            }else if(args[0].equalsIgnoreCase("fecha")){
                // /miplugin fecha
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String date = dateFormat.format(new Date());
                sender.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix+"&7Fecha y hora actual: &e"+date));
            }else if(args[0].equalsIgnoreCase("get")){
                // /miplugin get <autor/version>
                subCommandGet(sender,args);
            }else if(args[0].equalsIgnoreCase("reload")){
                // /miplugin reload
                subCommandReload(sender);
            }else if(args[0].equalsIgnoreCase("item")){
                // /miplugin item (opcional)<cantidad>
                subCommandItem(player,args);
            }
            else{
                help(sender);
            }
        }else{
           // /miplugin
           help(sender);
        }

        return true;
    }

    public void help(CommandSender sender){
        sender.sendMessage(MessageUtils.getColoredMessage("&f&l----------COMANDOS &c&lMIPLUGIN&b&l2&f&l----------"));
        sender.sendMessage(MessageUtils.getColoredMessage("&7- /miplugin bienvenida"));
        sender.sendMessage(MessageUtils.getColoredMessage("&7- /miplugin fecha"));
        sender.sendMessage(MessageUtils.getColoredMessage("&7- /miplugin get <autor/version>"));
        sender.sendMessage(MessageUtils.getColoredMessage("&f&l----------COMANDOS &c&lMIPLUGIN&b&l2&f&l----------"));
    }

    public void subCommandGet(CommandSender sender,String[] args){
        if(!sender.hasPermission("miplugin.commands.get")){
            sender.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix+"&cNo tienes permisos para usar este comando."));
            return;
        }

        if(args.length == 1){
            // /miplugin get
            sender.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix+"&cDebes usar &7/miplugin get <autor/version>"));
            return;
        }

        if(args[1].equalsIgnoreCase("autor")){
            // /miplugin get autor
            sender.sendMessage(MessageUtils.getColoredMessage(
                    MiPlugin2.prefix+"&7Los autores del plugin son: &e"+plugin.getDescription().getAuthors()));
        }else if(args[1].equalsIgnoreCase("version")){
            // /miplugin get version
            sender.sendMessage(MessageUtils.getColoredMessage(
                    MiPlugin2.prefix+"&7La version del plugin es: &e"+plugin.getDescription().getVersion()));
        }else{
            sender.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix+"&cDebes usar &7/miplugin get <autor/version>"));
        }
    }

    public void subCommandReload(CommandSender sender) {
        if (!sender.hasPermission("miplugin.commands.reload")) {
            sender.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix + "&cNo tienes permisos para usar este comando."));
            return;
        }
        plugin.getMainConfigManager().reloadConfig();
        sender.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix + "&aConfiguración recargada."));
    }

    public void subCommandItem(Player player,String[] args) {
        // /miplugin item (opcional)<cantidad>
        if (!player.hasPermission("miplugin.commands.item")) {
            player.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix + "&cNo tienes permisos para usar este comando."));
            return;
        }

        int amount = 1;
        if(args.length >= 2){
            try{
                amount = Integer.parseInt(args[1]);
                if(amount <= 0){
                    player.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix + "&cUsa una cantidad válida."));
                    return;
                }
            }catch(NumberFormatException e){
                player.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix + "&cUsa una cantidad válida."));
                return;
            }
        }

        if(player.getInventory().firstEmpty() == -1){
            player.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix + "&cTienes el inventario lleno."));
        }else{
            //ItemStack item = ItemUtils.generateEmeraldItem(amount);
            ItemStack item = ItemUtils.generatePotionItem(amount);
            player.getInventory().addItem(item);
            player.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix + "&aItem recibido."));
        }
    }
}

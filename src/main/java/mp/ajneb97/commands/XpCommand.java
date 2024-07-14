package mp.ajneb97.commands;

import mp.ajneb97.MiPlugin2;
import mp.ajneb97.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XpCommand implements CommandExecutor {

    private MiPlugin2 plugin;
    public XpCommand(MiPlugin2 plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        // /experiencia <cantidad> (opcional)<jugador>
        if(!sender.hasPermission("miplugin.commands.experiencia")){
            sender.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix+"&cNo tienes permisos para usar este comando."));
            return true;
        }

        if(args.length == 0){
            sender.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix+"&cDebes usar: &7/experiencia <cantidad> (opcional)<jugador>"));
            return true;
        }

        int cantidad = 0;
        try{
            cantidad = Integer.parseInt(args[0]);
            if(cantidad <= 0){
                sender.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix+"&cDebes usar una cantidad válida."));
                return true;
            }
        }catch(NumberFormatException e){
            sender.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix+"&cDebes usar una cantidad válida."));
            return true;
        }

        Player player = null;
        if(args.length == 1){
            if(sender instanceof Player){
                player = (Player)sender;
            }else{
                sender.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix+"&cDebes usar: &7/experiencia <cantidad> <jugador>"));
                return true;
            }
        }else{
            player = Bukkit.getPlayer(args[1]);
            if(player == null){
                sender.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix+"&cEl jugador &7"+args[1]+" &cno esta conectado."));
                return true;
            }
        }

        player.giveExp(cantidad);
        player.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix+"&aHas recibido &e"+cantidad+" &ade experiencia."));
        sender.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix+"&aEl jugador &e"+player.getName()+" &aha recibido &e"+cantidad+" &ade experiencia."));

        return true;
    }
}

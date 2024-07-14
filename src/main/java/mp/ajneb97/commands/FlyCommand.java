package mp.ajneb97.commands;

import mp.ajneb97.MiPlugin2;
import mp.ajneb97.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {

    private MiPlugin2 plugin;
    public FlyCommand(MiPlugin2 plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        // /volar (opcional)<jugador>
        if(!sender.hasPermission("miplugin.commands.volar")){
            sender.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix+"&cNo tienes permisos para usar este comando."));
            return true;
        }

        Player player = null;
        if(args.length == 0){
            if(sender instanceof Player){
                player = (Player)sender;
            }else{
                sender.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix+"&cDebes usar: &7/volar <jugador>"));
                return true;
            }
        }else{
            player = Bukkit.getPlayer(args[0]);
            if(player == null){
                sender.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix+"&cEl jugador &7"+args[0]+" &cno esta conectado."));
                return true;
            }
        }

        if(player.getAllowFlight()){
            player.setAllowFlight(false);
            player.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix+"&aModo de vuelo desactivado."));
            if(!player.equals(sender)){
                sender.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix+"&aModo de vuelo desactivado para &e"+player.getName()+"&a."));
            }
        }else{
            player.setAllowFlight(true);
            player.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix+"&aModo de vuelo activado."));
            if(!player.equals(sender)){
                sender.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix+"&aModo de vuelo activado para &e"+player.getName()+"&a."));
            }
        }

        return true;
    }
}

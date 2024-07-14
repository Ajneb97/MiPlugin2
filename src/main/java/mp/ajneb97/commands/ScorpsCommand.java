package mp.ajneb97.commands;

import mp.ajneb97.MiPlugin2;
import mp.ajneb97.managers.PlayerDataManager;
import mp.ajneb97.model.InventoryPlayer;
import mp.ajneb97.utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ScorpsCommand implements CommandExecutor {

    private MiPlugin2 plugin;
    public ScorpsCommand(MiPlugin2 plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        // /scorps (opcional)<nombre>
        if(!(sender instanceof Player)){
            sender.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix+"&cSolamente puedes usar este comando desde un jugador."));
            return true;
        }

        Player player = (Player)sender;
        PlayerDataManager playerDataManager = plugin.getPlayerDataManager();

        if(args.length == 0){
            // /scorps
            int scorps = playerDataManager.getScorpsByPlayer(player);
            player.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix+" &7Tus Scorps: &e"+scorps));
        }else{
            // /scorps <nombre>
            int scorps = playerDataManager.getScorpsByName(args[0]);
            player.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix+" &7Scorps de "+args[0]+": &e"+scorps));
        }


        return true;
    }
}

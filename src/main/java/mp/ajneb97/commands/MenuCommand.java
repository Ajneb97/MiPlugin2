package mp.ajneb97.commands;

import mp.ajneb97.MiPlugin2;
import mp.ajneb97.managers.MenuInventoryManager;
import mp.ajneb97.model.InventoryPlayer;
import mp.ajneb97.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MenuCommand implements CommandExecutor {

    private MiPlugin2 plugin;
    public MenuCommand(MiPlugin2 plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        // /menu
        if(!(sender instanceof Player)){
            sender.sendMessage(MessageUtils.getColoredMessage(MiPlugin2.prefix+"&cSolamente puedes usar este comando desde un jugador."));
            return true;
        }

        Player player = (Player)sender;

        plugin.getMenuInventoryManager().openMainInventory(new InventoryPlayer(player));
        return true;
    }
}

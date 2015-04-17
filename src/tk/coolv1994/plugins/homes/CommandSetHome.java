package tk.coolv1994.plugins.homes;

import tk.coolv1994.gawdapi.events.Command;
import tk.coolv1994.gawdapi.perms.Permissions;
import tk.coolv1994.gawdapi.utils.Chat;

/**
 * Created by Vinnie on 2/17/2015.
 */
public class CommandSetHome implements Command {
    @Override
    public void onCommand(String player, String[] args) {
        if (!Permissions.hasPermission(player, "homes.set")) {
            Chat.sendMessage(player, "No permission.");
            return;
        }
        if(Homes.useBed) {
            Chat.sendMessage(player, "Sleep in a bed to set your home.");
            return;
        }
        if (args.length >= 3) {
            int x = Integer.parseInt(args[0]);
            int y = Integer.parseInt(args[1]);
            int z = Integer.parseInt(args[2]);
            Homes.setHome(player, String.format("%d %d %d", x, y, z));
            Chat.sendMessage(player, "Your home has been set.");
        } else {
            Chat.sendMessage(player, "Use: !sethome <x> <y> <z>");
        }
    }
}

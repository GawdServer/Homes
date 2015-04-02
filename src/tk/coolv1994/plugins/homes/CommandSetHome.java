package tk.coolv1994.plugins.homes;

import tk.coolv1994.gawdserver.events.Command;
import tk.coolv1994.gawdserver.utils.Chat;

/**
 * Created by Vinnie on 2/17/2015.
 */
public class CommandSetHome implements Command {
    @Override
    public void onCommand(String player, String[] args) {
        if(!Homes.useBed) {
            if (args.length >= 3) {
                int x = Integer.parseInt(args[0]);
                int y = Integer.parseInt(args[1]);
                int z = Integer.parseInt(args[2]);
                Homes.homes.setProperty(player, String.format("%d %d %d", x, y, z));
                Chat.sendMessage(player, "Your home has been set.");
            } else {
                Chat.sendMessage(player, "Use: !sethome <x> <y> <z>");
            }
            return;
        }
        Chat.sendMessage(player, "Sleep in a bed to set your home.");
    }
}

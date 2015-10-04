package io.github.gawdserver.homes;

import io.github.gawdserver.api.events.Command;
import io.github.gawdserver.api.perms.Permissions;
import io.github.gawdserver.api.player.Sender;
import io.github.gawdserver.api.utils.Chat;

/**
 * Created by Vinnie on 2/17/2015.
 */
public class CommandSetHome implements Command {

    @Override
    public void playerCommand(String player, String[] args) {
        if (!Permissions.hasPermission(player, "homes.set")) {
            Chat.sendMessage(player, "No permission.");
            return;
        }
        if (Homes.useBed) {
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

    @Override
    public void serverCommand(Sender sender, String[] args) {
        if (Homes.useBed) {
            Chat.sendMessage(Sender.CONSOLE.name(), "Disabled.");
            return;
        }
        if (args.length >= 4) {
            int x = Integer.parseInt(args[1]);
            int y = Integer.parseInt(args[2]);
            int z = Integer.parseInt(args[3]);
            Homes.setHome(args[0], String.format("%d %d %d", x, y, z));
            Chat.sendMessage(Sender.CONSOLE.name(), "Player home has been set.");
        } else {
            Chat.sendMessage(Sender.CONSOLE.name(), "Use: !sethome <player> <x> <y> <z>");
        }
    }
}

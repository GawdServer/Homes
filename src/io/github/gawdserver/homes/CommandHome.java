package io.github.gawdserver.homes;

import io.github.gawdserver.api.Server;
import io.github.gawdserver.api.events.Command;
import io.github.gawdserver.api.perms.Permissions;
import io.github.gawdserver.api.player.PlayerList;
import io.github.gawdserver.api.player.Sender;
import io.github.gawdserver.api.utils.Chat;

import org.jnbt.CompoundTag;
import org.jnbt.NBTInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Vinnie on 2/17/2015.
 */
public class CommandHome implements Command {

    @Override
    public void playerCommand(String player, String[] args) {
        if (!Permissions.hasPermission(player, "homes.use")) {
            Chat.sendMessage(player, "No permission.");
            return;
        }
        if (!Homes.useBed) {
            if (Homes.hasHome(player)) {
                Server.sendCommand(String.format("tp %s %s", player, Homes.getHome(player)));
            } else {
                Chat.sendMessage(player, "You do not have a home set.");
            }
            return;
        }
        File playerData = new File(Homes.world + "/playerdata/" + PlayerList.getUUID(player) + ".dat");

        CompoundTag master = null;
        try {
            NBTInputStream ns = new NBTInputStream(new FileInputStream(playerData));
            master = (CompoundTag) ns.readTag();
            ns.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (master != null && master.getValue().containsKey("SpawnX")) {
            Object x = master.getValue().get("SpawnX").getValue();
            Object y = master.getValue().get("SpawnY").getValue();
            Object z = master.getValue().get("SpawnZ").getValue();
            Server.sendCommand("tp " + player + " " + x + " " + y + " " + z);
        } else {
            Chat.sendMessage(player, "You do not have a home set.");
        }
    }

    @Override
    public void serverCommand(Sender sender, String[] args) {
        if (args.length == 0) {
            Chat.sendMessage(Sender.CONSOLE.name(), "Use: !home <player>");
            return;
        }
        if (!Homes.useBed) {
            if (Homes.hasHome(args[0])) {
                Server.sendCommand(String.format("tp %s %s", args[0], Homes.getHome(args[0])));
            } else {
                Chat.sendMessage(Sender.CONSOLE.name(), "Player does not have a home set.");
            }
            return;
        }
        File playerData = new File(Homes.world + "/playerdata/" + PlayerList.getUUID(args[0]) + ".dat");

        CompoundTag master = null;
        try {
            NBTInputStream ns = new NBTInputStream(new FileInputStream(playerData));
            master = (CompoundTag) ns.readTag();
            ns.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (master != null && master.getValue().containsKey("SpawnX")) {
            Object x = master.getValue().get("SpawnX").getValue();
            Object y = master.getValue().get("SpawnY").getValue();
            Object z = master.getValue().get("SpawnZ").getValue();
            Server.sendCommand("tp " + args[0] + " " + x + " " + y + " " + z);
        } else {
            Chat.sendMessage(Sender.CONSOLE.name(), "Player does not have a home set.");
        }
    }
}

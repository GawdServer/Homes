package tk.coolv1994.plugins.homes;

import tk.coolv1994.gawdapi.Gawd;
import tk.coolv1994.gawdapi.events.Command;
import tk.coolv1994.gawdapi.perms.Permissions;
import tk.coolv1994.gawdapi.player.PlayerList;
import tk.coolv1994.gawdapi.utils.Chat;

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
    public void onCommand(String player, String[] args) {
        if (!Permissions.hasPermission(player, "homes.use")) {
            Chat.sendMessage(player, "No permission.");
            return;
        }
        if(!Homes.useBed) {
            if (Homes.hasHome(player)) {
                Gawd.sendCommand(String.format("tp %s %s", player, Homes.getHome(player)));
            } else {
                Chat.sendMessage(player, "You do not have a home set.");
            }
            return;
        }
        File playerData = new File("./" + Homes.world + "/playerdata/" + PlayerList.getUUID(player) + ".dat");

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
            Gawd.sendCommand("tp " + player + " " + x + " " + y + " " + z);
        } else {
            Chat.sendMessage(player, "You do not have a home set.");
        }
    }
}

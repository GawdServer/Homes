package io.github.gawdserver.homes;

import io.github.gawdserver.api.plugin.Plugin;
import io.github.gawdserver.api.plugin.PluginDir;

import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Vinnie on 2/2/2015.
 */
public class Homes implements Plugin {
    private static final Logger logger = Logger.getLogger("Homes");
    private static final File homeFile = new File(PluginDir.getPluginDir(), "Homes/homes.txt");
    private static Properties homes;
    public static boolean useBed = false;
    public static String world = "world";

    public Homes() {
        homes = new Properties();
    }

    public static boolean hasHome(String player) {
        return homes.containsKey(player);
    }

    public static String getHome(String player) {
        return homes.getProperty(player);
    }

    public static void setHome(String player, String coords) {
        homes.setProperty(player, coords);
    }

    private void loadHomes() {
        try {
            homes.load(new FileInputStream(homeFile));
        } catch (FileNotFoundException e) {
            homeFile.getParentFile().mkdirs();
            homes.setProperty("UseBedAsHome", "false");
            homes.setProperty("WorldName", "world");
            saveHomes();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error loading homes.", ex);
        }
    }

    private void saveHomes() {
        try {
            homes.store(new FileOutputStream(homeFile), "Player Homes (username=x y z)");
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error saving homes.", ex);
        }
    }

    @Override
    public void startup() {
        loadHomes();
        useBed = Boolean.parseBoolean(homes.getProperty("UseBedAsHome", "false"));
        world = homes.getProperty("WorldName", "world");
    }

    @Override
    public void shutdown() {
        saveHomes();
    }
}

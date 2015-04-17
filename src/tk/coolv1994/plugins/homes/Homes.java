package tk.coolv1994.plugins.homes;

import tk.coolv1994.gawdapi.plugin.Plugin;

import java.io.*;
import java.util.Properties;

/**
 * Created by Vinnie on 2/2/2015.
 */
public class Homes implements Plugin {
    private static final File homeFile = new File("./plugins/Homes/homes.txt");
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
        } catch (IOException e) {
            System.out.println("Error loading homes.\n" + e.getMessage());
        }
    }

    private void saveHomes() {
        try {
            homes.store(new FileOutputStream(homeFile), "Player Homes (username=x y z)");
        } catch (IOException e) {
            System.out.println("Error saving homes.\n" + e.getMessage());
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

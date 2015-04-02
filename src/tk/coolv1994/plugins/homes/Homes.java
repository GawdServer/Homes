package tk.coolv1994.plugins.homes;

import tk.coolv1994.gawdserver.plugin.Plugin;

import java.io.*;
import java.util.Properties;

/**
 * Created by Vinnie on 2/2/2015.
 */
public class Homes implements Plugin {
    private static final File homeFile = new File("./plugins/Homes/homes.txt");
    public static final Properties homes = new Properties();
    public static boolean useBed = false;

    private void loadHomes() {
        try {
            homes.load(new FileInputStream(homeFile));
        } catch (FileNotFoundException e) {
            homeFile.getParentFile().mkdirs();
            homes.setProperty("UseBedAsHome", "false");
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
    }

    @Override
    public void shutdown() {
        saveHomes();
    }
}

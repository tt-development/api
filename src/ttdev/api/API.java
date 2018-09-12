package ttdev.api;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Spigot API for minecraft 1.8 - 1.12. Developed by T&T development.
 * See README.md to get all features of this API.
 *
 * @author Tre & Thomas
 * @version 1.5
 * @category API
 * @see README.md
 * @since 13-6-2018
 */

public class API extends JavaPlugin {

    private static API singleton;
    private static PluginManager pluginManager;

    private static APIMode mode = APIMode.DEVELOPING;

    @Override
    public void onEnable() {
        singleton = this;

        pluginManager = Bukkit.getPluginManager();

        //Auto updater.
        if (mode.equals(APIMode.LIVE)) {
            PluginUpdater.startTimer();
        }

    }

    /**
     * @return
     */
    public static API getInstance() {
        return singleton;
    }

    public static PluginManager getPluginManager() {
        return pluginManager;
    }

    public static String getVersion() {
        return getInstance().getDescription().getVersion();
    }

    public static APIMode getMode() {
        return mode;
    }

    public static void runAsyncTask(Runnable runnable) {
        new BukkitRunnable() {
            @Override
            public void run() {
                runnable.run();
            }
        }.runTaskAsynchronously(singleton);
    }

    public static void runSyncTask(Runnable runnable) {
        new BukkitRunnable() {
            @Override
            public void run() {
                runnable.run();
            }
        }.runTask(singleton);
    }

    public static void runTaskLater(Runnable runnable, long delay) {
        new BukkitRunnable() {
            @Override
            public void run() {
                runnable.run();
            }
        }.runTaskLater(singleton, delay);
    }

}

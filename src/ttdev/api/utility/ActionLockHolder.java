package ttdev.api.utility;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import ttdev.api.API;
import ttdev.api.general.data.DataStore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ActionLockHolder {

    private static List<ActionLock> actionLocks = new ArrayList<>();

    public static void loadLocks(JavaPlugin plugin) {
        DataStore dataStore = new DataStore(plugin.getDataFolder().getPath() + "/lock-info.yml");
        int lockCount = dataStore.loadInteger("lock-count");

        /* Load all ActionLock's */
        for (int i = 0; i < lockCount; i++) {
            ActionLock lock = new ActionLock();
            lock.load(dataStore);
        }
    }

    public static void saveLocks(JavaPlugin plugin) {
        DataStore dataStore = new DataStore(plugin.getDataFolder().getPath() + "/lock-info.yml");
        dataStore.saveInteger(actionLocks.size(), "lock-count");
        actionLocks.forEach(lock -> lock.save(dataStore));
    }

    public static boolean hasLock(Player player) {
        UUID uuid = player.getUniqueId();
        return actionLocks.stream().anyMatch(lock -> lock.getPlayerUUID().equals(uuid));
    }

    public static boolean hasLock(Player player, LockableAction lockableAction) {
        UUID uuid = player.getUniqueId();
        return actionLocks.stream().anyMatch(lock -> lock.getPlayerUUID().equals(uuid)
                && lock.getLockableAction().equals(lockableAction));
    }

    public static void addLock(ActionLock lock) {
        actionLocks.add(lock);

        new BukkitRunnable() {
            @Override
            public void run() {
                actionLocks.remove(lock);
            }
        }.runTaskLater(API.getInstance(), (lock.getTime() / 1000) * 20);
    }

    public static void removeLock(ActionLock lock) {
        actionLocks.remove(lock);
    }

    public static List<ActionLock> getLocks() {
        return actionLocks;
    }
}

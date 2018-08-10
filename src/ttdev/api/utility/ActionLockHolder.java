package ttdev.api.utility;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import ttdev.api.general.data.DataStore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ActionLockHolder {

    private static List<ActionLock> actionLocks = new ArrayList<>();

    public static void loadLocks(JavaPlugin plugin) {
        DataStore dataStore = new DataStore(plugin.getDataFolder().getPath() + "/lock-data.yml");
        int lockCount = dataStore.loadInteger("lock-count");

        /* Load all ActionLock's */
        for (int i = 0; i < lockCount; i++) {
            ActionLock lock = new ActionLock();
            lock.load(dataStore);
            actionLocks.add(lock);
        }
    }

    public static void saveLocks(JavaPlugin plugin) {
        DataStore dataStore = new DataStore(plugin.getDataFolder().getPath() + "/lock-data.yml");
        dataStore.saveInteger(actionLocks.size(), "lock-count");
        actionLocks.forEach(lock -> lock.save(dataStore));
    }

    public static boolean hasLock(Player player) {
        UUID uuid = player.getUniqueId();
        return actionLocks.stream().anyMatch(lock -> lock.getPlayerUUID().equals(uuid));
    }

    public static boolean hasLock(Player player, Object action) {
        UUID uuid = player.getUniqueId();
        return actionLocks.stream().anyMatch(lock -> lock.getPlayerUUID().equals(uuid)
                && lock.getAction().equals(action));
    }

    public static void addLock(ActionLock lock) {
        actionLocks.add(lock);
    }

    public static void removeLock(ActionLock lock) {
        actionLocks.remove(lock);
    }

    public static List<ActionLock> getLocks() {
        return actionLocks;
    }
}

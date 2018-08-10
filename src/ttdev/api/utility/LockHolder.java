package ttdev.api.utility;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import ttdev.api.general.data.DataStore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LockHolder {

    private static List<Lock> locks = new ArrayList<>();

    public static void loadLocks(JavaPlugin plugin) {
        DataStore dataStore = new DataStore(plugin.getDataFolder().getPath() + "/lock-data.yml");
        int lockCount = dataStore.loadInteger("lock-count");

        /* Load all Lock's */
        for (int i = 0; i < lockCount; i++) {
            Lock lock = new Lock();
            lock.load(dataStore);
            locks.add(lock);
        }
    }

    public static void saveLocks(JavaPlugin plugin) {
        DataStore dataStore = new DataStore(plugin.getDataFolder().getPath() + "/lock-data.yml");
        dataStore.saveInteger(locks.size(), "lock-count");
        locks.forEach(lock -> lock.save(dataStore));
    }

    public static boolean hasLock(Player player) {
        UUID uuid = player.getUniqueId();
        return locks.stream().anyMatch(lock -> lock.getPlayerUUID().equals(uuid));
    }

    public static boolean hasLock(Player player, Object action) {
        UUID uuid = player.getUniqueId();
        return locks.stream().anyMatch(lock -> lock.getPlayerUUID().equals(uuid)
                && lock.getAction().equals(action));
    }

    public static Lock getLock(Player player, Object action) {
        UUID uuid = player.getUniqueId();
        return locks.stream()
                .filter(lock -> lock.getPlayerUUID().equals(uuid))
                .filter(lock -> lock.getAction().equals(action))
                .findAny()
                .orElse(null);
    }

    public static void addLock(Lock lock) {
        locks.add(lock);
    }

    public static void removeLock(Lock lock) {
        locks.remove(lock);
    }

    public static List<Lock> getLocks() {
        return locks;
    }
}

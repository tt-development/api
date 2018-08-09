package ttdev.api.utility;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import ttdev.api.API;
import ttdev.api.general.data.DataStore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ActionLockHolder {

    private static List<ActionLock> actionLocks = new ArrayList<>();

    public static void loadLocks(){
        while (true) {
            ActionLock actionLock = new ActionLock();
            DataStore dataStore = new DataStore(API.getInstance().getDataFolder().getAbsolutePath());
            if (!actionLock.load(dataStore)) {
                break;
            }
            actionLocks.add(actionLock);
        }
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

    public static void addLock(ActionLock actionLock) {
        actionLocks.add(actionLock);

        new BukkitRunnable() {
            @Override
            public void run() {
                actionLocks.remove(actionLock);
            }
        }.runTaskLater(API.getInstance(), (actionLock.getTime() / 1000) * 20);
    }

    public static void removeLock(ActionLock actionLock) {
        actionLocks.remove(actionLock);
    }

    public static List<ActionLock> getLocks() {
        return actionLocks;
    }
}

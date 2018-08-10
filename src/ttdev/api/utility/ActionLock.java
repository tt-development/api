package ttdev.api.utility;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import ttdev.api.API;
import ttdev.api.general.data.DataStore;
import ttdev.api.general.data.IPreservable;

import java.util.UUID;

public class ActionLock implements IPreservable {

    private static int nextLockID = 0;

    private UUID uuid;
    private Object action;
    private volatile long seconds;
    private int lockID;

    /* This default constructor is required for an IPreservable object.
     * It serves the same function as a Serializable object */
    public ActionLock() {

    }

    public ActionLock(Player player, Object action, long seconds) {
        lockID = nextLockID++;

        this.uuid = player.getUniqueId();
        this.seconds = seconds;

        startTimer();
    }

    private void startTimer() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (ActionLock.this.seconds < 1) {
                    ActionLockHolder.removeLock(ActionLock.this);
                    this.cancel();
                }
                ActionLock.this.seconds--;
                System.out.println("Seconds is now: " + ActionLock.this.seconds);
            }
        }.runTaskTimer(API.getInstance(), 20, 20);
    }

    public UUID getPlayerUUID() {
        return uuid;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public Object getAction() {
        return action;
    }

    public long getTime() {
        return seconds;
    }

    public int getLockID() {
        return lockID;
    }

    @Override
    public boolean save(DataStore dataStore) {
        dataStore.useIdentifier(Integer.toString(lockID));
        dataStore.saveString(uuid.toString(), "uuid");
        dataStore.saveLong(seconds, "seconds");
        dataStore.saveString(action.toString(), "action");

        return true;
    }

    @Override
    public boolean load(DataStore dataStore) {
        FileConfiguration configuration = dataStore.getConfiguration();

        for (String key : configuration.getKeys(false)) {

            if (key.equals("lock-count")) {
                continue;
            }

            dataStore.useIdentifier(key);
            lockID = Integer.parseInt(key);
            uuid = UUID.fromString(dataStore.loadString("uuid"));
            seconds = dataStore.loadLong("seconds");
            action = dataStore.loadString("action");
        }

        startTimer();

        return true;
    }
}

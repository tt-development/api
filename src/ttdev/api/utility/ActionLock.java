package ttdev.api.utility;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import ttdev.api.general.data.DataStore;
import ttdev.api.general.data.IPreservable;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ActionLock implements IPreservable {

    private static int nextLockID = 0;

    private UUID uuid;
    private LockableAction lockableAction;
    private long time;
    private int lockID;

    /* This default constructor is required for an IPreservable object.
    * It serves the same function as a Serializable object */
    public ActionLock() {

    }

    public ActionLock(Player player, LockableAction lockableAction, TimeUnit timeUnit, long time) {
        lockID = nextLockID++;

        this.uuid = player.getUniqueId();
        this.lockableAction = lockableAction;
        this.time = timeUnit.toMillis(time);
    }

    public UUID getPlayerUUID() {
        return uuid;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public LockableAction getLockableAction() {
        return lockableAction;
    }

    public long getTime() {
        return time;
    }

    public int getLockID() {
        return lockID;
    }

    @Override
    public boolean save(DataStore dataStore) {
        dataStore.useFile("lock-data.yml");
        dataStore.useIdentifier(Integer.toString(lockID));
        dataStore.saveString(uuid.toString(), "uuid");
        dataStore.saveLong(time, "time");

        if (lockableAction.getType().equals(LockableAction.Type.COMMAND)) {
            dataStore.saveString(lockableAction.toString(), "action");
        } else {
            dataStore.save((IPreservable) lockableAction);
        }

        return true;
    }

    @Override
    public boolean load(DataStore dataStore) {
        dataStore.useFile("lock-data.yml");
        FileConfiguration configuration = dataStore.getConfiguration();
        for (String key : configuration.getKeys(false)) {
            dataStore.useIdentifier(key);
            try {
                lockID = Integer.parseInt(key);
                uuid = UUID.fromString(dataStore.loadString("uuid"));
                time = dataStore.loadLong("time");
                dataStore.load((IPreservable) lockableAction);
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }
}

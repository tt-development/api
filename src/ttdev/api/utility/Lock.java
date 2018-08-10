package ttdev.api.utility;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import ttdev.api.API;
import ttdev.api.general.data.DataStore;
import ttdev.api.general.data.IPreservable;

import java.util.UUID;

public class Lock implements IPreservable {

    private static int nextLockID = 0;

    private UUID uuid;
    private Object action;
    private Time time;
    private int lockID;

    /* This default constructor is required for an IPreservable object.
     * It serves the same function as a Serializable object */
    public Lock() {

    }

    public Lock(Player player, Object action, Lock.Time time) {
        lockID = nextLockID++;

        this.uuid = player.getUniqueId();
        this.time = time;

        startTimer();
    }

    private void startTimer() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (time.getTime(TimeUnit.SECONDS) < 1) {
                    LockHolder.removeLock(Lock.this);
                    this.cancel();
                }

                time.subtractTime(TimeUnit.SECONDS, 1);

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

    public Time getTime() {
        return time;
    }

    public long getTime(TimeUnit timeUnit){
        return time.getTime(timeUnit);
    }

    public int getLockID() {
        return lockID;
    }

    @Override
    public boolean save(DataStore dataStore) {
        dataStore.useIdentifier(Integer.toString(lockID));
        dataStore.saveString(uuid.toString(), "uuid");
        dataStore.saveLong(time.getTime(TimeUnit.SECONDS), "time");
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
            time = new Time(TimeUnit.SECONDS, dataStore.loadLong("time"));
            action = dataStore.loadString("action");
        }

        startTimer();

        return true;
    }

    public enum TimeUnit {
        SECONDS, MINUTES, HOURS, DAYS
    }

    public static class Time {

        private long time;

        public Time(TimeUnit timeUnit, long time) {
            switch (timeUnit) {
                case DAYS:
                    this.time = time * 60 * 60 * 24;
                    break;
                case HOURS:
                    this.time = time * 60 * 60;
                    break;
                case MINUTES:
                    this.time = time * 60;
                default:
                    this.time = time;
            }
        }

        public long getTime(TimeUnit timeUnit) {
            switch (timeUnit) {
                case DAYS:
                    return time / 60 / 60 / 24;
                case HOURS:
                    return time / 60 / 60;
                case MINUTES:
                    return time / 60;
                default:
                    return time;
            }
        }

        public long subtractTime(TimeUnit timeUnit, long amount) {
            switch (timeUnit) {
                case DAYS:
                    return time -= time / 60 / 60 / 24;
                case HOURS:
                    return time -= time / 60 / 60;
                case MINUTES:
                    return time -= time / 60;
                default:
                    return time -= time;
            }
        }

    }
}

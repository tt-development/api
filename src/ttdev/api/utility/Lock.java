package ttdev.api.utility;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import ttdev.api.API;
import ttdev.api.general.data.DataStore;
import ttdev.api.general.data.IPreservable;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
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
                if (time.getTime(ChronoUnit.SECONDS) < 1) {
                    LockHolder.removeLock(Lock.this);
                    this.cancel();
                }

                time.subtractTime(ChronoUnit.SECONDS, 1);

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

    public long getTime(ChronoUnit chronoUnit) {
        return time.getTime(chronoUnit);
    }

    public int getLockID() {
        return lockID;
    }

    @Override
    public boolean save(DataStore dataStore) {
        dataStore.useIdentifier(Integer.toString(lockID));
        dataStore.saveString(uuid.toString(), "uuid");
        dataStore.saveLong(time.getTime(ChronoUnit.SECONDS), "time");
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
            time = new Time(ChronoUnit.SECONDS, dataStore.loadLong("time"));
            action = dataStore.loadString("action");
        }

        startTimer();

        return true;
    }

    public static class Time {

        private long time; // Time in seconds

        public Time(ChronoUnit chronoUnit, long time) {
            this.time = Duration.of(time, chronoUnit).get(ChronoUnit.SECONDS);
        }

        public long getTime(ChronoUnit chronoUnit) {
            return Duration.of(time, chronoUnit).get(chronoUnit);
        }

        public long subtractTime(ChronoUnit chronoUnit, long amount) {
            return time -= Duration.of(amount, chronoUnit).get(ChronoUnit.SECONDS);
        }

    }
}

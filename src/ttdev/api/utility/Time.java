package ttdev.api.utility;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class Time {

    private long time; // Time in seconds

    // Used only as convenience in getting the formatted time
    private final int secondsInMinute = 60;
    private final int secondsInHour = secondsInMinute * 60;
    private final int secondsInDay = secondsInHour * 24;

    /**
     * @param chronoUnit
     * @param time
     * @deprecated Create via static methods in {@code Time}
     */
    @Deprecated
    public Time(ChronoUnit chronoUnit, long time) {
        this.time = Duration.of(time, chronoUnit).get(ChronoUnit.SECONDS);
    }

    private Time(long days, long hours, long minutes, long seconds) {
        time = Duration.ofDays(days).get(ChronoUnit.SECONDS);
        time += Duration.ofHours(hours).get(ChronoUnit.SECONDS);
        time += Duration.ofMinutes(minutes).get(ChronoUnit.SECONDS);
        time += seconds;
    }

    public static Time of(long days, long hours, long minutes, long seconds) {
        return new Time(days, hours, minutes, seconds);
    }

    public static Time of(long minutes, long seconds) {
        return new Time(0, 0, minutes, seconds);
    }

    public static Time ofSeconds(long seconds) {
        return new Time(ChronoUnit.SECONDS, seconds);
    }

    public static Time ofMinutes(long minutes) {
        return new Time(ChronoUnit.MINUTES, minutes);
    }

    public static Time ofHours(long hours) {
        return new Time(ChronoUnit.HOURS, hours);
    }

    public static Time ofDays(long days) {
        return new Time(ChronoUnit.DAYS, days);
    }

    public long getTime(ChronoUnit chronoUnit) {
        return Duration.of(time, chronoUnit).get(chronoUnit);
    }

    /**
     * Get the time as a {@code String} in {@code DD days HH hours MM minutes SS seconds} format.
     *
     * @return
     */
    public String getFormattedTime() {

        long days, hours, minutes, seconds;
        long localTime = time;

        days = localTime / secondsInDay;
        localTime -= days * secondsInDay;

        hours = localTime / secondsInHour;
        localTime -= hours * secondsInHour;

        minutes = localTime / secondsInMinute;
        localTime -= minutes * secondsInMinute;

        seconds = localTime;

            /* The ways in which time can be displayed depending
            on whether the value of the time unit is greater
            than 0, unless it's seconds.
             */
        if (days > 0) {
            return String.format("%d days, %d hours, %d minutes, %d seconds", days, hours, minutes, seconds);
        } else if (hours > 0) {
            return String.format("%d hours, %d minutes, %d seconds", hours, minutes, seconds);
        } else if (minutes > 0) {
            return String.format("%d minutes, %d seconds", minutes, seconds);
        } else {
            return String.format("%d seconds", seconds);
        }

    }

    public long subtractTime(ChronoUnit chronoUnit, long amount) {
        return time -= Duration.of(amount, chronoUnit).get(ChronoUnit.SECONDS);
    }

}

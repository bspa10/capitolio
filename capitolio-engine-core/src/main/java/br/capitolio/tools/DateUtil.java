package br.capitolio.tools;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * Adaptado de Apache POI.
 *
 * src/main/java/org/apache/poi/ss/usermodel/DateUtil.java
 */
public class DateUtil {
    public static final int SECONDS_PER_MINUTE = 60;
    public static final int MINUTES_PER_HOUR = 60;
    public static final int HOURS_PER_DAY = 24;
    public static final int SECONDS_PER_DAY = (HOURS_PER_DAY * MINUTES_PER_HOUR * SECONDS_PER_MINUTE);
    public static final long DAY_MILLISECONDS = SECONDS_PER_DAY * 1000L;

    public static Date now() {
        return new Date();
    }

    public static Date addMinutes(int minutes) {
        final var now = System.currentTimeMillis();
        return new Date(now + (minutes * 60 * 1000L));
    }

    public static LocalDateTime toDate(String serial) {
        final var dSerial = Double.parseDouble(serial);
        if (!(dSerial > -Double.MIN_VALUE)) {
            return null;
        }

        final var wholeDays = (int)Math.floor(dSerial);
        final var millisecondsInDay = (int)((dSerial - wholeDays) * DAY_MILLISECONDS + 0.5);
        final var calendar=  Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault()); // using default time-zone
        setCalendar(calendar, wholeDays, millisecondsInDay, false, false);

        return calendar.toInstant().atZone(calendar.getTimeZone().toZoneId()).toLocalDateTime();
    }

    private static void setCalendar(Calendar calendar, int wholeDays, int millisecondsInDay, boolean use1904windowing, boolean roundSeconds) {
        var startYear = 1900;
        var dayAdjust = -1; // Excel thinks 2/29/1900 is a valid date, which it isn't

        if (use1904windowing) {
            startYear = 1904;
            dayAdjust = 1; // 1904 date windowing uses 1/2/1904 as the first day
        } else if (wholeDays < 61) {
            // Date is prior to 3/1/1900, so adjust because Excel thinks 2/29/1900 exists
            // If Excel date == 2/29/1900, will become 3/1/1900 in Java representation
            dayAdjust = 0;
        }

        calendar.set(startYear, Calendar.JANUARY, wholeDays + dayAdjust, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, millisecondsInDay);

        if (calendar.get(Calendar.MILLISECOND) == 0) {
            calendar.clear(Calendar.MILLISECOND);
        }

        if (roundSeconds) {
            calendar.add(Calendar.MILLISECOND, 500);
            calendar.clear(Calendar.MILLISECOND);
        }
    }

    public static Date toDate(LocalDateTime ldt) {
        return Date.from(
                ZonedDateTime
                        .of(
                                ldt,
                                TimeZone.getTimeZone("UTC").toZoneId())
                        .toInstant()
        );
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant()
                .atZone(TimeZone.getTimeZone("UTC").toZoneId()) // java.util.Date uses UTC
                .toLocalDateTime();
    }

    public static String format(String pattern, TemporalAccessor ldt) {
        return DateTimeFormatter.ofPattern(pattern).format(ldt);
    }

    public static String format(String pattern, Date date) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String format(String pattern, long milliseconds) {
        return format(pattern, new Date(milliseconds));
    }
}

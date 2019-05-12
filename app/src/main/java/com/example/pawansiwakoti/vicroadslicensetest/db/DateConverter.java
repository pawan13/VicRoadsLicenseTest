package com.example.pawansiwakoti.vicroadslicensetest.db;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * This class helps us to convert data to long and back to date again
 * in order to save it in database
 */
public class DateConverter {
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return  date == null ? null : date.getTime();
    }
}

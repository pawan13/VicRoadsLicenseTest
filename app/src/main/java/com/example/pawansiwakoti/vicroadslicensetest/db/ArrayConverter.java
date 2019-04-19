package com.example.pawansiwakoti.vicroadslicensetest.db;

import android.arch.persistence.room.TypeConverter;

public class ArrayConverter {
    @TypeConverter
    public static String toString(String[] array) {
        if (array == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String s: array) {
            sb.append(s)
                    .append(",,");
        }
        return sb.toString();
    }

    @TypeConverter
    public static String[] toStringArray(String text) {
        if (text == null) return null;
        return text.split(",,");
    }
}

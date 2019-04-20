package com.example.pawansiwakoti.vicroadslicensetest;

public class StringUtils {
    public static boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

    public static String fullName(String firstName, String lastName) {
        if (firstName == null && lastName == null) return "Unknown";
        return String.format("%s %s", firstName == null ? "" : firstName, lastName == null ? "" : lastName);
    }
}
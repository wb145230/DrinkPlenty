package com.wb.drinkplenty.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPreferenceSetting {

    private static final String CUP_SERVICE = "CupService";
    private static final String CUP_NIGHT_MODE = "NightMode";
    private static final String CUP_WHITELISTMATTER = "WhiteListMatters";

    public static void setCupService(Context content, Boolean value) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(content);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(CUP_SERVICE, value);
        editor.commit();
    }

    public static Boolean getCupService(Context content) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(content);
        return settings.getBoolean(CUP_SERVICE, false);
    }

    public static void setCupNightModeService(Context content, Boolean value) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(content);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(CUP_NIGHT_MODE, value);
        editor.commit();
    }

    public static Boolean getCupNightModeService(Context content) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(content);
        return settings.getBoolean(CUP_NIGHT_MODE, false);
    }

    public static void setCupWhiteListMatters(Context content, Boolean value) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(content);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(CUP_WHITELISTMATTER, value);
        editor.commit();
    }

    public static Boolean getCupWhiteListMatters(Context content) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(content);
        return settings.getBoolean(CUP_WHITELISTMATTER, false);
    }
}

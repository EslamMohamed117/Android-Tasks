package com.example.online_store;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceManager;

public class Utils {
    protected static int themeSetting;
    private static void modifyStatusBarColor(AppCompatActivity activity)
    {
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(activity, R.color.backgroundColor));
    }
    private static void modifyAppBarColor(ActionBar actionBar, Resources resources)
    {
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor(resources.getString(R.color.backgroundColor)));

        // Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
    }
    public static void initializeActivity(AppCompatActivity activity)
    {
        modifyStatusBarColor(activity);
        modifyAppBarColor(activity.getSupportActionBar(), activity.getResources());
        changeThemeMode(PreferenceManager.getDefaultSharedPreferences(activity).getString("theme", ""));
    }
    public static void changeThemeMode(String key) {
        if(key.equals("system_default"))
            themeSetting = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
        else if(key.equals("dark"))
            themeSetting = AppCompatDelegate.MODE_NIGHT_YES;
        else if(key.equals("white"))
            themeSetting = AppCompatDelegate.MODE_NIGHT_NO;
        AppCompatDelegate.setDefaultNightMode(themeSetting);
    }
}

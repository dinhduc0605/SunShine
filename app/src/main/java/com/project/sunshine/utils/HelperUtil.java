package com.project.sunshine.utils;

import android.content.Context;
import android.preference.PreferenceManager;

import com.project.sunshine.R;

import java.text.SimpleDateFormat;

/**
 * Created by Nguyen Dinh Duc on 2/21/2016.
 */
public class HelperUtil {
    public static String getReadableDateString(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, MMM d");
        return simpleDateFormat.format(time);
    }

    public static String formatMaxMinTemp(float max, float min, Context context) {

        String unitType = PreferenceManager
                .getDefaultSharedPreferences(context)
                .getString(context.getString(R.string.pref_unit_key),
                        context.getString(R.string.pref_unit_default));
        if (unitType.equals(context.getString(R.string.pref_unit_imperial))) {
            max = (max * 1.8f) + 32;
            min = (min * 1.8f) + 32;
        }
        return Math.round(max) + "/" + Math.round(min);
    }
}

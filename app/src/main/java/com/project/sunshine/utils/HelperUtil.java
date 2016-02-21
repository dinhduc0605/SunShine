package com.project.sunshine.utils;

import java.text.SimpleDateFormat;

/**
 * Created by Nguyen Dinh Duc on 2/21/2016.
 */
public class HelperUtil {
    public static String getReadableDateString(long time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd");
        return simpleDateFormat.format(time);
    }

    public static String formatMaxMinTemp(float max, float min) {
        return Math.round(max) + "/" + Math.round(min);
    }
}

/*
 * Copyright (C) 2016 Felipe Joglar Santos
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.fjoglar.etsitnews.utils;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

    private final static String LOG_TAG = DateUtils.class.getSimpleName();

    /**
     * Converts a String to a Date Object.
     */
    public static Date StringToDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.US);
        Date result = null;
        try {
            result = formatter.parse(date);
        } catch (ParseException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }
        return result;
    }

    public static String formatTime(long pDate) {
        int diffInDays = 0;
        SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z", Locale.US);
        Calendar c = Calendar.getInstance();
        String formattedDate = format.format(c.getTime());

        Date d1;

        try {
            d1 = format.parse(formattedDate);
            long diff = d1.getTime() - pDate;

            diffInDays = (int) (diff / (1000 * 60 * 60 * 24));
            if (diffInDays > 0) {
                if (diffInDays > 0 && diffInDays < 7) {
                    return diffInDays + "d";
                } else {
                    SimpleDateFormat formatter = new SimpleDateFormat("d");
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(pDate);
                    String day = formatter.format(calendar.getTime());
                    formatter = new SimpleDateFormat("MMM");
                    String month = FormatTextUtils.capitalizeWord(formatter.format(calendar.getTime()));
                    return day + " " + month;
                }
            } else {
                int diffHours = (int) (diff / (60 * 60 * 1000));
                if (diffHours > 0) {
                    return diffHours + "h";
                } else {
                    int diffMinutes = (int) ((diff / (60 * 1000) % 60));
                    return diffMinutes + "m";
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "error";
    }

}

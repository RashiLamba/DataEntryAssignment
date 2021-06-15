package com.example.dataentryassignment.room;

import android.text.TextUtils;

import androidx.room.TypeConverter;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class ListConvertor {

    @TypeConverter
    public static List<String> stringToList(String stringList){
        return Arrays.asList(stringList.split(","));
    }

    @TypeConverter
    public static String listToString(List<String> listString){
        return TextUtils.join(",",listString);
    }
}

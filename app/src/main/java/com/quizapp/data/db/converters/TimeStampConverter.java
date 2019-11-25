package com.quizapp.data.db.converters;



import androidx.room.TypeConverter;

import java.util.Date;

public class TimeStampConverter {


   @TypeConverter
    public static Date fromTimestamp(Long time){
       if (time == null){
           return null;
       }else{

           return new Date(time);
       }
    }
    public static Long dateToTimetamp(Date date){
       return date == null? 0L : date.getTime();
    }
}

package com.wyx.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {
    public static String dateUtil(){
        Date date=new Date();
        SimpleDateFormat sticketDate= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  sticketDate.format(date);
    }
}

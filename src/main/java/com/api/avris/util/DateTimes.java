package com.api.avris.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateTimes {

    public static String getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", new Locale("id", "ID"));
        Calendar calnow =  Calendar.getInstance();
        return sdf.format(calnow.getTime());
    }

    public static String getHour(){
        String nol_jam = "", nol_menit = "";
        Date dateTime = new Date();
        int nilai_jam = dateTime.getHours();
        int nilai_menit = dateTime.getMinutes();

        if(nilai_jam <= 9) nol_jam= "0";
        if(nilai_menit <= 9) nol_menit= "0";

        String waktu = nol_jam + Integer.toString(nilai_jam);
        String menit = nol_menit + Integer.toString(nilai_menit);
        String waktuaAndMenit = waktu+":"+menit;

        return waktuaAndMenit;
    }

}

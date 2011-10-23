/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author soleman
 */
public class DateFormater {
    /*
     * ======================== Date Calculation And Operation ======================
     */

    /**
     * Return n day after a current Date. example 12/01/2011 next 1 Day, 13/01/2011
     * @param StrDate
     * @return 
     */
    public static String nextNDate(String StrDate, int nDay) throws ParseException {
        SimpleDateFormat formater = new SimpleDateFormat("d/M/yyyy");
        Date result = formater.parse(StrDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(result);
        cal.add(Calendar.DAY_OF_MONTH, nDay);
        return formater.format(cal.getTime());
    }

    /**
     * Formate a DD/MM/YYYY format date to YYYY-MM-DD format in mysql
     * @param StrDate
     * @return 
     */
    public static String formatDateToDBFormat(String StrDate) throws ParseException {
        if (!StrDate.equals("")) {
            SimpleDateFormat formaterV = new SimpleDateFormat("d/M/yyyy");
            Date result = formaterV.parse(StrDate);
            SimpleDateFormat formaterD = new SimpleDateFormat("yyyy-M-d");
            return formaterD.format(result);
        } else {
            return StrDate;
        }
    }

    /**
     * Formate a DD/MM/YYYY format date to Date
     * @param StrDate
     * @return 
     */
    public static Date getDateFromViewFormat(String StrDate) throws ParseException {
        if (!StrDate.equals("")) {
            SimpleDateFormat formaterV = new SimpleDateFormat("d/M/yyyy");
            Date result = formaterV.parse(StrDate);
            return result;
        } else {
            return null;
        }
    }

    /**
     *  Memformat Data ke bentuk tampilan format date senin, 14 agustus 2010
     * @param StrDate
     * @return
     * @throws ParseException 
     */
    public static String formatDateToView(String StrDate) throws ParseException {
        if (!StrDate.equals("")) {
            SimpleDateFormat formaterD = new SimpleDateFormat("yyyy-M-d");
            Date result = formaterD.parse(StrDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(result);
            int Day = cal.get(Calendar.DAY_OF_WEEK);
            int Month = cal.get(Calendar.MONTH);
            int Year = cal.get(Calendar.YEAR);
            int tanggal = cal.get(Calendar.DAY_OF_MONTH);
            StringBuilder Sb = new StringBuilder();
            StringBuilder append = Sb.append(DayOfTheWeek(Day)).append(", ").append(tanggal).append(" ").append(MonthOfTheYear(Month)).append(" ").append(Year);
            return append.toString();
        } else {
            return StrDate;
        }
    }

    /**
     * Get the day of the date
     * @param StrDate
     * @return
     * @throws ParseException 
     */
    public static String getDayfromDate(String StrDate) throws ParseException {
        if (!StrDate.equals("")) {
            SimpleDateFormat formaterD = new SimpleDateFormat("d/M/yyyy");
            Date result = formaterD.parse(StrDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(result);
            int Day = cal.get(Calendar.DAY_OF_WEEK);
            return DayOfTheWeek(Day);
        } else {
            return null;
        }
    }

    /**
     * Mengambil Hari dalam satu minggu
     * @param i
     * @return 
     */
    public static String DayOfTheWeek(int i) {
        String Day = new String();
        if (i == 1) {
            Day = "Minggu";
        } else if (i == 2) {
            Day = "Senin";
        } else if (i == 3) {
            Day = "Selasa";
        } else if (i == 4) {
            Day = "Rabu";
        } else if (i == 5) {
            Day = "Kamis";
        } else if (i == 6) {
            Day = "Jumat";
        } else if (i == 7) {
            Day = "Sabtu";
        }

        return Day;
    }

    /**
     * Mengembalikan String bulan dalam bahasa indonesia
     * @param i
     * @return 
     */
    public static String MonthOfTheYear(int i) {
        String Month = new String();
        if (i == 0) {
            Month = "Januari";
        } else if (i == 1) {
            Month = "Februari";
        } else if (i == 2) {
            Month = "Maret";
        } else if (i == 3) {
            Month = "April";
        } else if (i == 4) {
            Month = "Mei";
        } else if (i == 5) {
            Month = "Juni";
        } else if (i == 6) {
            Month = "Juli";
        } else if (i == 7) {
            Month = "Agustus";
        } else if (i == 8) {
            Month = "September";
        } else if (i == 9) {
            Month = "Oktober";
        } else if (i == 10) {
            Month = "November";
        } else if (i == 11) {
            Month = "Desember";
        }

        return Month;
    }

    /**
     * Format a YYYY-MM-DD format in mysql to DD/MM/YYYY format
     * @param StrDate
     * @return 
     */
    public static String formatDateToCalFormat(String StrDate) throws ParseException {
        if (!StrDate.equals("")) {
            SimpleDateFormat formaterV = new SimpleDateFormat("yyyy-M-d");
            Date result = formaterV.parse(StrDate);
            SimpleDateFormat formaterD = new SimpleDateFormat("d/M/yyyy");
            return formaterD.format(result);
        } else {
            return StrDate;
        }
    }
    
    /**
     * Format a Date to DD/MM/YYYY format
     * @param StrDate
     * @return 
     */
    public static String formatDateToCalFormat(Date StrDate) throws ParseException {
        if (StrDate!=null) {
            SimpleDateFormat formaterD = new SimpleDateFormat("d/M/yyyy");
            return formaterD.format(StrDate);
        } else {
            return new String();
        }
    }

    /**
     * Format a YYYY-MM-DD format in mysql to Date format
     * @param StrDate
     * @return 
     */
    public static Date getDateFromSqlFormat(String StrDate) throws ParseException {
        if (!StrDate.equals("")) {
            SimpleDateFormat formaterV = new SimpleDateFormat("yyyy-M-d");
            Date result = formaterV.parse(StrDate);
            return result;
        } else {
            return null;
        }
    }
}

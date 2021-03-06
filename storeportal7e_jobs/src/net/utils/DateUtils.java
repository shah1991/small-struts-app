package net.utils;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;


public class DateUtils {
     public static final String STANDARD_DATE = "dd/MM/yyyy";
//	private static final String DATE_WITH_DAY = "EEEE dd/MM/yyyy";
	public static final String DATE_TIME = "dd/MM/yyyy hh:mm aaa";
	private static final String TIME = "HH:mm:ss";
    private static final String TABLE_DATE = "ddMMyy";
    private static final String yyyyMMdd = "yyyyMMdd";
    private static final String BINGO_CONVENTION = "yyyyMMddHHmmss";
    private static final String BINGO_REVERT_CONVENTION = "yyyyMMdd";
    private static final String DATABASE_DATE = "yyyy-MM-dd";
    private static final String CURRENT_YEAR = "yyyy";
    private static final String DAY = "EEEE";
    private static final String REPORT_DATE = "dd-MMM-yy";

    private static final String DATETIME = "yyyy-MM-dd HH:mm:ss";

    public static String formatDateTime(Date date) {
        final SimpleDateFormat sdf = new SimpleDateFormat(DATETIME);
        return date==null? null: sdf.format(date);
    }

    public static Date parse(String date) throws RuntimeException {
        try{
            final SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME);
            sdf.setLenient(false);
            return date==null? null: sdf.parse(date);
        } catch (ParseException pe){
            throw new RuntimeException(pe);
        }
    }
    
    public static Date parse3(String date) throws RuntimeException {
        try{
            final SimpleDateFormat sdf = new SimpleDateFormat(STANDARD_DATE);
            sdf.setLenient(false);
            return date==null? null: sdf.parse(date);
        } catch (ParseException pe){
            throw new RuntimeException(pe);
        }
    }

    /**
     * To get date in "dd/MM/yyyy"
     * @param date date in type String
     * @return String date of "dd/MM/yyyy"
     * @throws RuntimeException if a date is null.
     */
    private static Date tempParse(String date) throws RuntimeException {
        try{
            final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            return date==null? null: sdf.parse(date);
        } catch (ParseException pe){
            throw new RuntimeException(pe);
        }
    }

    /**
     * To get date in "MM/dd/yyyy"
     * @param date date in type String
     * @return String date of "MM/dd/yyyy"
     */
    public static String getTempTableDateFormat(String date) {
        final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        return date==null? null: sdf.format(tempParse(date));
    }

    /**
     * To get date in "yyyy-MM-dd"
     * @param date date in type String
     * @return String date of "yyyy-MM-dd"
     * @throws RuntimeException if a date is null.
     */
    private static Date dataEntryParse(String date) throws RuntimeException {
        try{
            final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            return date==null? null: sdf.parse(date);
        } catch (ParseException pe){
            throw new RuntimeException(pe);
        }
    }

    /**
     * To get date in "dd/MM/yyyy"
     * @param date date in type String
     * @return String date of "dd/MM/yyyy"
     */
    public static String formatDataEntryDateFormat(String date) {
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return date==null? null: sdf.format(dataEntryParse(date));
    }

    /**
     * Convert date in type Date to String date of dd/MM/yyyy
     * @param date java.util.Date
     * @return String date of dd/MM/yyyy
     */
    public static String formatDate(Date date){
        final SimpleDateFormat sdf = new SimpleDateFormat(STANDARD_DATE);
        return date==null? null: sdf.format(date);
    }

    /**
     * Convert date in type Date to String date of dd/MM/yyyy
     * @param date java.util.Date
     * @param format date format
     * @return String date
     */
    public static String formatDate(Date date, String format){
        final SimpleDateFormat sdf = new SimpleDateFormat(format);
        return date==null? null: sdf.format(date);
    }

    /**
     * Convert date in type String to String date of dd/MM/yyyy
     * @param date date in type String
     * @return String date of dd/MM/yyyy
     */
    public static String formatDate(String date){
        final SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME);
        return date==null? null: sdf.format(parse(date));
    }

    /**
     * Converts time from a date to hours:minutes:seconds
     * @param date java.util.Date
     * @return String time in kk:mm:ss
     */
    public static String getTime(Date date) {
        final SimpleDateFormat sdf = new SimpleDateFormat(TIME);
        return date==null? null: sdf.format(date);
    }

    /**
     * Get table date format
     * @param date java.util.Date
     * @return String ddMMyy
     */
    public static String formatYYYYMMDD(Date date) {
        final SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMdd);
        return date==null? null: sdf.format(date);
    }

    /**
     * Get table date format
     * @param date String
     * @return String ddMMyy
     */
    public static String getTableDateFormat(String date) {
        final SimpleDateFormat sdf = new SimpleDateFormat(TABLE_DATE);
        return date==null? null: sdf.format(parse(date));
    }

    /**
     * Get list of java.util.Date from a date range;
     * @param dateFrom date
     * @param dateTo date
     * @return List<Date> dates
     */
    private static List<Date> getTableDateRange(String dateFrom, String dateTo) {
        List<Date> listDates = new ArrayList<Date>();
        Date dateF = DateUtils.parse(dateFrom);
        Date dateT = DateUtils.parse(dateTo);
        long days = (dateT.getTime() - dateF.getTime()) / 1000 / 60 / 60 / 24;
        for (long i = 0; i < days + 1; i++) {
            Date s = new Date(dateF.getTime() + (1000 * 60 * 60 * 24 * i));
            listDates.add(s);
        }
        return listDates;
    }

    /**
     * Get total days in a date range. if dateTo is null or empty, it returns 1 day
     * @param dateFrom String
     * @param dateTo String
     * @return days in between both date
     */
    public static int getDaysBetweenDate(String dateFrom, String dateTo) {
        if (dateTo == null || dateTo.equals("")) {
            return 1;
        }
        Date dateF = DateUtils.parse(dateFrom);
        Date dateT = DateUtils.parse(dateTo);
        return (int) ((dateT.getTime() - (dateF.getTime())) / 1000 / 60 / 60 / 24) + 1;
    }
    
    public static int getDaysBetweenDateStandard(String dateFrom, String dateTo) {
        if (dateTo == null || dateTo.equals("")) {
            return 1;
        }
        Date dateF = DateUtils.parse3(dateFrom);
        Date dateT = DateUtils.parse3(dateTo);
        return (int) ((dateT.getTime() - (dateF.getTime())) / 1000 / 60 / 60 / 24) + 1;
    }

    /**
     * Get date in a date range. if dateTo is null or empty, it returns 1 day
     * @param date specifies the date in array value
     * @return list of records
     */
    public static List<Date> getTableDateForLoop(String[] date) {
        List<Date> listDates = new ArrayList<Date>();
        if(StringUtils.isNotBlank(date[0]) && StringUtils.isNotBlank(date[1])) listDates = DateUtils.getTableDateRange(date[0], date[1]);
        else listDates.add(DateUtils.parse(date[0]));
        return listDates;
    }

    /**
     * To retrieve the total seconds
     * @param hourMinSec time in minute second
     * @return Total seconds
     */
    public static int getSeconds(String hourMinSec) {
        String[] arr = hourMinSec.split(":");
        return (Integer.parseInt(arr[0]) * 3600) + (Integer.parseInt(arr[1]) * 60) + (Integer.parseInt(arr[2]));
    }

    /**
     * To retrieve the total minutes
     * @param hourMinSec time in minute second
     * @return Total minutes
     */
    public static double getMinutes(String hourMinSec) {
        String[] arr = hourMinSec.split(":");
        return ((Integer.parseInt(arr[0]) * 3600) + (Integer.parseInt(arr[1]) * 60) + (Integer.parseInt(arr[2])))/60;
    }

    /**
     * To retrieve time in format "00:00:00"
     * @param total total time
     * @return Time in format "00:00:00"
     */
    public static String getTimeFromSeconds(int total) {
        int hours = total/3600;
        int secondsLeft = total%3600;
        int minutes = secondsLeft/60;
        int seconds = secondsLeft%60;

        String strHours;
        if(hours < 10) strHours = "0" + String.valueOf(hours);
        else strHours =  String.valueOf(hours);

        String strMinutes;
        if(minutes < 10) strMinutes = "0" + String.valueOf(minutes);
        else strMinutes =  String.valueOf(minutes);

        String strSeconds;
        if(seconds < 10) strSeconds = "0" + String.valueOf(seconds);
        else strSeconds =  String.valueOf(seconds);

        return strHours + ":" + strMinutes + ":" + strSeconds;
    }

    /**
     * To retrieve last date of the month
     * author: Louis @ 18/01/2007
     * @param date the date
     * @return last date of the month
     */
    public static String getLastDateOfMonth(String date)
    {
        final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String newDate = date.substring(6,10) +"-"+ date.substring(3,5) +"-"+ date.substring(0,2);
        java.sql.Date jdbcDate = java.sql.Date.valueOf(newDate);

        Calendar cal = Calendar.getInstance();
        cal.setTime(jdbcDate);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), getLastDayOfMonth(jdbcDate));

        return sdf.format(cal.getTime().getTime());
    }

    /**
     * To retrieve last day of the month
     * author: Louis @ 18/01/2007
     * @param sqlDate the date
     * @return last day of the month
     */
    private static int getLastDayOfMonth(java.sql.Date sqlDate)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(sqlDate);

        return cal.getActualMaximum(Calendar.DATE);
    }

    /**
     * To retrieve system date
     * author: Louis @ 27/01/2007
     * @return system date
     */
    private static String getCurrentDate()
    {
       java.util.Date curdt = new java.util.Date();
       SimpleDateFormat formatter = new SimpleDateFormat (DATE_TIME);
       return  formatter.format(curdt);

    }

    /**
     * To convert the date from type Date("dd/MM/yyyy") to type String("yyyyMMddkkmmss") which is Bingo Date.
     * @param date date in type Date("dd/MM/yyyy")
     * @return date in type String("yyyyMMddkkmmss").
     */
    public static String formatBingoDate(Date date){
        final SimpleDateFormat sdf = new SimpleDateFormat(BINGO_CONVENTION);
        return date==null? null: sdf.format(date);
    }

    /**
     * To convert the date from type String("yyyy-MM-dd") to type String("dd/MM/yyyy").
     * @param date date in type String("yyyy-MM-dd")
     * @return date in type String("dd/MM/yyyy").
     */
    public static String convertDBtoGiantDate(String date) {
        final SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME);
        return date==null? null: sdf.format(parse2(date));
    }

    /**
     * To convert the date from type Date("dd-mm-yyyy") to type String("yyyy-MM-dd").
     * @param date date in type Date("dd-mm-yyyy")
     * @return date in type String("yyyy-MM-dd").
     */
    public static String convertDateToDatabaseDate(Date date) {
        final SimpleDateFormat sdf = new SimpleDateFormat(DATABASE_DATE);
        return date==null? null: sdf.format(date);
    }

    public static String convertDateToDatabaseDate2(Date date) {
        final SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM");
        return date==null? null: sdf.format(date);
    }

    /**
     * To convert date in type String to "yyyy-MM-dd" date in Type Date format.
     * @param date date in type String in "dd-mm-yyyy" format
     * @return date in type Date in "yyyy-MM-dd" format.
     * @throws RuntimeException if a date is null.
     */
    private static Date parse2(String date) throws RuntimeException {
        try{
            final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            return date==null? null: sdf.parse(date);
        } catch (ParseException pe){
            throw new RuntimeException(pe.getMessage());
        }
    }

    /**
     * To convert the date from type String("dd/mm/yyyy") to type String("yyyy-MM-dd").
     * @param date date in type String("dd/mm/yyyy")
     * @return date in type String("yyyy-MM-dd").
     */
    public static String convertGiantDateToDBDate(String date) {
        final SimpleDateFormat sdf = new SimpleDateFormat(DATABASE_DATE);
        return date==null? null: sdf.format(parse(date));
    }

    /**
     * To get number of days btw dateFrom and current date
     * @param date String
     * @return total number of days
     */
    public static int getNumberOfDaysByDate(String date) {
        Date dateF = DateUtils.parse(date);
        String current = DateUtils.getCurrentDate();
        Date curd = DateUtils.parse(current);
        return (int) ((curd.getTime() - dateF.getTime()) / 1000 / 60 / 60 / 24) + 1;
    }

    public static Date formatBingoRevertDate(Date d) {
        final SimpleDateFormat sdf = new SimpleDateFormat(BINGO_REVERT_CONVENTION);
        String date = sdf.format(d);
        try {
            return d==null? null: sdf.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static String formatBingoDate2(Date d) {
        final SimpleDateFormat sdf = new SimpleDateFormat(BINGO_REVERT_CONVENTION);
        return d==null? null: sdf.format(d);
    }

    public static String getCurrentYear() {
        java.util.Date curdt = new java.util.Date();
        SimpleDateFormat formatter = new SimpleDateFormat(CURRENT_YEAR);
        return formatter.format(curdt);
    }

    public static String convertDBDateToTableDate(String date) {
        final SimpleDateFormat sdf = new SimpleDateFormat(TABLE_DATE);
        return date==null? null: sdf.format(dataEntryParse(date));
    }

    public static String formatDay(String date){
        final SimpleDateFormat sdf = new SimpleDateFormat(DAY);
        return date==null? null: sdf.format(parse(date));
    }

    public static String convertGiantToEjDate(String date){
        final SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMdd);
        return date==null? null: sdf.format(parse(date));
    }

    public static String formatReportDate(Date date){
        final SimpleDateFormat sdf = new SimpleDateFormat(REPORT_DATE);
        return date==null? null: sdf.format(date);
    }

    public static String convertYYYYMMDD(String rawDate) {
        String date;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.setLenient(false);
        Date utilDate;
        try {
            utilDate = sdf.parse(rawDate);
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }

        sdf = new SimpleDateFormat("dd/MM/yyyy");
        date = sdf.format(utilDate);
        return date;
    }

    public static String convertToDateDDMY(String date){
        String dd = date.substring(6,8);
        String m;
        if(date.substring(4,6).equals("10"))
            m = "A";
        else if(date.substring(4,6).equals("11"))
            m = "B";
        else if(date.substring(4,6).equals("12"))
            m = "C";
        else m = date.substring(5,6);
        String y = date.substring(3,4);
        return dd + m + y;
    }

    /**
     * January = 1
     * @param month month
     * @param year year
     * @return no of days in the month
     */
    public static Integer getNumberOfDaysInMonth(int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    
    public static boolean isToday(Date d) {
    	return org.apache.commons.lang.time.DateUtils.isSameDay(d, new Date());
    }
}

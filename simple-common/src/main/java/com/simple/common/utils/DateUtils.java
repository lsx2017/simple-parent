package com.simple.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 提供日期有关的相关操作
 * @author liushx
 * @date 2016年11月1日
 */
public class DateUtils{
	public final static String PATTERN_DATE = "yyyy-MM-dd"; 
	public final static String PATTERN_24TIME = "yyyy-MM-dd HH:mm:ss"; 
	public final static String PATTERN_MINUTE= "yyyy-MM-dd HH:mm"; 
	
	private static Map<String, SimpleDateFormat> formats;

	static {
		formats = new HashMap<String, SimpleDateFormat>();
		formats.put(PATTERN_DATE, new SimpleDateFormat(PATTERN_DATE));
		formats.put(PATTERN_24TIME, new SimpleDateFormat(PATTERN_24TIME));
		formats.put(PATTERN_MINUTE, new SimpleDateFormat(PATTERN_MINUTE));
	}
	
	/**
	 * 根据一个时间获取这个时间的前一天时间
	 * Aug 25, 2014
	 * 3:46:31 PM
	 * @param date
	 * @return
	 */
	public static Date getProcDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
		return calendar.getTime();
	}
	
	/**
	 * 获得当前事件的Long值
	 * Aug 25, 2014
	 * 3:42:19 PM
	 * @return
	 */
	public static Long getNowTime(){
		return new Date().getTime();
	}
    /**
     * 根据传入的模式参数返回当天的日期
     * @param pattern 传入的模式
     * @return 按传入的模式返回一个字符串
     */
    public static String getToday(String pattern)
    {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
    /**
     * 根据传入的模式参数返回昨天的日期
     * @param pattern 传入的模式
     * @return 按传入的模式返回一个字符串
     */
    public static String getYesterday(String pattern)
    {
    	Date date = new Date(new Date().getTime()-24*60*60*1000);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }
    
    /**
     * 根据传入的模式参数返回当天的日期
     * @param pattern 传入的模式
     * @return 按传入的模式返回一个字符串
     */
    public static String getToday()
    {
        return getToday("yyyy-MM-dd");
    }
    
    /**
     * 根据传入的模式参数返回当天的日期
     * @param pattern 传入的模式
     * @return 按传入的模式返回一个字符串
     */
    public static String getNow()
    {
    	return getToday("yyyy-MM-dd HH:mm:ss");
    }   
    
    /**
     * 比较两个日期大小
     * @param date1 日期字符串
     * @param pattern1 日期格式
     * @param date2 日期字符串
     * @param pattern2 日期格式
     * @return boolean 若是date1比date2小则返回true
     * @throws ParseException
     */
    public static boolean compareMinDate(String date1, String pattern1,
                                         String date2, String pattern2) throws ParseException
    {
        Date d1 = convertToCalendar(date1, pattern1).getTime();
        Date d2 = convertToCalendar(date2, pattern2).getTime();
        return d1.before(d2);
    }

    /**
     * 比较两个日期大小
     * @param date1 Date
     * @param date2 Date
     * @return boolean 若是date1比date2小则返回true
     */
    public static boolean compareMinDate(Date date1, Date date2)
    {
        try
        {
            return DateUtils.compareMinDate(DateUtils.formatDate(date1, "yyyy-MM-dd HH:mm:ss"),
                                           "yyyy-MM-dd HH:mm:ss",
                                           DateUtils.formatDate(date2, "yyyy-MM-dd HH:mm:ss"),
                                           "yyyy-MM-dd HH:mm:ss");
        }
        catch(Exception ex)
        {
            return false;
        }
    }

    /**
     * 根据传入的日期字符串以及格式，产生一个Calendar对象
     * @param date 日期字符串
     * @param pattern 日期格式
     * @return Calendar
     * @throws ParseException 当格式与日期字符串不匹配时抛出该异常
     */
    public static Calendar convertToCalendar(String date, String pattern) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date d = sdf.parse(date);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(d);
        return calendar;
    }

    /**
     * 用途：以指定的格式格式化日期字符串
     * @param pattern 字符串的格式
     * @param currentDate 被格式化日期
     * @return String 已格式化的日期字符串
     * @throws NullPointerException 如果参数为空
     */
    public static String formatDate(Calendar currentDate, String pattern)
    {
        if(currentDate == null || pattern == null)
        {
            throw new NullPointerException("The arguments are null !");
        }
        Date date = currentDate.getTime();
        return formatDate(date, pattern);
    }

    /**
     * 用途：以指定的格式格式化日期字符串
     * @param pattern 字符串的格式
     * @param currentDate 被格式化日期
     * @return String 已格式化的日期字符串
     * @throws NullPointerException 如果参数为空
     */
    public static String formatDate(Date currentDate, String pattern)
    {
        if(currentDate == null || "".equals(currentDate) || pattern == null)
        {
        	return null;
            //throw new NullPointerException("The arguments are null !");
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(currentDate);
    }

    /**
     * 用途：以指定的格式格式化日期字符串
     * @param currentDate 被格式化日期字符串 必须为yyyymmdd
     * @param pattern 字符串的格式
     * @return String 已格式化的日期字符串
     * @throws NullPointerException 如果参数为空
     * @throws java.text.ParseException 若被格式化日期字符串不是yyyymmdd形式时抛出
     */
    public static String formatDate(String currentDate, String pattern) throws java.text.ParseException
    {
        if(currentDate == null || pattern == null)
        {
        	return null;
            //throw new NullPointerException("The arguments are null !");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(currentDate);
        sdf.applyPattern(pattern);
        return sdf.format(date);
    }

    /**
     * 用途：以指定的格式格式化日期字符串
     * @param strDate 被格式化日期字符串 必须为yyyymmdd
     * @param formator 格式字符串
     * @return String 已格式化的日期字符串
     * @throws NullPointerException 如果参数为空
     * @throws java.text.ParseException 若被格式化日期字符串不是yyyymmdd形式时抛出
     */
    public static Calendar strToDate(String strDate, String formator)
    {
        if(strDate == null || formator == null)
        {
            throw new NullPointerException("The arguments are null !");
        }

        Calendar date = Calendar.getInstance();
        date.setTime(java.sql.Date.valueOf(strDate));
        return date;
    }

    /**
     * 判断当前时间是否在参数时间内（当开始时间大于结束时间表示时间段的划分从begin到第二天的end时刻）
     *  例如当前时间在12：00 传入参数为（12,12,0,1）返回true
     *  例如当前时间在12：00 传入参数为（12,12,1,0）返回true
     * @param beginHour int 开始的小时值
     * @param endHour int   结束的小时值
     * @param beginMinu int 开始的分钟值
     * @param endMinu int   结束的分钟值
     * @return boolean
     */
    public static boolean isInTime(int beginHour, int endHour,
                                   int beginMinu,
                                   int endMinu)
    {
        Date date1 = new Date();
        Date date2 = new Date();
        Date nowDate = new Date();
        date1.setHours(beginHour);
        date2.setHours(endHour);
        date1.setMinutes(beginMinu);
        date2.setMinutes(endMinu);
        if(date1 == date2)
        {
            return false;
        }
        //yyyy-MM-dd HH:mm:ss
        if(
                DateUtils.compare(date2, date1))
        {
            if(!DateUtils.compare(nowDate, date1)
               || DateUtils.compare(nowDate, date2))
            {
                return true;
            }
        }
        else
        {
            if(
                    !DateUtils.compare(nowDate, date1) &&
                    DateUtils.compare(nowDate, date2)
                    )
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 开始时间小于结束时间返回true，否则返回false
     * @param beginDate Date
     * @param endDate Date
     * @return boolean
     */
    private static boolean compare(Date beginDate, Date endDate)
    {
        try
        {

            return DateUtils.compareMinDate(DateUtils.formatDate(beginDate,
                    "yyyy-MM-dd HH:mm:ss"),
                                           "yyyy-MM-dd HH:mm:ss",
                                           DateUtils.formatDate(endDate,
                    "yyyy-MM-dd HH:mm:ss"),
                                           "yyyy-MM-dd HH:mm:ss");

        }
        catch(Exception ex)
        {
//            log.error ( "时间格式转换错误" + ex ) ;
            return false;
        }
    }

    /**
     * 将指定格式的时间String转为Date类型
     * @param dateStr String 待转换的时间String
     * @param pattern String 转换的类型
     * @throws ParseException
     * @return Date
     */
    public static Date convertStringToDate(String dateStr,String patternner) throws ParseException
    {
        String pattern =patternner;

        if(dateStr == null || "".equals(dateStr))
        {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(dateStr);
    }

    public static String convertDateToString(Date date) throws ParseException
    {
        if(date == null)
        {
            return "";
        }
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }
    
    public static void main(String[] args){
    	try {
    		long ll=StringToLong("2016-8");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * 给日期增加时间间隔
     * 
     * eg.给当前的日期加一周:addInterval(date, 1, "01");
     * 
     * @param date 基础日期
     * @param num 时间间隔量
     * @param timeUnit 时间间隔单位 00:Calendar.DAY_OF_YEAR;01:Calendar.WEEK_OF_YEAR;02:Calendar.MONTH;否则返回null
     * @return 增加后的日期
     * @author shenyj 2012/07/17 created
     */
    public static Date addInterval(Date date, int num, String timeUnit) {
    	if (date == null) {
    		return null;
    	}
    	Integer calendarTimeUnit = convert2CalendarUnit(timeUnit);
    	if (calendarTimeUnit == null) {
    		//不支持的单位转换或者timeUnit为null，则直接返回date
    		return date;
    	}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendarTimeUnit, num);
		return calendar.getTime();
    }
    
    /**
	 * 将时间单位转换为Calendar类的单位
	 * 
	 * @param timeUnit 时间单位 00:Calendar.DAY_OF_YEAR;01:Calendar.WEEK_OF_YEAR;02:Calendar.MONTH;否则返回null
	 * @return 
	 * @author shenyj 2012/07/17 created
	 */
	private static Integer convert2CalendarUnit(String timeUnit) {
		if (timeUnit != null && !"".equals(timeUnit)) {
			if ("00".equals(timeUnit)) {
				return Calendar.DAY_OF_YEAR;//日
			} else if ("01".equals(timeUnit)) {
				return Calendar.WEEK_OF_YEAR;//周
			} else if ("02".equals(timeUnit)) {
				return Calendar.MONTH;//月
			}
		}
		
		return null;
	}
	
	/**
	 * 格式化分钟，如“2000”转化为“1天9小时20分钟”
	 * 
	 * @param minutes
	 * @return
	 */
	public static String formatMinute(int minutes) {
		if (minutes <= 0) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		int m = minutes % 60;
		if (m != 0) {
			sb.append(m).append("分钟");
		}
		int hours = minutes / 60;
		if (hours == 0) {
			return sb.toString();
		}
		int h = hours % 24;
		if (h != 0) {
			sb.insert(0, h + "小时");
		}
		int days = hours / 24;
		if (days == 0) {
			return sb.toString();
		}
		sb.insert(0, days + "天");
		return sb.toString();
	}
	
	public static String getDatestr1(String d){
		if(d == null){return "";}
		String[] array1 = d.split("-");
		if(array1 == null || array1.length != 3){
			return d;
		}
		return array1[0]+"年"+array1[1]+"月"+array1[2]+"日";
	}

	/**
	 * 获取本月的第一天
	 * @pattern: yyyy-MM-dd
	 * @return: yyyy-MM-dd
	 */
	public static String getMonthFirstDay() {   
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
	    return DateUtils.formatDate(calendar, "yyyy-MM-dd");
	}
	
	/** 
     * 取得月第一天 
     *  
     * @param date 
     * @return 
     */  
    public static Date getFirstDateOfMonth(Date date) {  
        Calendar c = Calendar.getInstance();  
        c.setTime(date);  
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));  
        return c.getTime();  
    }
    
    /** 
     * 取得月最后一天 
     *  
     * @param date 
     * @return 
     */  
    public static Date getLastDateOfMonth(Date date) {  
        Calendar c = Calendar.getInstance();  
        c.setTime(date);  
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));  
        return c.getTime();  
    } 
    
	public static Date[] getSeason(int nSeason){
		Calendar c = Calendar.getInstance();
		Date[] season = new Date[2];
		if (nSeason == 1) {// 第一季度  
            c.set(Calendar.MONTH, Calendar.JANUARY);  
			season[0] = getFirstDateOfMonth(c.getTime());
            c.set(Calendar.MONTH, Calendar.MARCH);  
			season[1] = getLastDateOfMonth(c.getTime());
        } else if (nSeason == 2) {// 第二季度  
            c.set(Calendar.MONTH, Calendar.APRIL);  
			season[0] = getFirstDateOfMonth(c.getTime());
            c.set(Calendar.MONTH, Calendar.JUNE);  
			season[1] = getLastDateOfMonth(c.getTime());
        } else if (nSeason == 3) {// 第三季度  
            c.set(Calendar.MONTH, Calendar.JULY);  
			season[0] = getFirstDateOfMonth(c.getTime());
            c.set(Calendar.MONTH, Calendar.SEPTEMBER); 
			season[1] = getLastDateOfMonth(c.getTime()); 
        } else if (nSeason == 4) {// 第四季度  
            c.set(Calendar.MONTH, Calendar.OCTOBER);  
			season[0] = getFirstDateOfMonth(c.getTime());
            c.set(Calendar.MONTH, Calendar.DECEMBER);  
			season[1] = getLastDateOfMonth(c.getTime()); 
        }  
        return season;  
	}
	
	/**
	 * 某一个月第一天和最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Map<String, Object> getFirstday_Lastday_Month(Date date,
			boolean isNeedHms) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Date theDate = calendar.getTime();

		// 上个月第一天
		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(theDate);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = df.format(gcLast.getTime());
		StringBuffer str = new StringBuffer().append(day_first);
		if (isNeedHms)
			str.append(" 00:00:00");
		day_first = str.toString();

		// 上个月最后一天
		calendar.add(Calendar.MONTH, 1); // 加一个月
		calendar.set(Calendar.DATE, 1); // 设置为该月第一天
		calendar.add(Calendar.DATE, -1); // 再减一天即为上个月最后一天
		String day_last = df.format(calendar.getTime());
		StringBuffer endStr = new StringBuffer().append(day_last);
		if (isNeedHms)
			str.append(" 23:59:59");
		day_last = endStr.toString();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("first", day_first);
		map.put("last", day_last);
		return map;
	}
	
	/**
	 * 某一个月第一天和最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Map<String, Object> getFirstLastDayByMonth(Date date,
			String pattern, boolean isNeedHms) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Date theDate = calendar.getTime();

		// 上个月第一天
		GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
		gcLast.setTime(theDate);
		gcLast.set(Calendar.DAY_OF_MONTH, 1);
		String day_first = df.format(gcLast.getTime());
		StringBuffer str = new StringBuffer().append(day_first);
		if (isNeedHms)
			str.append(" 00:00:00");
		day_first = str.toString();

		// 上个月最后一天
		calendar.add(Calendar.MONTH, 1); // 加一个月
		calendar.set(Calendar.DATE, 1); // 设置为该月第一天
		calendar.add(Calendar.DATE, -1); // 再减一天即为上个月最后一天
		String day_last = df.format(calendar.getTime());
		StringBuffer endStr = new StringBuffer().append(day_last);
		if (isNeedHms)
			endStr.append(" 23:59:59");
		day_last = endStr.toString();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("first", day_first);
		map.put("last", day_last);
		return map;
	}
	
	/**
	 * 将日期字符串格式化成 yyyy-MM-dd HH:mm:ss 的 java.sql.Date对象
	 * @param dateTimeString
	 * @return
	 */
	public static java.util.Date parseDateTime(String dateTimeString) {
		java.util.Date date = null;
		try {
			date = getDateFormat(PATTERN_24TIME).parse(dateTimeString);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return date;
	}
	
	
	/**
	 * 将日期字符串格式化成 yyyy-MM-dd HH:mm 的 java.sql.Date对象
	 * @param dateTimeString
	 * @return
	 */
	public static java.util.Date parseDateMinute(String dateTimeString) {
		java.util.Date date = null;
		try {
			date = getDateFormat(PATTERN_MINUTE).parse(dateTimeString);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return date;
	}
	
	/**
	 * 根据pattern取得的date formate
	 * 
	 * @param pattern
	 * @return
	 */
	public static SimpleDateFormat getDateFormat(String pattern) {
		SimpleDateFormat dateFormat = formats.get(pattern);
		if (dateFormat == null) {
			dateFormat = new SimpleDateFormat(pattern);
			formats.put(pattern, dateFormat);
		}
		return dateFormat;
	}
	
	/**
	 * 日期字符串 转换为Long
	 * @param str "yyyy_mm"
	 * @return yyyymm
	 */
	public static Long StringToLong(String str){
		String temp=null;
		String strs[]=str.split("-");
		temp=strs[0];
		if(strs[1].length()==1)
		temp+='0'; 
		temp+=strs[1];
		return Long.valueOf(temp);
	}
	/**
	 * 获取当前月份
	 * @return long
	 */
	public static Long getYearCurMonth2Long(String pattern){
		Date curDate=new Date();
		return StringToLong(formatDate(curDate, pattern));
	}
	/**
	 * 获取当前上月份
	 * @param pattern
	 * @return long
	 */
	public static Long getYearProntMonth2Long(String pattern){
		Date curDate=DateUtils.addInterval(new Date(), -1, "02");
		return StringToLong(formatDate(curDate, pattern));
	}
	
	public static String getStrartDate() {
		return null;
	}
}

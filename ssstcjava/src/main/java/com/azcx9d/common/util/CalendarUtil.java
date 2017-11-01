package com.azcx9d.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//需要覆盖提交
public class CalendarUtil {
	
	private static final Calendar CALENDAR = Calendar.getInstance();
	private static final SimpleDateFormat sf =new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat sf2 =new SimpleDateFormat("MM-dd");
	private static final SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat sdf1 =new SimpleDateFormat("yyyyMMddHHmmssS");
	private static final SimpleDateFormat sdf2 =new SimpleDateFormat("yyyyMMddHHmmss");
	private static final SimpleDateFormat time =new SimpleDateFormat("HHmmss");
	
	public static int getYear(){
		return CALENDAR.get(Calendar.YEAR); 
	}
	
	public static int getMonth(){
		return CALENDAR.get(Calendar.MONTH);
	}
	
	public static int getDay(){
		return CALENDAR.get(Calendar.DAY_OF_MONTH);
	}
	
	public static int getHour(){
		return CALENDAR.get(Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * 把yyyy-MM-dd转化为yyyy-MM-dd 23:59:59
	 * @param date 日期
	 * @return 转换后的日期
	 */
	public static Date constructDate(String date){
		
		Date newDate = null;
		try {
			date = date+" 23:59:59";
			newDate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newDate;
	}
	
	/**
	 * 根据MM-dd格式化
	 * @param date
	 * @return
	 */
	public static String formatBymmdd(Date date){
		
		return sf2.format(date);
	}
	
	/**
	 * 根据yyyy-MM-dd格式化
	 * @param date
	 * @return
	 */
	public static Date formatByDate(Date date){
		
		Date newDate = null;
		try {
			newDate = sf.parse(sf.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newDate;
	}
	
	/**
	 * 根据yyyy-MM-dd格式化
	 * @param date
	 * @return yyyy-MM-dd
	 */
	public static String formatDateByString(Date date){
		return sf.format(date);
	}
	
	/**
	 * 把字符串类型的日期转换为日期类型
	 * @param date
	 * @return Date
	 */
	public static Date formatDateByString(String date){
		try {
			return sf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 根据yyyy-MM-dd HH:mm:ss格式化
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String formatDateByTime(Date date){
		return sf.format(date);
	}
	
	/**
	 * 根据yyyy-MM-dd HH:mm:ss格式化
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static Date formatByTime(Date date){
		Date newDate = null;
		try {
			newDate = sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newDate;
	}
	
	/**
	 * yyyyMMddHHmmssS
	 * @param date
	 * @return String yyyyMMddHHmmssS
	 */
	public static String formatByMillisecond(Date date){
		return sdf1.format(date);
	}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
	public static String format2yyyyMMddHHmmss(Date date){
		return sdf2.format(date);
	}
	
	/**
	 * 根据MMddHHmmss格式化
	 * @param date
	 * @return MMddHHmmss
	 */
	public static String formatByMonth(Date date){
		SimpleDateFormat sf =new SimpleDateFormat("MMddHHmmss");
		return sf.format(date);
	}
	
	/**
	 * 根据间隔计算前N天
	 * @param date
	 * @param space
	 * @return Date
	 */
	public static Date findDatesBySpace(Date date,int space){
    	try {
			return  sf.parse(sf.format((date).getTime() - 60*60*1000*24L*space));   //间隔的日期
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}       
    }
	
	/**
	 * 获取两个日期之间的日期
	 * @param String date1
	 * @param String date2
	 * @return List<Date>
	 */
	public static List<Date> getDates(String date1, String date2){ 
	    List<Date> dates = new ArrayList<Date>();
        try {
			if(date1.equals(date2)){ 
				dates.add(sf.parse(date1));   
			    return dates; 
			} 
			 
			String tmp; 
			if(date1.compareTo(date2) > 0){  //确保 date1的日期不晚于date2 
			    tmp = date1; 
			    date1 = date2;  
			    date2 = tmp; 
			} 
			dates.add(sf.parse(date1));
			tmp = sf.format(str2Date(date1).getTime() + 3600*24*1000); 
			 
			int num = 0;  
			while(tmp.compareTo(date2) <0){                   
			    dates.add(sf.parse(tmp));
			    num++; 
			    tmp = sf.format(str2Date(tmp).getTime() + 3600*24*1000); 
			} 
			 
			//if(num == 0) 
				dates.add(sf.parse(date2));
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return dates; 
    } 
    
	/**
	 * 获取两个日期之间的日期
	 * @param Date date1
	 * @param Date date2
	 * @return List<Date>
	 */
	public static List<Date> getDates(Date date1, Date date2){ 
	    List<Date> dates = new ArrayList<Date>();
        try {
			if(date1.equals(date2)){ 
				dates.add(date1);   
			    return dates; 
			} 
			 
			Date tmp; 
			if(date1.compareTo(date2) > 0){  //确保 date1的日期不晚于date2 
			    tmp = date1; 
			    date1 = date2;  
			    date2 = tmp; 
			} 
			dates.add(date1);
			tmp = new Date(date1.getTime() + 3600*24*1000); 
			 
			while(tmp.compareTo(date2) <0){                   
			    dates.add(tmp);
			    tmp = new Date(tmp.getTime() + 3600*24*1000); 
			} 
			 
			//dates.add(date2);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return dates; 
    } 
     
    public static Date str2Date(String str) { 
    	String dateFormat = "yyyy-MM-dd"; 
        SimpleDateFormat sf = new SimpleDateFormat(dateFormat);
        
        if (str == null) return null; 
         
        try { 
            return sf.parse(str); 
        } catch (ParseException e) { 
            e.printStackTrace(); 
        } 
        return null; 
    } 

    /**
     * 根据日期计算前一天或者后一天
     * @param date  目标日期
     * @param method  表示加一天或者减一天
     * @return Date 计算后的日期
     */
    public static Date getDateByMethod(String date,String method){
    	Date newdate = null;
    	try {
    		if (date == null) return null; 
    		
			if("subtract".equals(method)){
				newdate = sf.parse(sf.format(str2Date(date).getTime() - 3600*24*1000));
			}
			if("add".equals(method)){
				newdate = sf.parse(sf.format(str2Date(date).getTime() + 3600*24*1000));
			}
		} catch (ParseException e) {
			
			e.printStackTrace();
			return null;
		}
		return newdate;
    }
    
    /**
     * 计算当前日期的前space天的日期
     * @param date
     * @param space
     * @return List<Date>
     */
    public static List<Date> getDatesBySpace(Date date,int space){
    	List<Date> dates = new ArrayList<Date>();
    	try {
			Date date1 = sf.parse(sf.format((date).getTime() - 60*60*1000*24L*space));       //间隔的日期
			dates.add(date1);
			Date temp = sf.parse(sf.format((date1).getTime() + 3600*24*1000));
			while(temp.compareTo(date)<=0){
				dates.add(temp);
				temp = sf.parse(sf.format((temp).getTime() + 3600*24*1000));
			}
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return dates;
    }
    
    /**
     * 计算当前日期的前space天的日期
     * @param date
     * @param space
     * @return List<Date> mm-dd格式
     */
    public static List<Date> getDatesByInterval(Date date,int space){
    	List<Date> dates = new ArrayList<Date>();
    	try {
			Date date1 = sf.parse(sf.format((date).getTime() - 60*60*1000*24L*space));       //间隔的日期
			dates.add(sf2.parse(sf.format((date1).getTime())));
			Date temp = sf.parse(sf.format((date1).getTime() + 3600*24*1000));
			while(temp.compareTo(date)<=0){
				dates.add(sf2.parse(sf.format((temp).getTime())));
				temp = sf.parse(sf.format((temp).getTime() + 3600*24*1000));
			}
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return dates;
    }
    
    /**
     * 计算当前日期的前space天的日期
     * @param date
     * @param space
     * @return List<Date> mm-dd格式
     */
    public static List<String> formatByInterval(Date date,int space){
    	List<String> dates = new ArrayList<String>();
    	try {
			Date date1 = sf.parse(sf.format((date).getTime() - 60*60*1000*24L*space));       //间隔的日期
			dates.add(sf2.format(date1));
			Date temp = sf.parse(sf.format((date1).getTime() + 3600*24*1000));
			while(temp.compareTo(date)<=0){
				dates.add(sf2.format(temp));
				temp = sf.parse(sf.format((temp).getTime() + 3600*24*1000));
			}
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return dates;
    }
    
    /**  
     * 计算两个日期之间相差的天数 ,日期极端
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return int 相差天数 
     */    
    public static int daysBetween(Date smdate,Date bdate) throws ParseException    
    {    
        smdate = sf.parse(sf.format(smdate));  
        bdate = sf.parse(sf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }    

    /**
     * 计算两个日期之间相差的天数, 字符串的日期格式的计算 
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return int 相差天数 
     */
    public static int daysBetween(String smdate,String bdate) throws ParseException{  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(sdf.parse(smdate));    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(sdf.parse(bdate));    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));     
    }  

    public static List<Date> getDateByMonth(Date date){
    	List<Date> dates = new ArrayList<Date>(0);
        try {
        	Date temp = new Date(date.getTime()-60*60*24*1000L*30);
			while(temp.compareTo(date) <=0){                   
			    dates.add(sf.parse(sf.format(temp)));
			    temp = sf.parse(sf.format((temp).getTime() + 3600*24*1000));
			} 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dates; 
    }
    
    /**
     * Unix时间戳转换成指定格式日期 
     * @param timestampString
     * @param formats
     * @return String
     */
    public static String TimeStamp2DateString(String timestampString){  
	    Long timestamp = timestampString.length()==10?(Long.parseLong(timestampString)*1000):Long.parseLong(timestampString);  
	    String date = sdf.format(new java.util.Date(timestamp));  
	    return date;  
    }
    
    /**
     * Unix时间戳转换成指定格式日期 
     * @param timestampString
     * @param formats
     * @return Date
     */
    public static Date TimeStamp2Date(String timestampString){  
    	Date date =null;
    	Long timestamp = timestampString.length()==10?(Long.parseLong(timestampString)*1000):Long.parseLong(timestampString); 
    	try {
			date = sdf.parse(sdf.format(new java.util.Date(timestamp)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    return date;  
    }
    
    /**
	 * 根据yyyy-MM-dd HH:mm:ss格式化
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static Date formatByTime(String date){
		Date newDate = null;
		try {
			newDate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			newDate = null;
		}
		return newDate;
	}
	/**
	 * 根据yyyy-MM-dd HH:mm:ss格式化
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 * @throws ParseException  
	 */
	public static Date formatToTime(String date) throws ParseException{
		Date newDate = null;
		newDate = sdf.parse(date);
		return newDate;
	}
	
	/**
	 * 根据yyyy-MM-dd HH:mm:ss格式化
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String format2String(Date date){
		return sdf.format(date);
	}
	
	/**
	 * 获取当前时间的时分秒 HHmmss（24小时格式）
	 * @return Integer
	 */
	public static Integer date2HHmmss(){
		return Integer.valueOf(time.format(new Date()));
	}
	
	/**
	 * 获取当前月第一天
	 * @return String
	 */
	public static String currentMonthFirstDay(){
		Calendar c = Calendar.getInstance();    
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        return sf.format(c.getTime());
	}
	public static String getDayDiff(int days){
		   Calendar cl = Calendar.getInstance();
		   cl.setTime(new Date());
		   cl.add(Calendar.DATE, days);
		   Date startDate = cl.getTime();
		   SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
		   String start = dd.format(startDate);
		   //System.out.println("   start "+start);
		   //System.out.println("   end "+ dd.format(new Date()));
		   return start+" 00:00:00";
	}
	public static String getDayDiff(String dateStr,int days){
		try {
			SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
		    Calendar cl = Calendar.getInstance();
		    cl.setTime( dd.parse(dateStr));
		    cl.add(Calendar.DATE, days);
		    Date startDate = cl.getTime();
		    String start = dd.format(startDate);
		    return start+" 00:00:00";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static  String getMonthDiff(int monthys){
		   Calendar cl = Calendar.getInstance();
		   cl.setTime(new Date());
		   cl.add(Calendar.MONTH, monthys);
		   Date startDate = cl.getTime();
		   SimpleDateFormat dd = new SimpleDateFormat("yyyy-MM-dd");
		   String start = dd.format(startDate);
		   //System.out.println("   start "+start);
		   //System.out.println("   end "+ dd.format(new Date()));
		   return start+" 00:00:00";
	 }
	/**
	 * 字符串添加天数
	 * 
	 * @param calendar
	 * @return Date
	 */
	public static Date getDateString(String date,int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(formatDateByString(date));
		calendar.add(Calendar.DAY_OF_MONTH, days);//天数加一，为-1的话是天数减1
		return calendar.getTime();
	}
	
	/**
	 * 通过给指定日期加上指定月数获取时间
	 * @param date
	 * @param month
	 * @return
	 */
	public static Date getDateByAddMonth(Date date,int month) {
		Calendar c = Calendar.getInstance();  
		c.setTime(date);
        c.add(Calendar.MONTH, month);
        return c.getTime();
	}
	
	 /**
     * 获取两个日期相差的天数
     * @param endDate
     * @param startDate
     * @return
     */
    public static int getDateDifference4Day(Date endDate,Date startDate) {
		return new Long((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24)).intValue();
    }
	
}

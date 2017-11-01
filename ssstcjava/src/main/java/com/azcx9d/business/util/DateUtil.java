package com.azcx9d.business.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by HuangQing on 2017/4/3 0002 14:13.
 */
public class DateUtil {
	
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
	private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
	private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");
	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final static SimpleDateFormat sdfTimes = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * 获取YYYY格式
	 * @return
	 */
	public static String getSdfTimes() {
		return sdfTimes.format(new Date());
	}
	
	/**
	 * 获取YYYY格式
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD格式
	 * @return
	 */
	public static String getDay(Date date){
		return sdfDay.format(date);
	}

	/**
	 * 获取YYYYMMDD格式
	 * @return
	 */
	public static String getDays(){
		return sdfDays.format(new Date());
	}

	/**
	 * 获取YYYY-MM-DD HH:mm:ss格式
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}

	/**
	* @Title: compareDate
	* @Description: TODO(日期比较，如果s>=e 返回true 否则返回false)
	* @param s
	* @param e
	* @return boolean  
	* @throws
	* @author fh
	 */
	public static boolean compareDate(String s, String e) {
		if(fomatDate(s)==null||fomatDate(e)==null){
			return false;
		}
		return fomatDate(s).getTime() >=fomatDate(e).getTime();
	}

	/**
	 * 格式化日期
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 校验日期是否合法
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}
	
	/**
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int getDiffYear(String startTime,String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			//long aa=0;
			int years=(int) (((fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}
	 
	/**
     * <li>功能描述：时间相减得到天数
     * @param beginDateStr
     * @param endDateStr
     * @return
     * long 
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;
        
            try {
				beginDate = format.parse(beginDateStr);
				endDate= format.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            //System.out.println("相隔的天数="+day);
      
        return day;
    }
    
    /**
     * 得到n天之后的日期
     * @param days
     * @return
     */
    public static String getAfterDayDate(int days) {
//    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, days); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }

    /**
     * 得到n天之后的日期
     * @param date 实际时间
     * @param days
     * @return
     */
	public static Date getAfterDayDate(Date date, int days) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + days);
		return now.getTime();
	}
    
    /**
     * 得到n天之后是周几
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
    	int daysInt = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        return dateStr;
    }

	/**
	 * 毫秒数转换成年月日时分秒格式字符串：默认格式；"yyyy-MM-dd HH:mm:ss"
	 * @param milliseconds 精确到毫秒的字符串
	 * @param format 要转换成的模板，如果为null或者空则采用默认格式；"yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	public static String timeStamp2Date(String milliseconds,String format) {
		if(milliseconds == null || milliseconds.isEmpty() || milliseconds.equals("null")){
			return "";
		}
		if(format == null || format.isEmpty()){
			format = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(Long.valueOf(milliseconds)));
	}

	/**
	 * 日期格式字符串转换成毫秒数
	 * @param date_str 字符串日期
	 * @param format 如：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String date2TimeStamp(String date_str,String format){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return String.valueOf(sdf.parse(date_str).getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 取得当前时间戳（毫秒）
	 * @return
	 */
	public static String timeStamp(){
		long time = System.currentTimeMillis();
		String t = String.valueOf(time);
		return t;
	}

	/**
	 * 传入时间戳，获取到多少天之后的时间戳
	 * @param time 时间戳毫秒
	 * @param days 多少天之后
	 * @return
	 */
	public static String getYMDAfterDays(String time, int days){
		Date afterDayDate = getAfterDayDate(new Date(Long.valueOf(time)), days);//获取到days天后的Date
		String afterDay = getDay(afterDayDate);	//获取到年月日
		String s = date2TimeStamp(afterDay, "yyyy-MM-dd");	//获取到时间戳
		return afterDay;
	}

	/**
	 * 传入时间戳，获取到多少天之后的时间戳
	 * @param time 时间戳毫秒
	 * @param days 多少天之后
	 * @return
	 */
	public static String getMillisecondsAfterDays(String time, int days){
		Date afterDayDate = getAfterDayDate(new Date(Long.valueOf(time)), days);//获取到days天后的Date
		String afterDay = getDay(afterDayDate);	//获取到年月日
		String s = date2TimeStamp(afterDay, "yyyy-MM-dd");	//获取到时间戳
		return s;
	}

//	/**
//	 * 获取之前或者之后多少天的年月日
//	 * @param date 实际时间
//	 * @param days 多少天之前或之后
//	 * @return 多少天之前或多少天之后的日期，格式：yyyy-MM-dd
//	 */
//	public static String getAfterDay(Date date,int days){
//
//		return null;
//	}

	public static void main(String[] args) {
//    	System.out.println(getDays());
//    	System.out.println(getAfterDayWeek("3"));
//		String s = "1491889339000";
//		String s1 = date2TimeStamp(s, "yyyy-MM-dd");
//		System.out.println(getDays(new Date(Long.valueOf(s))));
//		String timeStamp = timeStamp();
//		String s = timeStamp2Date(timeStamp, "yyyy-MM-dd");
//		System.out.println(s);


//		String afterDayDate = getAfterDayDate(6);
//		System.out.println(afterDayDate);
//		String day = getDay(new Date(afterDayDate));
//		String s = timeStamp2Date(afterDayDate, "yyyy-MM-dd");
//		System.out.println(day);
		String time = timeStamp();	//获取当前时间戳
		Date afterDayDate = getAfterDayDate(new Date(Long.valueOf(time)), -2);//获取到-2天前的Date
		String afterDay = getDay(afterDayDate);	//获取到年月日
		System.out.println(afterDay);
//		String s = date2TimeStamp(afterDay + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
		String s2 = date2TimeStamp(afterDay, "yyyy-MM-dd");	//获取到时间戳
//		System.out.println(s);
		System.out.println(s2);
//		String[] split = afterDay.split(" ");
//		System.out.println(split[0]);
//		System.out.println(split[1]);
	}

}

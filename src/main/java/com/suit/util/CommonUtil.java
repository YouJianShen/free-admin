package com.suit.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CommonUtil {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
	// 获取下一个月，格式为yyyyMM
	public static int nextMonth(int month) throws ParseException {
		Date nowDate = sdf.parse(month + "");
		Calendar c = Calendar.getInstance();
		c.setTime(nowDate);
		c.add(Calendar.MONTH, 1);
		int nextmonth = Integer.parseInt(sdf.format(c.getTime()));
		return nextmonth;
	}
}

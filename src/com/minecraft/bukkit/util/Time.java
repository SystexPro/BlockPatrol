package com.minecraft.bukkit.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Time {
	
	public Time() {}

	public static String Month() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MMMMM");
		return sdf.format(cal.getTime());
	}

	public static String time() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
		return sdf.format(cal.getTime());
	}

	public static String Day() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		return sdf.format(cal.getTime());
	}

	public static String getFullDateTime() {
		String month = Month().substring( 0, 3 );
		String day = Day();
		return month + "." + day + "@" + time();
	}

}

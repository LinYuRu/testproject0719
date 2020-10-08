package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;
import java.util.Random;

public class Student extends basicStudent{
	

	public String getId() {
		String pattern = "ABCDEFGHJKLMNPQRSTUVWXYZIO";

		Random r = new Random();
		String s = "";
		int checknum = 0;

		int t = (r.nextInt(26) + 65);
//		System.out.println(t);
		s += (char) t;
//		System.out.println("英文字母: " + s);
		t = pattern.indexOf((char) t) + 10;
		checknum += t / 10;
		checknum += t % 10 * 9;

		t = getStudentSexNo();
		s += Integer.toString(t);
//		System.out.println("第2個數字 " + s);
		checknum += t * 8;

		for (int i = 2; i < 9; i++) {
			s += Integer.toString(t = r.nextInt(10));
			checknum += t * (9 - i);
		}

		checknum = (10 - ((checknum) % 10)) % 10;
		s += Integer.toString(checknum);
		System.out.println(s);
		return s;
	}

	public String getRandomSno() {
		String pattern = "ABCDEFGHJKLMNPQRSTUVWXYZIO";

		Random r = new Random();
		String s = "";

		int t = (r.nextInt(26) + 65);
		s += (char) t;
		t = pattern.indexOf((char) t) + 10;

		for (int i = 1; i < 4; i++) {
			s += Integer.toString(t = r.nextInt(10));
		}

		return s;
	}

	public Integer getStudentSexNo() {
		Random r = new Random();
		int t = r.nextInt(2) + 1;
		return t;
	}

	public String getStudentSex(String id) {
		String sex = id.substring(1, 2);
		if (sex.matches("1")) {
			return "男";
		} else {
			return "女";
		}
	}

	public String getStudentName(String id) {
		String sex = id.substring(1, 2);
		if (sex.matches("1")) {
			return "金小胖";
		} else {
			return "張曼玉";
		}
	}

	public int getStudentAge() {
		int age = 0;
		try {
			age = minusDate(birthday());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return age;
	}

	public java.sql.Date birthday() throws ParseException {
		Random r = new Random();
		String monthstr = null;
		String daystr = null;

		int months = (r.nextInt(12) + 1);
		if (months < 10) {
			monthstr = "0" + Integer.toString(r.nextInt(12) + 1);
		} else {
			monthstr = Integer.toString(r.nextInt(12) + 1);
		}
		int days = (r.nextInt(30) + 1);
		if (days < 10) {
			daystr = "0" + Integer.toString(days);
		} else {
			daystr = Integer.toString(days);
		}
		String yearstr = Integer.toString(r.nextInt(30) + 1980);
		String birthday = yearstr + "-" + monthstr + "-" + daystr;

//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		String date = dateFormat.format(birthday);

//		System.out.println(dateFormat.format(date));
		 SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	        java.util.Date parsed = format.parse(birthday);
	        java.sql.Date date = new java.sql.Date(parsed.getTime());
//			date = dateFormat.parse(birthday);
		return date;
	}

	public int minusDate(java.sql.Date birthday) throws ParseException {
		Calendar cal = Calendar.getInstance();
		Calendar bir = Calendar.getInstance();
		bir.setTime(birthday);
		if (cal.before(birthday)) {
			throw new IllegalArgumentException("The birthday is before Now,It‘s unbelievable");
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayNow = cal.get(Calendar.DAY_OF_MONTH);
		int yearBirth = bir.get(Calendar.YEAR);
		int monthBirth = bir.get(Calendar.MONTH);
		int dayBirth = bir.get(Calendar.DAY_OF_MONTH);
		int age = yearNow - yearBirth;
		if (monthNow < monthBirth || (monthNow == monthBirth && dayNow < dayBirth)) {
			age--;
		}
//		Integer ages = Integer.valueOf(age); 
		return age;
	}
}

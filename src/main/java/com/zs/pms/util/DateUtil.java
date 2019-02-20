package com.zs.pms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public String getStrDate() {
		//��õ�ǰ����
		Calendar cal=Calendar.getInstance();
		//��õ�ǰ��
		int year=cal.get(Calendar.YEAR);
		//��õ�ǰ��
		int month=cal.get(Calendar.MONTH)+1;
		//��
		int date=cal.get(Calendar.DAY_OF_MONTH);
		//Сʱ24
		int hh=cal.get(Calendar.HOUR_OF_DAY);
		//���õ�ע��
		int minute=cal.get(Calendar.MINUTE);
		int second=cal.get(Calendar.SECOND);
	//	
//		return year+"-"+month+"-"+date+" "+hour+":"+minute+":"+second;
		
		//ͨ�����Сʱ���ж�������������
		String str="";
		if(hh>=6&&hh<=11) {
			str="上午好";
		}else if(hh>11&&hh<=14) {
			str="中午好";
		}else if(hh>14&&hh<=18) {
			str="下午好";
		}else if(hh>18&&hh<=24) {
			str="晚上好";
		}else {
			str="凌晨好";
		}
		return year+"-"+month+"-"+date+" "+hh+":"+minute+":"+second+str;
		}
	public static Date getStrToDate(String time,String pattern) throws ParseException {
		SimpleDateFormat sdf=new SimpleDateFormat(pattern);
		return sdf.parse(time);
	}
	public static String getDateToStr(Date time,String pattern) {
		SimpleDateFormat sdf=new SimpleDateFormat(pattern);
		return sdf.format(time);
	}



//public static void main (String[]args) {
//	System.out.println(DateUtil.getStrDate());
//	try {
//		System.out.println(DateUtil.getStrToDate(DateUtil.getStrDate(), "yyyy-mm-dd hh:mm:ss"));
//	} catch (ParseException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	try {
//		System.out.println(DateUtil.getDateToStr(DateUtil.getStrToDate(DateUtil.getStrDate(), "yyyy-mm-dd hh:mm:ss"), "yyyy-mm-dd hh:mm:ss"));
//	} catch (ParseException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//}
}
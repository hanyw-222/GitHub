package com.demo.util;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期字符串转日期类型
 */
public class DateConverter implements Converter<String, Date> {     
 @Override     
 public Date convert(String source) {
     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
     dateFormat.setLenient(false);     
     try {     
         try {
			return dateFormat.parse(source);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}     
     } catch (ParseException e) {     
         e.printStackTrace();    
     }           
     return null;    
 }
}  

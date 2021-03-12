package com.happypets.aplicacion.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DBDataUtils {
	public static Date formatDate(String fecha) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = dateFormat.parse(fecha);
		return date;
	}
}

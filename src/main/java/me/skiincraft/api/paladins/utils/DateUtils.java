package me.skiincraft.api.paladins.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static Date parseDate(String data) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");
		DateFormat outputformat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		Date date = null;
		String output = null;
		try {
			date = df.parse(data);
			output = outputformat.format(date);
			return outputformat.parse(output);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}
		return date;
	}

}

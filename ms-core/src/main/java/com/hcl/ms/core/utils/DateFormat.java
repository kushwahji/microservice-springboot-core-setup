/**
 * 
 */
package com.hcl.ms.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import com.hcl.ms.core.exception.ApplicationException;

/**
 * @author SANTOSH
 *
 */
public class DateFormat {

	private static Logger logger = LoggerFactory.getLogger(DateFormat.class);

	/**
	 * Convert Date to String
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String convertDateToString(Date date, String pattern) {
		SimpleDateFormat format = null;
		try {
			logger.info("Given Date is :" + date);
			logger.info("Given Pattern is :" + pattern);
			format = new SimpleDateFormat(pattern);
		} catch (Exception e) {
			throw new ApplicationException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), e.getMessage());
		}

		return format.format(date);
	}

	/**
	 * Convert String to Date
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date convertStringToDate(String date, String pattern) {
		SimpleDateFormat formatter = null;
		Date parsedDate = null;
		try {
			formatter = new SimpleDateFormat(pattern);
			parsedDate = formatter.parse(date);
		} catch (Exception e) {
			throw new ApplicationException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage(), e.getMessage());
		}

		return parsedDate;
	}
}

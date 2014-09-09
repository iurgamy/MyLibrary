package com.epam.library.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import com.epam.library.dao.AbstractDAO;

public class DateConvert {
	public static Logger logger = Logger.getLogger(AbstractDAO.class.getName());

	public static Date convertToYear(String year) {
		Date date = null;
		try {
			date = new Date(new SimpleDateFormat("yyyy").parse(year).getTime());
		} catch (ParseException e) {
			logger.severe(e.getMessage());
		}
		return date;
	}

	public static Date convertToDate(String year) {
		Date date = null;
		try {
			date = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(year)
					.getTime());
		} catch (ParseException e) {
			date = null;
			logger.severe(e.getMessage());
		}
		return date;
	}
}

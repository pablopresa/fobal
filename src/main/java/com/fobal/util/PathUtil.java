package com.fobal.util;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class PathUtil {

	public static String getPropertyAsString(String propertiesFile, String propertyName) {
		return ResourceBundle.getBundle(propertiesFile).getString(propertyName);
	}

	public static Object getPropertyAsObject(String propertiesFile, String propertyName) {
		return ResourceBundle.getBundle(propertiesFile).getObject(propertyName);
	}

	public static List<String> getPropertyAsStringList(String propertiesFile, String propertyName) {
		String prop = ResourceBundle.getBundle(propertiesFile).getString(propertyName);
		return Arrays.asList(prop.split(","));
	}

	public static Integer getPropertyAsInteger(String propertiesFile, String propertyName) {
		String prop = ResourceBundle.getBundle(propertiesFile).getString(propertyName);
		try {

			return Integer.parseInt(prop);
		} catch (NumberFormatException e) {

			return null;
		}
	}
}
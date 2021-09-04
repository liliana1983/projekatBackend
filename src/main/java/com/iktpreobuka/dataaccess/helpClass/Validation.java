package com.iktpreobuka.dataaccess.helpClass;

public class Validation {
	
	public static <T> T setIfNotNull (T oldProperty, T newProperty) {
		return newProperty.equals(null)? oldProperty:newProperty;
	}

}

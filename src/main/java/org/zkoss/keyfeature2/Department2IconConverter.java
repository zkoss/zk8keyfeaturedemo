package org.zkoss.keyfeature2;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.Converter;
import org.zkoss.zk.ui.Component;

public class Department2IconConverter implements Converter<String, Department, Component> {

	public Department coerceToBean(String arg0, Component arg1, BindContext arg2) {
		return null;
	}

	public String coerceToUi(Department department, Component component, BindContext context) {
		switch (department) {
		case ADMIN:
			return "z-icon-sitemap";
		case IT:
			return "z-icon-gears";
		case RD:
			return "z-icon-graduation-cap";
		case SALES:
			return "z-icon-users";
		default:
			return "";
		}
	}


}

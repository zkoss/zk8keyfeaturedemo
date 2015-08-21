package org.zkoss.keyfeature2;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.Converter;
import org.zkoss.keyfeature2.organization.Department;
import org.zkoss.zk.ui.Component;

public class Department2ColorConverter implements Converter<String, Department, Component> {

	public Department coerceToBean(String arg0, Component arg1, BindContext arg2) {
		return null;
	}

	public String coerceToUi(Department department, Component component, BindContext context) {
		switch (department) {
		case ADMIN:
			return "black";
		case IT:
			return "green";
		case RD:
			return "purple";
		case SALES:
			return "blue";
		default:
			return "";
		}
	}


}

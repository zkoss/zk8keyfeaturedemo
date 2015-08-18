package org.zkoss.keyfeature2;

public class Person {
	public static String test="in Person";
	private String name;
	private Department department;
	
	public Person(String name, Department department) {
		this.name = name;
		this.department = department;;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}

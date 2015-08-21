package org.zkoss.keyfeature2.organization;

import org.zkoss.zul.ListModelList;

public class Organization {

	private ListModelList<Person> members;
	private String name;
	
	public Organization(String name) {
		super();
		this.name = name;
		this.members = new ListModelList<Person>();
	}

	public String getName() {
		return name;
	}
	
	public ListModelList<Person> getMembers() {
		return members;
	}
	
	public int getMemberCount() {
		return getMembers().size();
	}
}

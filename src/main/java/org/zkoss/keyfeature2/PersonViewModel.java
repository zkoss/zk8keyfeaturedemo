package org.zkoss.keyfeature2;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zul.ListModelList;

public class PersonViewModel {
	public static String test="in ViewModel";
	private ListModelList<Person> personList;
	
	@Init
	public void init(){
		personList = new ListModelList<Person>();
		
		personList.add(new Person("Matthew", Department.ADMIN));
		personList.add(new Person("Mark", Department.IT));
		personList.add(new Person("Lucas", Department.SALES));
		personList.add(new Person("John", Department.RD));
		personList.add(new Person("Paul", Department.SALES));
		personList.add(new Person("Peter", Department.RD));		
	}

	public ListModelList<Person> getPersonList() {
		return personList;
	}
	
	
}

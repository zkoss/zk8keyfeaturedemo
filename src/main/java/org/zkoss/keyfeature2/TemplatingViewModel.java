package org.zkoss.keyfeature2;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.Init;
import org.zkoss.keyfeature2.organization.Department;
import org.zkoss.keyfeature2.organization.Organization;
import org.zkoss.keyfeature2.organization.Person;
import org.zkoss.zul.ListModelList;

public class TemplatingViewModel {
	private List<TemplateInfo> availableTemplates = new ArrayList<TemplateInfo>();
	private TemplateInfo currentTemplate;
	
	private Organization organization;

	@Init
	public void init(){
		availableTemplates.add(new TemplateInfo("zk grid readonly", "zk-grid.zul"));
		availableTemplates.add(new TemplateInfo("zk grid editable", "zk-grid-editable.zul"));
		availableTemplates.add(new TemplateInfo("bootstrap table", "bootstrap-table.zul"));
		availableTemplates.add(new TemplateInfo("HTML box", "html-box.zul"));
		availableTemplates.add(new TemplateInfo("HTML list", "html-list.zul"));
		currentTemplate = availableTemplates.get(0); 
		
		loadOrganization();		
	}

	private void loadOrganization() {
		organization = new Organization("Matt's Madness");
		ListModelList<Person> members = organization.getMembers();
		members.add(new Person("Matthew", Department.ADMIN));
		members.add(new Person("Mark", Department.IT));
		members.add(new Person("Lucas", Department.SALES));
		members.add(new Person("John", Department.RD));
		members.add(new Person("Paul", Department.SALES));
		members.add(new Person("Peter", Department.RD));
	}

	public TemplateInfo getCurrentTemplate() {
		return currentTemplate;
	}
	
	public void setCurrentTemplate(TemplateInfo currentTemplate) {
		this.currentTemplate = currentTemplate;
	}
	
	public List<TemplateInfo> getAvailableTemplates() {
		return availableTemplates;
	}

	public Organization getOrganization() {
		return organization;
	} 
}

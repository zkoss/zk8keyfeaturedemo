package org.zkoss.keyfeature3;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyCommand;
import org.zkoss.bind.annotation.NotifyCommands;
import org.zkoss.bind.annotation.ToClientCommand;
import org.zkoss.bind.annotation.ToServerCommand;

//@ToClientCommand({ "easypiechart$clientUpdate" })
@NotifyCommand(value = "easypiechart$clientUpdate", onChange = "_vm_.percentage")

@NotifyCommands({ @NotifyCommand(value = "dhtmlxscheduler$addClientEvent", onChange = "_vm_.addEventList"),
		@NotifyCommand(value = "dhtmlxscheduler$deleteClientEvent", onChange = "_vm_.removeEventList") })
@ToClientCommand({ "easypiechart$clientUpdate", "dhtmlxscheduler$addClientEvent", "dhtmlxscheduler$deleteClientEvent" })
@ToServerCommand({ "dhtmlxscheduler$addServerEvent", "dhtmlxscheduler$deleteServerEvent",
		"dhtmlxscheduler$changeServerEvent" })
public class IntegrationShowcaseViewModel {

	private boolean likesJava8 = true;
	private String percentage = "33%";
	private String syntax = "var int i = 0;";
	private String markdown = "This is a normal paragraph: \n\n" + "    System.out.println('code block')";
	private List<SchedulerEvent> addEventList; // transfer object
	private List<String> removeEventList; // transfer object

	@Init
	public void init() {
		addEventList = new ArrayList<SchedulerEvent>();
		removeEventList = new ArrayList<String>();
	}

	@Command("dhtmlxscheduler$addServerEvent")
	public void add(@BindingParam("id") String id, @BindingParam("start_date") String start_date,
			@BindingParam("end_date") String end_date, @BindingParam("subject") String subject,
			@BindingParam("text") String text) {
		addEventList.add(new SchedulerEvent(id, start_date, end_date, subject, text));
	}

	@Command("dhtmlxscheduler$deleteServerEvent")
	public void delete(@BindingParam("id") String id) {
		removeEventList.add(id);
	}

	@Command("dhtmlxscheduler$changeServerEvent")
	public void change(@BindingParam("id") int id, @BindingParam("start_date") String start_date,
			@BindingParam("end_date") String end_date, @BindingParam("subject") String subject,
			@BindingParam("text") String text) {
		// do something after some scheduler events have been changed.
	}

	public boolean isLikesJava8() {
		return likesJava8;
	}

	public void setLikesJava8(boolean likesJava8) {
		this.likesJava8 = likesJava8;
	}

	public String getPercentage() {
		return percentage;
	}

	public String getSyntax() {
		return syntax;
	}

	public void setSyntax(String syntax) {
		this.syntax = syntax;
	}

	public String getMarkdown() {
		return markdown;
	}

	public void setMarkdown(String markdown) {
		this.markdown = markdown;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public List<SchedulerEvent> getAddEventList() {
		return addEventList;
	}

	public List<String> getRemoveEventList() {
		return removeEventList;
	}

}

package org.zkoss.keyfeature3;

import org.zkoss.bind.annotation.NotifyCommand;
import org.zkoss.bind.annotation.ToClientCommand;

@NotifyCommand(value = "easypiechart$clientUpdate", onChange = "_vm_.percentage")
@ToClientCommand({ "easypiechart$clientUpdate" })
public class IntegrationShowcaseViewModel {

	private boolean likesJava8 = true;
	private String percentage = "33%";
	private String chartist_percentage = "55%";
	private String syntax = "var int i = 0; \n\n for(var i=0; i<10; i++) { \n \tconsole.log('Hello');\n }\n";
	private String markdown = "This is a normal paragraph: \n\n" + "    System.out.println('code block') \n\n"
			+ "#Large#";

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

	public String getChartist_percentage() {
		return chartist_percentage;
	}

	public void setChartist_percentage(String chartist_percentage) {
		this.chartist_percentage = chartist_percentage;
	}

}

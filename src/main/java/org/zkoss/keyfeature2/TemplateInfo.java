package org.zkoss.keyfeature2;

public class TemplateInfo {
	private String name;
	private String uri;
	
	public TemplateInfo(String name, String uri) {
		super();
		this.name = name;
		this.uri = uri;
	}

	public String getName() {
		return name;
	}

	public String getUri() {
		return uri;
	}
}
package org.zkoss.keyfeature2.catalog;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Author {
	private String name;
	private List<CatalogItem> items;

	public Author() {}

	public Author(String name) {
		setName(name);
		items = new ArrayList<CatalogItem>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<CatalogItem> getItems() {
		return items;
	}
	
	public String getIcon() {
		return name.toLowerCase(Locale.US);
	}
}
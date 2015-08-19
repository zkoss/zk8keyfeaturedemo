package org.zkoss.keyfeature2;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;

public class ThemeViewModel {
	private Map<String, String> themeMap = new LinkedHashMap<String, String>();
	private String selectedTheme ="zk";
	

	@Init
	public void init(){
		themeMap.put("zk", "grid.zul");
		themeMap.put("bootstrap", "grid-bootstrap.zul");
		themeMap.put("HTML", "grid-html.zul");
		themeMap.put("HTML box", "box.zul");
	}

	public Set<String> getThemeList() {
		return themeMap.keySet();
	}
	
	public String getSelectedTheme() {
		return selectedTheme;
	}
	
	@NotifyChange("selectedThemeUri")
	public void setSelectedTheme(String selectedTheme) {
		this.selectedTheme = selectedTheme;
	}
	
	public String getSelectedThemeUri() {
		return themeMap.get(selectedTheme);
	}
}

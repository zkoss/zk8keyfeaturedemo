/** DataListViewModel.java.

	Purpose:
		
	Description:
		
	History:
		12:07:27 PM Jan 22, 2015, Created by jumperchen

Copyright (C) 2015 Potix Corporation. All Rights Reserved.
*/
package org.zkoss.keyfeature2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.ToClientCommand;
import org.zkoss.keyfeature2.catalog.Catalog;

/**
 * @author jumperchen
 *
 */
@ToClientCommand({"filter, changeMode"})
public class DataListViewModel {
	
	private List<TemplateInfo> availableTemplates = new ArrayList<TemplateInfo>();
	private TemplateInfo currentTemplate;
	private Catalog catalog;
	
	@Init
	public void init() {
		availableTemplates.add(newTemplateInfo("grid", "templates/catalogGrid.zul", "z-icon-th-large"));
		availableTemplates.add(newTemplateInfo("list", "templates/catalogList.zul", "z-icon-th-list"));
		availableTemplates.add(newTemplateInfo("tree", "templates/catalogTree.zul", "z-icon-sitemap"));
		availableTemplates.add(newTemplateInfo("editable", "templates/catalogEditable.zul", "z-icon-edit"));
		availableTemplates.add(newTemplateInfo("raw data", "templates/catalogRaw.zul", "z-icon-download"));
		currentTemplate = availableTemplates.get(0); 
		
		catalog = new Catalog();
	}

	private TemplateInfo newTemplateInfo(String name, String uri, String icon) {
		return new TemplateInfo(name, uri, icon) {
			@Override
			public boolean isActive() {
				return currentTemplate == this;
			}
		};
	}
	
	@Command("changeTemplate")
	@NotifyChange("currentTemplate")
	public void changeTemplate(@BindingParam("template") TemplateInfo template) {
		BindUtils.postNotifyChange(null, null, currentTemplate, "active");
		currentTemplate = template;
		BindUtils.postNotifyChange(null, null, currentTemplate, "active");
	}
	
	public TemplateInfo getCurrentTemplate() {
		return currentTemplate;
	}

	public List<TemplateInfo> getAvailableTemplates() {
		return availableTemplates;
	}

	public Catalog getCatalog() {
		return catalog;
	}
}

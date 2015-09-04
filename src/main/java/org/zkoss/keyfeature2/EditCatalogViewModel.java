package org.zkoss.keyfeature2;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.keyfeature2.catalog.Catalog;
import org.zkoss.keyfeature2.catalog.CatalogItem;
import org.zkoss.zk.ui.util.Clients;

public class EditCatalogViewModel {

	@Command
	public void save() {
		Clients.showNotification("catalog updated");
	}
	
}

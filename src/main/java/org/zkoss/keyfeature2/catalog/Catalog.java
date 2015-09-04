package org.zkoss.keyfeature2.catalog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Catalog {
	private static String[] ITEMS = new String[] { "Briefcase", "FolderABlue", "Globe", "MailboxFlag", "ReadingGlass", "Spyglass"};
	private static String[] authors_list = new String[] { "Alien", "Astronauta", "Bombero", "Comisario", "Dreds", "Hiphopper", "Mimo", "Mounstruo"};

	private Random random = new Random();

	private List<CatalogItem> allItems;
	private List<Author> allAuthors;
	
	public Catalog() {
		loadAuthors();
		loadItems();
	}

	public List<CatalogItem> getItems() {
		return allItems;
	}
	
	public List<Author> getAuthors() {
		return allAuthors;
	}

	public List<Author> getActiveAuthors() {
		List<Author> activeAuthors = new ArrayList<Author>(allAuthors);
		Iterator<Author> iterator = activeAuthors.iterator();
		while(iterator.hasNext()) {
			Author author = iterator.next();
			if(author.getItems().isEmpty()) {
				iterator.remove();
			}
		}
		return activeAuthors;
	}
	
	private void loadItems() {
		allItems = new LinkedList<CatalogItem>();
		for (String name : ITEMS) {
			CatalogItem item = new CatalogItem(name, randomAuthor());
			allItems.add(item);
			item.getAuthor().getItems().add(item);
		}
	}
	
	private void loadAuthors() {
		allAuthors = new ArrayList<Author>();
		for (String name : authors_list) {
			allAuthors.add(new Author(name));
		}
	}
	
	private Author randomAuthor() {
		return allAuthors.get(random.nextInt(allAuthors.size()));
	}
}


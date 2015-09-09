package org.zkoss.keyfeature2.catalog;

import org.zkoss.bind.annotation.Immutable;


public class CatalogItem {
	private String image;
	private String title;
	private Author author;
	
	public CatalogItem() {}

	public CatalogItem(String title, Author author) {
		this.title = title;
		this.author = author;
		this.image = "image/Centigrade-Widget-Icons/" + title + "-128x128.png";
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Immutable
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author.getItems().remove(this);
		this.author = author;
		this.author.getItems().add(this);
	}

	//TODO: use converter
	public String getImage() {
		return image;
	}
}
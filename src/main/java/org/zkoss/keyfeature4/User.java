package org.zkoss.keyfeature4;

public class User {
	private String gender;
	private String name;
	private String email;
	private String picture;

	public User(String gender, String name, String email, String picture) {
		this.gender = gender;
		this.name = name;
		this.email = email;
		this.picture = picture;
	}

	public String getGender() {
		return gender;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPicture() {
		return picture;
	}
}

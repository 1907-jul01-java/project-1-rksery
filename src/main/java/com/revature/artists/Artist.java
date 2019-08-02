package com.revature.artists;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Artist {

	// constructors, getters & setters, tostring, hashcode, equals
	private String title = "Artist";
	private String username;
	private String pw;
	private String displayname;

	public Artist() {
		super();
	}

	public Artist(String title, String username, String pw, String displayname) {
		this.title = title;
		this.username = username;
		this.pw = pw;
		this.displayname = displayname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	@Override
	public String toString() {
		return "Artist [displayname=" + displayname + ", pw=" + pw + ", title=" + title + ", username=" + username
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((displayname == null) ? 0 : displayname.hashCode());
		result = prime * result + ((pw == null) ? 0 : pw.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artist other = (Artist) obj;
		if (displayname == null) {
			if (other.displayname != null)
				return false;
		} else if (!displayname.equals(other.displayname))
			return false;
		if (pw == null) {
			if (other.pw != null)
				return false;
		} else if (!pw.equals(other.pw))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
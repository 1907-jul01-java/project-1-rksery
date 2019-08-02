package com.revature.artworks;

import java.math.BigDecimal;

public class Artwork {

	// constructors, getters & setters, tostring, hashcode, equals
	private int artid;
	private String title;
	private BigDecimal price;
	private String filepath;
	private int approved;

	public Artwork() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + approved;
		result = prime * result + artid;
		result = prime * result + ((filepath == null) ? 0 : filepath.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Artwork other = (Artwork) obj;
		if (approved != other.approved)
			return false;
		if (artid != other.artid)
			return false;
		if (filepath == null) {
			if (other.filepath != null)
				return false;
		} else if (!filepath.equals(other.filepath))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Artwork [artid=" + artid + ", title=" + title + ", price=" + price + ", filepath=" + filepath
				+ ", approved=" + approved + "]";
	}

	public int getArtid() {
		return artid;
	}

	public void setArtid(int artid) {
		this.artid = artid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

	public Artwork(int artid, String title, BigDecimal price, String filepath) {
		super();
		this.artid = artid;
		this.title = title;
		this.price = price;
		this.filepath = filepath;
	}

}

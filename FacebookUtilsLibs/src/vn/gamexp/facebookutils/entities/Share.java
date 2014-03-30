package vn.gamexp.facebookutils.entities;

public class Share {
	/**
	 * The title of the item to be shared
	 */
	private String name = null;
	/**
	 * The subtitle of the item to be shared.
	 */
	private String caption = null;
	/**
	 * The description of the item to be shared.
	 */
	private String description = null;
	/**
	 * The URL of the item to be shared.
	 */
	private String link = null;
	/**
	 * The URL of the image of the item to be shared.
	 */
	private String picture = null;
	/**
	 * The Facebook ID of the place
	 */
	private String place = null;
	/**
	 * The 'ref' property of the item to be shared.
	 */
	private String ref = null;

	public Share() {
	}

	public String getName() {
		return name;
	}

	/**
	 * Sets the title of the item to be shared.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getCaption() {
		return caption;
	}

	/**
	 * Sets the subtitle of the item to be shared.
	 * 
	 * @param caption
	 */
	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description of the item to be shared.
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	/**
	 * Sets the URL of the item to be shared.
	 * 
	 * @param link
	 */
	public void setLink(String link) {
		this.link = link;
	}

	public String getPicture() {
		return picture;
	}

	/**
	 * Sets the URL of the image of the item to be shared.
	 * 
	 * @param picture
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getPlace() {
		return place;
	}

	/**
	 * Sets the place for the item to be shared.
	 * 
	 * @param place
	 */
	public void setPlace(String place) {
		this.place = place;
	}

	public String getRef() {
		return ref;
	}

	/**
	 * Sets the 'ref' property of the item to be shared.
	 * 
	 * @param ref
	 */
	public void setRef(String ref) {
		this.ref = ref;
	}
}

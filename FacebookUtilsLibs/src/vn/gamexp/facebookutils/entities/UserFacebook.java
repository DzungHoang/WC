package vn.gamexp.facebookutils.entities;

public class UserFacebook {

	public static class Info {
		public static String facebookId = "facebookId";
		public static String name = "name";
		public static String firstName = "firstName";
		public static String lastName = "lastName";
		public static String link = "link";
		public static String userName = "userName";
		public static String gender = "gender";
	}

	private String facebookId = null;
	private String name = null;
	private String firstName = null;
	private String lastName = null;
	private String link = null;
	private String userName = null;
	private String gender = null;

	public UserFacebook(){
	}
	
	public UserFacebook(String facebookId, String name, String firstName,
			String lastName, String link, String userName, String gender,
			String locale) {
		super();
		this.facebookId = facebookId;
		this.name = name;
		this.firstName = firstName;
		this.lastName = lastName;
		this.link = link;
		this.userName = userName;
		this.gender = gender;
	}

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}

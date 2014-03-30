package vn.gamexp.facebookutils.entities;

public class Feed {
	public String appId = "app_id"; // Your app's unique identifier.
									// Required.
	public String redirectUri = "redirect_uri"; // The URL to redirect to
												// after a person clicks
												// a button on the
												// dialog. Required when
												// using URL
												// redirection.
	public String from = "from";// The ID or username of the person
								// posting the message. If this is
								// unspecified, it defaults to the
								// current person. If specified, it must
								// be the ID of the person or of a page
								// that the person administers.
	public String to = "to"; // The ID or username of the profile that
								// this story will be published to. If this
								// is unspecified, it defaults to the value
								// of from.
	public String link = "link";// The link attached to this post
	public String picture = "picture";// The URL of a picture attached to
										// this post. The picture must
										// be at least 200px by 200px.
										// See our documentation on
										// maximizing distribution for
										// media content for more
										// information on sizes.
	public String name = "name";// The name of the link attachment.
	public String caption = "caption";// The caption of the link (appears
										// beneath the link name). If
										// not specified, this field is
										// automatically populated with
										// the URL of the link.
	public String description = "description"; // The description of the
												// link (appears beneath
												// the link caption). If
												// not specified, this
												// field is
												// automatically
												// populated by
												// information scraped
												// from the link,
												// typically the title
												// of the page.
	public String actions = "actions"; // A JSON array containing a
										// single object describing the
										// action link which will appear
										// next to the 'Comment' and
										// 'Like' link under posts. The
										// contained object must have
										// the keys name and link.
	public String ref = "ref";// This argument is a comma-separated list,
								// consisting of at most 5 distinct
								// items, each of length at least 1 and
								// at most 15 characters drawn from the
								// set
								// '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_'.
								// Each category is used in Facebook
								// Insights to help you measure the
								// performance of different types of
								// post

	public Feed() {
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}
}

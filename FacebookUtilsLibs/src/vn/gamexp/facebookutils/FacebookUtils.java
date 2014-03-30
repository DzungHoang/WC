package vn.gamexp.facebookutils;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import vn.gamexp.facebookutils.entities.Feed;
import vn.gamexp.facebookutils.entities.LoginResult;
import vn.gamexp.facebookutils.entities.Parameters;
import vn.gamexp.facebookutils.entities.Result;
import vn.gamexp.facebookutils.entities.Share;
import vn.gamexp.facebookutils.entities.UserFacebook;
import vn.gamexp.facebookutils.listener.OnFeedListener;
import vn.gamexp.facebookutils.listener.OnLoginListener;
import vn.gamexp.facebookutils.listener.OnRequestListener;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import com.facebook.FacebookException;
import com.facebook.FacebookRequestError;
import com.facebook.Request;
import com.facebook.Request.Callback;
import com.facebook.Request.GraphUserCallback;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.FacebookDialog.ShareDialogBuilder;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

public class FacebookUtils {
	private Context mContext = null;
	private Activity mActivity = null;
	private UiLifecycleHelper mUiHelper = null;
	private boolean mIsRunningLogin;

	/**
	 * Constructor
	 * 
	 * @param context
	 *            Application context
	 * @param activity
	 *            Activity
	 * @param appId
	 *            Application id from Facebook dashboard
	 */
	public FacebookUtils(Context context, Activity activity, String appId, String keyHash) {
		mContext = context;
		mActivity = activity;

		Parse.initialize(mContext, appId, keyHash);
		ParseFacebookUtils.initialize(appId);
		mUiHelper = new UiLifecycleHelper(mActivity, null);
	}
	
	public void shareBitMap(Bitmap bitmap) {
		Request request = Request.newUploadPhotoRequest(getSession(), bitmap, new Callback() {
			@Override
			public void onCompleted(Response response) {
				Log.e("TienVV", "respone: " + response.toString());
			}
		});
		request.executeAsync();
	}
	
	/**
	 * Get current session
	 * 
	 * @return
	 */
	public Session getSession() {
		return ParseFacebookUtils.getSession();
	}

	/**
	 * Check is login to Facebook or not
	 * 
	 * @return
	 */
	public boolean isLogin() {
		ParseUser user = ParseUser.getCurrentUser();
		if (user != null && ParseFacebookUtils.isLinked(user)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Login to facebook
	 * 
	 * @param activity
	 * @param permissions
	 * @param timeout
	 *            time on seconds, after timeout, if not login then return
	 *            TIMEOUT result
	 * @param listener
	 */
	public void login(final Activity activity, final List<String> permissions,
			final int timeout, final OnLoginListener listener) {
		mIsRunningLogin = true;
		CountDownTimer timer = new CountDownTimer(timeout * 1000, 100) {

			@Override
			public void onTick(long millisUntilFinished) {
			}

			@Override
			public void onFinish() {
				if (mIsRunningLogin) {
					mIsRunningLogin = false;
					if (listener != null) {
						listener.onDone(LoginResult.TIMEOUT, null);
					}
				}
			}
		};
		timer.start();
		ParseFacebookUtils.logIn(permissions, activity, new LogInCallback() {

			@Override
			public void done(ParseUser user, ParseException exception) {
				if (mIsRunningLogin == false) {
					if (user != null && exception == null) {
						logout();
					}
					return;
				}
				mIsRunningLogin = false;
				if (exception != null) {
					if (listener != null) {
						listener.onDone(LoginResult.FAILED, exception);
					}
				} else {
					if (user == null) {
						if (listener != null) {
							listener.onDone(LoginResult.USER_OR_PASSWORD_WRONG,
									exception);
						}
					} else {
						if (listener != null) {
							listener.onDone(LoginResult.SUCCESSED, exception);
						}
					}
				}
			}
		});
	}

	/**
	 * Logout of Facebook
	 */
	public void logout() {
		ParseUser.logOut();
	}

	/**
	 * Request to get user facebook information. It MUST be called before call
	 * getUserFacebook() method
	 * 
	 * @param listener
	 *            OnRequestListener(boolean result) : result = true if get
	 *            information success, result = false in otherwise
	 */
	public void requestGetUserInfo(final OnRequestListener listener) {
		Request request = Request.newMeRequest(getSession(),
				new GraphUserCallback() {

					@Override
					public void onCompleted(GraphUser user, Response response) {
						if (user != null) {
							JSONObject userProfile = new JSONObject();
							try {
								Log.d("TienVV", "userid: " + user.getId());
								userProfile.put(UserFacebook.Info.facebookId,
										user.getId());
								userProfile.put(UserFacebook.Info.name,
										user.getName());
								userProfile.put(UserFacebook.Info.firstName,
										user.getFirstName());
								userProfile.put(UserFacebook.Info.lastName,
										user.getLastName());
								userProfile.put(UserFacebook.Info.link,
										user.getLink());
								userProfile.put(UserFacebook.Info.userName,
										user.getUsername());
								userProfile.put(UserFacebook.Info.gender,
										String.valueOf(user
												.getProperty("gender")));
								// Save the user profile info in a user property
								ParseUser currentUser = ParseUser
										.getCurrentUser();
								currentUser.put("profile", userProfile);
								currentUser.saveInBackground();
								if (listener != null) {
									listener.onDone(Result.SUCCESS, null);
								}
							} catch (JSONException e) {
								if (listener != null) {
									listener.onDone(Result.FAILED, e);
								}
							}
						} else {
							if ((response.getError().getCategory() == FacebookRequestError.Category.AUTHENTICATION_RETRY)
									|| (response.getError().getCategory() == FacebookRequestError.Category.AUTHENTICATION_REOPEN_SESSION)) {
								logout();
							}
							if (listener != null) {
								listener.onDone(Result.FAILED, null);
							}
						}
					}
				});
		request.executeAsync();
	}

	/**
	 * Get user facebook information
	 * 
	 * @return
	 */
	public UserFacebook getUserFacebook() {
		UserFacebook user = null;
		ParseUser parseUser = ParseUser.getCurrentUser();
		JSONObject userProfile = parseUser.getJSONObject("profile");
		if (userProfile != null) {
			user = new UserFacebook();
			try {
				if (userProfile.has(UserFacebook.Info.facebookId)) {
					user.setFacebookId(userProfile
							.getString(UserFacebook.Info.facebookId));
				}
				if (userProfile.has(UserFacebook.Info.name)) {
					user.setName(userProfile.getString(UserFacebook.Info.name));
				}
				if (userProfile.has(UserFacebook.Info.firstName)) {
					user.setFirstName(userProfile
							.getString(UserFacebook.Info.firstName));
				}
				if (userProfile.has(UserFacebook.Info.lastName)) {
					user.setLastName(userProfile
							.getString(UserFacebook.Info.lastName));
				}
				if (userProfile.has(UserFacebook.Info.userName)) {
					user.setUserName(userProfile
							.getString(UserFacebook.Info.userName));
				}
				if (userProfile.has(UserFacebook.Info.link)) {
					user.setLink(userProfile.getString(UserFacebook.Info.link));
				}
				if (userProfile.has(UserFacebook.Info.gender)) {
					user.setGender(userProfile
							.getString(UserFacebook.Info.gender));
				}
			} catch (JSONException e) {
				Log.d("TienVV", "ex get user:" + e.toString());
				return null;
			}
		}
		return user;
	}

	/**
	 * Open a page using Page ID (not include profile page) through Facebook
	 * application on device
	 * 
	 * @param pageId
	 */
	public void openPage(String pageId) {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		String uri = "fb://page/" + pageId;
		Uri.parse(uri);
		intent.setData(Uri.parse(uri));
		mActivity.startActivity(intent);
	}

	/**
	 * Share a link to user's wall
	 * 
	 * @param link
	 *            Link to share
	 */
	public void shareLink(String link) {
		ShareDialogBuilder builder = new ShareDialogBuilder(mActivity);
		Share shareObj = new Share();
		shareObj.setLink(link);
		builder = addParametersToShareDialogBuilder(builder, shareObj);
		mUiHelper.trackPendingDialogCall(builder.build().present());
	}

	/**
	 * Publish a feed to user's wall using Feed
	 * 
	 * @param feed
	 *            Feed data
	 * @param listener
	 *            Callback OnFeedListener
	 */
	public void publishFeed(Feed feed, OnFeedListener listener) {
		Bundle parameters = getParametersFromFeed(feed);
		feed(parameters, listener);
	}

	/**
	 * Share something to user's wall
	 * 
	 * @param shareObj
	 */
	public void publishShare(Share shareObj) {
		ShareDialogBuilder builder = new ShareDialogBuilder(mActivity);
		builder = addParametersToShareDialogBuilder(builder, shareObj);
		mUiHelper.trackPendingDialogCall(builder.build().present());
	}

	/**
	 * Publish a request for friends to using app or play game
	 * 
	 * @param message
	 *            Message to invite friends
	 * @param listener
	 *            Callback called when request success or failed
	 */
	public void publishRequest(String message, OnRequestListener listener) {
		Bundle parameters = new Bundle();
		parameters.putString("message", message);
		request(parameters, listener);
	}

	/**
	 * Publish a feed to user's wall with Parameters
	 * 
	 * @param parameters
	 * @param listener
	 *            Callback OnFeedListener
	 */
	private void feed(Bundle parameters, final OnFeedListener listener) {
		WebDialog dialog = new WebDialog.FeedDialogBuilder(mActivity,
				getSession(), parameters).build();
		dialog.setOnCompleteListener(new OnCompleteListener() {

			@Override
			public void onComplete(Bundle values, FacebookException error) {
				if (listener == null) {
					return;
				}
				if (error == null) {
					String requestId = values.getString("post_id");
					if (requestId != null) {
						listener.onDone(Result.SUCCESS, error);
					} else {
						listener.onDone(Result.CANCLED, error);
					}
				} else {
					listener.onDone(Result.FAILED, error);
				}
			}
		});
		dialog.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				if (listener != null) {
					listener.onDone(Result.CANCLED, null);
				}
			}
		});
		dialog.show();
	}

	/**
	 * Send a request with Parameters
	 * 
	 * @param parameters
	 * @param listener
	 *            Callback OnRequestListener
	 */
	private void request(Bundle parameters, final OnRequestListener listener) {
		WebDialog dialog = new WebDialog.RequestsDialogBuilder(mActivity,
				getSession(), parameters).build();
		dialog.setOnCompleteListener(new OnCompleteListener() {

			@Override
			public void onComplete(Bundle values, FacebookException error) {
				if (listener == null) {
					return;
				}
				if (error == null) {
					String requestId = values.getString("request");
					Log.d("TienVV", "request id: " + requestId);
					if (requestId != null) {
						listener.onDone(Result.SUCCESS, error);
					} else {
						listener.onDone(Result.CANCLED, error);
					}
				} else {
					listener.onDone(Result.FAILED, error);
				}
			}
		});
		dialog.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				if (listener != null) {
					listener.onDone(Result.CANCLED, null);
				}
			}
		});
		dialog.show();
	}

	/**
	 * Get parameters from feed
	 * 
	 * @param feed
	 * @return Parameter bundle
	 */
	private Bundle getParametersFromFeed(Feed feed) {
		Bundle result = new Bundle();
		String appId = feed.getAppId();
		String redirectUri = feed.getRedirectUri();
		String from = feed.getFrom();
		String to = feed.getTo();
		String link = feed.getLink();
		String picture = feed.getPicture();
		String name = feed.getName();
		String caption = feed.getCaption();
		String description = feed.getDescription();
		String actions = feed.getActions();
		String ref = feed.getRef();
		if (appId != null) {
			result.putString(Parameters.appId, appId);
		}
		if (redirectUri != null) {
			result.putString(Parameters.redirectUri, redirectUri);
		}
		if (from != null) {
			result.putString(Parameters.from, from);
		}
		if (to != null) {
			result.putString(Parameters.to, to);
		}
		if (link != null) {
			result.putString(Parameters.link, link);
		}
		if (picture != null) {
			result.putString(Parameters.picture, picture);
		}
		if (name != null) {
			result.putString(Parameters.name, name);
		}
		if (caption != null) {
			result.putString(Parameters.caption, caption);
		}
		if (description != null) {
			result.putString(Parameters.description, description);
		}
		if (actions != null) {
			result.putString(Parameters.actions, actions);
		}
		if (ref != null) {
			result.putString(Parameters.ref, ref);
		}
		return result;
	}

	/**
	 * Add parameters to ShareDialogBuilder
	 * 
	 * @param builder
	 *            ShareDialogBuilder which is want to add parameters to
	 * @param share
	 *            Share object include information of sharing information
	 * @return
	 */
	private ShareDialogBuilder addParametersToShareDialogBuilder(
			ShareDialogBuilder builder, Share share) {
		String name = share.getName();
		String caption = share.getCaption();
		String description = share.getDescription();
		String link = share.getLink();
		String picture = share.getPicture();
		String place = share.getPlace();
		String ref = share.getRef();
		if (name != null) {
			builder.setName(name);
		}
		if (caption != null) {
			builder.setCaption(caption);
		}
		if (description != null) {
			builder.setDescription(description);
		}
		if (link != null) {
			builder.setLink(link);
		}
		if (picture != null) {
			builder.setPicture(picture);
		}
		if (place != null) {
			builder.setPlace(place);
		}
		if (ref != null) {
			builder.setRef(ref);
		}
		return builder;
	}

	/**
	 * Call in onActivityResult
	 * 
	 * @param requestCode
	 * @param resultCode
	 * @param data
	 */
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		try {
			ParseFacebookUtils.finishAuthentication(requestCode, resultCode,
					data);
		} catch (Exception e) {

		}
	}
}

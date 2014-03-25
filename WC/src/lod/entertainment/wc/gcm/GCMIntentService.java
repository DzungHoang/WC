package lod.entertainment.wc.gcm;

import java.util.regex.Pattern;

import lod.entertainment.wc.HomeActivity;
import lod.entertainment.wc.R;
import lod.entertainment.wc.WCApplication;
import lod.entertainment.wc.data.UpdateMatchdayAsync;
import lod.entertainment.wc.utils.Utils;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.Patterns;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {

	private static final String TAG = "GCMIntentService";

	private static final String MESSAGE_NOTIFY = "NOTIFICATION";
	private static final String MESSAGE_UPDATE_RESULT = "UPDATE_RESULT";

	private WCApplication mApplication;

	public GCMIntentService() {
		super(CommonUtilities.SENDER_ID);
	}

	public GCMIntentService(WCApplication application) {
		super(CommonUtilities.SENDER_ID);
		mApplication = application;
	}

	/**
	 * Method called on device registered
	 **/
	@Override
	protected void onRegistered(Context context, String registrationId) {
		Log.i(TAG, "Device registered: regId = " + registrationId);
		CommonUtilities.displayMessage(context,
				"Your device registred with GCM");
		ServerUtilities.register(context, "WC-2014", getEmail(context),
				registrationId);
	}

	private String getEmail(Context context) {
		String email = "";
		Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
		Account[] accounts = AccountManager.get(context).getAccounts();
		for (Account account : accounts) {
			if (emailPattern.matcher(account.name).matches()) {
				email = account.name;
				Log.d("DungHV", "account.name = " + account.name);
			}
		}
		return email;
	}

	/**
	 * Method called on device un registred
	 * */
	@Override
	protected void onUnregistered(Context context, String registrationId) {
		Log.i(TAG, "Device unregistered");
		CommonUtilities.displayMessage(context,
				getString(R.string.gcm_unregistered));
		ServerUtilities.unregister(context, registrationId);
	}

	/**
	 * Method called on Receiving a new message
	 * */
	@Override
	protected void onMessage(Context context, Intent intent) {
		Log.i(TAG, "Received message");
		String message = intent.getExtras().getString("message");
		String param1 = intent.getExtras().getString("param1");
		String param2 = intent.getExtras().getString("param2");
		String param3 = intent.getExtras().getString("param3");

		if (message.equals(MESSAGE_NOTIFY)) {
			generateNotification(context, message);
		} else if (message.equals(MESSAGE_UPDATE_RESULT)) {
			if (param1 != null && param1.length() > 0 && param2 != null
					&& param2.length() > 0) {
				try {
					UpdateMatchdayAsync update = new UpdateMatchdayAsync(
							getApplicationContext());
					update.execute(param1, param2);
					if (Utils.checkAppRunning(getApplicationContext())) {
						Intent intentHome = new Intent();
						intentHome.setClass(getApplicationContext(),
								HomeActivity.class);
						intentHome.putExtra("need_update", true);
						intentHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						getApplicationContext().startActivity(intentHome);
					}
				} catch (Exception e) {

				}
			}
		}
	}

	/**
	 * Method called on receiving a deleted message
	 * */
	@Override
	protected void onDeletedMessages(Context context, int total) {
		Log.i(TAG, "Received deleted messages notification");
		String message = getString(R.string.gcm_deleted, total);
		CommonUtilities.displayMessage(context, message);
		// notifies user
		generateNotification(context, message);
	}

	/**
	 * Method called on Error
	 * */
	@Override
	public void onError(Context context, String errorId) {
		Log.i(TAG, "Received error: " + errorId);
		CommonUtilities.displayMessage(context,
				getString(R.string.gcm_error, errorId));
	}

	@Override
	protected boolean onRecoverableError(Context context, String errorId) {
		// log message
		Log.i(TAG, "Received recoverable error: " + errorId);
		CommonUtilities.displayMessage(context,
				getString(R.string.gcm_recoverable_error, errorId));
		return super.onRecoverableError(context, errorId);
	}

	/**
	 * Issues a notification to inform the user that server has sent a message.
	 */
	private static void generateNotification(Context context, String message) {
		int icon = R.drawable.ic_launcher;
		long when = System.currentTimeMillis();
		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = new Notification(icon, message, when);

		String title = context.getString(R.string.app_name);

		Intent notificationIntent = new Intent(context, HomeActivity.class);
		// set intent so it does not start a new activity
		notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent intent = PendingIntent.getActivity(context, 0,
				notificationIntent, 0);
		notification.setLatestEventInfo(context, title, message, intent);
		notification.flags |= Notification.FLAG_AUTO_CANCEL;

		// Play default notification sound
		notification.defaults |= Notification.DEFAULT_SOUND;

		// Vibrate if vibrate is enabled
		notification.defaults |= Notification.DEFAULT_VIBRATE;
		notificationManager.notify(0, notification);

	}
}

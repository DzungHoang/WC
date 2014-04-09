package lod.wc.gcm;

import java.util.regex.Pattern;

import lod.wc.HomeActivity;
import lod.wc.R;
import lod.wc.WCApplication;
import lod.wc.data.UpdateMatchdayAsync;
import lod.wc.utils.LogUtils;
import lod.wc.utils.Utils;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Patterns;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {

	private static final String TAG = "GCMIntentService";

	private static final String MESSAGE_NOTIFY = "NOTIFICATION";
	private static final String MESSAGE_UPDATE_RESULT = "UPDATE_RESULT";
	private static final String MESSAGE_UPDATE_APP = "UPDATE_APP";

//	private WCApplication mApplication;

	public GCMIntentService() {
		super(CommonUtilities.SENDER_ID);
	}

	public GCMIntentService(WCApplication application) {
		super(CommonUtilities.SENDER_ID);
//		mApplication = application;
	}

	/**
	 * Method called on device registered
	 **/
	@Override
	protected void onRegistered(Context context, String registrationId) {
		LogUtils.i(TAG, "Device registered: regId = " + registrationId);
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
				LogUtils.d("DungHV", "account.name = " + account.name);
			}
		}
		return email;
	}

	/**
	 * Method called on device un registred
	 * */
	@Override
	protected void onUnregistered(Context context, String registrationId) {
		LogUtils.i(TAG, "Device unregistered");
		CommonUtilities.displayMessage(context,
				getString(R.string.gcm_unregistered));
		ServerUtilities.unregister(context, registrationId);
	}

	/**
	 * Method called on Receiving a new message
	 * */
	@Override
	protected void onMessage(Context context, Intent intent) {
		LogUtils.i(TAG, "Received message");
		String message = intent.getExtras().getString("message");
		String param1 = intent.getExtras().getString("param1");
		String param2 = intent.getExtras().getString("param2");
		String param3 = intent.getExtras().getString("param3");

		if (message.equals(MESSAGE_NOTIFY)) {
			Utils.generateNotification(context, param1);
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
		}else if (message.equals(MESSAGE_UPDATE_APP)){
			Intent updateApp = new Intent(Intent.ACTION_VIEW);
			updateApp.setData(Uri.parse("market://details?id=" + param1));
			PendingIntent pending = PendingIntent.getActivity(getApplicationContext(), 0, updateApp, 0);
			Utils.generateNotification(getApplicationContext(), param2, param3, pending);
		}
	}

	/**
	 * Method called on receiving a deleted message
	 * */
	@Override
	protected void onDeletedMessages(Context context, int total) {
		LogUtils.i(TAG, "Received deleted messages notification");
		String message = getString(R.string.gcm_deleted, total);
		CommonUtilities.displayMessage(context, message);
		// notifies user
		Utils.generateNotification(context, message);
	}

	/**
	 * Method called on Error
	 * */
	@Override
	public void onError(Context context, String errorId) {
		LogUtils.i(TAG, "Received error: " + errorId);
		CommonUtilities.displayMessage(context,
				getString(R.string.gcm_error, errorId));
	}

	@Override
	protected boolean onRecoverableError(Context context, String errorId) {
		// LogUtils message
		LogUtils.i(TAG, "Received recoverable error: " + errorId);
		CommonUtilities.displayMessage(context,
				getString(R.string.gcm_recoverable_error, errorId));
		return super.onRecoverableError(context, errorId);
	}

}

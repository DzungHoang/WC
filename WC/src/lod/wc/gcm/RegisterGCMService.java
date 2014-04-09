package lod.wc.gcm;

import lod.wc.utils.LogUtils;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;

import com.google.android.gcm.GCMRegistrar;

public class RegisterGCMService extends Service{

	// Asyntask
    AsyncTask<Void, Void, Void> mRegisterTask;
     
    // Alert diaLogUtils manager
    AlertDialogManager alert = new AlertDialogManager();
     
    // Connection detector
    ConnectionDetector cd;
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		cd = new ConnectionDetector(getApplicationContext());
		 
        // Check if Internet present
        if (!cd.isConnectingToInternet()) {
            // Internet Connection is not present
            alert.showAlertDialog(getApplicationContext(),
                    "Internet Connection Error",
                    "Please connect to working Internet connection", false);
        }
     // Make sure the device has the proper dependencies.
        GCMRegistrar.checkDevice(this);
     // Get GCM registration id
        final String regId = GCMRegistrar.getRegistrationId(this);
        LogUtils.d("DungHV","regId = " + regId);
        // Check if regid already presents
        if (regId.equals("")) {
            // Registration is not present, register now with GCM           
        	GCMRegistrar.register(this, CommonUtilities.SENDER_ID);
            LogUtils.d("DungHV","regId = " + regId);
        } else {
            // Device is already registered on GCM
            if (GCMRegistrar.isRegisteredOnServer(this)) {
                // Skips registration.              
//                Toast.makeText(getApplicationContext(), "Already registered with GCM", Toast.LENGTH_LONG).show();
            } else {
                // Try to register again, but not in the UI thread.
                // It's also necessary to cancel the thread onDestroy(),
                // hence the use of AsyncTask instead of a raw thread.
                final Context context = this;
                mRegisterTask = new AsyncTask<Void, Void, Void>() {
 
                    @Override
                    protected Void doInBackground(Void... params) {
                        // Register on our server
                        // On server creates a new user
                        ServerUtilities.register(context, "Dz", "test", regId);
                        return null;
                    }
 
                    @Override
                    protected void onPostExecute(Void result) {
                        mRegisterTask = null;
                    }
 
                };
                mRegisterTask.execute(null, null, null);
            }
        }
		return START_FLAG_REDELIVERY;
	}

}

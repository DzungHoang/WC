package lod.entertainment.wc.gcm;

import android.content.Context;

import com.google.android.gcm.GCMBroadcastReceiver;

public class MyGCMReceiver extends GCMBroadcastReceiver {

	@Override
	protected String getGCMIntentServiceClassName(Context context) {
		// TODO Auto-generated method stub
		return "lod.entertainment.wc.gcm.GCMIntentService";
	}

}

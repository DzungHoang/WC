package lod.entertainment.wc.data;

import java.util.Calendar;
import java.util.TimeZone;

import lod.entertainment.wc.utils.PreferenceUtils;
import lod.entertainment.wc.utils.Utils;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class UpdateMatchdayAsync extends AsyncTask<Integer, Void, String>{
	
	private final String url = "http://footballdb.herokuapp.com/api/v1/event/world.2014/round/";
	
	private Context mContext;
	public UpdateMatchdayAsync(Context context){
		mContext = context;
	}
	
	@Override
	protected String doInBackground(Integer... params) {
		String ret = "";
		int round_number = params[0].intValue();
		Log.d("DungHV","doInBackground: " + round_number);
		if ((round_number > 0) && (round_number < 21)){
			HTTPHandler handler = new HTTPHandler();
			ret = handler.makeHTTPRequest(url + round_number, HTTPHandler.GET);
//			Utils.overwriteFileInSD(mContext, ret, "games_round_"+round_number+".json");
		}
		return ret;
	}

	@Override
	protected void onPostExecute(String result) {
//		Log.d("DungHV","onPostExe: " + result);
		//get timezone of user
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		long currentMillis = calendar.getTimeInMillis();
		PreferenceUtils.getInstance().setLastUpdated(""+currentMillis);
//		Log.d("DungHV","currentMillis = " + currentMillis);
		//sample to get last updated
//		String millis = PreferenceUtils.getInstance().getLastUpdated();
//		String date = Utils.getDate(Long.valueOf(millis).longValue(), "dd/MM/yyyy hh:mm:ss");
//		Log.d("DungHV","date = " + date);
		super.onPostExecute(result);
	}
}

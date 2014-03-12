package lod.entertainment.wc.data;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class UpdateDataService extends Service{

	public static String CMD_UPDATE_MATCHDAY = "update_match_day";
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		String action = intent.getAction();
		Log.d("DungHV","onStartCommand: " + action);
		if (action.equals(CMD_UPDATE_MATCHDAY)){
			
			UpdateMatchdayAsync updateMatchday = new UpdateMatchdayAsync(getApplicationContext());
			updateMatchday.execute(calculateMatchDay());
		}
		return START_REDELIVER_INTENT;
	}
	
	/**
	 * Calculate the match day to request equivalent API
	 * http://footballdb.herokuapp.com/api/v1/event/world.2014/round/round_number
	 * @return round_number
	 * */
	private int calculateMatchDay(){
		//dummy code: return 1
		return 1;
	}
}
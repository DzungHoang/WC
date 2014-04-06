package lod.entertainment.wc.data;

import java.util.Calendar;
import java.util.TimeZone;

import lod.entertainment.wc.HomeActivity;
import lod.entertainment.wc.entity.GameInfo;
import lod.entertainment.wc.utils.LogUtils;
import lod.entertainment.wc.utils.PreferenceUtils;
import lod.entertainment.wc.utils.Utils;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

public class UpdateMatchdayAsync extends AsyncTask<String, Void, String>{
	
	private final String url = "http://footballdb.herokuapp.com/api/v1/event/world.2014/round/";
	
	private Context mContext;
	public UpdateMatchdayAsync(Context context){
		mContext = context;
	}
	
	@Override
	protected String doInBackground(String... params) {
		String ret = "";
		int round_number = Integer.parseInt(params[0]);
		String json = params[1];
		LogUtils.d("DungHV","doInBackground: round_number = " + round_number);
		LogUtils.d("DungHV","doInBackground: json = " + json);
		if(json != null && json.length() > 0){
			ret = json;
			GameInfo temp = Utils.parseGameInfo(json);
			if(temp != null){
				String title = temp.getTeam1().getCode() + " - " + temp.getTeam2().getCode();
				int score1 = temp.getScore1() + temp.getScore1Ext();
				int score2 = temp.getScore2() + temp.getScore2Ext();
				String msg = "    " + score1 + "      -      " + score2;
				Intent notifyIntent = new Intent(mContext,
						HomeActivity.class);
				notifyIntent.putExtra("game_index", temp.getIndex());

				PendingIntent pending = PendingIntent.getActivity(mContext,
						0, notifyIntent, 0);
				
				Utils.generateNotification(mContext, title, msg, pending);
				
				if(temp.getIndex() < 49 && temp.getIndex() > PreferenceUtils.getInstance().getLastUpdatedGame()){
					//update database
					DatabaseWC db = new DatabaseWC(mContext);
					int team1win = score1 > score2 ? 1:0;
					int team1draw = score1 == score2 ? 1:0;
					int team1lose = 1 - team1win;
					
					db.updateTeamInfoStanding(temp.getTeam1().getCode(), 1, team1win, team1draw,  team1lose, score1, score2, team1win*3 + team1draw);
					db.updateTeamInfoStanding(temp.getTeam2().getCode(), 1, team1lose, team1draw,  team1win, score2, score1, team1lose*3 + team1draw);
					
					PreferenceUtils.getInstance().setLastUpdatedGame(temp.getIndex());
				}
				
			}
		}else{
			if ((round_number > 0) && (round_number < 21)){
				HTTPHandler handler = new HTTPHandler();
				ret = handler.makeHTTPRequest(url + round_number, HTTPHandler.GET);
			}
		}
		if(ret.length() > 0){
			Utils.overwriteFileInSD(mContext, ret, "games_index_"+round_number+".json");
		}
		return ret;
	}

	@Override
	protected void onPostExecute(String result) {
//		LogUtils.d("DungHV","onPostExe: " + result);
		//get timezone of user
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		long currentMillis = calendar.getTimeInMillis();
		PreferenceUtils.getInstance().setLastUpdated(""+currentMillis);
//		LogUtils.d("DungHV","currentMillis = " + currentMillis);
		//sample to get last updated
//		String millis = PreferenceUtils.getInstance().getLastUpdated();
//		String date = Utils.getDate(Long.valueOf(millis).longValue(), "dd/MM/yyyy hh:mm:ss");
//		LogUtils.d("DungHV","date = " + date);
		super.onPostExecute(result);
	}
}

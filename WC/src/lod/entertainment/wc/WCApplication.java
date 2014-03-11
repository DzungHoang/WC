package lod.entertainment.wc;

import java.util.ArrayList;

import lod.entertainment.wc.entity.MatchDayInfo;
import lod.entertainment.wc.entity.TeamInfo;
import lod.entertainment.wc.utils.Utils;
import android.app.Application;
import android.content.Intent;
import android.util.Log;

public class WCApplication extends Application{

	ArrayList<TeamInfo> mTeamList;
	ArrayList<MatchDayInfo> mMatchDayList;
	@Override
	public void onCreate() {
		super.onCreate();
		Intent intent =  new Intent();
		intent.setClass(getApplicationContext(), MyService.class);
		startService(intent);
		
		long time = System.currentTimeMillis();
		String jsonString = Utils.loadJSONFromAsset(this, "team_list.json");
		Log.d("DungHV","jsonString = " + jsonString);
		mTeamList = Utils.parseTeamList(jsonString);
		Log.d("DungHV","teamList.length() = " + mTeamList.size());
		
		String matchString = Utils.loadJSONFromAsset(this, "match_day_list.json");
		Log.d("DungHV","matchString = " + matchString);
		mMatchDayList = Utils.parseMatchDayList(matchString);
		Log.d("DungHV","matchList.length() = " + mMatchDayList.size());
		
		long gap = System.currentTimeMillis() - time;
		Log.d("DungHV","gap = " + gap);
	}
}

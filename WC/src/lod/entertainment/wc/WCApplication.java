package lod.entertainment.wc;

import java.util.ArrayList;
import java.util.List;

import lod.entertainment.wc.entity.GameInfo;
import lod.entertainment.wc.entity.MatchDayInfo;
import lod.entertainment.wc.entity.TeamInfo;
import lod.entertainment.wc.utils.PreferenceUtils;
import lod.entertainment.wc.utils.Utils;
import android.app.Application;
import android.content.Intent;
import android.util.Log;

public class WCApplication extends Application{

	private TeamInfo mTeamFavorite = null;
	private PreferenceUtils mPrefUtils;
	
	private ArrayList<TeamInfo> mTeamList;
	private ArrayList<MatchDayInfo> mMatchDayList;
	private List<GameInfo> mGameScheduleList;
	@Override
	public void onCreate() {
		super.onCreate();
		Intent intent =  new Intent();
		intent.setClass(getApplicationContext(), MyService.class);
		startService(intent);
		
		// Read match schedule list
		long times = System.currentTimeMillis();
		Log.d("DungHV", "start ");
		String gameString = Utils.loadJSONFromAsset(this, "schedule.json");
		Log.d("TienVV", "game string: " + gameString);
		mGameScheduleList = Utils.parseGameSchedule(gameString);
		Log.d("TienVV", "game size: " + mGameScheduleList.size());
		long timee = System.currentTimeMillis();
		Log.d("TienVV", "range mili: " + (timee - times));
		
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
		
		// Read team favorite from preferences
		mPrefUtils = new PreferenceUtils(getApplicationContext());
		String keyTeam = mPrefUtils.getTeamFavorite();
		mTeamFavorite = getTeamByCode(keyTeam);
	}
	
	public List<TeamInfo> getListTeam() {
		return mTeamList;
	}
	
	public List<GameInfo> getGameSchedule() {
		return mGameScheduleList;
	}
	
	/**
	 * Get team info by team's key
	 * @param key
	 * @return Team info object
	 */
	public TeamInfo getTeamByCode(String key) {
		if (mTeamList == null || mTeamList.size() == 0 || key == null) {
			return null;
		}
		TeamInfo team = null;
		for (TeamInfo teamItem : mTeamList) {
			if (key.equals(teamItem.getCode())) {
				team = teamItem;
				break;
			}
		}
		return team;
	}
	
	/**
	 * Get team's name by team's code
	 * @param code Team's code
	 * @return Team's name
	 */
	public String getTeamNameByCode(String code) {
		String result = null;
		if (mTeamList == null || mTeamList.size() == 0 || code == null) {
			return null;
		}
		for (TeamInfo team : mTeamList) {
			if (code.equals(team.getCode())) {
				result = team.getName();
				break;
			}
		}
		return result;
	}
	
	/**
	 * @return Favorite team of user
	 */
	public TeamInfo getTeamFavorite() {
		return mTeamFavorite;
	}
	
	/**
	 * Store team favorite to preferences
	 * @param keyTeam
	 */
	public void setTeamFavorite(String keyTeam) {
		mPrefUtils.setTeamFavorite(keyTeam);
		mTeamFavorite = getTeamByCode(keyTeam);
	}
}

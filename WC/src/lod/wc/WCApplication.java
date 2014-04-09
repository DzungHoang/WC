package lod.wc;

import java.util.ArrayList;
import java.util.List;

import lod.wc.entity.GameInfo;
import lod.wc.entity.MatchDayInfo;
import lod.wc.entity.TeamInfo;
import lod.wc.gcm.RegisterGCMService;
import lod.wc.utils.LogUtils;
import lod.wc.utils.PreferenceUtils;
import lod.wc.utils.Utils;
import vn.gamexp.facebookutils.FacebookUtils;
import android.app.Application;
import android.content.Intent;

public class WCApplication extends Application{

	private TeamInfo mTeamFavorite = null;
	private PreferenceUtils mPrefUtils;
	
	private ArrayList<TeamInfo> mTeamList;
	private ArrayList<MatchDayInfo> mMatchDayList;
	private List<GameInfo> mGameScheduleList;
	
	private FacebookUtils mFacebookUtils;
	@Override
	public void onCreate() {
		super.onCreate();
		Intent intent = new Intent();
		intent.setClass(this, RegisterGCMService.class);
		startService(intent);
		// Read match schedule list
		long times = System.currentTimeMillis();
		LogUtils.d("DungHV", "start ");
		String gameString = Utils.loadJSONFromAsset(this, "schedule.json");
		LogUtils.d("TienVV", "game string: " + gameString);
		mGameScheduleList = Utils.parseGameSchedule(gameString);
		LogUtils.d("TienVV", "game size: " + mGameScheduleList.size());
		long timee = System.currentTimeMillis();
		LogUtils.d("TienVV", "range mili: " + (timee - times));
		
		long time = System.currentTimeMillis();
		String jsonString = Utils.loadJSONFromAsset(this, "team_list.json");
		LogUtils.d("DungHV","jsonString = " + jsonString);
		mTeamList = Utils.parseTeamList(jsonString);
		LogUtils.d("DungHV","teamList.length() = " + mTeamList.size());
		
		String matchString = Utils.loadJSONFromAsset(this, "match_day_list.json");
		LogUtils.d("DungHV","matchString = " + matchString);
		mMatchDayList = Utils.parseMatchDayList(matchString);
		LogUtils.d("DungHV","matchList.length() = " + mMatchDayList.size());
		
		// Read team favorite from preferences
		mPrefUtils = new PreferenceUtils(getApplicationContext());
		String keyTeam = mPrefUtils.getTeamFavorite();
		mTeamFavorite = getTeamByCode(keyTeam);
		
//		for(int i = 1; i<21; i++){
//			Utils.copyFromAssetToSD(this, "games_round_"+ i + ".json");
//		}
//				
//		updateGameResult();
		long gap = System.currentTimeMillis() - time;
		LogUtils.d("DungHV","gap = " + gap);
	}
	
	public void updateGameResult(){
		for (int i = 1; i < 65; i++){
			Utils.updateGameResults(this, "games_index_" + i + ".json", mGameScheduleList);
		}
		
//		for(int i = 0; i< mGameScheduleList.size(); i++){
//			GameInfo temp = mGameScheduleList.get(i);
//			if(temp != null){
//				int score1 = temp.getScore1() + temp.getScore1Ext();
//				int score2 = temp.getScore2() + temp.getScore2Ext();
//
//				if(temp.getIndex() < 49 && score1 >= 0 && score2 >=0 ){
//					//update database
//					DatabaseWC db = new DatabaseWC(this);
//					int team1win = score1 > score2 ? 1:0;
//					int team1draw = score1 == score2 ? 1:0;
//					int team1lose = 1 - team1win;
//					
//					db.updateTeamInfoStanding(temp.getTeam1().getCode(), 1, team1win, team1draw,  team1lose, score1, score2, team1win*3 + team1draw);
//					db.updateTeamInfoStanding(temp.getTeam2().getCode(), 1, team1lose, team1draw,  team1win, score2, score1, team1lose*3 + team1draw);
//				}
//				
//			}
//		}
	}
	
	public List<TeamInfo> getListTeam() {
		return mTeamList;
	}
	
	public List<GameInfo> getGameSchedule() {
		return mGameScheduleList;
	}
	
	/**
	 * Get list game schedule of a team
	 * @param teamCode Team's code
	 * @return
	 */
	public List<GameInfo> getGameScheduleOfTeam(String teamCode) {
		List<GameInfo> list = new ArrayList<GameInfo>();
		if (teamCode == null || teamCode.length() == 0) {
			return list;
		}
		for (GameInfo game : mGameScheduleList) {
			TeamInfo team1 = game.getTeam1();
			TeamInfo team2 = game.getTeam2();
			if (teamCode.equals(team1.getCode()) || teamCode.equals(team2.getCode())) {
				list.add(game);
			}
		}
		return list;
	}
	
	/**
	 * Get list game schedule of a group
	 * @param group Group's code
	 * @return
	 */
	public List<GameInfo> getGameScheduleOfGroup(String group) {
		List<GameInfo> list = new ArrayList<GameInfo>();
		if (group == null || group.length() == 0) {
			return list;
		}
		// Group match from 1->48
		for (int i = 0; i < 48; i++){
			GameInfo game = mGameScheduleList.get(i);
			TeamInfo team1 = game.getTeam1();
			if (group.equals(team1.getGroup())) {
				list.add(game);
			}
		}
		return list;
	}
	
	/**
	 * Get list game schedule in range of match number
	 * Example: group match from 1-48
	 * Round of 16 from 49-56
	 * @param from
	 * @param to
	 * @return
	 */
	public List<GameInfo> getGameScheduleInRange(int from, int to) {
		List<GameInfo> list = new ArrayList<GameInfo>();
		if (!(to >= from) || from < 1) {
			return list;
		}
		for (int i = (from - 1); i < to; i++){
			list.add(mGameScheduleList.get(i));
		}
		return list;
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
	
	/**
	 * Get list of team in a group
	 * @param group Group's code
	 * @return
	 */
	public List<TeamInfo> getListTeamFromGroup(String group) {
		List<TeamInfo> listTeam = new ArrayList<TeamInfo>();
		if (group == null || group.length() == 0) {
			return listTeam;
		}
		for (TeamInfo team : mTeamList) {
			if (group.equals(team.getGroup())) {
				listTeam.add(team);
			}
		}
		return listTeam;
	}
	
	/**
	 * Set facebook utils object
	 * @param facebookUtils
	 */
	public void setFacebookUtil(FacebookUtils facebookUtils) {
		mFacebookUtils = facebookUtils;
	}
	/**
	 * Get facebook utils object
	 * @return
	 */
	public FacebookUtils getFacebookUtils() {
		return mFacebookUtils;
	}
}

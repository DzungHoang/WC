package lod.entertainment.wc.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferenceUtils {

	private Context mContext;
	private static final String KEY_PREF = "wc_preferences";
	private static final String KEY_TEAM = "key_team";
	private static final String KEY_LAST_UPDATED = "last_updated";
	private static final String KEY_UPDATED_GAME = "updated_game";
	private SharedPreferences mPref;
	private Editor mEditor;
	
	public PreferenceUtils(Context context) {
		mContext = context;
		mPref = mContext.getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE);
		mInstance = this;
	}
	private static PreferenceUtils mInstance;
	public static PreferenceUtils getInstance(){
		return mInstance;
	}
	
	/**
	 * Set favorite team (by team key) to preference
	 * @param teamKey
	 */
	public void setTeamFavorite(String teamKey) {
		mEditor = mPref.edit();
		mEditor.putString(KEY_TEAM, teamKey);
		mEditor.commit();
	}
	
	/**
	 * Get favorite team
	 * @return Team key or NULL if user not select favorite team
	 */
	public String getTeamFavorite() {
		return mPref.getString(KEY_TEAM, null);
	}
	/**
	 * Set last updated time to preference
	 * @param lastUpdated
	 */
	public void setLastUpdated(String lastUpdated) {
		mEditor = mPref.edit();
		mEditor.putString(KEY_LAST_UPDATED, lastUpdated);
		mEditor.commit();
	}
	
	/**
	 * Get last updated time
	 * @return Last updated
	 */
	public String getLastUpdated() {
		return mPref.getString(KEY_LAST_UPDATED, null);
	}
	/**
	 * Set last updated time to preference
	 * @param lastUpdated
	 */
	public void setLastUpdatedGame(int lastUpdated) {
		mEditor = mPref.edit();
		mEditor.putInt(KEY_UPDATED_GAME, lastUpdated);
		mEditor.commit();
	}
	
	/**
	 * Get last updated time
	 * @return Last updated
	 */
	public int getLastUpdatedGame() {
		return mPref.getInt(KEY_UPDATED_GAME, 0);
	}
}

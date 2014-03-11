package lod.entertainment.wc.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferenceUtils {

	private Context mContext;
	private static final String KEY_PREF = "wc_preferences";
	private static final String KEY_TEAM = "key_team";
	private SharedPreferences mPref;
	private Editor mEditor;
	
	public PreferenceUtils(Context context) {
		mContext = context;
		mPref = mContext.getSharedPreferences(KEY_PREF, Context.MODE_PRIVATE);
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
}

package lod.entertainment.wc.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import lod.entertainment.wc.entity.GameInfo;
import lod.entertainment.wc.entity.MatchDayInfo;
import lod.entertainment.wc.entity.TeamInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;

public class Utils {
	/**
	 * Load a json file in /assets folder
	 * 
	 * @return json string
	 * */
	public static String loadJSONFromAsset(Context context, String fileName) {
		String json = null;
		try {

			InputStream is = context.getAssets().open(fileName);

			int size = is.available();

			byte[] buffer = new byte[size];

			is.read(buffer);

			is.close();

			json = new String(buffer, "UTF-8");

		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
		return json;

	}

	/**
	 * Parse team list from a json String
	 * 
	 * @return An ArrayList of TeamInfo
	 * */
	public static ArrayList<TeamInfo> parseTeamList(String json) {
		ArrayList<TeamInfo> result = new ArrayList<TeamInfo>();
		if (json != null) {
			try {
				JSONObject jsonObject = new JSONObject(json);
				JSONArray listTeam = jsonObject.getJSONArray("teams");
				if (listTeam != null) {
					for (int i = 0; i < listTeam.length(); i++) {
						JSONObject team = listTeam.getJSONObject(i);
						if (team != null) {
							String key = team.getString("key");
							String name = team.getString("title");
							String code = team.getString("code");
							String group = team.getString("group");

							TeamInfo tempTeam = new TeamInfo(key, name, code,
									group);
							result.add((TeamInfo) tempTeam);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	/**
	 * Parse match day list from a json String
	 * 
	 * @return An ArrayList of MatchDayInfo
	 * */
	public static ArrayList<MatchDayInfo> parseMatchDayList(String json) {
		ArrayList<MatchDayInfo> result = new ArrayList<MatchDayInfo>();
		if (json != null) {
			try {
				JSONObject jsonObject = new JSONObject(json);
				JSONArray listTeam = jsonObject.getJSONArray("rounds");
				if (listTeam != null) {
					for (int i = 0; i < listTeam.length(); i++) {
						JSONObject team = listTeam.getJSONObject(i);
						if (team != null) {
							String pos = team.getString("pos");
							String title = team.getString("title");
							String start = team.getString("start_at");
							String end = team.getString("end_at");

							MatchDayInfo tempTeam = new MatchDayInfo(pos,
									title, start, end);
							result.add((MatchDayInfo) tempTeam);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	/**
	 * Parse game list from a json String
	 * 
	 * @return An ArrayList of GameInfo
	 * */
//	public static void parseGameList(ArrayList<GameInfo> gameList, String json) {
//		if (gameList == null) gameList = new ArrayList<GameInfo>();
//		
//		
//		ArrayList<TeamInfo> result = new ArrayList<TeamInfo>();
//		if (json != null) {
//			try {
//				JSONObject jsonObject = new JSONObject(json);
//				JSONArray gameTeam = jsonObject.getJSONArray("games");
//				if (gameTeam != null) {
//					for (int i = 0; i < gameTeam.length(); i++) {
//						JSONObject game = gameTeam.getJSONObject(i);
//						if (game != null) {
//							String key = team.getString("key");
//							String name = team.getString("title");
//							String code = team.getString("code");
//							String group = team.getString("group");
//
//							TeamInfo tempTeam = new TeamInfo(key, name, code,
//									group);
//							result.add((TeamInfo) tempTeam);
//						}
//					}
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//	}
	/**
	 * Copy file from asset to SD card. Path in asset: asset/games_round_1.json
	 * Path in SD: path_to_SD/games_round_1.json
	 * */
	public static void copyFromAssetToSD(Context context, String pathInAsset){
		try{
		InputStream inputStream = context.getAssets().open(pathInAsset);
		String parent = context.getExternalFilesDir(null).getAbsolutePath();
		OutputStream output = new FileOutputStream(parent + "/" + pathInAsset);

		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = inputStream.read(buffer)) > 0) {
			output.write(buffer, 0, length);
		}

		// Close the streams
		output.flush();
		output.close();
		inputStream.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Overwrite a string to a file
	 * */
	public static void overwriteFileInSD(Context context, String str2OverWrite, String fileName) {
		try {
			String parent = context.getExternalFilesDir(null).getAbsolutePath();
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
					new FileOutputStream(parent + "/" + fileName));
			outputStreamWriter.write(str2OverWrite);
			outputStreamWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Return date in specified format.
	 * @param milliSeconds Date in milliseconds
	 * @param dateFormat Date format 
	 * @return String representing date in specified format
	 */
	public static String getDate(long milliSeconds, String dateFormat)
	{
	    // Create a DateFormatter object for displaying date in specified format.
	    DateFormat formatter = new SimpleDateFormat(dateFormat);

	    // Create a calendar object that will convert the date and time value in milliseconds to date. 
	     Calendar calendar = Calendar.getInstance();
	     calendar.setTimeInMillis(milliSeconds);
	     return formatter.format(calendar.getTime());
	}
}

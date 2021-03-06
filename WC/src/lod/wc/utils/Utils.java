package lod.wc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import lod.wc.HomeActivity;
import lod.wc.R;
import lod.wc.entity.GameInfo;
import lod.wc.entity.MatchDayInfo;
import lod.wc.entity.TeamInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;

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
	 * Load a json file in external storage folder
	 * 
	 * @return json string
	 * */
	public static String loadJSONFromExternal(Context context, String fileName) {
		String json = null;
		try {
			String parent = context.getExternalFilesDir(null).getAbsolutePath();
			InputStream is = new FileInputStream(parent + "/" + fileName);

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
	 * This method update GameInfo in list. It should be called only for match
	 * on current day, not for a batch of update all GameInfo
	 * */
	public static void updateGameResults(Context context, String fileName,
			List<GameInfo> gameInfoList) {
		if ((gameInfoList == null) || (context == null) || (fileName == null))
			return;
		String jsonString = loadJSONFromExternal(context, fileName);
		if (jsonString != null) {
			LogUtils.d("DungHV", "updateGameResults: json = " + jsonString);
			try {
				JSONObject game = new JSONObject(jsonString);
				// if(jsonObject != null){
				// JSONArray games = jsonObject.getJSONArray("games");
				// if(games!=null){
				// for (int i = 0; i < games.length(); i++){
				// JSONObject game = games.getJSONObject(i);
				if (game != null) {
					int score1 = game.getInt("score1");
					// hard code to detect the real score
					// because currently, score1 is the time of match
					// ex: score1 = 13 => match start at 13:00
					// besides, the real score is really rare to be equal
					// "13 - 0" =))))))
					if (Integer.valueOf(score1).intValue() < 13) {
//						String team1_code = game.getString("team1_code");
//						String team2_code = game.getString("team2_code");
//						String play_at = game.getString("play_at");
						String index = game.getString("game_index");
						GameInfo temp = findGame(index, gameInfoList);
						if (temp != null) {
							int score2 = 0;
							int score1ext = 0;
							int score2ext = 0;
							int score1p = -1;
							int score2p = -1;
							try {
								score2 = game.getInt("score2");
								score1ext = game.getInt("score1ot");
								score2ext = game.getInt("score2ot");
								score1p = game.getInt("score1p");
								score2p = game.getInt("score2p");
							} catch (Exception e) {
								e.printStackTrace();
							} finally {
								temp.setScore(score1, score2, score1ext,
										score2ext, score1p, score2p);
							}
							temp.getTeam1().setCode(
									game.getString("team1_code"));
							temp.getTeam1().setKey(game.getString("team1_key"));
							temp.getTeam1().setName(
									game.getString("team1_title"));
							temp.getTeam2().setCode(
									game.getString("team2_code"));
							temp.getTeam2().setKey(game.getString("team2_key"));
							temp.getTeam2().setName(
									game.getString("team2_title"));
						}
					}
				}
				// }
				// }
				// }
			} catch (Exception e) {
//				e.printStackTrace();
			}
		}
	}

	/**
	 * Find the game in list, based on team_code
	 * 
	 * @return the equivalent GameInfo
	 * */
//	private static GameInfo findGame(String team1_code, String team2_code,
//			String play_at, List<GameInfo> gameList) {
//		if (gameList == null)
//			return null;
//		GameInfo ret = null;
//		for (int i = 0; i < gameList.size(); i++) {
//			GameInfo temp = gameList.get(i);
//			if (team1_code.equals(temp.getTeam1().getCode())
//					&& team2_code.equals(temp.getTeam2().getCode())
//					&& play_at.equals(temp.getDate())) {
//				ret = temp;
//			}
//		}
//		return ret;
//	}

	private static GameInfo findGame(String index, List<GameInfo> gameList) {
		if (gameList == null)
			return null;
		GameInfo ret = null;
		try {
			int game_index = Integer.parseInt(index);
			for (int i = 0; i < gameList.size(); i++) {
				GameInfo temp = gameList.get(i);
				if (game_index == temp.getIndex()) {
					ret = temp;
				}
			}
		} catch (Exception e) {

		}

		return ret;
	}

	public static List<GameInfo> parseGameSchedule(String json) {
		List<GameInfo> listResult = new ArrayList<GameInfo>();
		if (json == null || json.length() == 0) {
			return listResult;
		}

		try {
			JSONObject object = new JSONObject(json);
			JSONArray arrGame = object.getJSONArray("games");
			if (arrGame != null) {
				for (int i = 0; i < arrGame.length(); i++) {
					JSONObject game = arrGame.getJSONObject(i);
					int index = Integer.parseInt(game.getString("game_index"));
					String team1Key = game.getString("team1_key");
					String team1Code = game.getString("team1_code");
					String team1Title = game.getString("team1_title");
					String team2Key = game.getString("team2_key");
					String team2Code = game.getString("team2_code");
					String team2Title = game.getString("team2_title");
					String date = game.getString("play_at");
					String time = game.getString("time");

					TeamInfo team1 = new TeamInfo(team1Key, team1Title,
							team1Code);
					TeamInfo team2 = new TeamInfo(team2Key, team2Title,
							team2Code);

					GameInfo item = new GameInfo(index, team1, team2, date,
							time);
					listResult.add(item);
				}
			}
		} catch (JSONException e) {
		}
		return listResult;
	}

	public static void updateGameSchedule(String gameInfo, List<GameInfo> list) {
		if (gameInfo == null || list == null || gameInfo.equals("")) {
			return;
		}
		try {
			JSONObject game = new JSONObject(gameInfo);
			int index = Integer.parseInt(game.getString("game_index"));
			String team1Key = game.getString("team1_key");
			String team1Code = game.getString("team1_code");
			String team1Title = game.getString("team1_title");
			String team2Key = game.getString("team2_key");
			String team2Code = game.getString("team2_code");
			String team2Title = game.getString("team2_title");
			String date = game.getString("play_at");
			String time = game.getString("time");

			TeamInfo team1 = new TeamInfo(team1Key, team1Title, team1Code);
			TeamInfo team2 = new TeamInfo(team2Key, team2Title, team2Code);

			GameInfo item = new GameInfo(index, team1, team2, date, time);
			for (int i = 0; i < list.size(); i++) {
				GameInfo temp = ((GameInfo) list.get(i));
				temp = item;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Copy file from asset to SD card. Path in asset: asset/games_round_1.json
	 * Path in SD: path_to_SD/games_round_1.json
	 * */
	public static void copyFromAssetToSD(Context context, String pathInAsset) {
		try {
			InputStream inputStream = context.getAssets().open(pathInAsset);
			String parent = context.getExternalFilesDir(null).getAbsolutePath();
			File file = new File(parent + "/" + pathInAsset);
			if (file.exists())
				return; // not overwrite the file if existed
			OutputStream output = new FileOutputStream(parent + "/"
					+ pathInAsset);

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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Overwrite a string to a file
	 * */
	public static void overwriteFileInSD(Context context, String str2OverWrite,
			String fileName) {
		try {
			String parent = context.getExternalFilesDir(null).getAbsolutePath();
			File file = new File(parent + "/" + fileName);
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (Exception e) {

				}
			}
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
	 * 
	 * @param milliSeconds
	 *            Date in milliseconds
	 * @param dateFormat
	 *            Date format
	 * @return String representing date in specified format
	 */
	public static String getDate(long milliSeconds, String dateFormat) {
		// Create a DateFormatter object for displaying date in specified
		// format.
		DateFormat formatter = new SimpleDateFormat(dateFormat);

		// Create a calendar object that will convert the date and time value in
		// milliseconds to date.
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliSeconds);
		return formatter.format(calendar.getTime());
	}

	/**
	 * Convert date time on GMT-3 (in Brazil) to date time in User's TimeZone
	 * 
	 * @param date
	 * @param time
	 * @return
	 */
	public static String getDateAsTimeZone(String date, String time) {
		String result = date.concat(" ").concat(time);
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		formatter.setTimeZone(TimeZone.getTimeZone("GMT-3"));
		try {
			Date d = formatter.parse(result);
			formatter.setTimeZone(TimeZone.getDefault());
			result = formatter.format(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Get the list of matches in today
	 * */
	public static List<GameInfo> matchesToday(List<GameInfo> allMatches) {
		List<GameInfo> tempMatches = new ArrayList<GameInfo>();

		if (allMatches != null) {
			for (int i = 0; i < allMatches.size(); i++) {
				GameInfo temp = allMatches.get(i);
				String date = getDateAsTimeZone(temp.getDate(), temp.getTime());
				date = date.substring(0, 10);
				Calendar c = Calendar.getInstance();
				SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
				String formattedDate = df.format(c.getTime());
				if (date.equals(formattedDate)) {
					tempMatches.add(allMatches.get(i));
				}
			}
		}
		return tempMatches;
	}

	public static String standardNumber(long number) {
		if (number < 10) {
			return "0" + number;
		} else {
			return String.valueOf(number);
		}
	}

	public static void saveBooleanToSharedPref(Context context, String key,
			boolean value) {
		Editor edit = context.getSharedPreferences("wc_pref",
				Context.MODE_PRIVATE).edit();
		edit.putBoolean(key, value);
		edit.commit();
	}

	public static boolean getBooleanFromSharedPred(Context context, String key,
			boolean def) {
		return context.getSharedPreferences("wc_pref", Context.MODE_PRIVATE)
				.getBoolean(key, def);
	}

	public static boolean checkAppRunning(Context context) {
		// TODO
		boolean ret = false;
		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> procInfos = activityManager
				.getRunningAppProcesses();
		for (int i = 0; i < procInfos.size(); i++) {
			if (procInfos.get(i).processName.equals("lod.wc")) {
				// Toast.makeText(context, "Browser is running",
				// Toast.LENGTH_LONG).show();
				String[] pkgList = procInfos.get(i).pkgList;
				if (pkgList != null) {
					// for (int j = 0; j< pkgList.length; j++){
					LogUtils.d("DungHV", "importance = "
							+ procInfos.get(i).importance);
					LogUtils.d("DungHV",
							"importanceReasonCode = "
									+ procInfos.get(i).importanceReasonCode);
					LogUtils.d("DungHV",
							"importance = "
									+ procInfos.get(i).importanceReasonComponent
											.toString());
					// }
				}
				ret = true;

			}
		}
		LogUtils.d("DungHV", "checkAppRunning: " + ret);
		return ret;
	}

	public static GameInfo parseGameInfo(String jsonResult) {
		if (jsonResult == null) {
			return null;
		}

		GameInfo ret = null;
		try {
			JSONObject game = new JSONObject(jsonResult);
			int index = Integer.parseInt(game.getString("game_index"));
			String team1Key = game.getString("team1_key");
			String team1Code = game.getString("team1_code");
			String team1Title = game.getString("team1_title");
			String team2Key = game.getString("team2_key");
			String team2Code = game.getString("team2_code");
			String team2Title = game.getString("team2_title");
			String date = game.getString("play_at");
			String time = game.getString("time");

			TeamInfo team1 = new TeamInfo(team1Key, team1Title, team1Code);
			TeamInfo team2 = new TeamInfo(team2Key, team2Title, team2Code);

			ret = new GameInfo(index, team1, team2, date, time);
			int score1 = 0;
			int score2 = 0;
			int score1ext = 0;
			int score2ext = 0;
			int score1p = -1;
			int score2p = -1;
			try {
				score1 = game.getInt("score1");
				score2 = game.getInt("score2");
				score1ext = game.getInt("score1ot");
				score2ext = game.getInt("score2ot");
				score1p = game.getInt("score1p");
				score2p = game.getInt("score2p");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				ret.setScore(score1, score2, score1ext, score2ext, score1p,
						score2p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

	
	public static void generateNotification(Context context, String title, String msg, PendingIntent pendingIntent){
		NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		
		Notification notification = new Notification.Builder(
				context)
				.setContentTitle(title)
				.setContentText(msg)
				.setContentIntent(pendingIntent)
				.setAutoCancel(true)
				.setSmallIcon(R.drawable.ic_launcher)
				.getNotification();
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		manager.notify(0, notification);
	}
	/**
	 * Issues a notification to inform the user that server has sent a message.
	 */
	public static void generateNotification(Context context, String message) {
		int icon = R.drawable.ic_launcher;
		long when = System.currentTimeMillis();
		NotificationManager notificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = new Notification(icon, message, when);

		String title = context.getString(R.string.app_name);

		Intent notificationIntent = new Intent(context, HomeActivity.class);
		// set intent so it does not start a new activity
		notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
				| Intent.FLAG_ACTIVITY_SINGLE_TOP);
		PendingIntent intent = PendingIntent.getActivity(context, 0,
				notificationIntent, 0);
		notification.setLatestEventInfo(context, title, message, intent);
		notification.flags |= Notification.FLAG_AUTO_CANCEL;

		// Play default notification sound
		notification.defaults |= Notification.DEFAULT_SOUND;

		// Vibrate if vibrate is enabled
		notification.defaults |= Notification.DEFAULT_VIBRATE;
		notificationManager.notify(0, notification);

	}
}

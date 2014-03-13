package lod.entertainment.wc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import lod.entertainment.wc.data.DatabaseWC;
import lod.entertainment.wc.entity.GameInfo;
import lod.entertainment.wc.entity.TeamInfo;
import lod.entertainment.wc.entity.TextViewWC;
import lod.entertainment.wc.utils.PreferenceUtils;
import lod.entertainment.wc.utils.Utils;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends Activity implements OnClickListener {

	private Context mContext;
	private DatabaseWC mDatabase;
	private WCApplication mApplication;
	
	private TextViewWC mTvTimeDaysHours;
	private TextViewWC mTvTimeMinutesSeconds;
	private Button mBtnGroup;
	private Button mBtnSchedule;
	private Button mBtnTeam;
	private ImageView mImgTeamLogo;
	private TextView mTvTeamFavorite;
	private Button mBtnFacebook;
	private Button mBtnSetting;
	private Button mBtnInfo;
	
	private static final int CODE_SELECT_TEAM = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_home);
		// Initiate data
		initData();
		// Initiate layout
		initLayout();
		// Initiate count down time
		initCountDownTime();
		// Initiate data on screen
		initData();
		
		mDatabase = new DatabaseWC(mContext);
	}

	/**
	 * Initiate layout
	 */
	private void initLayout() {
		mTvTimeDaysHours = (TextViewWC) findViewById(R.id.tv_home_time_days_hours);
		mTvTimeMinutesSeconds = (TextViewWC) findViewById(R.id.tv_home_time_minutes_second);
		mBtnGroup = (Button) findViewById(R.id.btn_home_group);
		mBtnGroup.setOnClickListener(this);
		mBtnSchedule = (Button) findViewById(R.id.btn_home_schedule);
		mBtnSchedule.setOnClickListener(this);
		
		mBtnTeam = (Button) findViewById(R.id.btn_home_team);
		mBtnTeam.setOnClickListener(this);
		mImgTeamLogo = (ImageView) findViewById(R.id.img_home_team_favorite);
		mTvTeamFavorite = (TextView) findViewById(R.id.tv_home_team_favorite);
		TeamInfo teamFavorite = mApplication.getTeamFavorite();
		if (teamFavorite == null) {
			mTvTeamFavorite.setText(R.string.btn_select_team_favorite); // Not have favorite team
			mTvTeamFavorite.setTextColor(Color.WHITE); // Default color is red (define in xml)
		} else {
			// Have favorite team
			mTvTeamFavorite.setText(teamFavorite.getName());
			mTvTeamFavorite.setTextSize(20);
			mTvTeamFavorite.setTextColor(Color.WHITE);
			mImgTeamLogo.setImageResource(teamFavorite.getFlag());
		}
		
		mBtnFacebook = (Button) findViewById(R.id.btn_home_facebook);
		mBtnFacebook.setOnClickListener(this);
		mBtnSetting = (Button) findViewById(R.id.btn_home_setting);
		mBtnSetting.setOnClickListener(this);
		mBtnInfo = (Button) findViewById(R.id.btn_home_info);
		mBtnInfo.setOnClickListener(this);
	}

	/**
	 * Initiate CountDown time to World Cup 2014
	 */
	@SuppressLint("SimpleDateFormat")
	private void initCountDownTime() {
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT-3")); // Timezone
																			// in
																			// Brazil
																			// is
																			// GMT-3
		c.set(2014, 5, 12, 17, 0, 0);
		Date dateDestination = c.getTime(); // Time to start WC (in brazil)
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z");
		formatter.setTimeZone(TimeZone.getDefault()); // Time zone of user
		
		Calendar destination = Calendar.getInstance();
		try {
			destination.setTime(formatter.parse(formatter.format(dateDestination)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Log.d("TienVV", "year: " + destination.get(Calendar.YEAR));
		Log.d("TienVV", "month: " + destination.get(Calendar.MONTH));
		Log.d("TienVV", "days: " + destination.get(Calendar.DAY_OF_MONTH));
		Log.d("TienVV", "hours: " + destination.get(Calendar.HOUR_OF_DAY));
		Log.d("TienVV", "minute: " + destination.get(Calendar.MINUTE));
		
		final Calendar start = Calendar.getInstance(TimeZone.getDefault());
		
		Log.d("TienVV", "year: " + start.get(Calendar.YEAR));
		Log.d("TienVV", "month: " + start.get(Calendar.MONTH));
		Log.d("TienVV", "days: " + start.get(Calendar.DAY_OF_MONTH));
		Log.d("TienVV", "hours: " + start.get(Calendar.HOUR_OF_DAY));
		Log.d("TienVV", "minute: " + start.get(Calendar.MINUTE));
		
		long timeCurrent = start.getTimeInMillis();
		long timeEnd = destination.getTimeInMillis();
		if (timeEnd > timeCurrent) {
			CountDownTimer timer = new CountDownTimer(timeEnd - timeCurrent,
					1000) {

				@Override
				public void onTick(long millisUntilFinished) {
					long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);
					long minutes = TimeUnit.SECONDS.toMinutes(seconds);
					seconds = seconds % 60;
					long hours = TimeUnit.MINUTES.toHours(minutes);
					minutes = minutes % 60;
					long days = TimeUnit.HOURS.toDays(hours);
					hours = hours % 24;
					
					mTvTimeDaysHours.setText(String.valueOf(days)
							.concat(" days ")
							.concat(String.valueOf(hours).concat(" hours")));
					mTvTimeMinutesSeconds
							.setText(String
									.valueOf(minutes)
									.concat(" mins ")
									.concat(String.valueOf(seconds).concat(
											" seconds")));
				}

				@Override
				public void onFinish() {

				}
			};
			timer.start();
		}else{
			//This case is when the WC is started
			//Then we display the today's matches
			List<GameInfo> todayMatches = Utils.matchesToday(mApplication.getGameSchedule());
			if(todayMatches != null && todayMatches.size() > 0){
				//Todo: TienVV make it display beautiful :))
				String msg = "Today has " + todayMatches.size() + " matches!";
				mTvTimeDaysHours.setText(msg);
			}else{
				String abc = "Today has no match!";
				mTvTimeDaysHours.setText(abc);
			}
		}
	}

	/**
	 * Initiate data on screen
	 */
	private void initData() {
		mContext = getApplicationContext();
		mApplication = (WCApplication) getApplication();
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_home_group:
			Intent intentGroup = new Intent(mContext, GroupActivity.class);
			startActivity(intentGroup);
			break;
		case R.id.btn_home_schedule:
			Intent intentSchedule = new Intent(mContext, ScheduleActivity.class);
			startActivity(intentSchedule);
			break;
		case R.id.btn_home_team:
			if (mApplication.getTeamFavorite() == null) {
				Intent intentSelectFavoriteTeam = new Intent(mContext, TeamFavoriteSelectActivity.class);
				startActivityForResult(intentSelectFavoriteTeam, CODE_SELECT_TEAM);
			} else {
				Intent i = new Intent(getApplicationContext(), TeamDetailActivity.class);
				i.putExtra(TeamDetailActivity.KEY_TEAM_CODE, mApplication.getTeamFavorite().getCode());
				startActivity(i);
			}
			break;
		case R.id.btn_home_facebook:
			// TODO:
			break;
		case R.id.btn_home_setting:
			// TODO:
			Intent intent = new Intent("lod.entertainment.wc.TEST_ACTION");
			Log.d("DungHV","send broad cast");
			sendBroadcast(intent);
			break;
		case R.id.btn_home_info:
			// TODO:
			break;

		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			if (requestCode == CODE_SELECT_TEAM) {
				String teamCode = data.getStringExtra(TeamFavoriteSelectActivity.KEY_TEAM_CODE);
				mApplication.setTeamFavorite(teamCode);
				TeamInfo team = mApplication.getTeamByCode(teamCode);
				mImgTeamLogo.setImageResource(team.getFlag());
				mTvTeamFavorite.setText(team.getName());
				mTvTeamFavorite.setTextSize(30);
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mDatabase.close();
		Log.d("TienVV", "destroy");
	}
}

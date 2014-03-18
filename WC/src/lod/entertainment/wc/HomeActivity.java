package lod.entertainment.wc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import lod.entertainment.wc.adapter.AdapterListScheduleLite;
import lod.entertainment.wc.data.DatabaseWC;
import lod.entertainment.wc.entity.GameInfo;
import lod.entertainment.wc.entity.TeamInfo;
import lod.entertainment.wc.utils.Utils;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.MenuCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
//
//import com.facebook.UiLifecycleHelper;
//import com.facebook.widget.FacebookDialog.ShareDialogBuilder;

public class HomeActivity extends ActionBarActivity implements OnClickListener {

	private Context mContext;
	private DatabaseWC mDatabase;
	private WCApplication mApplication;
	private ShareActionProvider actionProvider;

	private RelativeLayout mFrmNextMatch;
	private ListView mLvNextMatch;
	private AdapterListScheduleLite mAdapterNextMatch;

	private RelativeLayout mFrmCountdown;
	private TextView mTvCountdownDayTitle;
	private TextView mTvCountdownHourTitle;
	private TextView mTvCountdownMinuteTitle;
	private TextView mTvCountdownSecondTitle;
	private TextView mTvCountdownDay;
	private TextView mTvCountdownHour;
	private TextView mTvCountdownMinute;
	private TextView mTvCountdownSecond;

	private Button mBtnGroup;
	private Button mBtnSchedule;
	private Button mBtnTeam;
	private ImageView mImgTeamLogo;
	private TextView mTvTeamFavorite;

	private static final int CODE_SELECT_TEAM = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_home);
		getSupportActionBar().setBackgroundDrawable(
				getResources().getDrawable(R.drawable.bg_actionbar));
		getSupportActionBar().setDisplayShowHomeEnabled(false);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		// getSupportActionBar().setBackgroundDrawable();
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
		mFrmNextMatch = (RelativeLayout) findViewById(R.id.frm_list_match);
		mLvNextMatch = (ListView) findViewById(R.id.lv_home_match);

		mFrmCountdown = (RelativeLayout) findViewById(R.id.frm_countdown);
		mTvCountdownDayTitle = (TextView) findViewById(R.id.tv_countdown_day_title);
		mTvCountdownDay = (TextView) findViewById(R.id.tv_countdown_day);
		mTvCountdownHourTitle = (TextView) findViewById(R.id.tv_countdown_hour_title);
		mTvCountdownHour = (TextView) findViewById(R.id.tv_countdown_hour);
		mTvCountdownMinuteTitle = (TextView) findViewById(R.id.tv_countdown_minutes_title);
		mTvCountdownMinute = (TextView) findViewById(R.id.tv_countdown_minute);
		mTvCountdownSecondTitle = (TextView) findViewById(R.id.tv_countdown_second_title);
		mTvCountdownSecond = (TextView) findViewById(R.id.tv_countdown_second);

		mBtnGroup = (Button) findViewById(R.id.btn_home_group);
		mBtnGroup.setOnClickListener(this);
		mBtnSchedule = (Button) findViewById(R.id.btn_home_fixture);
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
			mTvTeamFavorite.setText(teamFavorite.getName().toUpperCase());
			mTvTeamFavorite.setTextSize(20);
			mTvTeamFavorite.setTextColor(Color.WHITE);
			mImgTeamLogo.setImageResource(teamFavorite.getFlag());
		}
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

		final Calendar start = Calendar.getInstance(TimeZone.getDefault());

		long timeCurrent = start.getTimeInMillis();
		long timeEnd = destination.getTimeInMillis();
		if (timeEnd > timeCurrent) {
			mFrmNextMatch.setVisibility(View.GONE);
			CountDownTimer timer = new CountDownTimer(timeEnd - timeCurrent,
					1000) {

				@Override
				public void onTick(long millisUntilFinished) {
					long seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished);
					long minutes = seconds / 60;
					seconds = seconds % 60;
					long hours = minutes / 60;
					minutes = minutes % 60;
					long days = hours / 24;
					hours = hours % 24;

					if (days < 10) {
						mTvCountdownDayTitle.setText(R.string.count_down_day);
					} else {
						mTvCountdownDayTitle.setText(R.string.count_down_days);
					}
					if (hours < 10) {
						mTvCountdownHourTitle.setText(R.string.count_down_hour);
					} else {
						mTvCountdownHourTitle
								.setText(R.string.count_down_hours);
					}
					if (minutes < 10) {
						mTvCountdownMinuteTitle
								.setText(R.string.count_down_minute);
					} else {
						mTvCountdownMinuteTitle
								.setText(R.string.count_down_minutes);
					}
					if (seconds < 10) {
						mTvCountdownSecondTitle
								.setText(R.string.count_down_second);
					} else {
						mTvCountdownSecondTitle
								.setText(R.string.count_down_seconds);
					}

					Log.d("TienVV", "day: " + Utils.standardNumber(days));
					Log.d("TienVV", "hour: " + Utils.standardNumber(hours));
					Log.d("TienVV", "minute: " + Utils.standardNumber(minutes));
					Log.d("TienVV", "second: " + Utils.standardNumber(seconds));

					mTvCountdownDay.setText(Utils.standardNumber(days));
					mTvCountdownHour.setText(Utils.standardNumber(hours));
					mTvCountdownMinute.setText(Utils.standardNumber(minutes));
					mTvCountdownSecond.setText(Utils.standardNumber(seconds));
				}

				@Override
				public void onFinish() {

				}
			};
			timer.start();
		} else {
			mFrmCountdown.setVisibility(View.GONE);
			// This case is when the WC is started
			// Then we display the today's matches
			List<GameInfo> todayMatches = Utils.matchesToday(mApplication
					.getGameSchedule());
			if (todayMatches != null && todayMatches.size() > 0) {
				// Todo: TienVV make it display beautiful :))
				// String msg = "Today has " + todayMatches.size() +
				// " matches!";
				// mTvTimeDaysHours.setText(msg);
				mAdapterNextMatch = new AdapterListScheduleLite(this,
						todayMatches);
				mLvNextMatch.setAdapter(mAdapterNextMatch);
			}else{
				mLvNextMatch.setVisibility(View.GONE);
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
		case R.id.btn_home_fixture:
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
		// case R.id.btn_home_facebook:
		// Toast.makeText(mContext, "dace", Toast.LENGTH_SHORT).show();
		// UiLifecycleHelper ui = new UiLifecycleHelper(this, null);
		// ShareDialogBuilder builder = new ShareDialogBuilder(this);
		// builder.setLink("https://play.google.com/store/apps/details?id=lod.game.goldmine");
		// ui.trackPendingDialogCall(builder.build().present());
		// break;
		// case R.id.btn_home_setting:
		// // TODO:
		// Intent intent = new Intent("lod.entertainment.wc.TEST_ACTION");
		// Log.d("DungHV","send broad cast");
		// sendBroadcast(intent);
		// break;
		// case R.id.btn_home_info:
		// // TODO:
		// break;

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
				mTvTeamFavorite.setText(team.getName().toUpperCase());
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);

		MenuItem item = menu.findItem(R.id.action_share);
		actionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=lod.game.goldmine");
		actionProvider.setShareIntent(intent);
		return true;
	}
}

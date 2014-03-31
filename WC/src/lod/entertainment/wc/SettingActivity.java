package lod.entertainment.wc;

import lod.entertainment.wc.entity.TeamInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingActivity extends ActionBarActivity implements
		OnClickListener {

	private WCApplication mApplication;
	private Context mContext;
	private ActionBar mActionbar;
	
	private Button mBtnCancel;
	private Button mBtnSave;
	private ImageView mImgFlag;
	private TextView mTvTeam;
	private Button mBtnChange;
	
	private static final int CODE_SELECT_TEAM = 1;
	
	private TeamInfo mTeamFavorite;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		// Initiate context
		initContext();
		// Initiate layout
		initLayout();
	}
	
	private void initContext() {
		mContext = getApplicationContext();
		mApplication = (WCApplication) getApplication();
		mTeamFavorite = mApplication.getTeamFavorite();
	}

	private void initLayout() {
		mActionbar = getSupportActionBar();
		mActionbar.setTitle(R.string.title_setting);
		mActionbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_actionbar));
		mActionbar.setHomeButtonEnabled(true);
		mActionbar.setIcon(R.drawable.ic_action_back);

		mBtnCancel = (Button) findViewById(R.id.btn_setting_cancel);
		mBtnCancel.setOnClickListener(this);
		mBtnSave = (Button) findViewById(R.id.btn_setting_save);
		mBtnSave.setOnClickListener(this);

		mImgFlag = (ImageView) findViewById(R.id.img_favorite_team);
		mTvTeam = (TextView) findViewById(R.id.tv_setting_favorite_team);
		mBtnChange = (Button) findViewById(R.id.btn_setting_favorite_team_change);
		mBtnChange.setOnClickListener(this);
		
		if (mTeamFavorite != null) {
			mImgFlag.setImageResource(mTeamFavorite.getFlag());
			mTvTeam.setText(mTeamFavorite.getName().toUpperCase());
		} else {
			mImgFlag.setImageResource(R.drawable.icon_flag_no);
			mTvTeam.setText(R.string.btn_select_team_favorite);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			if (requestCode == CODE_SELECT_TEAM) {
				String teamCode = data.getStringExtra(TeamFavoriteSelectActivity.KEY_TEAM_CODE);
				mTeamFavorite = mApplication.getTeamByCode(teamCode);
				mImgFlag.setImageResource(mTeamFavorite.getFlag());
				mTvTeam.setText(mTeamFavorite.getName().toUpperCase());
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_setting_favorite_team_change:
			Intent intentSelectFavoriteTeam = new Intent(mContext, TeamFavoriteSelectActivity.class);
			startActivityForResult(intentSelectFavoriteTeam, CODE_SELECT_TEAM);
			break;
		case R.id.btn_setting_cancel:
			this.finish();
			break;
		case R.id.btn_setting_save:
			// Save favorite team
			mApplication.setTeamFavorite(mTeamFavorite.getCode());
			this.finish();
			break;

		default:
			break;
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			this.finish();
			break;

		default:
			break;
		}
		return true;
	}
}

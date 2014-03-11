package lod.entertainment.wc;

import java.util.ArrayList;
import java.util.List;

import lod.entertainment.wc.adapter.AdapterListTeamFavoriteSelection;
import lod.entertainment.wc.entity.TeamInfo;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class TeamFavoriteSelectActivity extends ActionBarActivity implements
		OnClickListener {

	private WCApplication mApplication;
	private ActionBar mActionbar;
	private ListView mLvListTeam;
	private AdapterListTeamFavoriteSelection mAdapterListTeam;
	private List<TeamInfo> mListTeamInfo;
	private Button mBtnDone;
	private Button mBtnCancel;
	
	public static final String KEY_TEAM_CODE = "key_team_code";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team_favorite_selection);
		mActionbar = getSupportActionBar();
		mActionbar.setTitle(R.string.title_team_selection);
		mApplication = (WCApplication) getApplication();
		// Initiate list team
		initListTeam();
		// Init button
		mBtnDone = (Button) findViewById(R.id.btn_team_favorite_done);
		mBtnDone.setOnClickListener(this);
		mBtnCancel = (Button) findViewById(R.id.btn_team_favorite_cancel);
		mBtnCancel.setOnClickListener(this);
	}

	private void initListTeam() {
		// Initiate list team
		mListTeamInfo = new ArrayList<TeamInfo>();
		// Group A
		mListTeamInfo.add(mApplication.getTeamByCode(TeamInfo.CODE_BRA));
		mListTeamInfo.add(mApplication.getTeamByCode(TeamInfo.CODE_CRO));
		mListTeamInfo.add(mApplication.getTeamByCode(TeamInfo.CODE_MEX));
		mListTeamInfo.add(mApplication.getTeamByCode(TeamInfo.CODE_CMR));
		// Group B
		mListTeamInfo.add(mApplication.getTeamByCode(TeamInfo.CODE_ESP));
		mListTeamInfo.add(mApplication.getTeamByCode(TeamInfo.CODE_NED));
		mListTeamInfo.add(mApplication.getTeamByCode(TeamInfo.CODE_CHI));
		mListTeamInfo.add(mApplication.getTeamByCode(TeamInfo.CODE_AUS));
		// Group C
		mListTeamInfo.add(mApplication.getTeamByCode(TeamInfo.CODE_COL));
		mListTeamInfo.add(mApplication.getTeamByCode(TeamInfo.CODE_GRE));
		mListTeamInfo.add(mApplication.getTeamByCode(TeamInfo.CODE_CIV));
		mListTeamInfo.add(mApplication.getTeamByCode(TeamInfo.CODE_JPN));
		// Group D
		mListTeamInfo.add(mApplication.getTeamByCode(TeamInfo.CODE_URU));
		mListTeamInfo.add(mApplication.getTeamByCode(TeamInfo.CODE_CRC));
		mListTeamInfo.add(mApplication.getTeamByCode(TeamInfo.CODE_ENG));
		mListTeamInfo.add(mApplication.getTeamByCode(TeamInfo.CODE_ITA));
		// Group E
		mListTeamInfo.add(mApplication.getTeamByCode(TeamInfo.CODE_SUI));
		mListTeamInfo.add(mApplication.getTeamByCode(TeamInfo.CODE_ECU));
		mListTeamInfo.add(mApplication.getTeamByCode(TeamInfo.CODE_FRA));
		mListTeamInfo.add(mApplication.getTeamByCode(TeamInfo.CODE_HON));
		// Group F
		mListTeamInfo.add(mApplication.getTeamByCode(TeamInfo.CODE_ARG));
		mListTeamInfo.add(mApplication.getTeamByCode(TeamInfo.CODE_BIH));
		mListTeamInfo.add(mApplication.getTeamByCode(TeamInfo.CODE_IRN));
		mListTeamInfo.add(mApplication.getTeamByCode(TeamInfo.CODE_NGA));
		// Group G
		mListTeamInfo.add(mApplication.getTeamByCode(TeamInfo.CODE_GER));
		mListTeamInfo.add(mApplication.getTeamByCode(TeamInfo.CODE_POR));
		mListTeamInfo.add(mApplication.getTeamByCode(TeamInfo.CODE_GHA));
		mListTeamInfo.add(mApplication.getTeamByCode(TeamInfo.CODE_USA));
		// Group A
		mListTeamInfo.add(mApplication.getTeamByCode(TeamInfo.CODE_BEL));
		mListTeamInfo.add(mApplication.getTeamByCode(TeamInfo.CODE_ALG));
		mListTeamInfo.add(mApplication.getTeamByCode(TeamInfo.CODE_RUS));
		mListTeamInfo.add(mApplication.getTeamByCode(TeamInfo.CODE_KOR));
		
		mLvListTeam = (ListView) findViewById(R.id.lv_team_favorite);
		mAdapterListTeam = new AdapterListTeamFavoriteSelection(this,
				mListTeamInfo);
		mLvListTeam.setAdapter(mAdapterListTeam);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_team_favorite_done:
			Intent data = new Intent();
			data.putExtra(KEY_TEAM_CODE, mAdapterListTeam.getCheckTeamCode());
			setResult(RESULT_OK, data);
			finish();
			break;
		case R.id.btn_team_favorite_cancel:
			setResult(RESULT_CANCELED);
			finish();
			break;

		default:
			break;
		}
	}

}

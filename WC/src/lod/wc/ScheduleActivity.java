package lod.wc;

import java.util.ArrayList;
import java.util.List;

import lod.wc.entity.GameInfo;
import lod.wc.entity.TeamInfo;
import lod.wc.fragment.FragmentScheduleList;
import lod.wc.fragment.FragmentScheduleMenu;
import lod.wc.fragment.FragmentScheduleMenu.OnMenuClickListener;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

public class ScheduleActivity extends ActionBarActivity implements
		OnMenuClickListener {

	private WCApplication mApplication;
	private ActionBar mActionbar;
	
	private List<GameInfo> mListMatch;
//	private FrameLayout mFrmMain;
	private FragmentManager mFragmentManager;
	// Fragments
	private FragmentScheduleMenu mFragmScheduleMenu;
	private FragmentScheduleList mFragmScheduleList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule);
		mActionbar = getSupportActionBar();
		mActionbar.setBackgroundDrawable(getResources().getDrawable(R.drawable.bg_actionbar));
		mActionbar.setHomeButtonEnabled(true);
		mActionbar.setIcon(R.drawable.ic_action_back);
		mActionbar.setTitle(R.string.title_fixtures);
		// Initiate context
		initContext();
		// Initiate layout
		initLayout();
	}

	private void initContext() {
		mApplication = (WCApplication) getApplication();
		mFragmentManager = getSupportFragmentManager();
		mFragmScheduleMenu = new FragmentScheduleMenu();
		mFragmScheduleMenu.setOnMenuClickListener(this);
		
		mFragmentManager.beginTransaction().replace(R.id.frm_schedule_main, mFragmScheduleMenu).commit();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		mApplication.updateGameResult();
	}

	private void initLayout() {
//		mFrmMain = (FrameLayout) findViewById(R.id.frm_schedule_main);
	}

	@Override
	public void onMenuClick(int buttonId) {
		switch (buttonId) {
		case R.id.btn_schedule_match_team:
			TeamInfo teamFavorite = mApplication.getTeamFavorite();
			mListMatch = mApplication.getGameScheduleOfTeam(teamFavorite.getCode());
			mFragmScheduleList = FragmentScheduleList.getInstance(mListMatch, false, true);
			mFragmScheduleList.setTitle(mActionbar, teamFavorite.getName() + "'s " + getString(R.string.btn_match_team));
			break;
		case R.id.btn_schedule_match_group:
			List<GameInfo> gameGroupA = mApplication.getGameScheduleOfGroup(TeamInfo.GROUP_A);
			List<GameInfo> gameGroupB = mApplication.getGameScheduleOfGroup(TeamInfo.GROUP_B);
			List<GameInfo> gameGroupC = mApplication.getGameScheduleOfGroup(TeamInfo.GROUP_C);
			List<GameInfo> gameGroupD = mApplication.getGameScheduleOfGroup(TeamInfo.GROUP_D);
			List<GameInfo> gameGroupE = mApplication.getGameScheduleOfGroup(TeamInfo.GROUP_E);
			List<GameInfo> gameGroupF = mApplication.getGameScheduleOfGroup(TeamInfo.GROUP_F);
			List<GameInfo> gameGroupG = mApplication.getGameScheduleOfGroup(TeamInfo.GROUP_G);
			List<GameInfo> gameGroupH = mApplication.getGameScheduleOfGroup(TeamInfo.GROUP_H);
			if (mListMatch == null) {
				mListMatch = new ArrayList<GameInfo>();
			} else {
				mListMatch.clear();
			}
			mListMatch.addAll(gameGroupA);
			mListMatch.addAll(gameGroupB);
			mListMatch.addAll(gameGroupC);
			mListMatch.addAll(gameGroupD);
			mListMatch.addAll(gameGroupE);
			mListMatch.addAll(gameGroupF);
			mListMatch.addAll(gameGroupG);
			mListMatch.addAll(gameGroupH);
			
			mFragmScheduleList = FragmentScheduleList.getInstance(mListMatch, true, false);
			mFragmScheduleList.setTitle(mActionbar, getString(R.string.btn_match_group));
			break;
		case R.id.btn_schedule_match_round_16:
			mListMatch = mApplication.getGameScheduleInRange(49, 56);
			mFragmScheduleList = FragmentScheduleList.getInstance(mListMatch);
			mFragmScheduleList.setTitle(mActionbar, getString(R.string.btn_match_round_16));
			break;
		case R.id.btn_schedule_match_quarter_final:
			mListMatch = mApplication.getGameScheduleInRange(57, 60);
			mFragmScheduleList = FragmentScheduleList.getInstance(mListMatch);
			mFragmScheduleList.setTitle(mActionbar, getString(R.string.btn_match_quarter_final));
			break;
		case R.id.btn_schedule_match_semi_final:
			mListMatch = mApplication.getGameScheduleInRange(61, 62);
			mFragmScheduleList = FragmentScheduleList.getInstance(mListMatch);
			mFragmScheduleList.setTitle(mActionbar, getString(R.string.btn_match_semi_final));
			break;
		case R.id.btn_schedule_match_third_place:
			mListMatch = mApplication.getGameScheduleInRange(63, 63);
			mFragmScheduleList = FragmentScheduleList.getInstance(mListMatch);
			mFragmScheduleList.setTitle(mActionbar, getString(R.string.btn_match_third));
			break;
		case R.id.btn_schedule_match_final:
			mListMatch = mApplication.getGameScheduleInRange(64, 64);
			mFragmScheduleList = FragmentScheduleList.getInstance(mListMatch);
			mFragmScheduleList.setTitle(mActionbar, getString(R.string.btn_match_final));
			break;

		default:
			break;
		}
		mFragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in, R.anim.slide_out,R.anim.slide_in_back, R.anim.slide_out_back)
			.addToBackStack(null).replace(R.id.frm_schedule_main, mFragmScheduleList).commit();
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			if (mFragmentManager.getBackStackEntryCount() > 0) {
				mFragmentManager.popBackStack();
				mActionbar.setTitle(R.string.title_fixtures);
			} else {
				finish();
			}
			break;

		default:
			break;
		}
		return true;
	}
	
	@Override
	public void onBackPressed() {
		if (mFragmentManager.getBackStackEntryCount() > 0) {
			mFragmentManager.popBackStack();
			mActionbar.setTitle(R.string.title_fixtures);
		} else {
			finish();
		}
	}
}

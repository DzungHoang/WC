package lod.entertainment.wc;

import java.util.ArrayList;
import java.util.List;

import lod.entertainment.wc.entity.GameInfo;
import lod.entertainment.wc.entity.TeamInfo;
import lod.entertainment.wc.fragment.FragmentScheduleList;
import lod.entertainment.wc.fragment.FragmentScheduleMenu;
import lod.entertainment.wc.fragment.FragmentScheduleMenu.OnMenuClickListener;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.FrameLayout;

public class ScheduleActivity extends FragmentActivity implements
		OnMenuClickListener {

	private WCApplication mApplication;
	
	private List<GameInfo> mListMatch;
	private FrameLayout mFrmMain;
	private FragmentManager mFragmentManager;
	// Fragments
	private FragmentScheduleMenu mFragmScheduleMenu;
	private FragmentScheduleList mFragmScheduleList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_schedule);
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

	private void initLayout() {
		mFrmMain = (FrameLayout) findViewById(R.id.frm_schedule_main);
	}

	@Override
	public void onMenuClick(int buttonId) {
		switch (buttonId) {
		case R.id.btn_schedule_match_team:
			TeamInfo teamFavorite = mApplication.getTeamFavorite();
			mListMatch = mApplication.getGameScheduleOfTeam(teamFavorite.getCode());
			mFragmScheduleList = FragmentScheduleList.getInstance(mListMatch, false, true);
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
			break;
		case R.id.btn_schedule_match_round_16:
			mListMatch = mApplication.getGameScheduleInRange(49, 56);
			mFragmScheduleList = FragmentScheduleList.getInstance(mListMatch);
			break;
		case R.id.btn_schedule_match_quarter_final:
			mListMatch = mApplication.getGameScheduleInRange(57, 60);
			mFragmScheduleList = FragmentScheduleList.getInstance(mListMatch);
			break;
		case R.id.btn_schedule_match_semi_final:
			mListMatch = mApplication.getGameScheduleInRange(61, 62);
			mFragmScheduleList = FragmentScheduleList.getInstance(mListMatch);
			break;
		case R.id.btn_schedule_match_third_place:
			mListMatch = mApplication.getGameScheduleInRange(63, 63);
			mFragmScheduleList = FragmentScheduleList.getInstance(mListMatch);
			break;
		case R.id.btn_schedule_match_final:
			mListMatch = mApplication.getGameScheduleInRange(64, 64);
			mFragmScheduleList = FragmentScheduleList.getInstance(mListMatch);
			break;

		default:
			break;
		}
		mFragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in, R.anim.slide_out,R.anim.slide_in_back, R.anim.slide_out_back)
			.addToBackStack(null).replace(R.id.frm_schedule_main, mFragmScheduleList).commit();
	}
	
	@Override
	public void onBackPressed() {
		if (mFragmentManager.getBackStackEntryCount() > 0) {
			mFragmentManager.popBackStack();
		} else {
			finish();
		}
	}
}

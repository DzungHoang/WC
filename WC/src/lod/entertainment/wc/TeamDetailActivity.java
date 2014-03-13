package lod.entertainment.wc;

import java.util.List;
import java.util.Locale;

import lod.entertainment.wc.entity.GameInfo;
import lod.entertainment.wc.entity.TeamInfo;
import lod.entertainment.wc.fragment.FragmentScheduleList;
import lod.entertainment.wc.fragment.FragmentTeamDetailProfile;
import lod.entertainment.wc.fragment.FragmentTeamDetailStanding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

public class TeamDetailActivity extends FragmentActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	private ViewPager mViewPager;
	/**
	 * Tab index
	 * */
	public final static int TAB_PROFILE = 0;
	public final static int TAB_SCHEDULE = 1;
	public final static int TAB_STANDING = 2;
	
	private WCApplication mApplication;
	public static final String KEY_TEAM_CODE = "team_code";

	private ImageView mImgTeamLogo;
	private TextView mTvTeamName;
	
	private String mTeamCode;
	private FragmentTeamDetailStanding mFragTeamStanding;
	private FragmentTeamDetailProfile mFragTeamProfile;
	private FragmentScheduleList mFragTeamSchedule;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team_details);
		mApplication = (WCApplication) getApplication();
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager_details);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		
		mImgTeamLogo = (ImageView) findViewById(R.id.img_details_team_logo);
		mTvTeamName = (TextView) findViewById(R.id.tv_details_team_title);

		mTeamCode = getIntent().getStringExtra(KEY_TEAM_CODE);
		TeamInfo team = mApplication.getTeamByCode(mTeamCode);
		
		mImgTeamLogo.setImageResource(getLogoId(mTeamCode));
		mTvTeamName.setText(team.getName().toUpperCase());
		
		
		List<GameInfo> listGame = mApplication.getGameScheduleOfTeam(mTeamCode);
		mFragTeamProfile = new FragmentTeamDetailProfile();
		mFragTeamSchedule = FragmentScheduleList.getInstance(listGame, false, true);
		mFragTeamStanding = FragmentTeamDetailStanding.getInstance(team);
		
		mViewPager.setCurrentItem(1);
	}

	private int getLogoId(String code) {
		int id = R.drawable.logo_bra;
		if (code.equals(TeamInfo.CODE_ALG)) {
			id = R.drawable.logo_alg;
		} else if (code.equals(TeamInfo.CODE_ARG)){
			id = R.drawable.logo_arg;
		} else if (code.equals(TeamInfo.CODE_AUS)){
			id = R.drawable.logo_aus;
		} else if (code.equals(TeamInfo.CODE_BEL)){
			id = R.drawable.logo_bel;
		} else if (code.equals(TeamInfo.CODE_BIH)){
			id = R.drawable.logo_bih;
		} else if (code.equals(TeamInfo.CODE_BRA)){
			id = R.drawable.logo_bra;
		} else if (code.equals(TeamInfo.CODE_CHI)){
			id = R.drawable.logo_chi;
		} else if (code.equals(TeamInfo.CODE_CIV)){
			id = R.drawable.logo_civ;
		} else if (code.equals(TeamInfo.CODE_CMR)){
			id = R.drawable.logo_cmr;
		} else if (code.equals(TeamInfo.CODE_COL)){
			id = R.drawable.logo_col;
		} else if (code.equals(TeamInfo.CODE_CRC)){
			id = R.drawable.logo_crc;
		} else if (code.equals(TeamInfo.CODE_CRO)){
			id = R.drawable.logo_cro;
		} else if (code.equals(TeamInfo.CODE_ECU)){
			id = R.drawable.logo_ecu;
		} else if (code.equals(TeamInfo.CODE_ENG)){
			id = R.drawable.logo_eng;
		} else if (code.equals(TeamInfo.CODE_ESP)){
			id = R.drawable.logo_esp;
		} else if (code.equals(TeamInfo.CODE_FRA)){
			id = R.drawable.logo_fra;
		} else if (code.equals(TeamInfo.CODE_GER)){
			id = R.drawable.logo_ger;
		} else if (code.equals(TeamInfo.CODE_GHA)){
			id = R.drawable.logo_gha;
		} else if (code.equals(TeamInfo.CODE_GRE)){
			id = R.drawable.logo_gre;
		} else if (code.equals(TeamInfo.CODE_HON)){
			id = R.drawable.logo_hon;
		} else if (code.equals(TeamInfo.CODE_IRN)){
			id = R.drawable.logo_irn;
		} else if (code.equals(TeamInfo.CODE_ITA)){
			id = R.drawable.logo_ita;
		} else if (code.equals(TeamInfo.CODE_JPN)){
			id = R.drawable.logo_jpn;
		} else if (code.equals(TeamInfo.CODE_KOR)){
			id = R.drawable.logo_kor;
		} else if (code.equals(TeamInfo.CODE_MEX)){
			id = R.drawable.logo_mex;
		} else if (code.equals(TeamInfo.CODE_NED)){
			id = R.drawable.logo_ned;
		} else if (code.equals(TeamInfo.CODE_NGA)){
			id = R.drawable.logo_nga;
		} else if (code.equals(TeamInfo.CODE_POR)){
			id = R.drawable.logo_por;
		} else if (code.equals(TeamInfo.CODE_RUS)){
			id = R.drawable.logo_rus;
		} else if (code.equals(TeamInfo.CODE_SUI)){
			id = R.drawable.logo_sui;
		} else if (code.equals(TeamInfo.CODE_URU)){
			id = R.drawable.logo_uru;
		} else if (code.equals(TeamInfo.CODE_USA)){
			id = R.drawable.logo_usa;
		}
		return id;
	}
	
	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		private Fragment fragment;

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			switch (position) {
			case TAB_SCHEDULE:
				fragment = mFragTeamSchedule;
				break;
			case TAB_PROFILE:
				fragment = mFragTeamProfile;
				break;
			case TAB_STANDING:
				fragment = mFragTeamStanding;
				break;
			default:
				fragment = mFragTeamProfile;
				break;
			}
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_fragment_profile).toUpperCase(l);
			case 1:
				return getString(R.string.title_fragment_schedule).toUpperCase(l);
			case 2:
				return getString(R.string.title_fragment_standing).toUpperCase(l);
			}
			return null;
		}
	}
}

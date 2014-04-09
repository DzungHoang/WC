package lod.wc.fragment;

import java.util.List;
import java.util.TimeZone;

import lod.wc.R;
import lod.wc.adapter.AdapterListSchedule;
import lod.wc.entity.GameInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentScheduleList extends Fragment{
	
	private static FragmentScheduleList INSTANCE = null;
	
	private View mView;
	
	private ListView mLvListMatch;
	private TextView mTvTimeZone;
	private AdapterListSchedule mAdapterSchedule;
	private static List<GameInfo> mListSchedule;
	private static boolean isGrouped;
	private static boolean isAteam;
	private ActionBar mActionbar;
	private String mTitle;
	
	public static FragmentScheduleList getInstance(List<GameInfo> listGame, boolean grouped, boolean ateam) {
		INSTANCE = new FragmentScheduleList();
		mListSchedule = listGame;
		isGrouped = grouped;
		isAteam = ateam;
		return INSTANCE;
	}
	
	public static FragmentScheduleList getInstance(List<GameInfo> listGame) {
		if (INSTANCE == null) {
			INSTANCE = new FragmentScheduleList();
		}
		mListSchedule = listGame;
		isGrouped = false;
		isAteam = false;
		return INSTANCE;
	}
	
	public void setListSchedule(List<GameInfo> listSchedule) {
		mListSchedule = listSchedule;
	}
	
	public void setGrouped(boolean grouped) {
		isGrouped = grouped;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_schedule_list, container, false);
		if (mActionbar != null) {
			mActionbar.setTitle(mTitle);
		}
		// Initiate layout
		initLayout();
		return mView;
	}
	
	private void initLayout() {
		mLvListMatch = (ListView) mView.findViewById(R.id.lv_schedule_list_list_match);
		mAdapterSchedule = new AdapterListSchedule(getActivity(), mListSchedule, isGrouped);
		mAdapterSchedule.setIsATeam(isAteam);
		mLvListMatch.setAdapter(mAdapterSchedule);
		
		mTvTimeZone = (TextView) mView.findViewById(R.id.tv_schedule_list_time_zone);
		mTvTimeZone.setText(getString(R.string.note_time_zone_schedule).concat(" (").concat(TimeZone.getDefault().getID().concat(")")));
	}
	
	public void setTitle(ActionBar actionbar, String title) {
		mActionbar = actionbar;
		mTitle = title;
	}
}

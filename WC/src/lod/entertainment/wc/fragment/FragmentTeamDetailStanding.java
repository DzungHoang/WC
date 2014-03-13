package lod.entertainment.wc.fragment;

import java.util.List;

import lod.entertainment.wc.R;
import lod.entertainment.wc.adapter.AdapterListTeamStanding;
import lod.entertainment.wc.data.DatabaseWC;
import lod.entertainment.wc.entity.TeamInfo;
import lod.entertainment.wc.entity.TeamStanding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FragmentTeamDetailStanding extends Fragment {

	private static FragmentTeamDetailStanding INSTANCE = null;

	public static FragmentTeamDetailStanding getInstance(TeamInfo team) {
		if (INSTANCE == null) {
			INSTANCE = new FragmentTeamDetailStanding();
		}
		mTeam = team;
		return INSTANCE;
	}

	private static TeamInfo mTeam;
	private List<TeamStanding> mListTeam;
	private DatabaseWC mDatabase;
	
	private View mView;
	private ListView mLvStanding;
	private AdapterListTeamStanding mAdapterStanding;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_team_detail_standing,
				container, false);
		mLvStanding = (ListView) mView
				.findViewById(R.id.lv_team_detail_standing);
		if (mAdapterStanding != null) {
			mLvStanding.setAdapter(mAdapterStanding);
		}
		return mView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mDatabase = new DatabaseWC(getActivity());
		mListTeam = mDatabase.getInfoStandingOfGroup(mTeam.getGroup());
		mAdapterStanding = new AdapterListTeamStanding(getActivity(), mListTeam);
		mLvStanding.setAdapter(mAdapterStanding);
	}
}

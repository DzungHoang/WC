package lod.entertainment.wc.fragment;

import java.util.List;

import lod.entertainment.wc.R;
import lod.entertainment.wc.TeamDetailActivity;
import lod.entertainment.wc.adapter.AdapterListTeamStanding;
import lod.entertainment.wc.data.DatabaseWC;
import lod.entertainment.wc.entity.TeamInfo;
import lod.entertainment.wc.entity.TeamStanding;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FragmentTeamDetailStanding extends Fragment implements
		OnItemClickListener {

	private static FragmentTeamDetailStanding INSTANCE = null;

	public static FragmentTeamDetailStanding getInstance(TeamInfo team) {
		INSTANCE = new FragmentTeamDetailStanding();
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
		mLvStanding.setOnItemClickListener(this);
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

	@Override
	public void onItemClick(AdapterView<?> adapter, View v, int position,
			long id) {
		String codeTeam = mListTeam.get(position).getCode();
		Intent i = new Intent(getActivity().getApplicationContext(),
				TeamDetailActivity.class);
		i.putExtra(TeamDetailActivity.KEY_TEAM_CODE, codeTeam);
		startActivity(i);

		getActivity().finish();
	}
}

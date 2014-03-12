package lod.entertainment.wc;

import java.util.List;

import lod.entertainment.wc.adapter.AdapterListTeamStanding;
import lod.entertainment.wc.data.DatabaseWC;
import lod.entertainment.wc.entity.TeamStanding;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

public class GroupActivity extends ActionBarActivity{

	private DatabaseWC mDatabase;
	private ListView mLvListTeam;
	private AdapterListTeamStanding mAdapterTeamStading;
	private List<TeamStanding> mListTeam;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group);
		getActionBar().setTitle(R.string.title_group);
		mDatabase = new DatabaseWC(this);
		// Initiate layout
		initLayout();
	}
	
	private void initLayout() {
		mLvListTeam = (ListView) findViewById(R.id.lv_group_list_team);
		mListTeam = mDatabase.getAllInfoTeamStanding();
		mAdapterTeamStading = new AdapterListTeamStanding(this, mListTeam);
		mLvListTeam.setAdapter(mAdapterTeamStading);
	}
}

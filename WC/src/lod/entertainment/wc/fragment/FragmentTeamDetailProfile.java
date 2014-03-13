package lod.entertainment.wc.fragment;

import lod.entertainment.wc.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentTeamDetailProfile extends Fragment{

	private View mView;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_team_detail_profile, container, false);
		return mView;
	}

}

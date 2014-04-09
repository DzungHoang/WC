package lod.wc.fragment;

import lod.wc.R;
import lod.wc.WCApplication;
import lod.wc.entity.TeamInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentScheduleMenu extends Fragment implements OnClickListener {

	public interface OnMenuClickListener {
		void onMenuClick(int buttonId);
	}
	
	private View mView;
	private Button mBtnTeamMatch;
	private Button mBtnGroupStage;
	private Button mBtnRound16;
	private Button mBtnQuartersFinal;
	private Button mBtnSemiFinal;
	private Button mBtnThirdPlace;
	private Button mBtnFinal;

	private OnMenuClickListener mListener;
	
	public void setOnMenuClickListener(OnMenuClickListener listener) {
		mListener = listener;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_schedule_menu, container,
				false);
		// Initiate layout
		initLayout();
		return mView;
	}

	private void initLayout() {
		mBtnTeamMatch = (Button) mView
				.findViewById(R.id.btn_schedule_match_team);
		mBtnGroupStage = (Button) mView
				.findViewById(R.id.btn_schedule_match_group);
		mBtnRound16 = (Button) mView
				.findViewById(R.id.btn_schedule_match_round_16);
		mBtnQuartersFinal = (Button) mView
				.findViewById(R.id.btn_schedule_match_quarter_final);
		mBtnSemiFinal = (Button) mView
				.findViewById(R.id.btn_schedule_match_semi_final);
		mBtnThirdPlace = (Button) mView
				.findViewById(R.id.btn_schedule_match_third_place);
		mBtnFinal = (Button) mView.findViewById(R.id.btn_schedule_match_final);

		mBtnTeamMatch.setOnClickListener(this);
		mBtnGroupStage.setOnClickListener(this);
		mBtnRound16.setOnClickListener(this);
		mBtnQuartersFinal.setOnClickListener(this);
		mBtnSemiFinal.setOnClickListener(this);
		mBtnThirdPlace.setOnClickListener(this);
		mBtnFinal.setOnClickListener(this);

		WCApplication application = (WCApplication) getActivity()
				.getApplication();
		TeamInfo teamFavorite = application.getTeamFavorite();
		if (teamFavorite == null) {
			mBtnTeamMatch.setVisibility(View.GONE);
		} else {
			mBtnTeamMatch.setText(teamFavorite.getName().toUpperCase().concat("'S ")
					.concat(getString(R.string.btn_match_team)));
		}
	}

	@Override
	public void onClick(View v) {
		if (mListener == null) {
			return;
		}
		mListener.onMenuClick(v.getId());
	}
}

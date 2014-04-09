package lod.wc.fragment;

import lod.wc.R;
import lod.wc.TeamDetailActivity;
import lod.wc.entity.TeamInfo;
import lod.wc.utils.Utils;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentTeamDetailProfile extends Fragment{

	private View mView;
	private ImageView mImgThumbs;
	private TextView mTvThumbCredit;
	private TextView mTvDetails;
	
	private String mTeamCode;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		mTeamCode = getActivity().getIntent().getStringExtra(TeamDetailActivity.KEY_TEAM_CODE);
		
		mImgThumbs.setImageResource(TeamInfo.getThumbResource(mTeamCode));
		mTvDetails.setText(Html.fromHtml(Utils.loadJSONFromAsset(getActivity(), mTeamCode.toLowerCase().concat(".html"))));
		mTvThumbCredit.setText(getThumbScreditResource(mTeamCode));
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_team_detail_profile, container, false);
		mImgThumbs = (ImageView) mView.findViewById(R.id.img_profile_thumbs);
		mTvThumbCredit = (TextView) mView.findViewById(R.id.tv_profile_thumbs_credit);
		mTvDetails = (TextView) mView.findViewById(R.id.tv_profile_details);
		return mView;
	}
	
	
	private String getThumbScreditResource(String code){
		if (code.equals(TeamInfo.CODE_ALG)) {
			return getString(R.string.credit_afp);
		} else if (code.equals(TeamInfo.CODE_ARG)) {
			return getString(R.string.credit_afp);
		} else if (code.equals(TeamInfo.CODE_AUS)) {
			return getString(R.string.credit_getty_image);
		} else if (code.equals(TeamInfo.CODE_BEL)) {
			return getString(R.string.credit_getty_image);
		} else if (code.equals(TeamInfo.CODE_BIH)) {
			return getString(R.string.credit_afp);
		} else if (code.equals(TeamInfo.CODE_BRA)) {
			return getString(R.string.credit_getty_image);
		} else if (code.equals(TeamInfo.CODE_CHI)) {
			return getString(R.string.credit_getty_image);
		} else if (code.equals(TeamInfo.CODE_CIV)) {
			return getString(R.string.credit_afp);
		} else if (code.equals(TeamInfo.CODE_CMR)) {
			return getString(R.string.credit_afp);
		} else if (code.equals(TeamInfo.CODE_COL)) {
			return getString(R.string.credit_afp);
		} else if (code.equals(TeamInfo.CODE_CRC)) {
			return getString(R.string.credit_afp);
		} else if (code.equals(TeamInfo.CODE_CRO)) {
			return getString(R.string.credit_getty_image);
		} else if (code.equals(TeamInfo.CODE_ECU)) {
			return getString(R.string.credit_getty_image);
		} else if (code.equals(TeamInfo.CODE_ENG)) {
			return getString(R.string.credit_getty_image);
		} else if (code.equals(TeamInfo.CODE_ESP)) {
			return getString(R.string.credit_afp);
		} else if (code.equals(TeamInfo.CODE_FRA)) {
			return getString(R.string.credit_getty_image);
		} else if (code.equals(TeamInfo.CODE_GER)) {
			return getString(R.string.credit_getty_image);
		} else if (code.equals(TeamInfo.CODE_GHA)) {
			return getString(R.string.credit_getty_image);
		} else if (code.equals(TeamInfo.CODE_GRE)) {
			return getString(R.string.credit_afp);
		} else if (code.equals(TeamInfo.CODE_HON)) {
			return getString(R.string.credit_afp);
		} else if (code.equals(TeamInfo.CODE_IRN)) {
			return getString(R.string.credit_getty_image);
		} else if (code.equals(TeamInfo.CODE_ITA)) {
			return getString(R.string.credit_getty_image);
		} else if (code.equals(TeamInfo.CODE_JPN)) {
			return getString(R.string.credit_getty_image);
		} else if (code.equals(TeamInfo.CODE_KOR)) {
			return getString(R.string.credit_getty_image);
		} else if (code.equals(TeamInfo.CODE_MEX)) {
			return getString(R.string.credit_afp);
		} else if (code.equals(TeamInfo.CODE_NED)) {
			return getString(R.string.credit_getty_image);
		} else if (code.equals(TeamInfo.CODE_NGA)) {
			return getString(R.string.credit_getty_image);
		} else if (code.equals(TeamInfo.CODE_POR)) {
			return getString(R.string.credit_getty_image);
		} else if (code.equals(TeamInfo.CODE_RUS)) {
			return getString(R.string.credit_afp);
		} else if (code.equals(TeamInfo.CODE_SUI)) {
			return getString(R.string.credit_afp);
		} else if (code.equals(TeamInfo.CODE_URU)) {
			return getString(R.string.credit_getty_image);
		} else if (code.equals(TeamInfo.CODE_USA)) {
			return getString(R.string.credit_getty_image);
		} else {
			return getString(R.string.credit_afp);
		}
	}
}

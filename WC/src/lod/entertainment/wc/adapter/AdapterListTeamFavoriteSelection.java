package lod.entertainment.wc.adapter;

import java.util.List;

import lod.entertainment.wc.R;
import lod.entertainment.wc.entity.TeamInfo;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class AdapterListTeamFavoriteSelection extends BaseAdapter implements
		OnClickListener {

	private Activity mActivity;
	private List<TeamInfo> mListTeam;
//	private List<RadioButton> mListRadioButton;
//	private boolean userSelected = false;
	private int mPosChecked;
	private RadioButton mRbtnCurrent;

	public AdapterListTeamFavoriteSelection(Activity activity,
			List<TeamInfo> listTeam) {
		mActivity = activity;
		mListTeam = listTeam;
//		mListRadioButton = new ArrayList<RadioButton>();
	}

	@Override
	public int getCount() {
		return mListTeam.size();
	}

	@Override
	public Object getItem(int arg0) {
		return mListTeam.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView == null) {
			LayoutInflater inflater = mActivity.getLayoutInflater();
			convertView = inflater.inflate(R.layout.item_list_team_favorite,
					parent, false);
			viewHolder = new ViewHolder();
			viewHolder.tvGroupTitle = (TextView) convertView
					.findViewById(R.id.item_tv_team_favorite_group_title);
			viewHolder.rdBtnSelection = (RadioButton) convertView
					.findViewById(R.id.item_rdbtn_team_favorite);
			viewHolder.imgFlag = (ImageView) convertView
					.findViewById(R.id.item_img_team_favorite_flag);
			viewHolder.tvTeamTitle = (TextView) convertView
					.findViewById(R.id.item_tv_team_favorite_title);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		TeamInfo item = mListTeam.get(position);
		if (position % 4 == 0) {
			viewHolder.tvGroupTitle.setVisibility(View.VISIBLE);
			viewHolder.tvGroupTitle.setText(item.getGroup().replace('_', ' '));
		} else {
			viewHolder.tvGroupTitle.setVisibility(View.GONE);
		}
		viewHolder.rdBtnSelection.setOnClickListener(this);
		if (mPosChecked == position) {
			viewHolder.rdBtnSelection.setChecked(true);
			mRbtnCurrent = viewHolder.rdBtnSelection;
		} else {
			viewHolder.rdBtnSelection.setChecked(false);
		}
		viewHolder.rdBtnSelection.setId(position);
		viewHolder.imgFlag.setImageResource(item.getFlag());
		viewHolder.tvTeamTitle.setText(item.getName());
		return convertView;
	}

	/**
	 * Get checked team key
	 * 
	 * @return
	 */
	public String getCheckTeamCode() {
		return mListTeam.get(mPosChecked).getCode();
	}

	class ViewHolder {
		TextView tvGroupTitle;
		RadioButton rdBtnSelection;
		ImageView imgFlag;
		TextView tvTeamTitle;
	}

	@Override
	public void onClick(View v) {
//		userSelected = true;
		if (mRbtnCurrent == null) {
			mRbtnCurrent = (RadioButton) v;
			mRbtnCurrent.setChecked(true);
			mPosChecked = v.getId();
		}

		if (mRbtnCurrent == v) {
			return;
		}

		mRbtnCurrent.setChecked(false);
		((RadioButton) v).setChecked(true);
		mRbtnCurrent = (RadioButton) v;
		mPosChecked = v.getId();
	}

}

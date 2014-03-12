package lod.entertainment.wc.adapter;

import java.util.List;

import lod.entertainment.wc.R;
import lod.entertainment.wc.WCApplication;
import lod.entertainment.wc.entity.TeamInfo;
import lod.entertainment.wc.entity.TeamStanding;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AdapterListTeamStanding extends BaseAdapter{

	private Activity mActivity;
	private WCApplication mApplication;
	private List<TeamStanding> mListTeamStanding;
	
	public AdapterListTeamStanding(Activity activity, List<TeamStanding> listTeam) {
		mActivity = activity;
		mApplication = (WCApplication) activity.getApplication();
		mListTeamStanding = listTeam;
	}
	
	@Override
	public int getCount() {
		return mListTeamStanding.size();
	}

	@Override
	public Object getItem(int arg0) {
		return mListTeamStanding.get(arg0);
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
			convertView = inflater.inflate(R.layout.item_list_group_team, parent, false);
			viewHolder = new ViewHolder();
			viewHolder.tvGroupTitle = (TextView) convertView.findViewById(R.id.item_tv_group_group_title);
			viewHolder.lnrTableTitle = (LinearLayout) convertView.findViewById(R.id.item_frm_group_table_title);
			viewHolder.imgFlag = (ImageView) convertView.findViewById(R.id.item_img_group_flag);
			viewHolder.tvTeamName = (TextView) convertView.findViewById(R.id.item_tv_group_team_title);
			viewHolder.tvMatchPlayed = (TextView) convertView.findViewById(R.id.item_tv_group_wp);
			viewHolder.tvWin = (TextView) convertView.findViewById(R.id.item_tv_group_win);
			viewHolder.tvDraw = (TextView) convertView.findViewById(R.id.item_tv_group_draw);
			viewHolder.tvLose = (TextView) convertView.findViewById(R.id.item_tv_group_lose);
			viewHolder.tvGoalFor = (TextView) convertView.findViewById(R.id.item_tv_group_goal_for);
			viewHolder.tvGoalAgaints = (TextView) convertView.findViewById(R.id.item_tv_group_goal_against);
			viewHolder.tvPoints = (TextView) convertView.findViewById(R.id.item_tv_group_points);
			
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		// Get item
		TeamStanding item = mListTeamStanding.get(position);
		
		if (position % 4 == 0) {
			viewHolder.tvGroupTitle.setVisibility(View.VISIBLE);
			viewHolder.tvGroupTitle.setText(item.getGroup().replace('_', ' '));
			viewHolder.lnrTableTitle.setVisibility(View.VISIBLE);
		} else {
			viewHolder.tvGroupTitle.setVisibility(View.GONE);
			viewHolder.lnrTableTitle.setVisibility(View.GONE);
		}
		viewHolder.imgFlag.setImageResource(TeamInfo.getFlagResource(item.getCode()));
		viewHolder.tvTeamName.setText(mApplication.getTeamNameByCode(item.getCode()));
		viewHolder.tvMatchPlayed.setText(String.valueOf(item.getMatchPlayed()));
		viewHolder.tvWin.setText(String.valueOf(item.getWin()));
		viewHolder.tvDraw.setText(String.valueOf(item.getDraw()));
		viewHolder.tvLose.setText(String.valueOf(item.getLose()));
		viewHolder.tvGoalFor.setText(String.valueOf(item.getGoalFor()));
		viewHolder.tvGoalAgaints.setText(String.valueOf(item.getGoalAgainst()));
		viewHolder.tvPoints.setText(String.valueOf(item.getPoints()));
		
		return convertView;
	}

	class ViewHolder {
		TextView tvGroupTitle;
		LinearLayout lnrTableTitle;
		ImageView imgFlag;
		TextView tvTeamName;
		TextView tvMatchPlayed;
		TextView tvWin;
		TextView tvDraw;
		TextView tvLose;
		TextView tvGoalFor;
		TextView tvGoalAgaints;
		TextView tvPoints;
	}
}

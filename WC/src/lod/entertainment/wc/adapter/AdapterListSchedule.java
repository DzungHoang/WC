package lod.entertainment.wc.adapter;

import java.util.List;

import lod.entertainment.wc.R;
import lod.entertainment.wc.entity.GameInfo;
import lod.entertainment.wc.entity.TeamInfo;
import lod.entertainment.wc.utils.Utils;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterListSchedule extends BaseAdapter{

	private Activity mActivity;
	private List<GameInfo> mListGame;
	
	private boolean isGrouped;
	private boolean isATeam; // TRUE if is a list match of a team, FALSE in otherwise
	
	public AdapterListSchedule(Activity activity, List<GameInfo> listGame, boolean isGrouped) {
		mActivity = activity;
		mListGame = listGame;
		this.isGrouped = isGrouped;
	}
	
	/**
	 * @param isATeam TRUE if is a list match of a team, FALSE in otherwise
	 */
	public void setIsATeam(boolean isATeam) {
		this.isATeam = isATeam;
	}
	
	@Override
	public int getCount() {
		return mListGame.size();
	}

	@Override
	public Object getItem(int arg0) {
		return mListGame.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			LayoutInflater inflater = mActivity.getLayoutInflater();
			convertView = inflater.inflate(R.layout.item_list_match_schedule, parent, false);
			viewHolder = new ViewHolder();
			
			viewHolder.tvGroupTitle = (TextView) convertView.findViewById(R.id.item_tv_schedule_group_title);
			viewHolder.tvMatchId = (TextView) convertView.findViewById(R.id.item_tv_schedule_match_id); 
			viewHolder.tvTime = (TextView) convertView.findViewById(R.id.item_tv_schedule_time);
			viewHolder.tvResult = (TextView) convertView.findViewById(R.id.item_tv_schedule_result);
			viewHolder.tvResultPen = (TextView) convertView.findViewById(R.id.item_tv_schedule_result_pen);
			viewHolder.imgTeam1 = (ImageView) convertView.findViewById(R.id.item_img_schedule_team_1);
			viewHolder.tvTeam1 = (TextView) convertView.findViewById(R.id.item_tv_schedule_team_1);
			viewHolder.imgTeam2 = (ImageView) convertView.findViewById(R.id.item_img_schedule_team_2);
			viewHolder.tvTeam2 = (TextView) convertView.findViewById(R.id.item_tv_schedule_team_2);
			
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		// Get item
		GameInfo game = mListGame.get(position);
		TeamInfo team1 = game.getTeam1();
		TeamInfo team2 = game.getTeam2();
		// Build to view
		if (isGrouped) {
			if (position % 6 == 0) {
				viewHolder.tvGroupTitle.setVisibility(View.VISIBLE);
				viewHolder.tvGroupTitle.setText(team1.getGroup().replace('_', ' '));
			} else {
				viewHolder.tvGroupTitle.setVisibility(View.GONE);	
			}
		} else {
			if (isATeam) {
				int index = game.getIndex();
				if (index < 49) {
					if (position == 0) {
						viewHolder.tvGroupTitle.setVisibility(View.VISIBLE);
						viewHolder.tvGroupTitle.setText(mActivity.getString(R.string.btn_match_group));
					} else {
						viewHolder.tvGroupTitle.setVisibility(View.GONE);
					}
				} else if (index < 57){
					viewHolder.tvGroupTitle.setVisibility(View.VISIBLE);
					viewHolder.tvGroupTitle.setText(mActivity.getString(R.string.btn_match_round_16));
				} else if (index < 61){
					viewHolder.tvGroupTitle.setVisibility(View.VISIBLE);
					viewHolder.tvGroupTitle.setText(mActivity.getString(R.string.btn_match_quarter_final));
				} else if (index < 63) {
					viewHolder.tvGroupTitle.setVisibility(View.VISIBLE);
					viewHolder.tvGroupTitle.setText(mActivity.getString(R.string.btn_match_semi_final));
				} else if (index == 63) {
					viewHolder.tvGroupTitle.setVisibility(View.VISIBLE);
					viewHolder.tvGroupTitle.setText(mActivity.getString(R.string.btn_match_third));
				} else if (index == 64) {
					viewHolder.tvGroupTitle.setVisibility(View.VISIBLE);
					viewHolder.tvGroupTitle.setText(mActivity.getString(R.string.btn_match_final));
				} else {
					viewHolder.tvGroupTitle.setVisibility(View.GONE);
				}
			} else {
				viewHolder.tvGroupTitle.setVisibility(View.GONE);
			}
		}
		viewHolder.tvMatchId.setText("M".concat(String.valueOf(game.getIndex())));
		viewHolder.tvTime.setText(Utils.getDateAsTimeZone(game.getDate(), game.getTime()).replace("2014/", ""));
		if (game.getScore1() < 0) {
			viewHolder.tvResult.setText("-");
		} else {
			viewHolder.tvResult.setText(String.valueOf(game.getScore1() + game.getScore1Ext()).concat(" - ").concat(String.valueOf(game.getScore2() + game.getScore2Ext())));
		}
		if (game.getScore1p() == -1) {
			viewHolder.tvResultPen.setVisibility(View.GONE);
		} else {
			viewHolder.tvResultPen.setVisibility(View.VISIBLE);
			viewHolder.tvResultPen.setText("(" + game.getScore1p() + " - " + game.getScore2p() + ")");
		}
		if (team1.getCode() != null) {
			viewHolder.imgTeam1.setImageResource(TeamInfo.getFlagResource(team1.getCode()));
		} else {
			viewHolder.imgTeam2.setImageResource(R.drawable.icon_flag_no);
		}
		viewHolder.tvTeam1.setText(team1.getName());
		if (team2.getCode() != null) {
			viewHolder.imgTeam2.setImageResource(TeamInfo.getFlagResource(team2.getCode()));
		} else {
			viewHolder.imgTeam2.setImageResource(R.drawable.icon_flag_no);
		}
		viewHolder.tvTeam2.setText(team2.getName());
		
		return convertView;
	}

	class ViewHolder {
		TextView tvGroupTitle;
		TextView tvMatchId;
		TextView tvTime;
		TextView tvResult;
		TextView tvResultPen;
		ImageView imgTeam1;
		TextView tvTeam1;
		ImageView imgTeam2;
		TextView tvTeam2;
	}
}

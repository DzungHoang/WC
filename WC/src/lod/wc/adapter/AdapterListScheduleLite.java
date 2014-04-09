package lod.wc.adapter;

import java.util.List;

import lod.wc.R;
import lod.wc.entity.GameInfo;
import lod.wc.entity.TeamInfo;
import lod.wc.utils.Utils;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterListScheduleLite extends BaseAdapter{

	private Activity mActivity;
	private List<GameInfo> mListGame;
	
	public AdapterListScheduleLite(Activity activity, List<GameInfo> listGame) {
		mActivity = activity;
		mListGame = listGame;
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
			convertView = inflater.inflate(R.layout.item_list_match_schedule_lite, parent, false);
			viewHolder = new ViewHolder();
			
			viewHolder.tvTime = (TextView) convertView.findViewById(R.id.item_tv_schedule_time_lite);
			viewHolder.tvResult = (TextView) convertView.findViewById(R.id.item_tv_schedule_result_lite);
			viewHolder.tvResultPen = (TextView) convertView.findViewById(R.id.item_tv_schedule_result_pen_lite);
			viewHolder.imgTeam1 = (ImageView) convertView.findViewById(R.id.item_img_schedule_team_1_lite);
			viewHolder.tvTeam1 = (TextView) convertView.findViewById(R.id.item_tv_schedule_team_1_lite);
			viewHolder.imgTeam2 = (ImageView) convertView.findViewById(R.id.item_img_schedule_team_2_lite);
			viewHolder.tvTeam2 = (TextView) convertView.findViewById(R.id.item_tv_schedule_team_2_lite);
			
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		// Get item
		GameInfo game = mListGame.get(position);
		TeamInfo team1 = game.getTeam1();
		TeamInfo team2 = game.getTeam2();
		// Build to view
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
		viewHolder.tvTeam1.setText(team1.getCode());
		if (team2.getCode() != null) {
			viewHolder.imgTeam2.setImageResource(TeamInfo.getFlagResource(team2.getCode()));
		} else {
			viewHolder.imgTeam2.setImageResource(R.drawable.icon_flag_no);
		}
		viewHolder.tvTeam2.setText(team2.getCode());
		
		return convertView;
	}

	class ViewHolder {
		TextView tvTime;
		TextView tvResult;
		TextView tvResultPen;
		ImageView imgTeam1;
		TextView tvTeam1;
		ImageView imgTeam2;
		TextView tvTeam2;
	}
}

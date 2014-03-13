package lod.entertainment.wc.receiver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import lod.entertainment.wc.R;
import lod.entertainment.wc.ScheduleActivity;
import lod.entertainment.wc.entity.GameInfo;
import lod.entertainment.wc.utils.Utils;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MatchdayNotificationReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
//		if (action.equals(Intent.ACTION_DATE_CHANGED)) {
			String gameString = Utils.loadJSONFromAsset(context,
					"schedule.json");
			List<GameInfo> gameScheduleList = Utils
					.parseGameSchedule(gameString);
			gameScheduleList = Utils.matchesToday(gameScheduleList);
			if (gameScheduleList != null && gameScheduleList.size() > 0) {
				NotificationManager notificationManager = (NotificationManager) context
						.getSystemService(Context.NOTIFICATION_SERVICE);
				for (int i = 0; i < gameScheduleList.size(); i++) {
					GameInfo temp = gameScheduleList.get(i);
					Intent notifyIntent = new Intent(context,
							ScheduleActivity.class);
					notifyIntent.putExtra("game_index", temp.getIndex());

					PendingIntent pending = PendingIntent.getActivity(context,
							0, notifyIntent, 0);

					Notification notification = new Notification.Builder(
							context)
							.setContentTitle(
									temp.getTeam1().getCode() + "  -  "
											+ temp.getTeam2().getCode()
											+ " is comming at:")
							.setContentText(
									Utils.getDateAsTimeZone(temp.getDate(),
											temp.getTime()))
							.setContentIntent(pending).setAutoCancel(true)
							.setSmallIcon(R.drawable.ic_launcher)
							.getNotification();
					notification.flags |= Notification.FLAG_AUTO_CANCEL;
					notificationManager.notify(temp.getIndex(), notification);
				}
			}
		}
//	}

}

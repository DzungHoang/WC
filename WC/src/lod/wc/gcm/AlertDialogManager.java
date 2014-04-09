package lod.wc.gcm;

import lod.wc.R;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AlertDialogManager {
	/**
	 * Function to display simple Alert DiaLog
	 * 
	 * @param context
	 *            - application context
	 * @param title
	 *            - alert diaLog title
	 * @param message
	 *            - alert message
	 * @param status
	 *            - success/failure (used to set icon) - pass null if you don't
	 *            want icon
	 * */
	public void showAlertDialog(Context context, String title, String message,
			Boolean status) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		// Setting DiaLog Title
		alertDialog.setTitle(title);

		// Setting DiaLog Message
		alertDialog.setMessage(message);

		if (status != null)
			// Setting alert diaLog icon
			alertDialog
					.setIcon((status) ? R.drawable.success : R.drawable.fail);

		// Setting OK Button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}
}
package vn.gamexp.facebookutils.listener;

import vn.gamexp.facebookutils.entities.Result;

public interface OnRequestListener {
	void onDone(Result result, Exception e);
}

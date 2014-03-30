package vn.gamexp.facebookutils.listener;

import vn.gamexp.facebookutils.entities.LoginResult;

public interface OnLoginListener {
	void onDone(LoginResult result, Exception e);
}

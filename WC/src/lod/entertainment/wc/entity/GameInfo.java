package lod.entertainment.wc.entity;

import java.util.Date;

import android.text.format.Time;

public class GameInfo {
	//These information is related to schedule
	private TeamInfo team1;
	private TeamInfo team2;
	
	//Date & time information is stored in timezone of Brazil
	//Before display, please use Utils.xxxx() to convert to user timezone
	private String startDate;
	private String startTime;
	
	private String stadium;
	
	
	//These information is related to result
	private int score1;
	private int score2;
	
	public static int GAME_NOT_START = 0;		//Start later than tomorrow
	public static int GAME_COMMING = 1;			//Start today
	public static int GAME_STARTED = 2;			//Started
	public static int GAME_HALFTIME = 3;			//Half-time
	public static int GAME_FULLTIME = 4;			//Full-time
	public static int GAME_EXT_1 = 5;				//End of extra 1
	public static int GAME_EXT_2 = 6;				//End of extra 2
	public static int GAME_PEN = 7;					//End of Penalty
	private int state;
	
	public GameInfo(){
		state = GAME_NOT_START;
	}
}

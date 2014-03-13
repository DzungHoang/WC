package lod.entertainment.wc.entity;

import java.util.Date;

import android.text.format.Time;

public class GameInfo {
	
	// Match index (in range 1-64)
	private int index;
	
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
	private int score1ext;
	private int score2ext;
	private int score1p;
	private int score2p;
	public static int GAME_NOT_START = 0;		//Start later than tomorrow
	public static int GAME_COMMING = 1;			//Start today
	public static int GAME_STARTED = 2;			//Started
	public static int GAME_HALFTIME = 3;			//Half-time
	public static int GAME_FULLTIME = 4;			//Full-time
	public static int GAME_EXT_1 = 5;				//End of extra 1
	public static int GAME_EXT_2 = 6;				//End of extra 2
	public static int GAME_PEN = 7;					//End of Penalty
	private int state;
	
	private void initScore(){
		state = GAME_NOT_START;
		// Default score is -1, match not stated
		score1 = -1;
		score2 = -1;
		score1ext = -1;
		score2ext = -1;
		score1p = -1;
		score2p = -1;
	}
	
	public GameInfo(int index, TeamInfo team1, TeamInfo team2, String startDate, String startTime) {
		initScore();
		this.index = index;
		this.team1 = team1;
		this.team2 = team2;
		this.startDate = startDate;
		this.startTime = startTime;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public TeamInfo getTeam1() {
		return this.team1;
	}
	
	public TeamInfo getTeam2() {
		return this.team2;
	}
	
	public String getDate() {
		return this.startDate;
	}
	
	public String getTime() {
		return this.startTime;
	}
	
	public int getState() {
		return this.state;
	}
	
	public String getStadium() {
		return this.stadium;
	}
	
	public int getScore1() {
		return this.score1;
	}
	
	public int getScore2() {
		return this.score2;
	}
	public void setScore(int score1, int score2, int score1ext, int score2ext, int score1p, int score2p){
		this.score1 = score1;
		this.score2 = score2;
		this.score1ext = score1ext;
		this.score2ext = score2ext;
		this.score1p = score1p;
		this.score2p = score2p;
	}
}

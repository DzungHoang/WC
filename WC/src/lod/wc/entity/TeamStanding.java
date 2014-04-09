package lod.wc.entity;

public class TeamStanding {

	private String code;
	private String group;
	private int matchPlayed;
	private int win;
	private int draw;
	private int lose;
	private int goalFor;
	private int goalAgainst;
	private int points;

	public TeamStanding() {
	}

	public TeamStanding(String code, String group, int matchPlayed, int win,
			int draw, int lose, int goalFor, int goalAgainst, int points) {
		super();
		this.code = code;
		this.group = group;
		this.matchPlayed = matchPlayed;
		this.win = win;
		this.draw = draw;
		this.lose = lose;
		this.goalFor = goalFor;
		this.goalAgainst = goalAgainst;
		this.points = points;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public int getMatchPlayed() {
		return matchPlayed;
	}

	public void setMatchPlayed(int matchPlayed) {
		this.matchPlayed = matchPlayed;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getLose() {
		return lose;
	}

	public void setLose(int lose) {
		this.lose = lose;
	}

	public int getGoalFor() {
		return goalFor;
	}

	public void setGoalFor(int goalFor) {
		this.goalFor = goalFor;
	}

	public int getGoalAgainst() {
		return goalAgainst;
	}

	public void setGoalAgainst(int goalAgainst) {
		this.goalAgainst = goalAgainst;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

}

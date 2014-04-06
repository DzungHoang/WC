package lod.entertainment.wc.entity;



public class MatchDayInfo {
	
	private String pos;
	private String title;
	private String start;
	private String end;
	
	public MatchDayInfo(String pos, String title, String start, String end){
		this.pos = pos;
		this.title = title;
		this.start = start;
		this.end = end;
	}
	/**
	 * @return position of match day
	 * */
	public String getPos(){
		return this.pos;
	}
	/**
	 * @return title of match day
	 * */
	public String getTitle(){
		return this.title;
	}
	/**
	 * @return Start time of match day
	 * */
	public String getStart(){
		return this.start;
	}
	/**
	 * @return Start time of match day
	 * */
	public String getEnd(){
		return this.end;
	}
}

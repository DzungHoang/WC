package lod.entertainment.wc.entity;

import lod.entertainment.wc.R;


public class TeamInfo {
	/*
	 * Team code
	 * */
	public static String CODE_GRE = "GRE";
	public static String CODE_RUS = "RUS";
	public static String CODE_NED = "NED";
	public static String CODE_GER = "GER";
	public static String CODE_POR = "POR";
	public static String CODE_ESP = "ESP";
	public static String CODE_ITA = "ITA";
	public static String CODE_CRO = "CRO";
	public static String CODE_FRA = "FRA";
	public static String CODE_ENG = "ENG";
	public static String CODE_SUI = "SUI";
	public static String CODE_BEL = "BEL";
	public static String CODE_BIH = "BIH";
	public static String CODE_ALG = "ALG";
	public static String CODE_CIV = "CIV";
	public static String CODE_GHA = "GHA";
	public static String CODE_CMR = "CMR";
	public static String CODE_NGA = "NGA";
	public static String CODE_MEX = "MEX";
	public static String CODE_USA = "USA";
	public static String CODE_HON = "HON";
	public static String CODE_CRC = "CRC";
	public static String CODE_ARG = "ARG";
	public static String CODE_BRA = "BRA";
	public static String CODE_CHI = "CHI";
	public static String CODE_URU = "URU";
	public static String CODE_COL = "COL";
	public static String CODE_ECU = "ECU";
	public static String CODE_AUS = "AUS";
	public static String CODE_JPN = "JPN";
	public static String CODE_KOR = "KOR";
	public static String CODE_IRN = "IRN";
	
	/*
	 * Team group
	 * */
	public static String GROUP_A = "GROUP_A";
	public static String GROUP_B = "GROUP_B";
	public static String GROUP_C = "GROUP_C";
	public static String GROUP_D = "GROUP_D";
	public static String GROUP_E = "GROUP_E";
	public static String GROUP_F = "GROUP_F";
	public static String GROUP_G = "GROUP_G";
	public static String GROUP_H = "GROUP_H";
	
	private String key;
	private String name;
	private String code;
	private String group;
	private int flag_resource;
	
	public TeamInfo(String key, String name, String code, String group){
		this.key = key;
		this.name = name;
		this.code = code;
		this.group = group;
		initFlagResource(code);
	}
	/**
	 * Based on team code, initialize nation flag resource ID
	 * */
	private void initFlagResource(String code){
		if(code.equals(CODE_GRE)){
			flag_resource = R.drawable.greece;
		}else if(code.equals(CODE_RUS)){
			flag_resource = R.drawable.russia;
		}
	}
	/**
	 * @return Team's nation name
	 * */
	public String getName(){
		return this.name;
	}
	/**
	 * @return Team's group
	 * */
	public String getGroup(){
		return this.group;
	}
	/**
	 * @return Team's nation flag resource in drawable
	 * */
	public int getFlag(){
		return flag_resource;
	}
}

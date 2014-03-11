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
		if (code.equals(CODE_ALG)) {
			flag_resource = R.drawable.flag_algeria;
		} else if (code.equals(CODE_ARG)) {
			flag_resource = R.drawable.flag_argentina;
		} else if (code.equals(CODE_AUS)) {
			flag_resource = R.drawable.flag_australia;
		} else if (code.equals(CODE_BEL)) {
			flag_resource = R.drawable.flag_belgium;
		} else if (code.equals(CODE_BIH)) {
			flag_resource = R.drawable.flag_bosnia;
		} else if (code.equals(CODE_BRA)) {
			flag_resource = R.drawable.flag_brazil;
		} else if (code.equals(CODE_CHI)) {
			flag_resource = R.drawable.flag_chile;
		} else if (code.equals(CODE_CIV)) {
			flag_resource = R.drawable.flag_cote_d_ivoire;
		} else if (code.equals(CODE_CMR)) {
			flag_resource = R.drawable.flag_cameroon;
		} else if (code.equals(CODE_COL)) {
			flag_resource = R.drawable.flag_colombia;
		} else if (code.equals(CODE_CRC)) {
			flag_resource = R.drawable.flag_costa;
		} else if (code.equals(CODE_CRO)) {
			flag_resource = R.drawable.flag_croatia;
		} else if (code.equals(CODE_ECU)) {
			flag_resource = R.drawable.flag_ecuador;
		} else if (code.equals(CODE_ENG)) {
			flag_resource = R.drawable.flag_england;
		} else if (code.equals(CODE_ESP)) {
			flag_resource = R.drawable.flag_spain;
		} else if (code.equals(CODE_FRA)) {
			flag_resource = R.drawable.flag_france;
		} else if (code.equals(CODE_GER)) {
			flag_resource = R.drawable.flag_germany;
		} else if (code.equals(CODE_GHA)) {
			flag_resource = R.drawable.flag_ghana;
		} else if (code.equals(CODE_GRE)) {
			flag_resource = R.drawable.flag_greece;
		} else if (code.equals(CODE_HON)) {
			flag_resource = R.drawable.flag_honduras;
		} else if (code.equals(CODE_IRN)) {
			flag_resource = R.drawable.flag_iran;
		} else if (code.equals(CODE_ITA)) {
			flag_resource = R.drawable.flag_italy;
		} else if (code.equals(CODE_JPN)) {
			flag_resource = R.drawable.flag_japan;
		} else if (code.equals(CODE_KOR)) {
			flag_resource = R.drawable.flag_korea_south;
		} else if (code.equals(CODE_MEX)) {
			flag_resource = R.drawable.flag_mexico;
		} else if (code.equals(CODE_NED)) {
			flag_resource = R.drawable.flag_netherlands;
		} else if (code.equals(CODE_NGA)) {
			flag_resource = R.drawable.flag_nigeria;
		} else if (code.equals(CODE_POR)) {
			flag_resource = R.drawable.flag_portugal;
		} else if (code.equals(CODE_RUS)) {
			flag_resource = R.drawable.flag_russia;
		} else if (code.equals(CODE_SUI)) {
			flag_resource = R.drawable.flag_switzerland;
		} else if (code.equals(CODE_URU)) {
			flag_resource = R.drawable.flag_uruguay;
		} else if (code.equals(CODE_USA)) {
			flag_resource = R.drawable.flag_united_states_of_america;
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
	
	/**
	 * @return Team's key
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * @return 
	 */
	public String getCode() {
		return code;
	}
}

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
		this.flag_resource = getFlagResource(code);
	}
	public TeamInfo(String key, String name, String code){
		this.key = key;
		this.name = name;
		this.code = code;
		autoArrangeGroup(code);
		this.flag_resource = getFlagResource(code);
	}
	private void autoArrangeGroup(String code){
		if (code.equals(CODE_ALG)) {
			this.group = GROUP_H;
		} else if (code.equals(CODE_ARG)) {
			this.group = GROUP_F;
		} else if (code.equals(CODE_AUS)) {
			this.group = GROUP_B;
		} else if (code.equals(CODE_BEL)) {
			this.group = GROUP_H;
		} else if (code.equals(CODE_BIH)) {
			this.group = GROUP_F;
		} else if (code.equals(CODE_BRA)) {
			this.group = GROUP_A;
		} else if (code.equals(CODE_CHI)) {
			this.group = GROUP_B;
		} else if (code.equals(CODE_CIV)) {
			this.group = GROUP_C;
		} else if (code.equals(CODE_CMR)) {
			this.group = GROUP_A;
		} else if (code.equals(CODE_COL)) {
			this.group = GROUP_C;
		} else if (code.equals(CODE_CRC)) {
			this.group = GROUP_D;
		} else if (code.equals(CODE_CRO)) {
			this.group = GROUP_A;
		} else if (code.equals(CODE_ECU)) {
			this.group = GROUP_E;
		} else if (code.equals(CODE_ENG)) {
			this.group = GROUP_D;
		} else if (code.equals(CODE_ESP)) {
			this.group = GROUP_B;
		} else if (code.equals(CODE_FRA)) {
			this.group = GROUP_E;
		} else if (code.equals(CODE_GER)) {
			this.group = GROUP_G;
		} else if (code.equals(CODE_GHA)) {
			this.group = GROUP_G;
		} else if (code.equals(CODE_GRE)) {
			this.group = GROUP_C;
		} else if (code.equals(CODE_HON)) {
			this.group = GROUP_E;
		} else if (code.equals(CODE_IRN)) {
			this.group = GROUP_F;
		} else if (code.equals(CODE_ITA)) {
			this.group = GROUP_D;
		} else if (code.equals(CODE_JPN)) {
			this.group = GROUP_C;
		} else if (code.equals(CODE_KOR)) {
			this.group = GROUP_H;
		} else if (code.equals(CODE_MEX)) {
			this.group = GROUP_A;
		} else if (code.equals(CODE_NED)) {
			this.group = GROUP_B;
		} else if (code.equals(CODE_NGA)) {
			this.group = GROUP_F;
		} else if (code.equals(CODE_POR)) {
			this.group = GROUP_G;
		} else if (code.equals(CODE_RUS)) {
			this.group = GROUP_H;
		} else if (code.equals(CODE_SUI)) {
			this.group = GROUP_E;
		} else if (code.equals(CODE_URU)) {
			this.group = GROUP_D;
		} else if (code.equals(CODE_USA)) {
			this.group = GROUP_G;
		}
	}
	
	/**
	 * Based on team code, initialize nation flag resource ID
	 * */
	public static int getFlagResource(String code){
		if (code.equals(CODE_ALG)) {
			return R.drawable.flag_algeria;
		} else if (code.equals(CODE_ARG)) {
			return R.drawable.flag_argentina;
		} else if (code.equals(CODE_AUS)) {
			return R.drawable.flag_australia;
		} else if (code.equals(CODE_BEL)) {
			return R.drawable.flag_belgium;
		} else if (code.equals(CODE_BIH)) {
			return R.drawable.flag_bosnia;
		} else if (code.equals(CODE_BRA)) {
			return R.drawable.flag_brazil;
		} else if (code.equals(CODE_CHI)) {
			return R.drawable.flag_chile;
		} else if (code.equals(CODE_CIV)) {
			return R.drawable.flag_cote_d_ivoire;
		} else if (code.equals(CODE_CMR)) {
			return R.drawable.flag_cameroon;
		} else if (code.equals(CODE_COL)) {
			return R.drawable.flag_colombia;
		} else if (code.equals(CODE_CRC)) {
			return R.drawable.flag_costa;
		} else if (code.equals(CODE_CRO)) {
			return R.drawable.flag_croatia;
		} else if (code.equals(CODE_ECU)) {
			return R.drawable.flag_ecuador;
		} else if (code.equals(CODE_ENG)) {
			return R.drawable.flag_england;
		} else if (code.equals(CODE_ESP)) {
			return R.drawable.flag_spain;
		} else if (code.equals(CODE_FRA)) {
			return R.drawable.flag_france;
		} else if (code.equals(CODE_GER)) {
			return R.drawable.flag_germany;
		} else if (code.equals(CODE_GHA)) {
			return R.drawable.flag_ghana;
		} else if (code.equals(CODE_GRE)) {
			return R.drawable.flag_greece;
		} else if (code.equals(CODE_HON)) {
			return R.drawable.flag_honduras;
		} else if (code.equals(CODE_IRN)) {
			return R.drawable.flag_iran;
		} else if (code.equals(CODE_ITA)) {
			return R.drawable.flag_italy;
		} else if (code.equals(CODE_JPN)) {
			return R.drawable.flag_japan;
		} else if (code.equals(CODE_KOR)) {
			return R.drawable.flag_korea_south;
		} else if (code.equals(CODE_MEX)) {
			return R.drawable.flag_mexico;
		} else if (code.equals(CODE_NED)) {
			return R.drawable.flag_netherlands;
		} else if (code.equals(CODE_NGA)) {
			return R.drawable.flag_nigeria;
		} else if (code.equals(CODE_POR)) {
			return R.drawable.flag_portugal;
		} else if (code.equals(CODE_RUS)) {
			return R.drawable.flag_russia;
		} else if (code.equals(CODE_SUI)) {
			return R.drawable.flag_switzerland;
		} else if (code.equals(CODE_URU)) {
			return R.drawable.flag_uruguay;
		} else if (code.equals(CODE_USA)) {
			return R.drawable.flag_united_states_of_america;
		} else {
			return R.drawable.icon_flag_no;
		}
	}
	
	/**
	 * Based on team code, initialize thumbs resource
	 * */
	public static int getThumbResource(String code){
		if (code.equals(CODE_ALG)) {
			return R.drawable.thumb_alg;
		} else if (code.equals(CODE_ARG)) {
			return R.drawable.thumb_arg;
		} else if (code.equals(CODE_AUS)) {
			return R.drawable.thumb_aus;
		} else if (code.equals(CODE_BEL)) {
			return R.drawable.thumb_bel;
		} else if (code.equals(CODE_BIH)) {
			return R.drawable.thumb_bih;
		} else if (code.equals(CODE_BRA)) {
			return R.drawable.thumb_bra;
		} else if (code.equals(CODE_CHI)) {
			return R.drawable.thumb_chi;
		} else if (code.equals(CODE_CIV)) {
			return R.drawable.thumb_civ;
		} else if (code.equals(CODE_CMR)) {
			return R.drawable.thumb_cmr;
		} else if (code.equals(CODE_COL)) {
			return R.drawable.thumb_coljpg;
		} else if (code.equals(CODE_CRC)) {
			return R.drawable.thumb_crc;
		} else if (code.equals(CODE_CRO)) {
			return R.drawable.thumb_cro;
		} else if (code.equals(CODE_ECU)) {
			return R.drawable.thumb_ecu;
		} else if (code.equals(CODE_ENG)) {
			return R.drawable.thumb_eng;
		} else if (code.equals(CODE_ESP)) {
			return R.drawable.thumb_esp;
		} else if (code.equals(CODE_FRA)) {
			return R.drawable.thumb_fra;
		} else if (code.equals(CODE_GER)) {
			return R.drawable.thumb_ger;
		} else if (code.equals(CODE_GHA)) {
			return R.drawable.thumb_gha;
		} else if (code.equals(CODE_GRE)) {
			return R.drawable.thumb_gre;
		} else if (code.equals(CODE_HON)) {
			return R.drawable.thumb_hon;
		} else if (code.equals(CODE_IRN)) {
			return R.drawable.thumb_irn;
		} else if (code.equals(CODE_ITA)) {
			return R.drawable.thumb_ita;
		} else if (code.equals(CODE_JPN)) {
			return R.drawable.thumb_jpn;
		} else if (code.equals(CODE_KOR)) {
			return R.drawable.thumb_kor;
		} else if (code.equals(CODE_MEX)) {
			return R.drawable.thumb_mex;
		} else if (code.equals(CODE_NED)) {
			return R.drawable.thumb_ned;
		} else if (code.equals(CODE_NGA)) {
			return R.drawable.thumb_nga;
		} else if (code.equals(CODE_POR)) {
			return R.drawable.thumb_por;
		} else if (code.equals(CODE_RUS)) {
			return R.drawable.thumb_rus;
		} else if (code.equals(CODE_SUI)) {
			return R.drawable.thumb_sui;
		} else if (code.equals(CODE_URU)) {
			return R.drawable.thumb_uru;
		} else if (code.equals(CODE_USA)) {
			return R.drawable.thumb_usa;
		} else {
			return R.drawable.thumb_bra;
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

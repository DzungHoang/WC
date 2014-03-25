package lod.entertainment.wc.data;

import java.util.ArrayList;
import java.util.List;

import lod.entertainment.wc.entity.TeamInfo;
import lod.entertainment.wc.entity.TeamStanding;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseWC extends SQLiteOpenHelper {

	private static final String DB_NAME = "data_wc.db";
	private static final int DB_VERSION = 1;

	private static final String TABLE_STANDING = "standing";
	private static final String COL_CODE = "code";
	private static final String COL_GROUP = "group_code";
	private static final String COL_MP = "match_played";
	private static final String COL_W = "win";
	private static final String COL_D = "draw";
	private static final String COL_L = "lose";
	private static final String COL_GF = "goal_for";
	private static final String COL_GA = "goal_against";
	private static final String COL_PTS = "point";

	private static final String CREATE_TABLE_STANDING = "CREATE TABLE IF NOT EXISTS "
			+ TABLE_STANDING
			+ "("
			+ COL_CODE
			+ " TEXT PRIMARY KEY, "
			+ COL_GROUP
			+ " TEXT, "
			+ COL_MP
			+ " INTEGER, "
			+ COL_W
			+ " INTEGER, "
			+ COL_D
			+ " INTEGER, "
			+ COL_L
			+ " INTEGER, "
			+ COL_GF
			+ " INTEGER, "
			+ COL_GA + " INTEGER, " + COL_PTS + " INTEGER)";

	public DatabaseWC(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		this.getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("TienVV", "onCreate");
		db.execSQL(CREATE_TABLE_STANDING);
		initTable(db);
		Log.d("TienVV", "init table");
	}

	/**
	 * Initiate data for table standing
	 */
	private void initTable(SQLiteDatabase db) {
		ContentValues values = new ContentValues();
		// Brazil
		values.put(COL_CODE, TeamInfo.CODE_BRA);
		values.put(COL_GROUP, TeamInfo.GROUP_A);
		values.put(COL_MP, 0);
		values.put(COL_W, 0);
		values.put(COL_D, 0);
		values.put(COL_L, 0);
		values.put(COL_GF, 0);
		values.put(COL_GA, 0);
		values.put(COL_PTS, 0);
		db.insert(TABLE_STANDING, null, values);
		// Croatia
		values.put(COL_CODE, TeamInfo.CODE_CRO);
		values.put(COL_GROUP, TeamInfo.GROUP_A);
		values.put(COL_MP, 0);
		values.put(COL_W, 0);
		values.put(COL_D, 0);
		values.put(COL_L, 0);
		values.put(COL_GF, 0);
		values.put(COL_GA, 0);
		values.put(COL_PTS, 0);
		db.insert(TABLE_STANDING, null, values);
		// Mexico
		values.put(COL_CODE, TeamInfo.CODE_MEX);
		values.put(COL_GROUP, TeamInfo.GROUP_A);
		values.put(COL_MP, 0);
		values.put(COL_W, 0);
		values.put(COL_D, 0);
		values.put(COL_L, 0);
		values.put(COL_GF, 0);
		values.put(COL_GA, 0);
		values.put(COL_PTS, 0);
		db.insert(TABLE_STANDING, null, values);
		// Cameroon
		values.put(COL_CODE, TeamInfo.CODE_CMR);
		values.put(COL_GROUP, TeamInfo.GROUP_A);
		values.put(COL_MP, 0);
		values.put(COL_W, 0);
		values.put(COL_D, 0);
		values.put(COL_L, 0);
		values.put(COL_GF, 0);
		values.put(COL_GA, 0);
		values.put(COL_PTS, 0);
		db.insert(TABLE_STANDING, null, values);
		// Spain
		values.put(COL_CODE, TeamInfo.CODE_ESP);
		values.put(COL_GROUP, TeamInfo.GROUP_B);
		values.put(COL_MP, 0);
		values.put(COL_W, 0);
		values.put(COL_D, 0);
		values.put(COL_L, 0);
		values.put(COL_GF, 0);
		values.put(COL_GA, 0);
		values.put(COL_PTS, 0);
		db.insert(TABLE_STANDING, null, values);
		// Netherlands
		values.put(COL_CODE, TeamInfo.CODE_NED);
		values.put(COL_GROUP, TeamInfo.GROUP_B);
		values.put(COL_MP, 0);
		values.put(COL_W, 0);
		values.put(COL_D, 0);
		values.put(COL_L, 0);
		values.put(COL_GF, 0);
		values.put(COL_GA, 0);
		values.put(COL_PTS, 0);
		db.insert(TABLE_STANDING, null, values);
		// Chile
		values.put(COL_CODE, TeamInfo.CODE_CHI);
		values.put(COL_GROUP, TeamInfo.GROUP_B);
		values.put(COL_MP, 0);
		values.put(COL_W, 0);
		values.put(COL_D, 0);
		values.put(COL_L, 0);
		values.put(COL_GF, 0);
		values.put(COL_GA, 0);
		values.put(COL_PTS, 0);
		db.insert(TABLE_STANDING, null, values);
		// Australia
		values.put(COL_CODE, TeamInfo.CODE_AUS);
		values.put(COL_GROUP, TeamInfo.GROUP_B);
		values.put(COL_MP, 0);
		values.put(COL_W, 0);
		values.put(COL_D, 0);
		values.put(COL_L, 0);
		values.put(COL_GF, 0);
		values.put(COL_GA, 0);
		values.put(COL_PTS, 0);
		db.insert(TABLE_STANDING, null, values);
		// Colombia
		values.put(COL_CODE, TeamInfo.CODE_COL);
		values.put(COL_GROUP, TeamInfo.GROUP_C);
		values.put(COL_MP, 0);
		values.put(COL_W, 0);
		values.put(COL_D, 0);
		values.put(COL_L, 0);
		values.put(COL_GF, 0);
		values.put(COL_GA, 0);
		values.put(COL_PTS, 0);
		db.insert(TABLE_STANDING, null, values);
		// Greece
		values.put(COL_CODE, TeamInfo.CODE_GRE);
		values.put(COL_GROUP, TeamInfo.GROUP_C);
		values.put(COL_MP, 0);
		values.put(COL_W, 0);
		values.put(COL_D, 0);
		values.put(COL_L, 0);
		values.put(COL_GF, 0);
		values.put(COL_GA, 0);
		values.put(COL_PTS, 0);
		db.insert(TABLE_STANDING, null, values);
		// Cote d'Ivoire
		values.put(COL_CODE, TeamInfo.CODE_CIV);
		values.put(COL_GROUP, TeamInfo.GROUP_C);
		values.put(COL_MP, 0);
		values.put(COL_W, 0);
		values.put(COL_D, 0);
		values.put(COL_L, 0);
		values.put(COL_GF, 0);
		values.put(COL_GA, 0);
		values.put(COL_PTS, 0);
		db.insert(TABLE_STANDING, null, values);
		// Japan
		values.put(COL_CODE, TeamInfo.CODE_JPN);
		values.put(COL_GROUP, TeamInfo.GROUP_C);
		values.put(COL_MP, 0);
		values.put(COL_W, 0);
		values.put(COL_D, 0);
		values.put(COL_L, 0);
		values.put(COL_GF, 0);
		values.put(COL_GA, 0);
		values.put(COL_PTS, 0);
		db.insert(TABLE_STANDING, null, values);
		// Uruguay
		values.put(COL_CODE, TeamInfo.CODE_URU);
		values.put(COL_GROUP, TeamInfo.GROUP_D);
		values.put(COL_MP, 0);
		values.put(COL_W, 0);
		values.put(COL_D, 0);
		values.put(COL_L, 0);
		values.put(COL_GF, 0);
		values.put(COL_GA, 0);
		values.put(COL_PTS, 0);
		db.insert(TABLE_STANDING, null, values);		
		// Costa rica
		values.put(COL_CODE, TeamInfo.CODE_CRC);
		values.put(COL_GROUP, TeamInfo.GROUP_D);
		values.put(COL_MP, 0);
		values.put(COL_W, 0);
		values.put(COL_D, 0);
		values.put(COL_L, 0);
		values.put(COL_GF, 0);
		values.put(COL_GA, 0);
		values.put(COL_PTS, 0);
		db.insert(TABLE_STANDING, null, values);
		// England
		values.put(COL_CODE, TeamInfo.CODE_ENG);
		values.put(COL_GROUP, TeamInfo.GROUP_D);
		values.put(COL_MP, 0);
		values.put(COL_W, 0);
		values.put(COL_D, 0);
		values.put(COL_L, 0);
		values.put(COL_GF, 0);
		values.put(COL_GA, 0);
		values.put(COL_PTS, 0);
		db.insert(TABLE_STANDING, null, values);
		// Italy
		values.put(COL_CODE, TeamInfo.CODE_ITA);
		values.put(COL_GROUP, TeamInfo.GROUP_D);
		values.put(COL_MP, 0);
		values.put(COL_W, 0);
		values.put(COL_D, 0);
		values.put(COL_L, 0);
		values.put(COL_GF, 0);
		values.put(COL_GA, 0);
		values.put(COL_PTS, 0);
		db.insert(TABLE_STANDING, null, values);
		// Switzerland
		values.put(COL_CODE, TeamInfo.CODE_SUI);
		values.put(COL_GROUP, TeamInfo.GROUP_E);
		values.put(COL_MP, 0);
		values.put(COL_W, 0);
		values.put(COL_D, 0);
		values.put(COL_L, 0);
		values.put(COL_GF, 0);
		values.put(COL_GA, 0);
		values.put(COL_PTS, 0);
		db.insert(TABLE_STANDING, null, values);
		// Ecuador
		values.put(COL_CODE, TeamInfo.CODE_ECU);
		values.put(COL_GROUP, TeamInfo.GROUP_E);
		values.put(COL_MP, 0);
		values.put(COL_W, 0);
		values.put(COL_D, 0);
		values.put(COL_L, 0);
		values.put(COL_GF, 0);
		values.put(COL_GA, 0);
		values.put(COL_PTS, 0);
		db.insert(TABLE_STANDING, null, values);
		// France
		values.put(COL_CODE, TeamInfo.CODE_FRA);
		values.put(COL_GROUP, TeamInfo.GROUP_E);
		values.put(COL_MP, 0);
		values.put(COL_W, 0);
		values.put(COL_D, 0);
		values.put(COL_L, 0);
		values.put(COL_GF, 0);
		values.put(COL_GA, 0);
		values.put(COL_PTS, 0);
		db.insert(TABLE_STANDING, null, values);
		// Honduras
		values.put(COL_CODE, TeamInfo.CODE_HON);
		values.put(COL_GROUP, TeamInfo.GROUP_E);
		values.put(COL_MP, 0);
		values.put(COL_W, 0);
		values.put(COL_D, 0);
		values.put(COL_L, 0);
		values.put(COL_GF, 0);
		values.put(COL_GA, 0);
		values.put(COL_PTS, 0);
		db.insert(TABLE_STANDING, null, values);
		// Argentina
		values.put(COL_CODE, TeamInfo.CODE_ARG);
		values.put(COL_GROUP, TeamInfo.GROUP_F);
		values.put(COL_MP, 0);
		values.put(COL_W, 0);
		values.put(COL_D, 0);
		values.put(COL_L, 0);
		values.put(COL_GF, 0);
		values.put(COL_GA, 0);
		values.put(COL_PTS, 0);
		db.insert(TABLE_STANDING, null, values);
		// Bosnia and Herzegovina
		values.put(COL_CODE, TeamInfo.CODE_BIH);
		values.put(COL_GROUP, TeamInfo.GROUP_F);
		values.put(COL_MP, 0);
		values.put(COL_W, 0);
		values.put(COL_D, 0);
		values.put(COL_L, 0);
		values.put(COL_GF, 0);
		values.put(COL_GA, 0);
		values.put(COL_PTS, 0);
		db.insert(TABLE_STANDING, null, values);
		// Iran
		values.put(COL_CODE, TeamInfo.CODE_IRN);
		values.put(COL_GROUP, TeamInfo.GROUP_F);
		values.put(COL_MP, 0);
		values.put(COL_W, 0);
		values.put(COL_D, 0);
		values.put(COL_L, 0);
		values.put(COL_GF, 0);
		values.put(COL_GA, 0);
		values.put(COL_PTS, 0);
		db.insert(TABLE_STANDING, null, values);
		// Nigeria
		values.put(COL_CODE, TeamInfo.CODE_NGA);
		values.put(COL_GROUP, TeamInfo.GROUP_F);
		values.put(COL_MP, 0);
		values.put(COL_W, 0);
		values.put(COL_D, 0);
		values.put(COL_L, 0);
		values.put(COL_GF, 0);
		values.put(COL_GA, 0);
		values.put(COL_PTS, 0);
		db.insert(TABLE_STANDING, null, values);
		// Germany
		values.put(COL_CODE, TeamInfo.CODE_GER);
		values.put(COL_GROUP, TeamInfo.GROUP_G);
		values.put(COL_MP, 0);
		values.put(COL_W, 0);
		values.put(COL_D, 0);
		values.put(COL_L, 0);
		values.put(COL_GF, 0);
		values.put(COL_GA, 0);
		values.put(COL_PTS, 0);
		db.insert(TABLE_STANDING, null, values);
		// Portugal
		values.put(COL_CODE, TeamInfo.CODE_POR);
		values.put(COL_GROUP, TeamInfo.GROUP_G);
		values.put(COL_MP, 0);
		values.put(COL_W, 0);
		values.put(COL_D, 0);
		values.put(COL_L, 0);
		values.put(COL_GF, 0);
		values.put(COL_GA, 0);
		values.put(COL_PTS, 0);
		db.insert(TABLE_STANDING, null, values);
		// Ghana
		values.put(COL_CODE, TeamInfo.CODE_GHA);
		values.put(COL_GROUP, TeamInfo.GROUP_G);
		values.put(COL_MP, 0);
		values.put(COL_W, 0);
		values.put(COL_D, 0);
		values.put(COL_L, 0);
		values.put(COL_GF, 0);
		values.put(COL_GA, 0);
		values.put(COL_PTS, 0);
		db.insert(TABLE_STANDING, null, values);
		// USA
		values.put(COL_CODE, TeamInfo.CODE_USA);
		values.put(COL_GROUP, TeamInfo.GROUP_G);
		values.put(COL_MP, 0);
		values.put(COL_W, 0);
		values.put(COL_D, 0);
		values.put(COL_L, 0);
		values.put(COL_GF, 0);
		values.put(COL_GA, 0);
		values.put(COL_PTS, 0);
		db.insert(TABLE_STANDING, null, values);
		// Belgium
		values.put(COL_CODE, TeamInfo.CODE_BEL);
		values.put(COL_GROUP, TeamInfo.GROUP_H);
		values.put(COL_MP, 0);
		values.put(COL_W, 0);
		values.put(COL_D, 0);
		values.put(COL_L, 0);
		values.put(COL_GF, 0);
		values.put(COL_GA, 0);
		values.put(COL_PTS, 0);
		db.insert(TABLE_STANDING, null, values);
		// Algeria
		values.put(COL_CODE, TeamInfo.CODE_ALG);
		values.put(COL_GROUP, TeamInfo.GROUP_H);
		values.put(COL_MP, 0);
		values.put(COL_W, 0);
		values.put(COL_D, 0);
		values.put(COL_L, 0);
		values.put(COL_GF, 0);
		values.put(COL_GA, 0);
		values.put(COL_PTS, 0);
		db.insert(TABLE_STANDING, null, values);
		// Russia
		values.put(COL_CODE, TeamInfo.CODE_RUS);
		values.put(COL_GROUP, TeamInfo.GROUP_H);
		values.put(COL_MP, 0);
		values.put(COL_W, 0);
		values.put(COL_D, 0);
		values.put(COL_L, 0);
		values.put(COL_GF, 0);
		values.put(COL_GA, 0);
		values.put(COL_PTS, 0);
		db.insert(TABLE_STANDING, null, values);
		// Korea Republic
		values.put(COL_CODE, TeamInfo.CODE_KOR);
		values.put(COL_GROUP, TeamInfo.GROUP_H);
		values.put(COL_MP, 0);
		values.put(COL_W, 0);
		values.put(COL_D, 0);
		values.put(COL_L, 0);
		values.put(COL_GF, 0);
		values.put(COL_GA, 0);
		values.put(COL_PTS, 0);
		db.insert(TABLE_STANDING, null, values);
	}
	
	/**
	 * Get all info of team standing
	 * 
	 * @return
	 */
	public List<TeamStanding> getAllInfoTeamStanding() {
		List<TeamStanding> list = new ArrayList<TeamStanding>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = null;
		try {
			c = db.query(TABLE_STANDING, null, null, null, null, null, null);
			if (c != null && c.moveToFirst()) {
				do {
					String code = c.getString(c.getColumnIndex(COL_CODE));
					String group = c.getString(c.getColumnIndex(COL_GROUP));
					int mp = c.getInt(c.getColumnIndex(COL_MP)); // Match played
					int win = c.getInt(c.getColumnIndex(COL_W));
					int draw = c.getInt(c.getColumnIndex(COL_D));
					int lose = c.getInt(c.getColumnIndex(COL_L));
					int gf = c.getInt(c.getColumnIndex(COL_GF));
					int ga = c.getInt(c.getColumnIndex(COL_GA));
					int pts = c.getInt(c.getColumnIndex(COL_PTS));

					list.add(new TeamStanding(code, group, mp, win, draw, lose,
							gf, ga, pts));
				} while (c.moveToNext());
			}
		} catch (SQLiteException e) {
			Log.e("TienVV", "getAllInfoTeamStanding ex=" + e.toString());
		} finally {
			if (c != null && !c.isClosed()) {
				c.close();
			}
			if (db != null && db.isOpen()) {
				db.close();
			}
		}
		return list;
	}

	/**
	 * Get info standing of team in a group
	 * 
	 * @param group
	 * @return
	 */
	public List<TeamStanding> getInfoStandingOfGroup(String group) {
		List<TeamStanding> list = new ArrayList<TeamStanding>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor c = null;
		try {
			c = db.query(TABLE_STANDING, null, COL_GROUP + "='" + group + "'",
					null, null, null, null);
			if (c != null && c.moveToFirst()) {
				do {
					String code = c.getString(c.getColumnIndex(COL_CODE));
					int mp = c.getInt(c.getColumnIndex(COL_MP)); // Match played
					int win = c.getInt(c.getColumnIndex(COL_W));
					int draw = c.getInt(c.getColumnIndex(COL_D));
					int lose = c.getInt(c.getColumnIndex(COL_L));
					int gf = c.getInt(c.getColumnIndex(COL_GF));
					int ga = c.getInt(c.getColumnIndex(COL_GA));
					int pts = c.getInt(c.getColumnIndex(COL_PTS));

					list.add(new TeamStanding(code, group, mp, win, draw, lose,
							gf, ga, pts));
				} while (c.moveToNext());
			}
		} catch (SQLiteException e) {
			Log.e("TienVV", "getInfoStandingOfGroup ex=" + e.toString());
		} finally {
			if (c != null && !c.isClosed()) {
				c.close();
			}
			if (db != null && db.isOpen()) {
				db.close();
			}
		}
		return list;
	}

	/**
	 * Update info standing of a team
	 * 
	 * @param code
	 *            Team's code
	 * @param mp
	 *            Positive if increase, Negative if decrease, = 0 if no change
	 *            this information
	 * @param win
	 *            Positive if increase, Negative if decrease, = 0 if no change
	 *            this information
	 * @param lose
	 *            Positive if increase, Negative if decrease, = 0 if no change
	 *            this information
	 * @param goalFor
	 *            Positive if increase, Negative if decrease, = 0 if no change
	 *            this information
	 * @param goalAgainst
	 *            Positive if increase, Negative if decrease, = 0 if no change
	 *            this information
	 * @param points
	 *            Positive if increase, Negative if decrease, = 0 if no change
	 *            this information
	 */
	public void updateTeamInfoStanding(String code, int mp, int win, int lose,
			int goalFor, int goalAgainst, int points) {
		SQLiteDatabase db = getWritableDatabase();
		Cursor c = null;
		try {
			c = db.query(TABLE_STANDING, null, COL_CODE + "='" + code + "'",
					null, null, null, null);
			if (c != null && c.moveToFirst()) {
				int mpOld = c.getInt(c.getColumnIndex(COL_MP));
				int winOld = c.getInt(c.getColumnIndex(COL_W));
				int loseOld = c.getInt(c.getColumnIndex(COL_L));
				int goalForOld = c.getInt(c.getColumnIndex(COL_GF));
				int goalAgainstOld = c.getInt(c.getColumnIndex(COL_GA));
				int pointsOld = c.getInt(c.getColumnIndex(COL_PTS));

				ContentValues values = new ContentValues();
				if (mp != 0) {
					values.put(COL_MP, mpOld + mp);
				}
				if (win != 0) {
					values.put(COL_W, winOld + win);
				}
				if (lose != 0) {
					values.put(COL_L, loseOld + lose);
				}
				if (goalFor != 0) {
					values.put(COL_GF, goalForOld + goalFor);
				}
				if (goalAgainst != 0) {
					values.put(COL_GA, goalAgainstOld - goalAgainst);
				}
				if (pointsOld != 0) {
					values.put(COL_PTS, pointsOld - points);
				}
				int count = db.update(CREATE_TABLE_STANDING, values, COL_CODE + "='" + code
						+ "'", null);
				if (count > 0) {
					Log.i("TienVV", "updateTeamInfoStanding: update " + COL_CODE + " success");
				} else {
					Log.i("TienVV", "updateTeamInfoStanding: update " + COL_CODE + " failed");
				}
			}
		} catch (SQLiteException e) {
			Log.e("TienVV", "updateTeamInfoStanding ex=" + e.toString());
		} finally {
			if (c != null && c.isClosed() == false) {
				c.close();
			}
			if (db != null && db.isOpen()) {
				db.close();
			}
		}
	}

	@Override
	public synchronized void close() {
		super.close();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}

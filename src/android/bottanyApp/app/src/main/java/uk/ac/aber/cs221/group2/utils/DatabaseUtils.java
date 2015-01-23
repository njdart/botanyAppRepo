package uk.ac.aber.cs221.group2.utils;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by xander on 21/01/2015.
 */
public class DatabaseUtils extends SQLiteOpenHelper {

    public static DatabaseUtils db;

    public static final String databaseName = "site.db";
    public static final int dbVersion = 1;

    public static final String sitesTableName = "site";
    public static final String sitesTable_siteId = "siteId";
    public static final String siteTable_siteName = "siteName";
    public static final String siteTable_siteOSGridRef = "osRef";
    public static final String siteTable_siteTimeStamp = "siteDate";

    public static final String plantsTableName = "plant";
    public static final String plantTable_plantId = "plantId";
    public static final String plantTable_plantLatinName = "plantName";

    public static final String specimenTableName = "specimen";
    public static final String specimenTable_specimenId = "specimenId";
    public static final String specimenTable_specimenName = "specimenName";
    public static final String specimenTable_specimenLat = "specimenLat";
    public static final String specimenTable_specimenLong= "specimenLong";
    public static final String specimenTable_specimenAbundance = "specimenAbundance";
    public static final String specimenTable_specimenComment = "specimenComment";
    public static final String specimenTable_specimenScenePhoto = "specimenScenePhoto";
    public static final String specimenTable_specimenSpecimenPhoto = "specimenSpecimenPhoto";

    public static final String userTableName = "user";
    public static final String userTable_userId = "userId";
    public static final String userTable_userName = "userName";
    public static final String userTable_userPhoneNumber = "userPhoneNumber";
    public static final String userTable_userEmail = "userEmail";

    private static final String createUserTableQuery = "CREATE TABLE " + userTableName + " (" +
            userTable_userId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            userTable_userName + " TEXT, " +
            userTable_userPhoneNumber + " TEXT, "+
            userTable_userEmail + " TEXT); ";

    private static final String createSpecimenTableQuery = "CREATE TABLE " + specimenTableName + " (" +
            specimenTable_specimenId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            specimenTable_specimenName + " TEXT, " +
            specimenTable_specimenLat + " DOUBLE, "+
            specimenTable_specimenLong + " DOUBLE, "+
            specimenTable_specimenAbundance + " TEXT, " +
            specimenTable_specimenComment + " TEXT, " +
            specimenTable_specimenScenePhoto + " TEXT, " +
            specimenTable_specimenSpecimenPhoto + " TEXT ); ";

    private static final String createSitesTableQuery = "CREATE TABLE " + sitesTableName + " (" +
            sitesTable_siteId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            siteTable_siteName + " TEXT, " +
            siteTable_siteTimeStamp + " DOUBLE, "+
            siteTable_siteOSGridRef + " TEXT); ";

    public static final String createPlantsTableQuery = "CREATE TABLE " + plantsTableName + " (" +
            plantTable_plantId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            plantTable_plantLatinName + " TEXT);";

    public static final String[] tableCreationQueries = {
            createSitesTableQuery,
            createPlantsTableQuery,
            createSpecimenTableQuery,
            createUserTableQuery
    };

    public DatabaseUtils(Context context) {
        super(context, databaseName, null, dbVersion);
        this.db = this;
        System.out.println("DATABASE LOCATED AT : " + context.getDatabasePath(databaseName));
        System.out.println("SQL Query " + createSitesTableQuery);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            for(String creationQuery : tableCreationQueries){
                db.execSQL(creationQuery);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + sitesTableName + "; " +
                   "DROP TABLE IF EXISTS " + specimenTableName + "; " +
                   "DROP TABLE IF EXISTS " + userTableName + "; " +
                   "DROP TABLE IF EXISTS " + plantsTableName + "; ");
        onCreate(db);
    }
}

package uk.ac.aber.cs221.group2.utils;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by xander on 21/01/2015.
 */
public class DatabaseUtils extends SQLiteOpenHelper {


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
            createPlantsTableQuery
    };

    public DatabaseUtils(Context context) {
        super(context, databaseName, null, dbVersion);
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
                   "DROP TABLE IF EXISTS " + plantsTableName + "; ");
        onCreate(db);
    }
}

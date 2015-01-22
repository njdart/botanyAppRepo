package uk.ac.aber.cs221.group2.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by xander on 21/01/2015.
 */
public class DatabaseUtils extends SQLiteOpenHelper {


    public static final String databaseName = "botanyApp.db";
    public static final int dbVersion = 1;

    public static final String sitesTableName = "site";
    public static final String sitesTable_siteId = "siteId";
    public static final String siteTable_siteName = "siteName";
    public static final String siteTable_siteOSGridRef = "osRef";
    public static final String siteTable_siteTimeStamp = "siteDate";

    public static final String plantsTable = "plants";
    public static final String plantTable_plantId = "plantId";
    public static final String plantTable_plantLatinName = "plantName";


    private static final String createTableQuery = "CREATE TABLE " + sitesTableName + " (" +
            sitesTable_siteId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            siteTable_siteName + " TEXT, " +
            siteTable_siteTimeStamp + " DOUBLE, "+
            siteTable_siteOSGridRef + " TEXT); " +
            "CREATE TABLE " + plantsTable + " (" +
            plantTable_plantId + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            plantTable_plantLatinName + " TEXT);";


    public DatabaseUtils(Context context) {
        super(context, databaseName, null, dbVersion);
        System.out.println("SQL Query " + createTableQuery);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + sitesTableName);
        onCreate(db);
    }
}

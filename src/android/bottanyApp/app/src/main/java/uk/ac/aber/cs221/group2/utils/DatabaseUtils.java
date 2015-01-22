<<<<<<< HEAD
package uk.ac.aber.cs221.group2.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by xander on 21/01/2015.
 */
public class DatabaseUtils extends SQLiteOpenHelper {


    public static final String DB_NAME = "site.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_ONE = "site";
    public static final String COLUMN_ID = "siteId";
    public static final String COLUMN_VISIT_NAME = "siteName";
    public static final String COLUMN_OS = "os_grid_refrence";

    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_ONE + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCFREMENT, " +
            COLUMN_VISIT_NAME + " TEXT, " +
            COLUMN_OS + " TEXT )";

    public DatabaseUtils(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ONE);
        onCreate(db);
    }
}
=======
package uk.ac.aber.cs221.group2.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by xander on 21/01/2015.
 */
public class DatabaseUtils extends SQLiteOpenHelper {


    public static final String DB_NAME = "site.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_TOURS = "site";
    public static final String COLUMN_ID = "siteId";
    public static final String COLUMN_VISIT_NAME = "siteName";

    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_TOURS + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCFREMENT, " +
            COLUMN_VISIT_NAME + " TEXT )";
    public static final String TABLE_SITE = "site";

    public DatabaseUtils(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TOURS);
        onCreate(db);
    }
}
>>>>>>> origin/master

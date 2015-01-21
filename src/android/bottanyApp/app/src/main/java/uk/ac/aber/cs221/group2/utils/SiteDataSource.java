package uk.ac.aber.cs221.group2.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by xander on 21/01/2015.
 */
public class SiteDataSource {

    SQLiteOpenHelper dbhelper;
    SQLiteDatabase database;
    public SiteDataSource( Context context){
        dbhelper = new DatabaseUtils(context);
        database = dbhelper.getWritableDatabase();
    }

    public void open(){
        database = dbhelper.getWritableDatabase();
    }

    public void close(){
        dbhelper.close();
    }

    public void create(String visit){
        ContentValues values = new ContentValues();
        values.put(DatabaseUtils.COLUMN_VISIT_NAME,visit);
        long insertid = database.insert(DatabaseUtils.TABLE_TOURS, null, values);


    }

}

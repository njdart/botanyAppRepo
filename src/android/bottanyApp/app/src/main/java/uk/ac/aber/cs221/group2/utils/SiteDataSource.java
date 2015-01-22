package uk.ac.aber.cs221.group2.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import uk.ac.aber.cs221.group2.dataClasses.Visit;


/**
 * Created by xander on 21/01/2015.
 */
public class SiteDataSource {

    SQLiteOpenHelper dbhelper;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            DatabaseUtils.COLUMN_ID,
            DatabaseUtils.COLUMN_VISIT_NAME,
            DatabaseUtils.COLUMN_OS,
            DatabaseUtils.COLUMN_DATE
    };
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

    public void create(Visit visit){
        ContentValues values = new ContentValues();
        values.put(DatabaseUtils.COLUMN_VISIT_NAME, visit.getVisitName());
        values.put(DatabaseUtils.COLUMN_OS, visit.getVisitOS());
        values.put(DatabaseUtils.COLUMN_DATE, visit.getVisitDate());

        long insertid = database.insert(DatabaseUtils.TABLE_SITE, null, values);


    }

    public List<String> findAll(){
        List<String>  sites =  new ArrayList<String>();
        Cursor cursor = database.query(DatabaseUtils.TABLE_SITE, allColumns ,null,null,null,null,null);
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                String site = new String();
                site = cursor.getString(cursor.getColumnIndex(DatabaseUtils.COLUMN_VISIT_NAME));
                sites.add(site);
            }
        }
        return sites;

    }

}
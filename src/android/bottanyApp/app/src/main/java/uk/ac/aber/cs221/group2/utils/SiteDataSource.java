package uk.ac.aber.cs221.group2.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
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
            DatabaseUtils.siteTable_siteId,
            DatabaseUtils.siteTable_siteName,
            DatabaseUtils.siteTable_siteOSGridRef,
            DatabaseUtils.siteTable_siteTimeStamp
    };
    public SiteDataSource( Context context){
        if(DatabaseUtils.db == null) {
            DatabaseUtils.db = new DatabaseUtils(context);
        }
        dbhelper = DatabaseUtils.db;
        database = dbhelper.getWritableDatabase();
    }

    public void open(){
       database = dbhelper.getWritableDatabase();
    }

    public void close(){
        dbhelper.close();
    }

    public long create(Visit visit){
        ContentValues values = new ContentValues();
        values.put(DatabaseUtils.siteTable_siteName, visit.getVisitName());
        values.put(DatabaseUtils.siteTable_siteOSGridRef, visit.getVisitOS());
        values.put(DatabaseUtils.siteTable_siteTimeStamp, visit.getVisitDate());
        values.put(DatabaseUtils.siteTable_siteId, visit.getVisitId());

        if(findByName(visit.getVisitName()) == null){
            //It doesnt exist in the database, add it
            return database.insert(DatabaseUtils.sitesTableName, null, values);
        }
        return visit.getVisitId();
    }

    public List<Visit> findAll(){
        List<Visit>  sites =  new ArrayList<Visit>();
        Cursor cursor = database.query(DatabaseUtils.sitesTableName, allColumns ,null,null,null,null,null);
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                Visit v = new Visit(cursor.getString(cursor.getColumnIndex(DatabaseUtils.siteTable_siteName)),
                                    cursor.getString(cursor.getColumnIndex(DatabaseUtils.siteTable_siteOSGridRef)));
                v.setId(cursor.getInt(cursor.getColumnIndex(DatabaseUtils.siteTable_siteId)));
            }
        }
        return sites;
    }

    public String FindByName(String s){

        String selectQuery = "SELECT * FROM " + DatabaseUtils.sitesTableName + "  WHERE " +
                DatabaseUtils.siteTable_siteName + "= '" + s + "';";
        System.out.println(selectQuery);
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                return cursor.getString(cursor.getColumnIndex(DatabaseUtils.siteTable_siteOSGridRef));
            }
        } else {
            return null;
        }
        return null;
    }


    public int FindByNameGetIndex(String s){

        String selectQuery = "SELECT * FROM " + DatabaseUtils.sitesTableName + "  WHERE " +
                DatabaseUtils.siteTable_siteName + "= '" + s + "';";
        System.out.println(selectQuery);
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                return cursor.getInt(cursor.getColumnIndex(DatabaseUtils.siteTable_siteId));
            }
        }
        return -1;
    }

    public Visit findById(int visitId) {
        String selectQuery = "SELECT * FROM " + DatabaseUtils.sitesTableName + "  WHERE " +
                DatabaseUtils.siteTable_siteId + "= " + visitId + ";";
        System.out.println(selectQuery);
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            Visit v = new Visit(cursor.getString(cursor.getColumnIndex(DatabaseUtils.siteTable_siteName)),
                    cursor.getString(cursor.getColumnIndex(DatabaseUtils.siteTable_siteOSGridRef)));
            v.setId(cursor.getInt(cursor.getColumnIndex(DatabaseUtils.siteTable_siteId)));
            return v;
        }
        return null;
    }

    public List<String> findAllNames() {
        String selectQuery = "SELECT * FROM " + DatabaseUtils.sitesTableName + ";";
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.getCount() > 0) {
            while(cursor.moveToNext()) {
                list.add(cursor.getString(cursor.getColumnIndex(DatabaseUtils.siteTable_siteName)));
            }
        }
        return list;
    }

    public Visit findByName(String s) {
        String selectQuery = "SELECT * FROM " + DatabaseUtils.sitesTableName + " WHERE " + DatabaseUtils.siteTable_siteName  + " = '" + s + "' LIMIT 1;";
        ArrayList<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            Visit v = new Visit(cursor.getString(cursor.getColumnIndex(DatabaseUtils.siteTable_siteName)),
                    cursor.getString(cursor.getColumnIndex(DatabaseUtils.siteTable_siteOSGridRef)));
            v.setId(cursor.getInt(cursor.getColumnIndex(DatabaseUtils.siteTable_siteId)));
            return v;
        }
        return null;
    }
}
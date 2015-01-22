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
 * Created by xander on 22/01/2015.
 */
public class PlantDataSource {

    SQLiteOpenHelper dbhelper;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            DatabaseUtils.plantTable_plantId,
            DatabaseUtils.plantTable_plantLatinName,
            };

    public PlantDataSource(Context context){
        dbhelper = new DatabaseUtils(context);
        database = dbhelper.getWritableDatabase();
    }

    public void open(){
        database = dbhelper.getWritableDatabase();
    }

    public void close(){
        dbhelper.close();
    }

    public void create(String plant){
        ContentValues values = new ContentValues();
        values.put(DatabaseUtils.plantTable_plantLatinName, plant);


        long insertid = database.insert(DatabaseUtils.plantsTable, null, values);
    }

    public List<String> findAll(){
        List<String>  sites =  new ArrayList<String>();
        Cursor cursor = database.query(DatabaseUtils.plantsTable, allColumns ,null,null,null,null,null);
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                String site = new String();
                site = cursor.getString(cursor.getColumnIndex(DatabaseUtils.plantTable_plantLatinName));
                sites.add(site);
            }
        }
        return sites;

    }

    public Visit FindByName(String s){
        //work on this
        return null;
    }

}

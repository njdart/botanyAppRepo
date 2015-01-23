package uk.ac.aber.cs221.group2.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONArray;
import org.json.JSONException;

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

    public PlantDataSource open(){
        database = dbhelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbhelper.close();
    }

    /**
     * Super duuper fast sql transactions for large data!
     * @param array the json array to be inserted
     */
    public void jsonTransaction(JSONArray array) {
        ContentValues values;
        database.beginTransaction();
        System.out.println("Making a jsonTransaction of " + array.length() + " items");
        try {
            for (int i = 0; i < array.length(); i++) {
                values = new ContentValues();
                values.put(DatabaseUtils.plantTable_plantLatinName, array.getString(i));
                database.insert(DatabaseUtils.plantsTableName, null, values);
            }
            database.setTransactionSuccessful();
        } catch (JSONException e){
            System.out.println("ERROR IN DATABASE TRANSACTION!");
            e.printStackTrace();
        } finally {
            database.endTransaction();
        }
    }
    public long create(String plant){
        ContentValues values = new ContentValues();
        values.put(DatabaseUtils.plantTable_plantLatinName, plant);

        return database.insert(DatabaseUtils.plantsTableName, null, values);
    }

    public List<String> findAll(){
        List<String>  sites =  new ArrayList<String>();
        Cursor cursor = database.query(DatabaseUtils.plantsTableName, allColumns ,null,null,null,null,null);
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                String site = new String();
                site = cursor.getString(cursor.getColumnIndex(DatabaseUtils.plantTable_plantLatinName));
                sites.add(site);
            }
        }
        return sites;

    }

    public int getRows(){
        String count = "SELECT count(*) FROM " + DatabaseUtils.plantsTableName + " ; ";
        Cursor cursor =  database.rawQuery(count,null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    public Visit FindByName(String s){
        //work on this
        return null;
    }

}

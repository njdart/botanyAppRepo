package uk.ac.aber.cs221.group2.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import uk.ac.aber.cs221.group2.dataClasses.Specimen;
import uk.ac.aber.cs221.group2.dataClasses.User;
import uk.ac.aber.cs221.group2.dataClasses.Visit;

/**
 * Created by xander on 23/01/2015.
 */
public class UserDataSource {


        SQLiteOpenHelper dbhelper;
        SQLiteDatabase database;

        private static final String[] allColumns = {
                DatabaseUtils.userTable_userId,
                DatabaseUtils.userTable_userName,
                DatabaseUtils.userTable_userPhoneNumber,
                DatabaseUtils.userTable_userEmail,

        };

        public UserDataSource(Context context){

            dbhelper = new DatabaseUtils(context);
            database = dbhelper.getWritableDatabase();
        }

        public UserDataSource open(){
            database = dbhelper.getWritableDatabase();
            return this;
        }

        public void close(){
            dbhelper.close();
        }


        public long create(User user){
            ContentValues values = new ContentValues();

            values.put(DatabaseUtils.userTable_userName, user.getName());
            values.put(DatabaseUtils.userTable_userPhoneNumber, user.getUserPhoneNumber());
            values.put(DatabaseUtils.userTable_userEmail, user.getUserEmail());
            return database.insert(DatabaseUtils.userTableName, null, values);
        }

        public List<User> findAll(){
            List<User>  users =  new ArrayList<User>();
            Cursor cursor = database.query(DatabaseUtils.userTableName, allColumns ,null,null,null,null,null);
            if(cursor.getCount()>0){
                while(cursor.moveToNext()){

                    User user = new User(cursor.getString(cursor.getColumnIndex(DatabaseUtils.userTable_userName)),
                            cursor.getString(cursor.getColumnIndex(DatabaseUtils.userTable_userPhoneNumber)),
                            cursor.getString(cursor.getColumnIndex(DatabaseUtils.userTable_userEmail))
                            );

                    users.add(user);
                }
            }
            return users;

        }

    public List<String> findAllNames(){
        List<String>  users =  new ArrayList<String>();
        Cursor cursor = database.query(DatabaseUtils.userTableName, allColumns ,null,null,null,null,null);
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){

                String user = cursor.getString(cursor.getColumnIndex(DatabaseUtils.userTable_userName));

                users.add(user);
            }
        }
        return users;

    }

    public List<String> findMatches(String string){
        List<String>  words =  new ArrayList<String>();

        String selectQuery = "SELECT "+ DatabaseUtils.plantTable_plantLatinName +" FROM " + DatabaseUtils.plantsTableName + "  WHERE "+ DatabaseUtils.plantTable_plantLatinName + " LIKE  '%"+string+"%';";
        System.out.println(selectQuery);
        Cursor cursor = database.rawQuery(selectQuery,null);
        System.out.println("CURSOR: " + cursor.toString());
        if(cursor.getCount()>0) {
            while (cursor.moveToNext()) {
                words.add(cursor.getString(cursor.getColumnIndex(DatabaseUtils.plantTable_plantLatinName)));
            }
        }
        return words;
    }

        public User FindByName(String s) {

            String selectQuery = "SELECT * FROM " + DatabaseUtils.userTableName + "  WHERE " + DatabaseUtils.userTable_userName + "= '" + s + "';";
            System.out.println(selectQuery);
            Cursor cursor = database.rawQuery(selectQuery, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    User user = new User(cursor.getString(cursor.getColumnIndex(DatabaseUtils.userTable_userName)), cursor.getString(cursor.getColumnIndex(DatabaseUtils.userTable_userPhoneNumber)), cursor.getString(cursor.getColumnIndex(DatabaseUtils.userTable_userEmail)));
                    return user;
                }
            } else {
                return null;
            }


            return null;
        }

    }





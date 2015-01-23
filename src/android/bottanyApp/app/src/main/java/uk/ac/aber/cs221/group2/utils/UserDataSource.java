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
            if(DatabaseUtils.db == null) {
                DatabaseUtils.db = new DatabaseUtils(context);
            }
            dbhelper = DatabaseUtils.db;
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
            return database.insert(DatabaseUtils.plantsTableName, null, values);
        }

        public List<User> findAll(){
            List<User>  users =  new ArrayList<User>();
            Cursor cursor = database.query(DatabaseUtils.specimenTableName, allColumns ,null,null,null,null,null);
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

        public Visit FindByName(String s){
            //work on this
            return null;
        }

    }





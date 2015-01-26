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

import uk.ac.aber.cs221.group2.dataClasses.Specimen;
import uk.ac.aber.cs221.group2.dataClasses.User;
import uk.ac.aber.cs221.group2.dataClasses.Visit;

/**
 * Created by xander on 23/01/2015.
 */
public class SpecimenDataSource {

        SQLiteOpenHelper dbhelper;
        SQLiteDatabase database;

        private static final String[] allColumns = {
                DatabaseUtils.specimenTable_specimenId,
                DatabaseUtils.specimenTable_specimenName,
                DatabaseUtils.specimenTable_specimenLat,
                DatabaseUtils.specimenTable_specimenLong,
                DatabaseUtils.specimenTable_specimenAbundance,
                DatabaseUtils.specimenTable_specimenComment,
                DatabaseUtils.specimenTable_specimenScenePhoto,
                DatabaseUtils.specimenTable_specimenSpecimenPhoto
        };

        public SpecimenDataSource(Context context){
            if(DatabaseUtils.db == null) {
                DatabaseUtils.db = new DatabaseUtils(context);
            }
            dbhelper = DatabaseUtils.db;
            database = dbhelper.getWritableDatabase();
        }

        public SpecimenDataSource open(){
            database = dbhelper.getWritableDatabase();
            return this;
        }

        public void close(){
            dbhelper.close();
        }


        public long create(Specimen specimen, User user, Visit visit){
            ContentValues values = new ContentValues();

            values.put(DatabaseUtils.specimenTable_specimenName, specimen.getName());
                    values.put(DatabaseUtils.specimenTable_specimenLat, specimen.getLatitude());
                    values.put(DatabaseUtils.specimenTable_specimenLong, specimen.getLongitude());
                    values.put(DatabaseUtils.specimenTable_specimenAbundance, specimen.getAbundance().toString());
                    if(specimen.getComment() != null)
                        values.put(DatabaseUtils.specimenTable_specimenComment, specimen.getComment());
                    if(specimen.getScenePhotoURI() != null)
                        values.put(DatabaseUtils.specimenTable_specimenScenePhoto, specimen.getScenePhotoURI());
                    if(specimen.getSpecimenPhotoURI() != null)
                        values.put(DatabaseUtils.specimenTable_specimenSpecimenPhoto, specimen.getSpecimenPhotoURI());
            return database.insert(DatabaseUtils.specimenTableName, null, values);
        }

        public List<Specimen> findAll(){
            List<Specimen>  specimens =  new ArrayList<Specimen>();
            Cursor cursor = database.query(DatabaseUtils.specimenTableName, allColumns ,null,null,null,null,null);
            if(cursor.getCount()>0){
                while(cursor.moveToNext()){
                    Specimen.AbundanceEnum abun = Specimen.AbundanceEnum.valueOf(cursor.getString(cursor.getColumnIndex(DatabaseUtils.specimenTable_specimenAbundance)));

                            Specimen specimen = new Specimen(cursor.getString(cursor.getColumnIndex(DatabaseUtils.specimenTable_specimenName)),
                                                             (double)cursor.getDouble(cursor.getColumnIndex(DatabaseUtils.specimenTable_specimenLat)),
                                                             (double)cursor.getDouble(cursor.getColumnIndex(DatabaseUtils.specimenTable_specimenLong)),
                                                             abun,
                                                             cursor.getString(cursor.getColumnIndex(DatabaseUtils.specimenTable_specimenComment)),
                                                             cursor.getString(cursor.getColumnIndex(DatabaseUtils.specimenTable_specimenScenePhoto)),
                                                             cursor.getString(cursor.getColumnIndex(DatabaseUtils.specimenTable_specimenSpecimenPhoto)),
                                                             User.CurrentUser,
                                                             Visit.CurrentVisit);

                    specimens.add(specimen);
                }
            }
            return specimens;

        }

        public Visit FindByName(String s){
            //work on this
            return null;
        }

    }



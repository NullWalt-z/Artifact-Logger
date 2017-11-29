package com.example.walter.artifactlogger;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.Vector;

/**
 * Created by Walter on 10/12/2017.
 */

public class DBHandler extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "Site_Info";
    private static final int DATABASE_VERSION = 1;

    //private static final String SITE_TABLE = "site";
    //private static final String SITE_COLUMN_NUMBER = "site_number";
    //private static final String SITE_COLUMN_LOCATION = "location";

    //private static final String UNIT_TABLE = "unit";
    //private static final String UNIT_COLUMN_SITE_NUMBER = "site_number";
    //private static final String UNIT_COLUMN_NUMBER = "unit_number";
    //private static final String UNIT_COLUMN_LOCATION = "location";

    private static final String ARTIFACT_TABLE = "artifact";
    //private static final String ARTIFACT_COLUMN_UNIT_NUMBER = "unit_number";
    private static final String ARTIFACT_COLUMN_NUMBER = "artifact_number";
    private static final String ARTIFACT_COLUMN_LOCATION = "location";
    private static final String ARTIFACT_COLUMN_DEPTH = "depth";
    private static final String ARTIFACT_COLUMN_AGE = "pre_hist";
    private static final String ARTIFACT_COLUMN_DESCRIPTION = "desc";

    //private static final String PHOTO_TABLE = "photo";
    //private static final String PHOTO_COLUMN_NUMBER = "photo_number";
    //private static final String PHOTO_COLUMN_ARTIFACT_NUMBER = "artifact_number";
    //private static final String PHOTO_COLUMN_PATH = "path";

    //public DBHandler(Context context) {
    //  super(context, DATABASE_NAME, null,1);
    //}

    public DBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        //database.execSQL("create table " + SITE_TABLE + "(" + SITE_COLUMN_NUMBER + " primary key, " + SITE_COLUMN_LOCATION + ")");
        //database.execSQL("create table unit" + "(unit_number text primary key, site_number text, location text, depth text, pre_hist text, desc text)");
        database.execSQL("create table "+ARTIFACT_TABLE+ "("+ARTIFACT_COLUMN_NUMBER+" text primary key, "+ARTIFACT_COLUMN_LOCATION+" text, "+ARTIFACT_COLUMN_DEPTH+" text, "+ARTIFACT_COLUMN_AGE+" text, "+ARTIFACT_COLUMN_DESCRIPTION+" text)");
        //database.execSQL("create table photo" + "(photo_number text primary key, artifact_number text, path text");
    }


    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        onCreate(database);
    }

    public void addArtifact(artifactObject artifact) {

        ContentValues values = new ContentValues();
        values.put(ARTIFACT_COLUMN_NUMBER, artifact.getArtifact_number());
        values.put(ARTIFACT_COLUMN_LOCATION, artifact.getLocation());
        values.put(ARTIFACT_COLUMN_DEPTH, artifact.getDepth());
        values.put(ARTIFACT_COLUMN_AGE, artifact.getHist_pre());
        values.put(ARTIFACT_COLUMN_DESCRIPTION, artifact.getDescription());
        //values.put(COLUMN_QUANTITY, product.getQuantity());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(ARTIFACT_TABLE, null, values);
        db.close();
    }

    public artifactObject getArtifact(String art_num) {
        String query = "Select * FROM " + ARTIFACT_TABLE + " WHERE artifact_number = " +art_num;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        artifactObject artifact = new artifactObject();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            artifact.setArtifact_number(cursor.getString(0));
            artifact.setLocation(cursor.getString(1));
            artifact.setDepth(cursor.getString(2));
            artifact.setHist_pre(cursor.getString(3));
            artifact.setPhotos(cursor.getString(4));
            cursor.close();
            }
        else {
            artifact = null;
        }
        db.close();
        return artifact;
    }

    public Vector<artifactObject> getAllArtifacts() {
        String query = "Select * FROM " + ARTIFACT_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        artifactObject artifact = new artifactObject();
        Vector<artifactObject> artifactList = new Vector(1);
        /*
        do{
            if (cursor.moveToFirst()) {
                cursor.moveToFirst();
                artifact.setArtifact_number(cursor.getString(0));
                artifact.setLocation(cursor.getString(1));
                artifact.setDepth(cursor.getString(2));
                artifact.setHist_pre(cursor.getString(3));
                artifact.setPhotos(cursor.getString(4));
                cursor.close();
                artifactList.add(artifact);
            } else {
                artifact = null;
            }
            cursor.

        }
        while(artifact != null);
        */

        int offset = 0;
        while(cursor.moveToNext()) {
            artifact.setArtifact_number(cursor.getString(0+offset));
            artifact.setLocation(cursor.getString(1+offset));
            artifact.setDepth(cursor.getString(2+offset));
            artifact.setHist_pre(cursor.getString(3+offset));
            artifact.setPhotos(cursor.getString(4+offset));
            artifactList.add(artifact);
            offset += 5;
        }
        db.close();
        return artifactList;
    }

}

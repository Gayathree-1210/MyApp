package com.example.travelapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "TravelApp";
    //table names

    private static final String BOOKING_TABLE_NAME = "bookings";
    //common column names
    private  static final String ID = "id";
    private static final String STARTED = "started";
    private static final String FINISHED = "finished";

    //hotel booking table
    private  static  final String CUSNAME = "name";
    private  static  final String CUSMAIL = "email";
    private  static  final String CUSMOBILE = "mobile";
    private  static  final String CUSID = "nic";
    private  static final  String HOTEL_NAME = "hotelName";


    public DBHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String BOOKING_TABLE_CREATE_QUERY = "CREATE TABLE "+BOOKING_TABLE_NAME+" " +
                "("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +CUSNAME + " TEXT,"
                +HOTEL_NAME + " TEXT,"
                +CUSMAIL + " TEXT,"
                +CUSMOBILE + " TEXT,"
                +CUSID + " TEXT,"
                +STARTED+ " TEXT,"
                +FINISHED+" TEXT" +
                ");";
        db.execSQL(BOOKING_TABLE_CREATE_QUERY);
        Log.d("T1","db created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        String DROP_BOOKING_TABLE_QUERY = "DROP TABLE IF EXISTS "+ BOOKING_TABLE_NAME;
        // Drop older table if existed
        db.execSQL(DROP_BOOKING_TABLE_QUERY);
        // Create tables again
        onCreate(db);

    }

    public void addBooking(BookingModel booking){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CUSNAME,booking.getName());
        contentValues.put(HOTEL_NAME,booking.gethName());
        contentValues.put(CUSMAIL,booking.getName());
        contentValues.put(CUSMOBILE,booking.getMobile());
        contentValues.put(CUSID,booking.getNic());
        contentValues.put(STARTED,booking.getStarted());
        contentValues.put(FINISHED,booking.getFinished());

        //save to table
        sqLiteDatabase.insert(BOOKING_TABLE_NAME,null,contentValues);
        // close database
        sqLiteDatabase.close();

    }
}

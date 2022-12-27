package com.vibhunorby.totalpaisa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "currencydb";

    // below int is our database version
    private static final int DB_VERSION = 6;

    // below variable is for our table name.
    private static final String TABLE_NAME = "mycurrencies";

    // below variable is for our id column.
    private static final String ID_COL = "id";


    private static final String TWO_THOUSAND_COL = "two_thousand_col";

    private static final String FIVE_HUNDRED_COL = "five_hundred";

    private static final String TWO_HUNDRED_COL = "two_hundred";

    private static final String ONE_HUNDRED_COL = "one_hundred";

    private static final String FIFTY_COL = "fifty";

    private static final String TWENTY_COL = "twenty";

    private static final String TEN_COL = "ten";

    private static final String FIVE_COL = "five";

    private static final String TWENTY_COIN_COL = "twenty_coin";

    private static final String TEN_COIN_COL = "ten_coin";

    private static final String FIVE_COIN_COL = "five_coin";

    private static final String TWO_COIN_COL = "two_coin";

    private static final String ONE_COIN_COL = "one_coin";

    private static final String EXTRA_COIN_COL = "extra_coin";

    private static final String RESULT_COL = "result";

    private static final String PAYEE_COL = "payee_name";

    private static final String DATE_COL = "date";

    private static final String TIME_COL = "time";

    private static final String DAY_COL =  "day";

    private static final String NOTES_COL = "notes";

    private static final String COINS_COL = "coins";







    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TWO_THOUSAND_COL + " TEXT,"
                + FIVE_HUNDRED_COL + " TEXT,"
                + TWO_HUNDRED_COL + " TEXT,"
                + ONE_HUNDRED_COL + " TEXT,"
                + FIFTY_COL + " TEXT,"
                + TWENTY_COL + " TEXT,"
                + TEN_COL + " TEXT,"
                + FIVE_COL + " TEXT,"
                + TWENTY_COIN_COL + " TEXT,"
                + TEN_COIN_COL + " TEXT,"
                + FIVE_COIN_COL + " TEXT,"
                + TWO_COIN_COL + " TEXT,"
                + ONE_COIN_COL + " TEXT,"
                + EXTRA_COIN_COL + " TEXT,"
                + RESULT_COL + " TEXT,"
                + PAYEE_COL + " TEXT,"
                + DATE_COL + " TEXT,"
                + TIME_COL + " TEXT,"
                + DAY_COL + " TEXT,"
                + NOTES_COL + " TEXT,"
                + COINS_COL + " TEXT)";


        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addNewCurrency(String currency2000, String currency500, String currency200, String currency100,
                             String currency50, String currency20, String currency10, String currency5,
                             String currency20_coin, String currency10_coin, String currency5_coin, String currency_2_coin,
                             String currency1_coin, String currency_extra, String result,String payee,String date,
                               String time, String day, String notes, String coins) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(TWO_THOUSAND_COL, currency2000);
        values.put(FIVE_HUNDRED_COL, currency500);
        values.put(TWO_HUNDRED_COL, currency200);
        values.put(ONE_HUNDRED_COL, currency100);
        values.put(FIFTY_COL, currency50);
        values.put(TWENTY_COL, currency20);
        values.put(TEN_COL, currency10);
        values.put(FIVE_COL, currency5);
        values.put(TWENTY_COIN_COL, currency20_coin);
        values.put(TEN_COIN_COL, currency10_coin);
        values.put(FIVE_COIN_COL, currency5_coin);
        values.put(TWO_COIN_COL, currency_2_coin);
        values.put(ONE_COIN_COL, currency1_coin);
        values.put(EXTRA_COIN_COL, currency_extra);
        values.put(RESULT_COL, result);
        values.put(PAYEE_COL,payee);
        values.put(DATE_COL,date);
        values.put(TIME_COL,time);
        values.put(DAY_COL,day);
        values.put(NOTES_COL,notes);
        values.put(COINS_COL,coins);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    // we have created a new method for reading all the courses.
    public ArrayList<CurrencyModal> readCurrencies() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCurrencies = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<CurrencyModal> currencyModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCurrencies.moveToLast()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                currencyModalArrayList.add(new CurrencyModal(cursorCurrencies.getString(1),
                        cursorCurrencies.getString(2),
                        cursorCurrencies.getString(3),
                        cursorCurrencies.getString(4),
                        cursorCurrencies.getString(5),
                        cursorCurrencies.getString(6),
                        cursorCurrencies.getString(7),
                        cursorCurrencies.getString(8),
                        cursorCurrencies.getString(9),
                        cursorCurrencies.getString(10),
                        cursorCurrencies.getString(11),
                        cursorCurrencies.getString(12),
                        cursorCurrencies.getString(13),
                        cursorCurrencies.getString(14),
                        cursorCurrencies.getString(15),
                        cursorCurrencies.getString(16),
                        cursorCurrencies.getString(17),
                        cursorCurrencies.getString(18),
                        cursorCurrencies.getString(19),
                        cursorCurrencies.getString(20),
                        cursorCurrencies.getString(21)
                ));
            } while (cursorCurrencies.moveToPrevious());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCurrencies.close();
        return currencyModalArrayList;
    }

    public void deleteCurrency(String currency2000, String currency500, String currency200, String currency100,
                               String currency50, String currency20, String currency10, String currency5,
                               String currency20_coin, String currency10_coin, String currency5_coin, String currency_2_coin,
                               String currency1_coin, String currency_extra, String result, String payee, String date,
                               String time, String day, String notes, String coins) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, "two_thousand_col=? and five_hundred=? and two_hundred=? and one_hundred=? and fifty=? and twenty=? and ten=?" +
                " and five=? and twenty_coin=? and ten_coin=? and five_coin=? and two_coin=? and one_coin=? and extra_coin=? and result=? and payee_name=? and date=? and time=? and day=? and notes=? and coins=?",new String[] {currency2000,currency500,currency200,currency100
        ,currency50,currency20,currency10,currency5,currency20_coin,currency10_coin,currency5_coin,currency_2_coin
                ,currency1_coin,currency_extra,result,payee,date,time,day,notes,coins});
        db.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }







}
package preston.com.stockapp.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Alex Preston on 10/30/16.
 */

public final class FeederReaderContract {

    private static final String TEXT_TYPE_TEXT = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_STOCKS_CREATE =
            "CREATE TABLE " + FeedEntry.TABLE_NAME_STOCKS + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.NAME + TEXT_TYPE_TEXT + COMMA_SEP +
                    FeedEntry.DAILY_VOLUME + TEXT_TYPE_TEXT + COMMA_SEP +
                    FeedEntry.CHANGE + TEXT_TYPE_TEXT + COMMA_SEP +
                    FeedEntry.DAYS_HIGH + TEXT_TYPE_TEXT + COMMA_SEP +
                    FeedEntry.DAYS_LOW + TEXT_TYPE_TEXT + COMMA_SEP +
                    FeedEntry.YEARS_HIGH + TEXT_TYPE_TEXT + COMMA_SEP +
                    FeedEntry.YEARS_LOW + TEXT_TYPE_TEXT + COMMA_SEP +
                    FeedEntry.MARKET_CAPITALIZATION + TEXT_TYPE_TEXT + COMMA_SEP +
                    FeedEntry.LAST_TRADE_PRICE + TEXT_TYPE_TEXT + COMMA_SEP +
                    FeedEntry.VOLUME + TEXT_TYPE_TEXT + COMMA_SEP +
                    FeedEntry.STOCK_EXCHANGE + TEXT_TYPE_TEXT + COMMA_SEP +
                    FeedEntry.DAYS_RANGE + TEXT_TYPE_TEXT + COMMA_SEP + " )";

    private static final String SQL_USERS_CREATE =
            "CREATE TABLE " + FeedEntry.TABLE_NAME_USERS + " ("
                    + FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedEntry.STOCKS + TEXT_TYPE_TEXT + " )";


    private static final String SQL_DELETE_ENTRIES_STOCKS =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME_STOCKS;

    private static final String SQL_DELETE_ENTRIES_USERS =
            "DROP TABLE IF EXISTS" + FeedEntry.TABLE_NAME_USERS;

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private FeederReaderContract() {

    }

    /* Inner class that defines the table contents */
    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME_USERS = "User";
        public static final String TABLE_NAME_STOCKS = "Stocks";
        public static final String STOCKS = "Stocks";
        public static final long DAILY_VOLUME = 0;
        public static final double CHANGE = 0;
        public static final double DAYS_HIGH = 0;
        public static final double DAYS_LOW = 0;
        public static final double YEARS_HIGH = 0;
        public static final double YEARS_LOW = 0;
        public static final String MARKET_CAPITALIZATION = "marketCapitalization";
        public static final double LAST_TRADE_PRICE = 0;
        public static final String NAME = "name";
        public static final long VOLUME = 0;
        public static final String STOCK_EXCHANGE = "stockExchange";
        public static final String DAYS_RANGE = "daysRange";

    }


    public class FeederReaderDBHelper extends SQLiteOpenHelper {


        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "StockReader.db";

        /**
         * Constructor
         * @param context
         */
        public FeederReaderDBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_STOCKS_CREATE);
            db.execSQL(SQL_USERS_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(SQL_DELETE_ENTRIES_STOCKS);
            db.execSQL(SQL_DELETE_ENTRIES_USERS);
            onCreate(db);
        }

        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            super.onDowngrade(db, oldVersion, newVersion);
        }
    }

}

package preston.com.stockapp.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.preston.data.repo.greendao.DaoMaster;
import com.preston.data.repo.greendao.DaoSession;
import com.preston.data.repo.greendao.StockDao;
import com.preston.data.repo.greendao.UserDao;

/**
 * Singleton for the database
 * Created by Alex Preston on 1/26/17.
 */

public class Database {
    private static Context context;
    private static Database instance = null;
    private UserDao userDao;
    private StockDao stockDao;


    /**
     * Returns UserDao
     *
     * @return
     */
    public UserDao getUserDao() {
        return userDao;
    }

    /**
     * Returns StockDao
     * @return
     */
    public StockDao getStockDao() {
        return stockDao;
    }

    /**
     * Gets instance of the userDatabase
     *
     * @param context
     * @return
     */
    public static Database getInstance(Context context) {
        if (instance == null) {
            synchronized (Database.class) {
                if (instance == null) {
                    instance = new Database(context.getApplicationContext());
                }
            }

        }
        return instance;
    }

    /**
     * Constructor
     *
     * @param context
     */
    public Database(Context context) {
        this.context = context;
    }

    /**
     * Check if the database exist and can be read.
     *
     * @return true if it exists and can be read, false if it doesn't
     */
    public boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            checkDB = SQLiteDatabase.openDatabase("../app/src-gen-dao", null,
                    SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        } catch (SQLiteException e) {
            // database doesn't exist yet.
            setUpDB();
        }
        return checkDB != null;
    }

    /**
     * Configures a returnable db
     *
     * @return UserDao
     */
    private void setUpDB() {
        DaoMaster.DevOpenHelper userMasterHelper = new DaoMaster.DevOpenHelper(context, "USER", null); //Create DB file
        SQLiteDatabase userDB = userMasterHelper.getWritableDatabase();
        DaoMaster userMaster = new DaoMaster(userDB);
        DaoSession masterUserSession = userMaster.newSession();
        userDao = masterUserSession.getUserDao();

        DaoMaster.DevOpenHelper stockMasterHelper = new DaoMaster.DevOpenHelper(context, "STOCK", null);
        SQLiteDatabase stockDB = stockMasterHelper.getWritableDatabase();
        DaoMaster stockMaster = new DaoMaster(stockDB);
        DaoSession masterStockSession = stockMaster.newSession();
        stockDao = masterStockSession.getStockDao();
    }
}

package preston.com.stockapp.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.preston.data.repo.greendao.DaoMaster;
import com.preston.data.repo.greendao.DaoSession;
import com.preston.data.repo.greendao.UserDao;

/**
 * Singleton for the database
 * Created by Alex Preston on 1/26/17.
 */

public class UserDatabase {
    private static Context context;
    private static UserDatabase instance = null;
    private UserDao userDao;


    /**
     * Returns UserDao
     *
     * @return
     */
    public UserDao getUserDao() {
        return userDao;
    }

    /**
     * Gets instance of the userDatabase
     *
     * @param context
     * @return
     */
    public static UserDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (UserDatabase.class) {
                if (instance == null) {
                    instance = new UserDatabase(context.getApplicationContext());
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
    public UserDatabase(Context context) {
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
            DaoMaster.DevOpenHelper masterHelper = new DaoMaster.DevOpenHelper(context, "USER", null); //Create DB file
            SQLiteDatabase db = masterHelper.getWritableDatabase();
            DaoMaster master = new DaoMaster(db);
            DaoSession masterSession = master.newSession();
            userDao = masterSession.getUserDao();
        }
        return checkDB != null;
    }

    /**
     * Configures a returnable db
     *
     * @return UserDao
     */
    public void setUpDB() {

    }
}

package preston.com.stockapp.util;

import android.content.Context;

import com.preston.data.repo.greendao.User;
import com.preston.data.repo.greendao.UserDao;

import java.util.List;

/**
 * Created by Alex Preston on 2/21/17.
 */

public class UserDatabaseLoader extends android.support.v4.content.AsyncTaskLoader<User> {
    private Database userDatabase;
    private UserDao userDao;
    private User user;
    private String username, password;

    public UserDatabaseLoader(Context context, String username, String password) {
        super(context);
        userDatabase = Database.getInstance(context);
        userDatabase.checkDataBase();
        userDao = userDatabase.getUserDao();
        this.username = username;
        this.password = password;
    }
    @Override
    public User loadInBackground() {
        if (checkValidAcc(username, password)) {
            return user;
        }
        return null;
    }

    /**
     * Checks to see if the account is in the system
     *
     * @param username
     * @param password
     * @return
     */
    private boolean checkValidAcc(String username, String password) {
        List<User> userList = userDao.queryBuilder().orderDesc(UserDao.Properties.EncodedId).build().list();

        if (userList.size() > 0) {
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getEncodedId().contentEquals(username) && userList.get(i).getPassword().contentEquals(password)) {
                    user = userList.get(i);
                    return true;
                }
            }
        }
        return false;
    }
}

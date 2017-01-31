package preston.com.stockapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.preston.data.repo.greendao.User;
import com.preston.data.repo.greendao.UserDao;

import java.util.List;

import preston.com.stockapp.util.UserDatabase;

/**
 * Created by Alex Preston on 10/27/16.
 */

public class SignInActivity extends AppCompatActivity {

    private static final String TAG = String.format("tag.%s", SignInActivity.class.getName());
    private EditText userName, password;
    private Button login;
    private UserDao userDao;
    private User user;
    private TextView greeting;
    private UserDatabase userDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_activity);

        userDatabase = UserDatabase.getInstance(this);

        userDao = userDatabase.getUserDao();


        userName = (EditText) findViewById(R.id.username_sign_in);
        password = (EditText) findViewById(R.id.password_sign_in);
        greeting = (TextView) findViewById(R.id.login_text_sign_in);
        login = (Button) findViewById(R.id.sign_in_button_sign_in);
        attachClickListener(login);

        setFonts();

    }

    /**
     * Sets the click listener
     *
     * @param button
     */
    private void attachClickListener(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidAcc(userName.getText().toString(), password.getText().toString())) {
                    signInSuccessful(user);
                } else {
                    signInFailure();
                }
            }
        });

    }

    /**
     * Sign in was a failure clear fields
     */
    private void signInFailure() {
        userName.setText("Wrong Username/Password");
        password.setText("Wrong Password");
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


    /**
     * Sign in is successful
     *
     * @param userToPass
     */
    private void signInSuccessful(User userToPass) {
        Intent intent = new Intent(this, PortfolioActivity.class);
        intent.putExtra("User", userToPass);
        startActivity(intent);
    }


    /**
     * Sets the fonts
     */
    public void setFonts() {

        Typeface face = Typeface.createFromAsset(getAssets(), "bondini.ttf");
        userName.setTypeface(face);
        password.setTypeface(face);
        greeting.setTypeface(face);
        login.setTypeface(face);

    }
}

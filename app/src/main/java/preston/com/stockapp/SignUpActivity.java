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

import preston.com.stockapp.util.UserDatabase;

/**
 * Created by Alex Preston on 1/18/17.
 */

public class SignUpActivity extends AppCompatActivity  {

    private EditText username, email, password, confirmPassword;
    private Button create;
    private TextView greeting;
    private User user;
    private UserDatabase userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);

        userDatabase = UserDatabase.getInstance(this);;
        userDatabase.setUpDB();


        //EditText
        email = (EditText) findViewById(R.id.email_sign_up);
        username = (EditText) findViewById(R.id.username_sign_up);
        password = (EditText) findViewById(R.id.password_sign_up);
        confirmPassword = (EditText) findViewById(R.id.confirm_password_sign_up);

        //Button
        create = (Button) findViewById(R.id.create_button_sign_up);
        attachOnClickListener();

        //Text view
        greeting = (TextView) findViewById(R.id.greeting_sign_up);


        setFonts();
    }

    /**
     * Attaches an onClick Listener
     */
    public void attachOnClickListener() {
     create.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             user = new User();
             if (checkNotNull(email.getText().toString(), username.getText().toString())) {
                 if (checkPasswordSame(password.getText().toString(), confirmPassword.getText().toString())) {
                     user.setUsername(username.getText().toString());
                     user.setEncodedId(username.getText().toString());
                     user.setEmail(email.getText().toString());
                     user.setPassword(password.getText().toString());
                     saveToSQL(user);
                     goToMyPortfolio(user);
                 }
             }
         }
     });
    }


    /**
     * Saves to the SQL Db
     *
     * @param userObj
     */
    public void saveToSQL(User userObj) {
        userDatabase.getUserDao().insertOrReplace(userObj);
    }

    /**
     * Sets the fonts
     */
    private void setFonts() {

        Typeface face = Typeface.createFromAsset(getAssets(), "bondini.ttf");
        email.setTypeface(face);
        username.setTypeface(face);
        password.setTypeface(face);
        confirmPassword.setTypeface(face);
        create.setTypeface(face);
        greeting.setTypeface(face);
    }




    /**
     *
     */
    private void goToMyPortfolio(User userObj) {
        Intent intent = new Intent(this, PortfolioActivity.class);
        intent.putExtra("User", userObj);
        startActivity(intent);
    }


    /**
     * Checks if the email and confirm email are the same
     *
     * @param email
     * @param userName
     * @return
     */
    private boolean checkNotNull(String userName, String email) {
        return userName != null && email != null ? true : false;
    }

    /**
     * Checks if the password and Confirm Pass are the same
     *
     * @param password
     * @param confirmPassword
     * @return
     */
    private boolean checkPasswordSame(String password, String confirmPassword) {
        return password.contentEquals(confirmPassword);
    }
}

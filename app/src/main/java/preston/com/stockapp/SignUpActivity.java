package preston.com.stockapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.preston.data.repo.greendao.User;

import preston.com.stockapp.util.Database;

/**
 * Created by Alex Preston on 1/18/17.
 */

public class SignUpActivity extends AppCompatActivity {

    private static final String USERNAME = "username";
    private EditText username, email, password, confirmPassword;
    private Button create;
    private TextView greeting;
    private User user;
    private Database userDatabase;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);

        checkPermissions();

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
     * Checks if the app has permission to use DB
     */
    public void checkPermissions() {
        //Checks if app has permission
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            //We don't have permission, should we show explanation
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Context context = getApplicationContext();
                CharSequence text = "App needs to access Database";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            } else {
                //No need for explanation, first time asking
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        } else {
            userDatabase = Database.getInstance(this);
            userDatabase.checkDataBase();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    userDatabase = Database.getInstance(this);
                    userDatabase.checkDataBase();
                } else {
                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                }

            }
        }
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
                        goToMyPortfolio(user.getEncodedId());
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
     *Launches the MyPortfolio Activity
     */
    private void goToMyPortfolio(String id) {
        Intent intent = new Intent(this, PortfolioActivity.class);
        intent.putExtra(USERNAME, id);
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
}

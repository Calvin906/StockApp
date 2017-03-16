package preston.com.stockapp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.preston.data.repo.greendao.User;
import com.preston.data.repo.greendao.UserDao;

import preston.com.stockapp.util.Database;
import preston.com.stockapp.util.UserDatabaseLoader;

/**
 * Created by Alex Preston on 10/27/16.
 */

public class SignInActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<User> {

    private static final String TAG = String.format("tag.%s", SignInActivity.class.getName());
    private static final String EXTRA_TEXT = "User";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1;
    private EditText userName, password;
    private Button login;
    private UserDao userDao;
    private TextView greeting;
    private Database userDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_activity);

        checkPermissions();

        userName = (EditText) findViewById(R.id.username_sign_in);
        password = (EditText) findViewById(R.id.password_sign_in);

        greeting = (TextView) findViewById(R.id.login_text_sign_in);
        login = (Button) findViewById(R.id.sign_in_button_sign_in);
        attachButtonClickListener(login);

        setFonts();

    }

    /**
     * Checks if the app has permission to use DB
     */
    public void checkPermissions() {
        //Checks if app has permission
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            //We don't have permission, should we show explanation
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Context context = getApplicationContext();
                CharSequence text = "App needs to access Database";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            } else {
                //No need for explanation, first time asking
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        } else {
            userDatabase = Database.getInstance(this);
            userDatabase.checkDataBase();
            userDao = userDatabase.getUserDao();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    userDatabase = Database.getInstance(this);
                    userDatabase.checkDataBase();
                    userDao = userDatabase.getUserDao();
                } else {
                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                }

            }
        }
    }

    /**
     * Sets the click listener
     *
     * @param button
     */
    private void attachButtonClickListener(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString(USERNAME, userName.getText().toString());
                bundle.putString(PASSWORD, password.getText().toString());
                getSupportLoaderManager().restartLoader(R.id.user_loader, bundle, SignInActivity.this).forceLoad();

            }
        });

    }


    /**
     * Sign in was a failure clear fields
     */
    private void signInFailure() {
        userName.setText("");
        password.setText("");
        Context context = getApplicationContext();
        CharSequence text = "Username and password might be wrong!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }


    /**
     * Sign in is successful
     *
     * @param id
     */
    private void signInSuccessful(String id) {
        Intent intent = new Intent(this, PortfolioActivity.class);
        intent.putExtra(USERNAME, id);
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


    @Override
    public android.support.v4.content.Loader<User> onCreateLoader(int id, Bundle args) {
        return new UserDatabaseLoader(SignInActivity.this, args.getString(USERNAME), args.getString(PASSWORD));
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<User> loader, User data) {
        if (data != null) {
            signInSuccessful(data.getEncodedId());
        } else {
            signInFailure();
        }

    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<User> loader) {

    }
}

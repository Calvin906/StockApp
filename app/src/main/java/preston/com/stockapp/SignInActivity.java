package preston.com.stockapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.preston.data.repo.greendao.DaoMaster;
import com.preston.data.repo.greendao.DaoSession;

/**
 * Created by Alex Preston on 10/27/16.
 */

public class SignInActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

//    private com.google.android.gms.common.SignInButton signIn;
//    private GoogleSignInOptions gso;
//    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;
    private static final String TAG = String.format("tag.%s", SignInActivity.class.getName());
    private EditText email, password;
    private Button login;
    private DaoSession daoSession;
    private TextView greeting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_activity);

//        signIn = (com.google.android.gms.common.SignInButton) findViewById(R.id.sign_in_button_si);
//
//
//        signIn.setOnClickListener(this);

//        // Configure sign-in to request the user's ID, email address, and basic
//        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
//        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
//
//        // Build a GoogleApiClient with access to the Google Sign-In API and the
//        // options specified by gso.
////        mGoogleApiClient = new GoogleApiClient.Builder(this)
////                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
////                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
////                .build();

        email = (EditText) findViewById(R.id.email_sign_in);
        password = (EditText) findViewById(R.id.password_sign_in);
        greeting = (TextView) findViewById(R.id.login_text_sign_in);
        login = (Button) findViewById(R.id.sign_in_button_sign_in);

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "user-db", null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

        setFonts();;

    }

    /**
     * Method implemented by onClickListener. Handles actions of clicks
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button_sign_in:
                signIn();
                break;
        }
    }



//    /**
//     * On the activity result, if the request code is the RC code, then it will create a google signIn result
//     * and check with teh handleSignInResult Method to see if it was successful
//     *
//     * @param requestCode
//     * @param resultCode
//     * @param data
//     */
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == RC_SIGN_IN) {
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            handleSignInResults(result);
//        }
//    }

    /**
     * This method checks to see if the sign in result is successful
     *
     * @param result
     */
    private void handleSignInResults(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult: " + result.isSuccess());
        if (result.isSuccess()) {
            //Sign in is successful, show UI TODO
            // GoogleSignInAccount acct = result.getSignInAccount();
//            User user = new User();
//            user.setEmail(email.getText().toString());
//            user.setPassword(password.getText().toString());
//            UserDao userDao = daoSession.getUserDao();
//            userDao.insertOrReplace(user);
            Intent intent = new Intent(this, PortfolioActivity.class);
            startActivity(intent);


        } else {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }

    }

    /**
     * Launch the sign In method
     */
    private void signIn() {
//        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
//        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    /**
     * Sets the fonts
     */
    public void setFonts() {

        Typeface face = Typeface.createFromAsset(getAssets(), "bondini.ttf");
        email.setTypeface(face);
        password.setTypeface(face);
        greeting.setTypeface(face);
        login.setTypeface(face);

    }
}

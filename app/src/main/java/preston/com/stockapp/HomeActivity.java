package preston.com.stockapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import preston.com.stockapp.util.SignUpFragment;

/**
 * Created by Alex Preston on 9/5/16.
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView welcome;
    private TextView signUp;
    private TextView signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        welcome = (TextView) findViewById(R.id.welcome_home);
        signUp = (TextView) findViewById(R.id.sign_up_home);
        signIn = (TextView) findViewById(R.id.sign_in);

        signUp.setOnClickListener(this);
        signIn.setOnClickListener(this);

        setFonts();

    }

    /**
     * onClick method dictates which button was clicked
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_up_home:
                signUpFrag();
                break;
            case R.id.sign_in:
                singInActivity();
                break;
        }
    }

    /**
     * A method which is called when the sign up text is clicked
     * Starts a pop up dialog
     */
    public void signUpFrag() {

        FragmentManager manager = getSupportFragmentManager();
        SignUpFragment fragment = new SignUpFragment();
        fragment.show(manager, "Fragment SignUp");
    }

    /**
     * A method to call the sign in Activity.
     */
    public void singInActivity() {
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }

    /**
     * Sets the fonts for the homepage
     */
    public void setFonts() {

        Typeface face = Typeface.createFromAsset(getAssets(), "bondini.ttf");
        welcome.setTypeface(face);
        signUp.setTypeface(face);
        signIn.setTypeface(face);
    }


}

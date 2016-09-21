package preston.com.stockapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import preston.com.stockapp.util.LoginFragment;
import preston.com.stockapp.util.SignUpFragment;

/**
 * Created by Alex Preston on 9/5/16.
 */
public class HomeActivity extends AppCompatActivity {

    private TextView welcome;
    private TextView signUp;
    private TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        welcome = (TextView) findViewById(R.id.welcome_home);
        signUp = (TextView) findViewById(R.id.sign_up_home);
        login = (TextView) findViewById(R.id.login_home);

        Typeface face = Typeface.createFromAsset(getAssets(), "bondini.ttf");
        welcome.setTypeface(face);
        signUp.setTypeface(face);
        login.setTypeface(face);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpFrag();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginFragment();
            }
        });

    }

    /**
     * A method which is called when the signup text is clicked
     * Starts a pop up dialog
     */
    public void signUpFrag() {

        FragmentManager manager = getSupportFragmentManager();
        SignUpFragment fragment = new SignUpFragment();
        fragment.show(manager, "Fragment SignUp");
    }

    public void loginFragment() {
        FragmentManager manager = getSupportFragmentManager();
        LoginFragment fragment = new LoginFragment();
        fragment.show(manager, "Fragment Login");
    }
}

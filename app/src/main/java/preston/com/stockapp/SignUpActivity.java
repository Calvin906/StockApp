package preston.com.stockapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Alex Preston on 1/18/17.
 */

public class SignUpActivity extends AppCompatActivity {

    private EditText email, confirmEmail, password, confirmPassword;
    private Button create;
    private TextView greeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity);

        //EditText
        email = (EditText) findViewById(R.id.email_sign_up);
        confirmEmail = (EditText) findViewById(R.id.confirm_email_sign_up);
        password = (EditText) findViewById(R.id.password_sign_up);
        confirmPassword = (EditText) findViewById(R.id.confirm_password_sign_up);

        //Button
        create = (Button) findViewById(R.id.create_button_sign_up);

        //Text view
        greeting = (TextView) findViewById(R.id.greeting_sign_up);


        setFonts();
    }

    /**
     * Sets the fonts
     */
    public void setFonts() {

        Typeface face = Typeface.createFromAsset(getAssets(), "bondini.ttf");
        email.setTypeface(face);
        confirmEmail.setTypeface(face);
        password.setTypeface(face);
        confirmPassword.setTypeface(face);
        create.setTypeface(face);
        greeting.setTypeface(face);
    }
}

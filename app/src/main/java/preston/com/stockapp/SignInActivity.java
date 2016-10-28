package preston.com.stockapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Alex Preston on 10/27/16.
 */

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView signIn;
    private TextView cancel;
    private GoogleSignInOptions gso;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        signIn = (TextView) findViewById(R.id.sign_in_button_si);
        cancel = (TextView) findViewById(R.id.cancel_button_si);






    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_button_si:


        }
    }
}

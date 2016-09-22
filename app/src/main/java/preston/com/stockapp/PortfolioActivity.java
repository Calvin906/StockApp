package preston.com.stockapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import preston.com.stockapp.R;

/**
 * Created by Alex Preston on 9/21/16.
 */

public class PortfolioActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.portfolio_activity);
    }
}

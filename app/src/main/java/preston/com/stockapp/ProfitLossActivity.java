package preston.com.stockapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Alex Preston on 10/5/16.
 */

public class ProfitLossActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profit_loss_activity);
    }

    /**
     * Sets the fonts
     */
    public void setFonts() {

        Typeface face = Typeface.createFromAsset(getAssets(), "bondini.ttf");
    }
}

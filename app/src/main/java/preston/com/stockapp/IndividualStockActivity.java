package preston.com.stockapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Alex Preston on 10/4/16.
 */

public class IndividualStockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invididual_stock_acitivity);
    }

    /**
     * Sets the fonts
     */
    public void setFonts() {

        Typeface face = Typeface.createFromAsset(getAssets(), "bondini.ttf");
    }
}

package preston.com.stockapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.preston.data.repo.greendao.Stock;

/**
 * Created by Alex Preston on 10/4/16.
 */

public class IndividualStockActivity extends AppCompatActivity {
    private Stock stock;
    private TextView priceLabel, price, changeLabel, change, dayHighLabel, dayHigh, volumeLabel, volume;
    private TextView dayLowLabel, dayLow, yearHighLabel, yearHigh, yearLowLabel, yearLow, marketCapLabel, marketCap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invididual_stock_acitivity);

        //Initialize the views
        priceLabel = (TextView) findViewById(R.id.price_label_individual);
        price = (TextView) findViewById(R.id.price_individual);
        changeLabel = (TextView) findViewById(R.id.change_label_individual);
        change = (TextView) findViewById(R.id.change_individual);
        dayHighLabel = (TextView) findViewById(R.id.day_high_label_individual);
        dayHigh = (TextView) findViewById(R.id.day_high_individual);
        dayLowLabel = (TextView) findViewById(R.id.day_low_label_individual);
        dayLow = (TextView) findViewById(R.id.day_low_individual);
        volumeLabel = (TextView) findViewById(R.id.volume_label_individual);
        volume = (TextView) findViewById(R.id.volume_individual);
        yearHighLabel = (TextView) findViewById(R.id.year_high_label_individual);
        yearHigh = (TextView) findViewById(R.id.year_high_individual);
        yearLowLabel = (TextView) findViewById(R.id.year_low_label_individual);
        yearLow = (TextView) findViewById(R.id.year_low_individual);
        marketCapLabel = (TextView) findViewById(R.id.market_cap_label_individual);
        marketCap = (TextView) findViewById(R.id.market_cap_individual);


        stock = (Stock) getIntent().getSerializableExtra("Stock");

    }

    /**
     * Sets the fonts
     */
    public void setFonts() {

        Typeface face = Typeface.createFromAsset(getAssets(), "bondini.ttf");
    }
}

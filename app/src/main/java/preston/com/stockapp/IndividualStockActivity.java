package preston.com.stockapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.preston.data.repo.greendao.Stock;

/**
 * Created by Alex Preston on 10/4/16.
 */

public class IndividualStockActivity extends AppCompatActivity implements View.OnClickListener {
    private Stock stock;
    private TextView ticker, name, priceLabel, price, changeLabel, change, dayHighLabel, dayHigh, volumeLabel, volume;
    private TextView dayLowLabel, dayLow, yearHighLabel, yearHigh, yearLowLabel, yearLow, marketCapLabel, marketCap;
    private Button add;
    private EditText addNumPurchased;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invididual_stock_acitivity);

        //Initialize the views
        ticker = (TextView) findViewById(R.id.ticker_individual);
        name = (TextView) findViewById(R.id.name_individual);
        price = (TextView) findViewById(R.id.price_individual);
        change = (TextView) findViewById(R.id.change_individual);
        dayHigh = (TextView) findViewById(R.id.day_high_individual);
        dayLow = (TextView) findViewById(R.id.day_low_individual);
        volume = (TextView) findViewById(R.id.volume_individual);
        yearHigh = (TextView) findViewById(R.id.year_high_individual);
        yearLow = (TextView) findViewById(R.id.year_low_individual);
        marketCap = (TextView) findViewById(R.id.market_cap_individual);
        addNumPurchased = (EditText)findViewById(R.id.desired_shares_individual);


        add = (Button) findViewById(R.id.add_button_individual);
        add.setOnClickListener(this);



        stock = (Stock) getIntent().getSerializableExtra("Stock");
        stock.setPricePurchased(Double.parseDouble(addNumPurchased.getText().toString()));
        setStock(stock);
        setFonts();

    }



    /**
     * Sets all the information
     *
     * @param stock
     */
    public void setStock(Stock stock) {

        ticker.setText(stock.getTicker());
        name.setText(stock.getName());
        price.setText(stock.getLastTradePrice().toString());
        dayHigh.setText(stock.getDaysHigh().toString());
        dayLow.setText(stock.getDaysLow().toString());
        yearHigh.setText(stock.getYearsHigh().toString());
        yearLow.setText(stock.getYearsLow().toString());
        marketCap.setText(stock.getMarketCapitalization());
        volume.setText(stock.getDailyVolume().toString());


        if (stock.getChange() > 0) {
            change.setText("+ " + stock.getChange().toString());
            change.setTextColor(getResources().getColor(R.color.percentageColorPositive));
        } else {
            change.setText(stock.getChange().toString());
            change.setTextColor(getResources().getColor(R.color.percentageColorNegative));
        }
    }

    /**
     * Sets the fonts
     */
    public void setFonts() {

        Typeface face = Typeface.createFromAsset(getAssets(), "bondini.ttf");

    }

    @Override
    public void onClick(View v) {
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}

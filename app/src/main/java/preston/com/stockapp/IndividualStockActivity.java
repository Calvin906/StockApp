package preston.com.stockapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.preston.data.repo.greendao.Stock;
import com.preston.data.repo.greendao.User;
import com.preston.data.repo.greendao.UserDao;

import java.util.List;

import preston.com.stockapp.util.Database;
import preston.com.stockapp.util.SyncDataLoader;

/**
 * Created by Alex Preston on 10/4/16.
 */

public class IndividualStockActivity extends AppCompatActivity implements View.OnClickListener {
    private Stock stock;
    private User user;
    private UserDao userDao;
    private TextView ticker, name, price, change, dayHigh, volume, dayLow, yearHigh, yearLow, marketCap;
    private Button add;
    private EditText addNumPurchased;
    private Database database;
    private static final String USERNAME = "username";
    private static final String STOCK = "stock";

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
        addNumPurchased = (EditText) findViewById(R.id.desired_shares_individual);


        add = (Button) findViewById(R.id.add_button_individual);
        add.setOnClickListener(this);

        database = Database.getInstance(this);
        database.checkDataBase();
        userDao = database.getUserDao();

        stock = getIntent().getParcelableExtra(STOCK);
        Bundle userBundle = new Bundle();
        userBundle.putString(USERNAME, getIntent().getStringExtra(USERNAME));
        getUserLoader(userBundle);

        setStock(stock);
        setFonts();

    }

    /**
     * Gets the user via a asyncTaskLoader
     *
     * @param userBundle
     */
    public void getUserLoader(Bundle userBundle) {
        getSupportLoaderManager().restartLoader(R.id.user_loader, userBundle, new LoaderManager.LoaderCallbacks<User>() {
            @Override
            public Loader<User> onCreateLoader(int id, final Bundle args) {
                return new SyncDataLoader<User>(IndividualStockActivity.this) {
                    @Override
                    protected User loadData() {
                        return getUser(args.getString(USERNAME));
                    }
                };
            }

            @Override
            public void onLoadFinished(Loader<User> loader, User data) {
                user = data;

            }

            @Override
            public void onLoaderReset(Loader<User> loader) {

            }
        });

    }


    /**
     * Sets all the information
     *
     * @param stock
     */
    public void setStock(Stock stock) {

        ticker.setText(stock.getTicker());
        name.setText(stock.getName());
        price.setText(String.format("$ %.2f", stock.getLastTradePrice()));
        dayHigh.setText(String.format("$ %.2f", stock.getDaysHigh()));
        dayLow.setText(String.format("$ %.2f", stock.getDaysLow()));
        yearHigh.setText(String.format("$ %.2f", stock.getYearsHigh()));
        yearLow.setText(String.format("$ %.2f", stock.getYearsLow()));
        marketCap.setText(String.format("$ %s", stock.getMarketCapitalization()));
        volume.setText(String.format("%d", stock.getVolume()));


        if (stock.getChange() > 0) {
            change.setText(String.format("+ %.2f", stock.getChange()));
            change.setTextColor(getResources().getColor(R.color.percentageColorPositive));
        } else {
            change.setText(String.format(" %.2f", stock.getChange()));
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
                stock.setPricePurchased(Double.parseDouble(addNumPurchased.getText().toString()));
                database.getStockDao().insertOrReplace(stock);
                startPortfolio(user.getEncodedId());

            }
        });
    }

    /**
     * Starts the activity
     */
    public void startPortfolio(String id) {
        Intent intent = new Intent(this, PortfolioActivity.class);
        intent.putExtra(USERNAME, id);
        startActivity(intent);

    }


    /**
     * Looks up the user in the db, this is called from another thread via AsyncTaskLoader
     *
     * @param username
     * @return
     */
    public User getUser(String username) {
        List<User> userList = userDao.queryBuilder().orderDesc(UserDao.Properties.EncodedId).build().list();

        if (userList.size() > 0) {
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getEncodedId().contentEquals(username)) {
                    User foundUser = userList.get(i);
                    return foundUser;
                }
            }
        }
        return null;
    }
}

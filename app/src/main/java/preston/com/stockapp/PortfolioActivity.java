package preston.com.stockapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.preston.data.repo.greendao.User;
import com.preston.data.repo.greendao.UserDao;

import java.util.List;

import preston.com.stockapp.util.Database;
import preston.com.stockapp.util.PortfolioRecyclerViewAdapter;
import preston.com.stockapp.util.SyncDataLoader;

/**
 * Created by Alex Preston on 9/21/16.
 */

public class PortfolioActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<User> {

    private RecyclerView pRecyclerView;
    private static final String USERNAME = "username";
    private PortfolioRecyclerViewAdapter pAdapter;
    private Button refresh, add;
    private User user;
    private UserDao userDao;
    private Database database;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portfolio_activity);

        //Get instance of Database
        database = Database.getInstance(this);
        database.checkDataBase();
        userDao = database.getUserDao();

        //Get the User
        Bundle userBundle = new Bundle();
        userBundle.putString(USERNAME, getIntent().getStringExtra(USERNAME));
        getUserLoader(userBundle);

        add = (Button) findViewById(R.id.add_button_portfolio);
        addButtonListener(add);
        refresh = (Button) findViewById(R.id.refresh_button_portfolio);

        //Initialize RecyclerView, and adapter, and Layout manager
        pRecyclerView = (RecyclerView) findViewById(R.id.portfolio_recycler_view);
        pAdapter = new PortfolioRecyclerViewAdapter();
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        //Set adapter and LayoutManager
        pRecyclerView.setAdapter(pAdapter);
        pRecyclerView.setLayoutManager(llm);


        addStocks();
        setFonts();
    }

    /**
     *
     * @param userBundle
     */
    public void getUserLoader(Bundle userBundle) {
        getSupportLoaderManager().restartLoader(R.id.user_loader, userBundle, PortfolioActivity.this).forceLoad();
    }

    public void addStocks() {
//        if (user.getStockList() != null) {
//            List<Stock> stocks = user.getStockList();
//            pAdapter.addStocks(stocks);
//        } else {
//
//        }

    }

    /**
     * Adds a button listener
     *
     * @param button
     */
    private void addButtonListener(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSearchActivity(user.getUsername());
            }
        });
    }

    /**
     * starts the search activity
     *
     * @param username
     */
    private void startSearchActivity(String username) {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra(USERNAME, username);
        startActivity(intent);
    }

    /**
     * Sets the fonts
     */
    private void setFonts() {

        Typeface face = Typeface.createFromAsset(getAssets(), "bondini.ttf");
        add.setTypeface(face);
        refresh.setTypeface(face);
    }

    @Override
    public Loader<User> onCreateLoader(int id, final Bundle args) {
        return new SyncDataLoader<User>(PortfolioActivity.this) {
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

    /**
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

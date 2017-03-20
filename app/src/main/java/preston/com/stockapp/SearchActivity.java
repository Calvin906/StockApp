package preston.com.stockapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import com.preston.data.repo.greendao.Stock;
import com.preston.data.repo.greendao.User;
import com.preston.data.repo.greendao.UserDao;

import java.util.List;

import preston.com.stockapp.util.Database;
import preston.com.stockapp.util.SearchLoader;
import preston.com.stockapp.util.SearchOnClickListener;
import preston.com.stockapp.util.SearchRecyclerViewAdapter;
import preston.com.stockapp.util.SyncDataLoader;

/**
 * Created by Alex Preston on 10/4/16.
 */

public class SearchActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Stock>, SearchView.OnQueryTextListener, SearchOnClickListener {
    private User user;
    private UserDao userDao;
    private Database database;
    private RecyclerView resultList;
    private SearchRecyclerViewAdapter searchAdapter;
    private static final String QUERY_PARAM = "query";
    private static final String USERNAME = "username";
    private static final String STOCK = "stock";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results_activity);

        searchAdapter = new SearchRecyclerViewAdapter(this);

        //Gets instance of the database
        database = Database.getInstance(this);
        database.checkDataBase();
        userDao = database.getUserDao();

        //Create Recycler view, set Layout, and Adapter
        resultList = (RecyclerView) findViewById(R.id.recycler_view_search);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        resultList.setLayoutManager(llm);
        resultList.setAdapter(searchAdapter);

        //Get the passed User
        Bundle userBundle = new Bundle();
        userBundle.putString(USERNAME, getIntent().getStringExtra(USERNAME));
        getUserLoader(userBundle);


    }

    public void getUserLoader(Bundle userBundle) {
        getSupportLoaderManager().restartLoader(R.id.user_loader, userBundle, new LoaderManager.LoaderCallbacks<User>() {
            @Override
            public Loader<User> onCreateLoader(int id, final Bundle args) {
                return new SyncDataLoader<User>(SearchActivity.this) {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        //Add to action bar if present
        inflater.inflate(R.menu.search_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        //Associate searchable config with Search view
        final SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setSubmitButtonEnabled(true);
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        Bundle bundle = new Bundle();
        bundle.putString(QUERY_PARAM, query);
        getSupportLoaderManager().restartLoader(1, bundle, SearchActivity.this).forceLoad();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }

    @Override
    public Loader<Stock> onCreateLoader(int id, Bundle args) {
        return new SearchLoader(SearchActivity.this, args.getString(QUERY_PARAM), user.getEncodedId());
    }

    @Override
    public void onLoadFinished(Loader<Stock> loader, Stock data) {
        if (data != null) {
            searchAdapter.addStocks(data);
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Stock doesn't exist!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    @Override
    public void onLoaderReset(Loader<Stock> loader) {

    }

    @Override
    public void onClick(Stock stockData) {
        Intent intent = new Intent(this, IndividualStockActivity.class);
        intent.putExtra(STOCK, stockData);
        intent.putExtra(USERNAME, user.getEncodedId());
        startActivity(intent);
    }

    /**
     * Sets the fonts
     */
    public void setFonts() {

        Typeface face = Typeface.createFromAsset(getAssets(), "bondini.ttf");

    }

    /**
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

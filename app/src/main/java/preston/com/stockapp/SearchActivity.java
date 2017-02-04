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

import preston.com.stockapp.util.SearchLoader;
import preston.com.stockapp.util.SearchOnClickListener;
import preston.com.stockapp.util.SearchRecyclerViewAdapter;

/**
 * Created by Alex Preston on 10/4/16.
 */

public class SearchActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Stock>, SearchView.OnQueryTextListener, SearchOnClickListener {
    private User user;
    private RecyclerView resultList;
    private SearchRecyclerViewAdapter searchAdapter;
    private static final String QUERY_PARAM = "query";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results_activity);

        searchAdapter = new SearchRecyclerViewAdapter(this);

        //Create Recycler view, set Layout, and Adapter
        resultList = (RecyclerView) findViewById(R.id.recycler_view_search);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        resultList.setLayoutManager(llm);
        resultList.setAdapter(searchAdapter);

        //Get the passed User
        user = (User) getIntent().getSerializableExtra("User");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)  {

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

    /**
     * Sets the fonts
     */
    public void setFonts() {

        Typeface face = Typeface.createFromAsset(getAssets(), "bondini.ttf");

    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        Bundle bundle = new Bundle();
        bundle.putString(QUERY_PARAM, query);
        getSupportLoaderManager().restartLoader(1,bundle, SearchActivity.this).forceLoad();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }

    @Override
    public Loader<Stock> onCreateLoader(int id, Bundle args) {
        return new SearchLoader(SearchActivity.this, args.getString(QUERY_PARAM));
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
        intent.putExtra("Stock", stockData);
        startActivity(intent);
    }
}

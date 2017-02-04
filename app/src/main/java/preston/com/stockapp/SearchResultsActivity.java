package preston.com.stockapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;

import com.preston.data.repo.greendao.Stock;
import com.preston.data.repo.greendao.User;

import java.util.ArrayList;
import java.util.List;

import preston.com.stockapp.util.SearchLoader;
import preston.com.stockapp.util.SearchRecyclerViewAdapter;
import preston.com.stockapp.util.StockApi;

/**
 * Created by Alex Preston on 10/4/16.
 */

public class SearchResultsActivity extends AppCompatActivity implements android.support.v4.app.LoaderManager.LoaderCallbacks<List<Stock>> {
    private User user;
    private RecyclerView resultList;
    private StockApi stockAPI;
    private String queryToPass;
    private SearchRecyclerViewAdapter searchAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results_activity);
        stockAPI.getInstance();

        searchAdapter = new SearchRecyclerViewAdapter();

        //Create Recycler view, set Layout, and Adapter
        resultList = (RecyclerView) findViewById(R.id.recycler_view_search);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        resultList.setLayoutManager(llm);
        resultList.setAdapter(searchAdapter);

        handleIntent(getIntent());

        //Get the passed User
        user = (User) getIntent().getSerializableExtra("User");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)  {

        MenuInflater inflater = getMenuInflater();
        //Add to action bar if present
        inflater.inflate(R.menu.search_menu, menu);

        //Associate searchable config with Search view
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }


    private void handleIntent(Intent intent) {
        //Get the intent, verify the action and get the query
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            queryToPass = intent.getStringExtra(SearchManager.QUERY);
            getSupportLoaderManager().initLoader(1,null,this).forceLoad();
        }
    }

    /**
     * Sets the fonts
     */
    public void setFonts() {

        Typeface face = Typeface.createFromAsset(getAssets(), "bondini.ttf");

    }


    @Override
    public android.support.v4.content.Loader<List<Stock>> onCreateLoader(int id, Bundle args) {
        return new SearchLoader(SearchResultsActivity.this, queryToPass);
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<List<Stock>> loader, List<Stock> data) {
        searchAdapter.addStocks(data);
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<List<Stock>> loader) {
        searchAdapter.addStocks(new ArrayList<Stock>());
    }
}

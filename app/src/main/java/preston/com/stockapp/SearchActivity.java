package preston.com.stockapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;

import com.preston.data.repo.greendao.User;
import com.preston.data.repo.greendao.stock.Stock;

/**
 * Created by Alex Preston on 10/4/16.
 *
 */

public class SearchActivity extends AppCompatActivity {
    private SearchView searchView;
    private User user;
    private Stock stock;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);

        searchView = (SearchView) findViewById(R.id.search_bar_search);

        //Get the passed User
        user = (User)getIntent().getSerializableExtra("User");

    }

    /**
     * Sets the fonts
     */
    public void setFonts() {

        Typeface face = Typeface.createFromAsset(getAssets(), "bondini.ttf");

    }
}

package preston.com.stockapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.preston.data.repo.greendao.User;

import preston.com.stockapp.util.PortfolioRecyclerViewAdapter;

/**
 * Created by Alex Preston on 9/21/16.
 */

public class PortfolioActivity extends Activity {

    private RecyclerView pRecyclerView;
    private PortfolioRecyclerViewAdapter pAdapter;
    private Button refresh, add;
    private User user;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portfolio_activity);

        user = (User) getIntent().getSerializableExtra("User");

        add = (Button) findViewById(R.id.add_button_portfolio);
        addButtonListener(add);
        refresh = (Button) findViewById(R.id.refresh_button_portfolio);

        //Initialize RecyclerView, and adapter, and Layoutmanager
        pRecyclerView = (RecyclerView) findViewById(R.id.portfolio_recycler_view);
        pAdapter = new PortfolioRecyclerViewAdapter();
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        //Set adapter and LayoutManager
        pRecyclerView.setAdapter(pAdapter);
        pRecyclerView.setLayoutManager(llm);

        setFonts();
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
                startSearchActivity(user);
            }
        });
    }

    /**
     * starts the search activity
     *
     * @param userToPass
     */
    private void startSearchActivity(User userToPass) {
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra("User", userToPass);
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
}

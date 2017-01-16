package preston.com.stockapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import preston.com.stockapp.util.PortfolioRecyclerViewAdapter;

/**
 * Created by Alex Preston on 9/21/16.
 */

public class PortfolioActivity extends Activity {

    private RecyclerView pRecyclerView;
    private PortfolioRecyclerViewAdapter pAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portfolio_activity);

        //Initialize RecyclerView, and adapter, and Layoutmanager
        pRecyclerView = (RecyclerView) findViewById(R.id.portfolio_recycler_view);
        pAdapter = new PortfolioRecyclerViewAdapter();
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        //Set adapter and LayoutManager
        pRecyclerView.setAdapter(pAdapter);
        pRecyclerView.setLayoutManager(llm);
    }


}

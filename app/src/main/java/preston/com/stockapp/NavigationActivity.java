package preston.com.stockapp;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import preston.com.stockapp.util.StockApi;
import preston.com.stockapp.util.SyncDataLoader;

public class NavigationActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Object> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        getSupportLoaderManager().initLoader(R.id.stock_list_loader, null, this);
    }

    @Override
    public Loader<Object> onCreateLoader(int id, Bundle args) {
        return new SyncDataLoader<Object>(this) {
            @Override
            protected Object loadData() {

                try {
                    JSONObject jsonObject = StockApi.getInstance().getStocks("MSFT", "YHOO");
                    Log.v("hey", "hey");
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<Object> loader, Object data) {

    }

    @Override
    public void onLoaderReset(Loader<Object> loader) {

    }
}

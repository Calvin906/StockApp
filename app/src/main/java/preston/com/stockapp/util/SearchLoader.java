package preston.com.stockapp.util;

import android.content.Context;

import com.preston.data.repo.greendao.Stock;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex Preston on 2/2/17.
 */

public class SearchLoader extends android.support.v4.content.AsyncTaskLoader<List<Stock>> {
    private StockApi stock;
    private String query;


    public SearchLoader(Context context, String query) {
        super(context);
        stock.getInstance();
        this.query = query;
    }

    @Override
    public List<Stock> loadInBackground() {
        List<Stock> stocks = new ArrayList<>();
        try {
            stocks.addAll(parse(stock.getStock(query)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Parse in the JSON Object
     * @param obj
     * @return
     */
    private List parse(JSONObject obj) {
        List<Stock> stocks = new ArrayList<>();

        return stocks;
    }
}

package preston.com.stockapp.util;

import android.content.Context;

import com.preston.data.repo.greendao.Stock;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Alex Preston on 2/2/17.
 */

public class SearchLoader extends android.support.v4.content.AsyncTaskLoader<Stock> {
    private StockApi stockApi;
    private String query;
    private String id;


    public SearchLoader(Context context, String query, String id) {
        super(context);
        this.query = query;
        this.id = id;
    }

    @Override
    public Stock loadInBackground() {
        stockApi = StockApi.getInstance();
        try {
            return parse(query);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Parse in the JSON Object
     *
     * @param query
     * @return
     */
    private Stock parse(String query) throws JSONException, IOException {
        if (query != null) {
            JSONObject obj = stockApi.getStock(query);
            JSONObject queryObj = obj.getJSONObject("query");
            JSONObject results = queryObj.getJSONObject("results");
            JSONObject quote = results.getJSONObject("quote");
            Stock stock = new Stock();
            stock.setEncodedId(id);
            stock.setTicker(quote.getString("symbol"));
            stock.setDailyVolume(quote.getLong("AverageDailyVolume"));
            stock.setChange(quote.getDouble("Change"));
            stock.setDaysLow(quote.getDouble("DaysLow"));
            stock.setDaysHigh(quote.getDouble("DaysHigh"));
            stock.setYearsLow(quote.getDouble("YearLow"));
            stock.setYearsHigh(quote.getDouble("YearHigh"));
            stock.setMarketCapitalization(quote.getString("MarketCapitalization"));
            stock.setLastTradePrice(quote.getDouble("LastTradePriceOnly"));
            stock.setDaysRange(quote.getString("DaysRange"));
            stock.setName(quote.getString("Name"));
            stock.setVolume(quote.getLong("Volume"));
            return stock;
        } else {
            return null;
        }

    }
}

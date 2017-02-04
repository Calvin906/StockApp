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
    private StockApi stockApi;
    private String query;


    public SearchLoader(Context context, String query) {
        super(context);
        this.query = query;
    }

    @Override
    public List<Stock> loadInBackground() {
        stockApi = StockApi.getInstance();
        List<Stock> stocks = new ArrayList<>();
        try {
            stocks.addAll(parse(query));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stocks;
    }

    /**
     * Parse in the JSON Object
     *
     * @param query
     * @return
     */
    private List parse(String query) throws JSONException, IOException {
        List<Stock> stocks = new ArrayList<>();
        if (query != null) {
            JSONObject obj = stockApi.getStock(query);
            JSONObject queryObj = obj.getJSONObject("query");
            JSONObject results = queryObj.getJSONObject("results");

            JSONObject quote = results.getJSONObject("quote");
            Stock stock = new Stock();
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
            stocks.add(stock);
        }
        return stocks;
    }
}

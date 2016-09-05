package preston.com.stockapp.util;

import android.support.annotation.WorkerThread;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by jpreston on 9/4/16.
 */
public class StockApi {

    private final String endpoint = "https://query.yahooapis.com/v1/public/yql";

    private final OkHttpClient client;

    private static volatile StockApi instance;

    private StockApi() {
        client = new OkHttpClient();
    }

    public static StockApi getInstance() {
        StockApi result = instance;
        if (result == null) {
            synchronized (StockApi.class) {
                result = instance;
                if (result == null) {
                    result = instance = new StockApi();
                }
            }
        }
        return result;
    }

    @WorkerThread
    public JSONObject getStock(String ticker) throws IOException, JSONException {
        return getStocks(ticker);
    }

    @WorkerThread
    public JSONObject getStocks(String... tickers) throws IOException, JSONException {
        String definition = "&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
        StringBuilder selectStatement = new StringBuilder("?q=select * from yahoo.finance.quote where symbol in(");
        for (String ticker: tickers) {
            selectStatement.append(String.format("\"%s\",", ticker));
        }
        selectStatement.replace(selectStatement.length()-1, selectStatement.length(), ")");

        String url = String.format("%s%s%s", endpoint, selectStatement.toString(), definition);
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        return new JSONObject(response.body().string());
    }
}

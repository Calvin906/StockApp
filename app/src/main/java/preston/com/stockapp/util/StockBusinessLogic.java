package preston.com.stockapp.util;

import android.content.Context;

/**
 * Created by jpreston on 9/4/16.
 */
public class StockBusinessLogic {

    private static volatile StockBusinessLogic instance;

    public static StockBusinessLogic getInstance() {
        StockBusinessLogic result = instance;
        if (result == null) {
            synchronized (StockBusinessLogic.class) {
                result = instance;
                if (result == null) {
                    result = instance = new StockBusinessLogic();
                }
            }
        }
        return result;
    }



}

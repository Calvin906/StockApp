package preston.com.stockapp.util;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.preston.data.repo.greendao.Stock;

import java.util.ArrayList;
import java.util.List;

import preston.com.stockapp.R;

/**
 * Created by Alex Preston on 2/3/17.
 */

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.StockViewHolder> {

    private List<Stock> stocks;

    public SearchRecyclerViewAdapter() {
        stocks = new ArrayList<>();
    }

    public void addStocks(List<Stock> stocks) {
        this.stocks = stocks;
        notifyDataSetChanged();
    }

    @Override
    public StockViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_content_views, parent, false);
        return new StockViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StockViewHolder holder, int position) {
        StockViewHolder dataHolder = holder;
        dataHolder.bindViews(holder, position);
    }

    @Override
    public int getItemCount() {
        return stocks.size();
    }

    public class StockViewHolder extends RecyclerView.ViewHolder {
        private TextView stockName;
        private TextView stockTicker;

        public StockViewHolder(View v) {
            super(v);
            stockName = (TextView) v.findViewById(R.id.stock_name_search_views);
            stockTicker = (TextView) v.findViewById(R.id.stock_ticker_search_views);
        }

        /**
         * binds the views
         * @param holder
         * @param pos
         */
        public void bindViews(StockViewHolder holder, int pos) {
            Stock stock = stocks.get(pos);
            holder.stockTicker.setText(stock.getTicker());
            holder.stockName.setText(stock.getName());
        }

    }
}

package preston.com.stockapp.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.preston.data.repo.greendao.Stock;

import java.util.ArrayList;
import java.util.List;

import preston.com.stockapp.R;

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.StockViewHolder> {

    private List<Stock> stocks;
    private SearchOnClickListener searchListener;

    public SearchRecyclerViewAdapter(SearchOnClickListener searchListener) {
        stocks = new ArrayList<>();
        this.searchListener = searchListener;
    }

    public void addStocks(Stock stocks) {
        this.stocks.add(stocks);
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

    public class StockViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView stockName;
        private TextView stockTicker;
        private Context context;

        public StockViewHolder(View v) {
            super(v);
            context = v.getContext();
            stockName = (TextView) v.findViewById(R.id.stock_name_search_views);
            stockTicker = (TextView) v.findViewById(R.id.stock_ticker_search_views);
            v.setOnClickListener(this);
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

        @Override
        public void onClick(View v) {
            searchListener.onClick(stocks.get(getAdapterPosition()));
        }
    }
}

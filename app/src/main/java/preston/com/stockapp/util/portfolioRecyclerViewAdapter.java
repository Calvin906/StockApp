package preston.com.stockapp.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import preston.com.stockapp.R;


/**
 * Created by Alex Preston on 11/1/16.
 */
interface stockClickListener {
    //TODO
}

public class PortfolioRecyclerViewAdapter extends RecyclerView.Adapter<PortfolioRecyclerViewAdapter.StockViewHolder> {

    /**
     * Add stocks to the view
     */
    public void addStocks() {

    }

    @Override
    public StockViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.portfolio_content_views, parent, false);
        return new StockViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StockViewHolder holder, int position) {
        StockViewHolder viewHolder = holder;
        viewHolder.bindViews(holder, position);

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class StockViewHolder extends RecyclerView.ViewHolder {
        private TextView percentage;
        private TextView price;
        private TextView ticker;
        private Context mContext;

        public StockViewHolder(View v) {
            super(v);
            mContext = v.getContext();
            percentage = (TextView) v.findViewById(R.id.percentage_portfolio);
            price = (TextView) v.findViewById(R.id.price_portfolio);
            ticker = (TextView) v.findViewById(R.id.ticker_portfolio);

        }

        public void bindViews(PortfolioRecyclerViewAdapter.StockViewHolder holder, int pos) {


        }
    }
}

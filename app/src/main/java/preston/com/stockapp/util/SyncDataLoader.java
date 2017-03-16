package preston.com.stockapp.util;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

/**
 * Created by Alex Preston on 2/21/17.
 *
 */

public abstract class SyncDataLoader<T> extends AsyncTaskLoader<T> {

    private T data;

    public SyncDataLoader(Context context) {
        super(context);
    }

    @Override
    public T loadInBackground() {
        this.data = loadData();
        return data;
    }

    @Override
    protected void onStartLoading() {
        boolean contentChanged = takeContentChanged();
        if (data != null && !contentChanged) {
            deliverResult(data);
        } else {
            forceLoad();
        }
    }

    protected boolean shouldDeliver(T data) {
        return true;
    }

    @Override
    public void deliverResult(T data) {
        if (shouldDeliver(data)) {
            super.deliverResult(data);
        } else {
            commitContentChanged();
        }
    }

    @Override
    public void stopLoading() {
        super.stopLoading();
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
    }

    @Override
    protected void onReset() {
        super.onReset();
    }

    protected abstract T loadData();
}

package preston.com.stockapp.util;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

/**
 * Created by jpreston on 9/4/16.
 */
public abstract class SyncDataLoader<T> extends AsyncTaskLoader<T> {

    private T data;

    public SyncDataLoader(Context context) {
        super(context);
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
    public T loadInBackground() {
        return this.data = loadData();
    }

    @Override
    public void stopLoading() {
        super.stopLoading();
        cancelLoad();
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();
        cancelLoad();
    }

    protected abstract T loadData();
}

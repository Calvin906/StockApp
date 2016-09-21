package preston.com.stockapp.util;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

/**
 * Created by Alex Preston on 9/20/16.
 */

public class SignUpFragment extends DialogFragment {


    static SignUpFragment newInstance(int num) {
        SignUpFragment fragment = new SignUpFragment();

        Bundle args = new Bundle();
        args.putInt("num", num);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

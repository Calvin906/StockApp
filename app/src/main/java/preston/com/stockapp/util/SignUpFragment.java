package preston.com.stockapp.util;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import preston.com.stockapp.R;

/**
 * Created by Alex Preston on 9/20/16.
 */

public class SignUpFragment extends DialogFragment {


   public SignUpFragment() {

   }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.sign_up_view, container);
        return view;
    }
}

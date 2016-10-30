package preston.com.stockapp.util;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import preston.com.stockapp.PortfolioActivity;
import preston.com.stockapp.R;

/**
 * Created by Alex Preston on 9/20/16.
 */

public class SignUpFragment extends DialogFragment implements View.OnClickListener {

    TextView cancel;
    TextView signUp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sign_up_view, container);
        cancel = (TextView) view.findViewById(R.id.cancel_button_sign_up);
        signUp = (TextView) view.findViewById(R.id.sign_up_button_sign_up);

        cancel.setOnClickListener(this);
        signUp.setOnClickListener(this);

        return view;
    }


    /**
     * onClick method dictates which button was clicked
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_button_sign_up:
                dismiss();
                break;
            case R.id.sign_up_button_sign_up:
                startPortfolioPage();
                break;
        }
    }

    /**
     * Starts the portfolio page
     */
    public void startPortfolioPage() {
        Intent intent = new Intent(getActivity(), PortfolioActivity.class);
        getActivity().startActivity(intent);
    }
}

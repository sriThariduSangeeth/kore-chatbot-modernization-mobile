package kore.botssdk.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kore.botssdk.R;
import kore.botssdk.models.Users;


public class ProfileFragment extends Fragment {


    private static final String ARG_PARAM3 = "currentUser";
    private Users currentUser;
    private TextView accBalance, cardNumber , accName ,cardValid;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        Bundle bundle = getArguments();

        accBalance = (TextView) view.findViewById(R.id.accBalance);
        cardNumber = view.findViewById(R.id.cardNumber);
        accName = view.findViewById(R.id.accName);
        cardValid = view.findViewById(R.id.textView8);
        if (bundle != null) {
            currentUser = (Users) getArguments().getSerializable(ARG_PARAM3);
        }

        accBalance.setText("$"+currentUser.getBalance());
        cardNumber.setText(""+currentUser.getAccNumber());
        accName.setText(currentUser.getUserFullName());
        cardValid.setText(currentUser.getValidDate().replace("-","/"));

        return view;
    }
}
package kore.botssdk.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import kore.botssdk.R;
import kore.botssdk.adapter.NotificationAdapter;
import kore.botssdk.adapter.TransactionAdapter;
import kore.botssdk.models.NotificationDetailModel;
import kore.botssdk.models.TransactionDetailModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotifyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotifyFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView1;
    ArrayList<NotificationDetailModel> dataHolder;

    public NotifyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotifyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotifyFragment newInstance(String param1, String param2) {
        NotifyFragment fragment = new NotifyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notify, container, false);
        recyclerView1 = view.findViewById(R.id.recycler_view_notif);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));

        dataHolder = new ArrayList<>();

        NotificationDetailModel t1 = new NotificationDetailModel(
                R.drawable.ic_image_photo, "Money Sent", "Your transaction has been success", "100$");

        NotificationDetailModel t2 = new NotificationDetailModel(
                R.drawable.ic_history, "App Update", "Version 4.2 patch has been updated", "");

        NotificationDetailModel t3 = new NotificationDetailModel(
                R.drawable.ic_image_photo, "Paid", "Ebay payment has been success", "200$");

        NotificationDetailModel t4 = new NotificationDetailModel(
                R.drawable.ic_image_photo, "Money Sent", "Your transaction has been authorized", "1000$");

        NotificationDetailModel t5 = new NotificationDetailModel(
                R.drawable.ic_history, "Money Transfer", "Funds has been transferred to XXXX", "300$");

        dataHolder.add(t1);
        dataHolder.add(t2);
        dataHolder.add(t3);
        dataHolder.add(t4);
        dataHolder.add(t5);

        recyclerView1.setAdapter(new NotificationAdapter(dataHolder));

        return view;
    }
}
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

    RecyclerView recyclerView1;
    ArrayList<NotificationDetailModel> dataHolder;


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
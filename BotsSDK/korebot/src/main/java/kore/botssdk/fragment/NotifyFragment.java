package kore.botssdk.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import kore.botssdk.R;
import kore.botssdk.adapter.NotificationAdapter;
import kore.botssdk.adapter.TransactionAdapter;
import kore.botssdk.models.NotificationDetailModel;
import kore.botssdk.models.TransactionDetailModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
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

        int startYear = 2018;                                    //Starting year of specified random date
        int endYear = 2022;                                    //Starting year of specified random date (including)
        long start = Timestamp.valueOf(startYear + 1 + "-1-1 0:0:0").getTime();
        long end = Timestamp.valueOf(endYear + "-1-1 0:0:0").getTime();
        long ms = (long) ((end - start) * Math.random() + start);    //The qualified number of 13-bit milliseconds is obtained.
        Date date = new Date(ms);

        //R.drawable.ic_money_notification
        NotificationDetailModel t1 = new NotificationDetailModel(date.toString(), "Money Sent", "Your transaction has been success", "100$");

        NotificationDetailModel t2 = new NotificationDetailModel(date.toString(), "App Update", "Version 4.2 patch has been updated", "");

        NotificationDetailModel t3 = new NotificationDetailModel(date.toString(), "Paid", "Ebay payment has been success", "200$");

        NotificationDetailModel t4 = new NotificationDetailModel(date.toString(), "Money Sent", "Your transaction has been authorized", "1000$");

        NotificationDetailModel t5 = new NotificationDetailModel(date.toString(), "Money Transfer", "Funds has been transferred to XXXX", "300$");

        NotificationDetailModel t6 = new NotificationDetailModel(date.toString(), "Paid", "Amazon payment has been success", "450$");

        NotificationDetailModel t7 = new NotificationDetailModel(date.toString(), "Money Sent", "Your transaction has been authorized", "1000$");

        NotificationDetailModel t8 = new NotificationDetailModel(date.toString(), "Money Transfer", "Funds has been transferred to XXXX", "300$");

        NotificationDetailModel t9 = new NotificationDetailModel(date.toString(), "Paid", "Amazon payment has been success", "450$");

        NotificationDetailModel t10 = new NotificationDetailModel(date.toString(), "Money Sent", "Your transaction has been authorized", "1000$");

        NotificationDetailModel t11 = new NotificationDetailModel(date.toString(), "Money Transfer", "Funds has been transferred to XXXX", "300$");

        NotificationDetailModel t12 = new NotificationDetailModel(date.toString(), "Paid", "Amazon payment has been success", "450$");
        dataHolder.add(t1);
        dataHolder.add(t2);
        dataHolder.add(t3);
        dataHolder.add(t4);
        dataHolder.add(t5);
        dataHolder.add(t6);
        dataHolder.add(t7);
        dataHolder.add(t8);
        dataHolder.add(t9);
        dataHolder.add(t10);
        dataHolder.add(t11);
        dataHolder.add(t12);
        recyclerView1.setAdapter(new NotificationAdapter(dataHolder));

        return view;
    }
}
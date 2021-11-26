package kore.botssdk.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import java.util.ArrayList;

import kore.botssdk.R;
import kore.botssdk.adapter.TransactionAdapter;
import kore.botssdk.models.TransactionDetailModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TransactionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TransactionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListAdapter mAdapter;

    RecyclerView recyclerView1;
    ArrayList<TransactionDetailModel> dataHolder;

    public TransactionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TransactionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TransactionFragment newInstance(String param1, String param2) {
        TransactionFragment fragment = new TransactionFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transaction, container, false);
        recyclerView1 = view.findViewById(R.id.recycler_view_act);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getContext()));

        dataHolder = new ArrayList<>();

        TransactionDetailModel t1 = new TransactionDetailModel(
                R.drawable.ic_icon_share, "Money Sent", "Pending", "100$");

        TransactionDetailModel t2 = new TransactionDetailModel(
                R.drawable.ic_mail_drawable, "Money Sent", "Sucesfull", "160$");

        TransactionDetailModel t3 = new TransactionDetailModel(
                R.drawable.ic_icon_share, "Money Sent", "Cancelled", "200$");

        TransactionDetailModel t4 = new TransactionDetailModel(
                R.drawable.ic_icon_share, "Money Sent", "Pending", "1000$");

        TransactionDetailModel t5 = new TransactionDetailModel(
                R.drawable.ic_mail_drawable, "Money Received", "Pending", "300$");

        dataHolder.add(t1);
        dataHolder.add(t2);
        dataHolder.add(t3);
        dataHolder.add(t4);
        dataHolder.add(t5);

        recyclerView1.setAdapter(new TransactionAdapter(dataHolder));

        return view;
    }

 /*   @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //recycler
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_act);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // define an adapter
//        mAdapter = new ListAdapter(null, getContext());
//        recyclerView.setAdapter(mAdapter);
    }*/
}
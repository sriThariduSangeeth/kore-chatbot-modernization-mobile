package kore.botssdk.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kore.botssdk.R;
import kore.botssdk.models.NotificationDetailModel;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder>{

    ArrayList<NotificationDetailModel> dataHolder;

    public NotificationAdapter(ArrayList<NotificationDetailModel> dataHolder) {
        this.dataHolder = dataHolder;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_activity, parent, false);
        return new NotificationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.image.setImageResource(dataHolder.get(position).getImage());
        holder.description.setText(dataHolder.get(position).getDescription());
        holder.transaction.setText(dataHolder.get(position).getTransaction());
        holder.amount.setText(dataHolder.get(position).getAmount());

    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;

        TextView transaction;

        TextView description;

        TextView amount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            transaction = itemView.findViewById(R.id.textView11);
            description = itemView.findViewById(R.id.textView12);
            amount = itemView.findViewById(R.id.textView13);

        }
    }
}

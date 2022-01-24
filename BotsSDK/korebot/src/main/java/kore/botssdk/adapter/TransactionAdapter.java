package kore.botssdk.adapter;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import kore.botssdk.R;
import kore.botssdk.models.TransactionDetailModel;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    ArrayList<TransactionDetailModel> dataHolder;

    public TransactionAdapter(ArrayList<TransactionDetailModel> dataHolder) {
        this.dataHolder = dataHolder;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_activity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //holder.image.setImageResource(dataHolder.get(position).getImage());
        holder.date.setText(dataHolder.get(position).getDate());
        holder.description.setText(dataHolder.get(position).getDescription());
        holder.transaction.setText(dataHolder.get(position).getTransaction());
        holder.amount.setText(dataHolder.get(position).getAmount());
    }

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        //ImageView image;

        TextView date;

        TextView transaction;

        TextView description;

        TextView amount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*Intent i = new Intent(v.getContext(),TransactionDetails.class);
                    i.putExtra("title", dataHolder.get(getAdapterPosition()));
                    v.getContext().startActivity(i);*/
                }
            });

            //image = itemView.findViewById(R.id.image);
            date = itemView.findViewById(R.id.textViewDate);
            transaction = itemView.findViewById(R.id.textView11);
            description = itemView.findViewById(R.id.textView12);
            amount = itemView.findViewById(R.id.textView13);

        }
    }

}

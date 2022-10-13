package sid.com.quotelyserver.DataModels;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import sid.com.quotelyserver.R;

/**
 * Created by siddheshdighe on 15/03/18.
 */

public class RecyclerQuotesInformationAdapter extends RecyclerView.Adapter<RecyclerQuotesInformationAdapter.RecyclerQuotesInformationViewHolder> {

    ArrayList<QuoteInformationModel> data;
    public RecyclerQuotesInformationAdapter(ArrayList<QuoteInformationModel> data){
        this.data = data;
    }

    @Override
    public RecyclerQuotesInformationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_layout,parent,false);
        RecyclerQuotesInformationViewHolder holder = new RecyclerQuotesInformationViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerQuotesInformationViewHolder holder, int position) {
        holder.tv.setText(data.get(position).title);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class RecyclerQuotesInformationViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public RecyclerQuotesInformationViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.rv_tv_tmp);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

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

public class RecyclerQuotesDataAdapter extends RecyclerView.Adapter<RecyclerQuotesDataAdapter.RecyclerQuotesDataViewHolder> {

    ArrayList<QuoteDataModel> data;
    public RecyclerQuotesDataAdapter(ArrayList<QuoteDataModel> data){
        this.data = data;
    }

    @Override
    public RecyclerQuotesDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_layout,parent,false);
        RecyclerQuotesDataViewHolder holder = new RecyclerQuotesDataViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerQuotesDataViewHolder holder, int position) {
        holder.tv.setText(data.get(position).quote);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class RecyclerQuotesDataViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public RecyclerQuotesDataViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.rv_tv_tmp);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}

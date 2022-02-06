package in.xparticle.apirecycleractivity.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.xparticle.apirecycleractivity.Model.ReModel;
import in.xparticle.apirecycleractivity.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<ReModel>mList;
    Context context;

public MyAdapter(ArrayList<ReModel>mList, Context context){
this.context=context;
this.mList=mList;
}

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowItem= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new MyViewHolder(rowItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
       holder.login.setText(mList.get(position).getLogin());
       holder.url.setText(mList.get(position).getUrl());
         holder.id.setText(mList.get(position).getId());
//        holder.id.setText("1");
        Log.e("ten", "onBindViewHolder: ten"+mList  );
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id,login,url;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id= itemView.findViewById(R.id.tv_id);
            login=itemView.findViewById(R.id.tv_login);
            url=itemView.findViewById(R.id.tv_url);
        }
    }
}

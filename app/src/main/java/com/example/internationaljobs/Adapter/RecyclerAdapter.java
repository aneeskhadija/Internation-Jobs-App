package com.example.internationaljobs.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.internationaljobs.Activity.JobDetails;
import com.example.internationaljobs.Activity.JobsActivity;
import com.example.internationaljobs.databinding.ItemsBinding;
import com.example.internationaljobs.modles.CataModle;
import com.example.internationaljobs.modles.JobModle;
import com.google.gson.Gson;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter {

    List<Object> list;
    Context context;
    int typeView;

    public RecyclerAdapter(List<Object> list, Context context,int typeView) {
        this.list = list;
        this.context = context;
        this.typeView=typeView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (typeView==1||typeView==2)
            return new CataHolder(ItemsBinding.inflate(LayoutInflater.from(context),parent,false));
        else
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

        if (typeView==1)
        {
            CataHolder holder= (CataHolder) viewHolder;
            ItemsBinding binding=holder.binding;
            CataModle cataModle= (CataModle) list.get(position);

            int p=position+1;
//            binding.count.setText(""+p);
//            binding.text.setText(cataModle.getTitle());

        }

        if (typeView==2)
        {
            CataHolder holder= (CataHolder) viewHolder;
            ItemsBinding binding=holder.binding;
            JobModle modle= (JobModle) list.get(position);

            binding.job.setText(modle.getTitle());
            binding.company.setText(modle.getCompany());
            binding.dateTxt.setText(modle.getStartDate());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    private class CataHolder extends RecyclerView.ViewHolder{

        ItemsBinding binding;

        public CataHolder(ItemsBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
            binding.arrowImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (typeView==1)
                    {
                        CataModle modle= (CataModle) list.get(getAdapterPosition());
                        Intent intent=new Intent(context, JobsActivity.class);
                        intent.putExtra("text",modle.getTitle());
                        intent.putExtra("id",modle.getId());
                        context.startActivity(intent);
                    }
                    else if (typeView==2)
                    {
                        Gson gson = new Gson();
                        JobModle jobModle= (JobModle) list.get(getAdapterPosition());
                        String myJson = gson.toJson(jobModle);
                        Intent intent=new Intent(context, JobDetails.class);
                        intent.putExtra("modle", myJson);
                        intent.putExtra("text",((Activity)context).getIntent().getStringExtra("text"));
                        context.startActivity(intent);

                        if (getAdapterPosition()%3==0) {

//                            Toast.makeText(context, "yahoo", Toast.LENGTH_SHORT).show();
                            JobsActivity.admobIntersial.showInsAd();
                        }
                    }

                }
            });
        }
    }

}

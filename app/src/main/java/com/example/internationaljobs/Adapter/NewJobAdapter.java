package com.example.internationaljobs.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.internationaljobs.Activity.JobDetails;
import com.example.internationaljobs.databinding.NewjobrcrItemsBinding;
import com.example.internationaljobs.modles.JobModle;
import com.google.gson.Gson;

import java.util.List;

public class NewJobAdapter extends RecyclerView.Adapter {

    List<Object> list;
    Context context;

    public NewJobAdapter(List<Object> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new JobHolder(NewjobrcrItemsBinding.inflate(LayoutInflater.from(context),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        JobHolder jobHolder= (JobHolder) holder;
        NewjobrcrItemsBinding binding=jobHolder.binding;
        JobModle modle= (JobModle) list.get(position);
        binding.job.setText(modle.getTitle());
        binding.company.setText(modle.getCompany());
        binding.dateTxt.setText(modle.getStartDate());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private class JobHolder extends RecyclerView.ViewHolder {
        NewjobrcrItemsBinding binding;
        public JobHolder(NewjobrcrItemsBinding binding) {
            super(binding.getRoot());
            this.binding=binding;

            binding.arrowImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Gson gson = new Gson();
                    JobModle jobModle= (JobModle) list.get(getAdapterPosition());
                    String myJson = gson.toJson(jobModle);
                    Intent intent=new Intent(context, JobDetails.class);
                    intent.putExtra("modle", myJson);
                    intent.putExtra("text","New Job");
                    context.startActivity(intent);

                }
            });
        }
    }
}

package com.example.nazmussakib.firebaseapp;

import android.app.Dialog;
import android.app.job.JobInfo;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import DatabaseConnection.Job;
import SharedPreference.SharedPreference;
import bottomNavigation.favoriteJobList;
import bottomNavigation.homeBottomNav;



public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private RecyclerViewAdapter.ViewHolder viewHolder;
    private Context context;
    private List<jobInfo> jobList;
    private List<jobInfo> favorateJob;
    private TextView jobTitleDetailsView,companyNameDetailsView,jobNatureDetailsView,experienceTimeDetailsView;
    private Button applyDetailsView;
    private static LikeButton likeButton;
    private Dialog mDialog;
    private String name;


    private List<Job> job;



    private homeBottomNav home;
    private favoriteJobList favoriteJobList;

   //shared preference
    private SharedPreference sharedPreference;

    //For search
    private List<jobInfo> serachJobInfo;



    public RecyclerViewAdapter(Context context,List<jobInfo> jobList) {

        this.context=context;
        this.jobList = jobList;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cutom_recyclerview, viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);

        home = new homeBottomNav(context);
        favoriteJobList = new favoriteJobList(context);
        holder.recylerCustomeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                jobTitleDetailsView = mDialog.findViewById(R.id.jobTitleDetails);
                jobNatureDetailsView = mDialog.findViewById(R.id.jobNatureDetails);
                companyNameDetailsView = mDialog.findViewById(R.id.companyNameDerails);
                experienceTimeDetailsView = mDialog.findViewById(R.id.experienceTimeDetails);
                likeButton = mDialog.findViewById(R.id.heart_button);
                applyDetailsView = mDialog.findViewById(R.id.applyDetails);

                    jobTitleDetailsView.setText(jobList.get(holder.getAdapterPosition()).getTitle());
                    jobNatureDetailsView.setText(jobList.get(holder.getAdapterPosition()).getJobNature());
                    companyNameDetailsView.setText(jobList.get(holder.getAdapterPosition()).getCompanyName());
                    experienceTimeDetailsView.setText(jobList.get(holder.getAdapterPosition()).getExperienceTime());

                mDialog.show();
                mDialog.setCancelable(true);

                if(jobList.get(holder.getAdapterPosition()).getLinked())
                {
                    likeButton.setLiked(true);
                }

              //added favourite list
                final int position =holder.getAdapterPosition();
                likeButton.setOnLikeListener(new OnLikeListener() {
                    @Override
                    public void liked(LikeButton likeButton) {

                    }

                    @Override
                    public void unLiked(LikeButton likeButton) {

                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position,jobList.size());
                        mDialog.dismiss();
                        }


                });


            }
        });

        mDialog = new Dialog(context);
        mDialog.setContentView(R.layout.recyler_cutom_dailog);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        this.viewHolder = viewHolder;

            jobInfo jobs = jobList.get(position);

            viewHolder.jobTitle.setText(jobs.getTitle());
            viewHolder.companyName.setText(jobs.getCompanyName());
            viewHolder.jobNature.setText(jobs.getJobNature());
            viewHolder.experienceTime.setText(jobs.getExperienceTime());

    }

    @Override
    public int getItemCount() {
        if(jobList!=null)
        {
            return jobList.size();
        }
        else
        {
            return 0;
        }

    }


    private void applyJob(final int position)
    {
        applyDetailsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                print("Apply Button Clicked");

                print("You have Clicked "+position);

            }

        });

    }



    //viewHolder class

    public class ViewHolder extends RecyclerView.ViewHolder  {
        private TextView jobTitle,jobNature,companyName,experienceTime;

        private LinearLayout recylerCustomeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            jobTitle =itemView.findViewById(R.id.jobTitle);
            jobNature = itemView.findViewById(R.id.jobNature);
            companyName = itemView.findViewById(R.id.companyName);
            experienceTime = itemView.findViewById(R.id.experienceTime);
            recylerCustomeLayout = itemView.findViewById(R.id.custom_layout);
        }

    }


private void print(Object o)
{
    Toast.makeText(context,o.toString(),Toast.LENGTH_SHORT).show();
}

public void updateJobInfo(List<jobInfo> jobSearch)
{
    serachJobInfo = new ArrayList<>();
    serachJobInfo.addAll(jobSearch);
    notifyDataSetChanged();
}




}


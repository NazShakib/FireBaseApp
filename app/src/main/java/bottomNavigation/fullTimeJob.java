package bottomNavigation;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nazmussakib.firebaseapp.R;
import com.example.nazmussakib.firebaseapp.RecyclerViewAdapter;
import com.example.nazmussakib.firebaseapp.jobInfo;

import java.util.ArrayList;
import java.util.List;

import DatabaseConnection.ApiService;
import DatabaseConnection.Job;
import DatabaseConnection.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class fullTimeJob extends Fragment {


    private List<jobInfo> fullTimeJobs = new ArrayList<>();
    private Context context;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    public fullTimeJob(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_full_time_job, container, false);
        recyclerView = view.findViewById(R.id.jobPost);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        //retrofit data loader

        ApiService service = RetrofitClientInstance.getRetrofit().create(ApiService.class);
        Call<List<Job>> call = service.getJobsList();

        call.enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {

                List<Job>jobs = response.body();

                for(int position = 0;position<jobs.size();position++)
                {
                    jobInfo jobInfo = new jobInfo(jobs.get(position).getTitle(),jobs.get(position).getJobNature(),
                            jobs.get(position).getCompanyName(),jobs.get(position).getLocation());
                    if(jobInfo.getJobNature().equals("Full Time"))
                    {
                        fullTimeJobs.add(jobInfo);
                    }
                }
                recyclerViewAdapter = new RecyclerViewAdapter(context,fullTimeJobs);
                recyclerViewAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {

            }

        });

        return view;
    }



}

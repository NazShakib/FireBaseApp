package bottomNavigation;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nazmussakib.firebaseapp.R;
import com.example.nazmussakib.firebaseapp.RecyclerViewAdapter;
import com.example.nazmussakib.firebaseapp.jobInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import DatabaseConnection.ApiService;
import DatabaseConnection.Job;
import DatabaseConnection.RetrofitClientInstance;
import SharedPreference.SharedPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class homeBottomNav extends Fragment {

    private static final String TAG = "homeBottomNav";

    private List<jobInfo> homeJobs = new ArrayList<>();
    private Context context;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private String myName = "HOME";
    private List<jobInfo> jobList = new ArrayList<>();


    private SharedPreference sharedPreference;


    public homeBottomNav(Context context) {

        this.context = context;


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_bottom_nav, container, false);
        sharedPreference = new SharedPreference();

        recyclerView = view.findViewById(R.id.jobPost);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //Retrofit data

        ApiService service = RetrofitClientInstance.getRetrofit().create(ApiService.class);
        Call<List<Job>> call = service.getJobsList();

        call.enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {

                jobList.clear();
                    List<Job> jobs = response.body();

                    //remove all the list from shared prefrences
                    List<jobInfo> sharedInfo = sharedPreference.getJobList(context);
                    for (int i=0;i<sharedInfo.size();i++)
                    {
                        sharedPreference.removeJobList(context,sharedInfo.get(i));
                    }


                  try {

                      int count = 0;
                      for (int position = jobs.size()-1; position >= 0; position--) {
                          jobInfo jobInfo = new jobInfo(jobs.get(position).getTitle(), jobs.get(position).getJobNature(),
                                  jobs.get(position).getCompanyName(), jobs.get(position).getLocation());
                          jobList.add(jobInfo);

                          if (count <= 9) {
                              sharedPreference.addJobList(context, jobInfo);
                          }
                          count++;
                      }
                      recyclerViewAdapter = new RecyclerViewAdapter(context, jobList);
                      recyclerViewAdapter.notifyDataSetChanged();
                      recyclerView.setAdapter(recyclerViewAdapter);
                  }
                  catch (ExceptionInInitializerError e)
                  {
                      Log.d(TAG, "onResponse: "+e.toString());
                  }

            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {

                jobList.clear();
                List<jobInfo> jobInfos =sharedPreference.getJobList(context);
                for (int position =0;position<jobInfos.size();position++)
                {
                    jobInfo jobInfo = new jobInfo(jobInfos.get(position).getTitle(), jobInfos.get(position).getJobNature(),
                            jobInfos.get(position).getCompanyName(), jobInfos.get(position).getExperienceTime());
                    jobList.add(jobInfo);
                }
                recyclerViewAdapter = new RecyclerViewAdapter(context, jobList);
                recyclerViewAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(recyclerViewAdapter);

            }

        });

        return view;
    }

    private void print(Object o)
    {
        Toast.makeText(context,o.toString(),Toast.LENGTH_SHORT).show();
    }

    public RecyclerViewAdapter getRecyclerViewAdapter() {
        return recyclerViewAdapter;
    }

    public List<jobInfo> getJobList() {
        return jobList;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }
}

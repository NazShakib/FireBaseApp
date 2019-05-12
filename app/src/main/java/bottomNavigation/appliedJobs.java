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

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class appliedJobs extends Fragment {


    private List<jobInfo> appliedJobs = new ArrayList<>();
    private Context context;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;


    public appliedJobs(Context context) {
        this.context = context;

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_applied_jobs, container, false);
        recyclerView = view.findViewById(R.id.jobPost);

        recyclerViewAdapter = new RecyclerViewAdapter(context,appliedJobs);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerViewAdapter);
        preparedJobCollection();
        return view;
    }


    private void preparedJobCollection() {
        clear();
        jobInfo jobinfo = new jobInfo("Web Developer","Job Nature: Full-Time","Virtual Ecos IT Firm","1-2 years");
        appliedJobs.add(jobinfo);

        jobinfo = new jobInfo("App Developer","Job Nature: Full-Time","Virtual Ecos IT Firm","2 years");
        appliedJobs.add(jobinfo);

        jobinfo = new jobInfo("Web Developer","Job Nature: Full-Time","Virtual Ecos IT Firm","1-2 years");
        appliedJobs.add(jobinfo);

        jobinfo = new jobInfo("App Developer","Job Nature: Full-Time","Virtual Ecos IT Firm","1-2 years");
        appliedJobs.add(jobinfo);

        jobinfo = new jobInfo("Web Developer","Job Nature: Full-Time","Virtual Ecos IT Firm","1-2 years");
        appliedJobs.add(jobinfo);

        jobinfo = new jobInfo("IOS Developer","Job Nature: Full-Time","Virtual Ecos IT Firm","3 years");
        appliedJobs.add(jobinfo);

        jobinfo = new jobInfo("Web Developer","Job Nature: Full-Time","Virtual Ecos IT Firm","1-2 years");
        appliedJobs.add(jobinfo);

        jobinfo = new jobInfo("IOS Developer","Job Nature: Full-Time","Virtual Ecos IT Firm","1-2 years");
        appliedJobs.add(jobinfo);

        recyclerViewAdapter.notifyDataSetChanged();

    }

    private void clear()
    {
        appliedJobs.clear();
    }


}

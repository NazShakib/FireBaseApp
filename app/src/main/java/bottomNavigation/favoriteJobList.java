package bottomNavigation;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
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
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class favoriteJobList extends Fragment  {



    private Context context;
    private List<jobInfo> favoriteJobList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private homeBottomNav home;


    public favoriteJobList(Context context) {
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_favorite_job_list, container, false);

        recyclerView = view.findViewById(R.id.jobPost);

        home = new homeBottomNav(context);
        recyclerViewAdapter = new RecyclerViewAdapter(context,favoriteJobList);
        preparedJobCollection();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerViewAdapter);


        return view;
    }

   private void preparedJobCollection() {
        clear();

       jobInfo jobinfo = new jobInfo("Web Developer","Job Nature: Full-Time","Virtual Ecos IT Firm","1-2 years",true);
        favoriteJobList.add(jobinfo);

        jobinfo = new jobInfo("App Developer","Job Nature: Full-Time","Virtual Ecos IT Firm","2 years",true);
        favoriteJobList.add(jobinfo);

        jobinfo = new jobInfo("Web Developer","Job Nature: Full-Time","Virtual Ecos IT Firm","1-2 years",true);
        favoriteJobList.add(jobinfo);

        jobinfo = new jobInfo("App Developer","Job Nature: Part-Time","Virtual Ecos IT Firm","1-2 years",true);
        favoriteJobList.add(jobinfo);

        jobinfo = new jobInfo("Web Developer","Job Nature: Part-Time","Virtual Ecos IT Firm","1-2 years",true);
        favoriteJobList.add(jobinfo);

        jobinfo = new jobInfo("IOS Developer","Job Nature: Part-Time","Virtual Ecos IT Firm","3 years",true);
        favoriteJobList.add(jobinfo);

        jobinfo = new jobInfo("Web Developer","Job Nature: Full-Time","Virtual Ecos IT Firm","1-2 years",true);
        favoriteJobList.add(jobinfo);

        jobinfo = new jobInfo("IOS Developer","Job Nature: Full-Time","Virtual Ecos IT Firm","1-2 years",true);
        favoriteJobList.add(jobinfo);


        recyclerViewAdapter.notifyDataSetChanged();
    }



    private void clear()
    {
        favoriteJobList.clear();
    }

    private void print(Object o)
    {
        Toast.makeText(context,o.toString(),Toast.LENGTH_SHORT).show();
    }

}

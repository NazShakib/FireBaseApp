package SharedPreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.example.nazmussakib.firebaseapp.jobInfo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SharedPreference {

    public static final String PREFS_NAME = "JOB_LIST";
    public static final String FAVORITES = "Favorite_JOBS";


    public SharedPreference() {
        super();
    }

    public void saveJobList(Context context, List<jobInfo> favorites) {
        SharedPreferences settings;
        Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();
        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);
        editor.putString(FAVORITES, jsonFavorites);
        editor.commit();
        editor.apply();
    }


    public void addJobList(Context context, jobInfo jobInfo) {
        List<jobInfo> favorites = getJobList(context);
        if (favorites == null)
            favorites = new ArrayList<jobInfo>();
        favorites.add(jobInfo);
        saveJobList(context, favorites);
    }

    public void removeJobList(Context context, jobInfo jobInfo) {
        ArrayList<jobInfo> favorites = getJobList(context);
        if (favorites != null) {
            favorites.remove(jobInfo);
            saveJobList(context, favorites);
        }
    }



    public ArrayList<jobInfo> getJobList(Context context) {
        SharedPreferences settings;
        List<jobInfo> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            jobInfo[] favoriteItems = gson.fromJson(jsonFavorites,
                    jobInfo[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<jobInfo>(favorites);
        } else
            return null;

        return (ArrayList<jobInfo>) favorites;
    }


}

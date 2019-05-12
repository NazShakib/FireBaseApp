package DatabaseConnection;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {


    @GET("jobs.php")
    Call<List<Job>> getJobsList();

    @GET("job.php")
    Call<JobDetails> getJobDetail(@Query("id") int id);

}

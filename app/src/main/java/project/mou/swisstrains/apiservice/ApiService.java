package project.mou.swisstrains.apiservice;

import project.mou.swisstrains.models.Result;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("v1/connections")
    Call<Result> getConnections(@Query("from") String from, @Query("to") String to);


}

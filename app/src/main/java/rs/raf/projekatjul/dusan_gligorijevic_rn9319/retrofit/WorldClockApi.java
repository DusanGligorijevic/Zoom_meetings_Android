package rs.raf.projekatjul.dusan_gligorijevic_rn9319.retrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WorldClockApi {


    @GET("/api/timezone/{region}/{city}")
    Call<EasternStandardTimeModel> getEasternStandardTimeForRegionAndCity(@Path(value = "region") String myRegion, @Path(value = "city") String myCity);

    @GET("/api/timezone/{region}")
    Call<ArrayList<String>> getCities(@Path(value = "region") String myRegion);
}

package net.syarihu.android.androidtraining.gifmagazine;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface GifmagazineApiService {
    @GET("/v1/gifs/search")
    Call<GifmagazineData> search(@Query("q") String query);
}

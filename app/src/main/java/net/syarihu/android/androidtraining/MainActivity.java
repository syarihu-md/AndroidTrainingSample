package net.syarihu.android.androidtraining;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;

import net.syarihu.android.androidtraining.databinding.ActivityMainBinding;
import net.syarihu.android.androidtraining.gifmagazine.GifmagazineApiService;
import net.syarihu.android.androidtraining.gifmagazine.GifmagazineData;
import net.syarihu.android.androidtraining.gifmagazine.SearchResult;

import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<GifmagazineData>, TextWatcher {
    GifmagazineApiService service;
    GifImageListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.gifmagazine.net/")
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(GifmagazineApiService.class);

        adapter = new GifImageListAdapter(this, new ArrayList<SearchResult>());
        binding.list.setAdapter(adapter);
        binding.search.addTextChangedListener(this);
    }

    @Override
    public void onResponse(@NonNull Call<GifmagazineData> call, @NonNull Response<GifmagazineData> response) {
        GifmagazineData data = response.body();
        if (data == null)
            return;
        if (!isFinishing())
            adapter.replaceList(Arrays.asList(data.data));
    }

    @Override
    public void onFailure(@NonNull Call<GifmagazineData> call, @NonNull Throwable t) {
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void afterTextChanged(Editable editable) {
        service.search(editable.toString()).enqueue(this);
    }
}

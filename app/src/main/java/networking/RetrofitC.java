package networking;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitC {
    public Api api;
    public  Retrofit retrofit;

    public RetrofitC() {
        retrofit= getRetrofit();
        api=retrofit.create(Api.class);
    }

    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}

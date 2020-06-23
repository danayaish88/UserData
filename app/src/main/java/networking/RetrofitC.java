package networking;

import java.util.List;

import DataModels.User;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitC {
    private Api api;
    private Retrofit retrofit;
    private static RetrofitC single_instance = null;


    public static RetrofitC getInstance()
    {
        if (single_instance == null)
            single_instance = new RetrofitC();

        return single_instance;
    }

    private RetrofitC() {
        retrofit= getRetrofit();
        api=retrofit.create(Api.class);
    }

    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public Observable<List<User>> getUsersFromApi(){
        return api.getUsers();
    }
}

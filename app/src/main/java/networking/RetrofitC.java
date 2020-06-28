package networking;

import java.util.List;

import DataModels.User;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitC {
    private static RetrofitC singleInstance = null;//TODO : please follow convention ( camel casing )
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private Api api;
    private Retrofit retrofit;


    public static RetrofitC getInstance() {
        if (singleInstance == null)
            singleInstance = new RetrofitC();

        return singleInstance;
    }

    private RetrofitC() {
        retrofit = getRetrofit();
        api = retrofit.create(Api.class);
    }

    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public Observable<List<User>> getUsersFromApi() {
        return api.getUsers();
    }
}

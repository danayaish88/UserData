package networking;

import DataModels.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Api {

    @GET("users")
    Observable<List<User>> getUsers();

}

package networking;

import DataModels.User;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("users")
    Call<List<User>> getUsers();

}

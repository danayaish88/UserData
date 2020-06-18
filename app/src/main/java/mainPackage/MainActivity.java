package mainPackage;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.userdata.R;
import java.util.List;
import DataModels.User;
import networking.Api;
import recyclerView.UserAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //TODO : please make sure to move the creation of the retrofit object
        // from this screen into the network package , since it might be needed
        // in different screens and to avoid duplicate code .

        Retrofit retrofit = getRetrofit();

        Api api = retrofit.create(Api.class);

        Call<List<User>> call = api.getUsers();

        // TODO : please dont put all of your calls into onCreate
        // as there are multiple lifecycle callbacks for an activity .
        // for example let the reference to recycler here onCreate and call
        // getUsers in onStart or onResume .
        callEnqueue(call);



        // Lookup the recyclerview in activity layout
         rvUsers = (RecyclerView) findViewById(R.id.rvUsers);

         //TODO : make sure to deleted commented out code
//        for(User u:users){
//            Log.d("name:", String.valueOf(u.getName()));
//
//        }
        // Create adapter passing in the sample user data
       // UserAdapter adapter = new UserAdapter(users);
        // Attach the adapter to the recyclerview to populate items
        //rvUsers.setAdapter(adapter);
        // Set layout manager to position the items
        //rvUsers.setLayoutManager(new LinearLayoutManager(this));
        // That's all!

    }

    private void callEnqueue(Call<List<User>> call) {
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> users = response.body();
                populateList(users);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

                // TODO : please make sure to handle API failure ,
                // for example try to show a toast using  Toast.makeText(...).show();
                // that will inform the user about network failure .
            }
        });
    }

    private void populateList(List<User> users) {
        UserAdapter userAdapter = new UserAdapter(users);
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
        rvUsers.setAdapter(userAdapter);
    }

    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
    }
}


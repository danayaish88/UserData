package mainPackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.userdata.R;
import java.util.List;
import DataModels.User;
import networking.RetrofitC;
import recyclerView.UserAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO : please dont put all of your calls into onCreate
        // as there are multiple lifecycle callbacks for an activity .
        // for example let the reference to recycler here onCreate and call
        // getUsers in onStart or onResume .

        getUsersFromApi();



        // Lookup the recyclerview in activity layout
         rvUsers = (RecyclerView) findViewById(R.id.rvUsers);

         //TODO : make sure to deleted commented out code
        // Create adapter passing in the sample user data
       // UserAdapter adapter = new UserAdapter(users);
        // Attach the adapter to the recyclerview to populate items
        //rvUsers.setAdapter(adapter);
        // Set layout manager to position the items
        //rvUsers.setLayoutManager(new LinearLayoutManager(this));
        // That's all!

    }

    private void getUsersFromApi() {

        RetrofitC retrofit =new RetrofitC();

        Call<List<User>> call = retrofit.api.getUsers();

        callEnqueue(call);

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

                t.printStackTrace();

                Toast toast = Toast.makeText(getApplicationContext(),
                        "Error getting Data",
                        Toast.LENGTH_SHORT);

                toast.show();

            }
        });
    }


    private void populateList(List<User> users) {
        UserAdapter userAdapter = new UserAdapter(users);
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
        rvUsers.setAdapter(userAdapter);
    }


}


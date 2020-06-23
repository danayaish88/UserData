package mainPackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.userdata.R;

import java.util.List;

import DataModels.User;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import networking.RetrofitC;
import recyclerView.UserAdapter;

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

        // Lookup the recyclerview in activity layout
         rvUsers = (RecyclerView) findViewById(R.id.rvUsers);

         //TODO : make sure to deleted commented out code


    }

    @Override
    protected void onStart() {
        super.onStart();
        getUsersFromApi();
    }

    private void getUsersFromApi() {

         RetrofitC.getInstance()
                 .getUsersFromApi()
                 .subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Observer<List<User>>() {
                     @Override
                     public void onSubscribe(Disposable d) {

                     }

                     @Override
                     public void onNext(List<User> users) {
                         populateList(users);

                     }

                     @Override
                     public void onError(Throwable e) {
                         e.printStackTrace();

                         Toast toast = Toast.makeText(getApplicationContext(),
                                 "Error getting Data",
                                 Toast.LENGTH_SHORT);

                         toast.show();
                     }

                     @Override
                     public void onComplete() {

                     }
                 });
    }

    private void populateList(List<User> users) {
        UserAdapter userAdapter = new UserAdapter(users);
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
        rvUsers.setAdapter(userAdapter);
    }

}


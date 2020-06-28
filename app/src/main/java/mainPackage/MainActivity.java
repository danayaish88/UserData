package mainPackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.userdata.R;

import DataModels.User;
import contract.UserActivityContract;
import presenter.UserPresenter;
import recyclerView.UserAdapter;

import static mainPackage.UserDetails.TAG;


public class MainActivity extends AppCompatActivity implements UserActivityContract.View {

    //TODO : please start use ButterKnife library
    private RecyclerView rvUsers;


    private UserPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // as there are multiple lifecycle callbacks for an activity .
        // for example let the reference to recycler here onCreate and call
        // getUsers in onStart or onResume .

        // Lookup the recyclerview in activity layout
        rvUsers = findViewById(R.id.rvUsers);
        mPresenter = new UserPresenter(this);


    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.start();
    }

    @Override
    public void init() {
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
        mPresenter.loadUsers();
    }

    @Override
    public void showError(String message) {

        Toast toast = Toast.makeText(this,
                "Error getting Data",
                Toast.LENGTH_SHORT);

        toast.show();
    }

    @Override
    public void loadDataInList() {
        UserAdapter userAdapter = new UserAdapter(mPresenter);
        rvUsers.setAdapter(userAdapter);
    }

    @Override
    public void startUSerDetailsActivity(User user) {
        Intent intent = new Intent(this, UserDetails.class);
        intent.putExtra(TAG, user); // TODO : by convention its KEY_USER ( use it instead of TAG )
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }
}


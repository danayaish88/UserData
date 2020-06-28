package mainPackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.userdata.R;

import java.util.List;

import DataModels.User;
import butterknife.BindView;
import butterknife.ButterKnife;
import contract.UserActivityContract;
import presenter.UserPresenter;
import recyclerView.UserAdapter;


public class MainActivity extends AppCompatActivity implements UserActivityContract.View {

    @BindView(R.id.rvUsers) RecyclerView rvUsers;

    //TODO : please start use ButterKnife library
    private UserPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        // as there are multiple lifecycle callbacks for an activity .
        // for example let the reference to recycler here onCreate and call
        // getUsers in onStart or onResume .
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
    public void loadDataInList(List<User> users) {
        UserAdapter userAdapter = new UserAdapter(mPresenter,users);
        rvUsers.setAdapter(userAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }
}
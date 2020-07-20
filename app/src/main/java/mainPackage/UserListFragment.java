package mainPackage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.userdata.R;

import java.util.List;

import DataModels.User;
import adapters.UserAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import contract.UserActivityContract;
import presenter.UserPresenter;


public class UserListFragment extends Fragment implements UserActivityContract.View {

    @BindView(R.id.rvUsers)
    RecyclerView rvUsers;

    private UserPresenter mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new UserPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void init() {
        rvUsers.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mPresenter.loadUsers();
    }

    @Override
    public void showError(String message) {
        Toast toast = Toast.makeText(this.getContext(),
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
    public void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }
}
package mainPackage;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.userdata.R;

import java.util.ArrayList;
import java.util.List;

import DataModels.User;
import adapters.IdsAdapter;
import adapters.RecyclerViewAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import contract.UserFragmentContract;
import presenter.UserPresenter;


public class UserListFragment extends Fragment implements UserFragmentContract.View {

    @BindView(R.id.rvUsers)
    RecyclerView rvUsers;

    private UserPresenter mPresenter;
    private ArrayList<Integer> ids;

    OnHeadlineSelectedListener callback;


    public interface OnHeadlineSelectedListener {
        public void onUserSelected(Integer position);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initList();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        callback = (OnHeadlineSelectedListener) context;
    }

    private void initList() {
        ids = new ArrayList<>();
        for(int i = 0; i < 10; i ++){
            ids.add(i);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);
        ButterKnife.bind(this, view);

        mPresenter = new UserPresenter(this);

        return view;
    }

    public static UserListFragment getInstance() {
        return new UserListFragment();
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start();
    }

    @Override
    public void init() {
        rvUsers.setLayoutManager(new LinearLayoutManager(this.getContext()));
        //mPresenter.loadUsers();
        mPresenter.loadIds(ids);
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
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(mPresenter,users);
        rvUsers.setAdapter(recyclerViewAdapter);
    }

    public void loadIdInList(List<Integer> data) {
        IdsAdapter idsAdapter = new IdsAdapter(mPresenter,data);
        rvUsers.setAdapter(idsAdapter);
    }

    @Override
    public void sendId(Integer id) {
        callback.onUserSelected(id);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
    }

}
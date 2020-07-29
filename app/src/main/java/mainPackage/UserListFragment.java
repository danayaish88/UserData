package mainPackage;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import adapters.RecyclerViewAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import contract.UserFragmentContract;
import presenter.UserPresenter;


public class UserListFragment extends Fragment implements UserFragmentContract.View {

    @BindView(R.id.rvUsers)
    RecyclerView rvUsers;

    private UserPresenter mPresenter;
    private static List<User> userList;

    OnHeadlineSelectedListener callback;


    public interface OnHeadlineSelectedListener {
        public void onUserSelected(Integer position);  //TODO : remove void , interface method are public final by default
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // TODO : check if your context is an instance of ur callback , otherwise its gonna cause runtime exception
        callback = (OnHeadlineSelectedListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);
        ButterKnife.bind(this, view);

        //TODO: i dont think preseneter is any longer needed
        mPresenter = UserPresenter.getIntance();
        mPresenter.setView(this);

        if(userList != null){
            loadDataInList(userList);
        }

        return view;
    }

    public static UserListFragment getInstance(List<User> users) {
        //TODO : please instead use bundle to pass users list , same concept we use to pass data between activities .
        userList = users;
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

    }

    @Override
    public void showError(String message) {
        Toast toast = Toast.makeText(this.getContext(),
                "Error getting Data", //TODO : all texts must be constatnts
                Toast.LENGTH_SHORT);

        toast.show();
    }

    public void loadDataInList(List<User> users) {
        //TODO : get adapter , then check if its null -> create one , if not , check its instance and update
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(mPresenter,users);
        // TODO : let the layout manager call be here , with same check for the adapter .
        rvUsers.setAdapter(recyclerViewAdapter);
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
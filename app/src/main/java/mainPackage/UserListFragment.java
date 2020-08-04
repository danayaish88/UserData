package mainPackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.userdata.R;

import java.util.ArrayList;
import java.util.List;

import DataModels.User;
import adapters.UsersAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;


public class UserListFragment extends Fragment implements UsersAdapter.OnUserSelectedListener {

    private static final String USERS = "Users";
    private UsersAdapter usersAdapter;
    private LinearLayoutManager linearLayoutManager;
    private OnHeadlineSelectedListener callback;

    @BindView(R.id.rvUsers)
    RecyclerView rvUsers;
    @BindView(R.id.noData)
    TextView noDataTV;

    public interface OnHeadlineSelectedListener {
        void onUserSelected(Integer position);  //TODO : remove void , interface method are public final by default
    }

    public UserListFragment() {
    }

    public static UserListFragment getInstance(List<User> users) {
        //TODO : please instead use bundle to pass users list , same concept we use to pass data between activities .
        UserListFragment userListFragment = new UserListFragment();

        Bundle args = new Bundle();
        args.putParcelableArrayList(USERS, (ArrayList<? extends Parcelable>) users);
        userListFragment.setArguments(args);

        return userListFragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // TODO : check if your context is an instance of ur callback , otherwise its gonna cause runtime exception
        if(context instanceof OnHeadlineSelectedListener){
            callback = (OnHeadlineSelectedListener) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);
        ButterKnife.bind(this, view);

        //TODO: i dont think preseneter is any longer needed

        List<User> userList = getUserList();

        viewData(userList);

        return view;
    }

    private void viewData(List<User> userList) {
        if(userList != null && userList.size() != 0){
            loadDataInList(userList);
            noDataTV.setVisibility(View.INVISIBLE);
            rvUsers.setVisibility(View.VISIBLE);
        }else{
            noDataTV.setVisibility(View.VISIBLE);
            rvUsers.setVisibility(View.INVISIBLE);
        }
    }

    private List<User> getUserList() {
        if(getArguments() != null){
            return getArguments().getParcelableArrayList(USERS);
        }
        return null;
    }

    public void loadDataInList(List<User> users) {
        //TODO : get adapter , then check if its null -> create one , if not , check its instance and update
        setUserAdapter();

        setLayoutManager();

        usersAdapter.setData(users);
        // TODO : let the layout manager call be here , with same check for the adapter .
    }

    private void setLayoutManager() {
        if(linearLayoutManager == null){
            linearLayoutManager = new LinearLayoutManager(this.getContext());
        }
        rvUsers.setLayoutManager(linearLayoutManager);
    }

    private void setUserAdapter() {
        if(usersAdapter == null){
            usersAdapter = new UsersAdapter(this);
        }
        rvUsers.setAdapter(usersAdapter);
    }

    @Override
    public void sendId(Integer id) {
        if(callback != null){
            callback.onUserSelected(id);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        usersAdapter.destroy();
    }

}
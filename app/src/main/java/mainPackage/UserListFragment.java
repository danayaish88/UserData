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

import com.example.userdata.R;

import java.util.List;

import DataModels.User;
import adapters.UsersAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import contract.UserFragmentContract;
import presenter.UserPresenter;


public class UserListFragment extends Fragment {

    @BindView(R.id.rvUsers)
    RecyclerView rvUsers;

    private List<User> userList;

    OnHeadlineSelectedListener callback;

    public UserListFragment(List<User> users) {
        userList = users;
    }

    public interface OnHeadlineSelectedListener {
          void onUserSelected(Integer position);  //TODO : remove void , interface method are public final by default
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

        if(userList != null){
            loadDataInList(userList);
        }

        return view;
    }

    public static UserListFragment getInstance(List<User> users) {
        //TODO : please instead use bundle to pass users list , same concept we use to pass data between activities .
        return new UserListFragment(users);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void loadDataInList(List<User> users) {
        //TODO : get adapter , then check if its null -> create one , if not , check its instance and update
        UsersAdapter usersAdapter = new UsersAdapter(users);
        // TODO : let the layout manager call be here , with same check for the adapter .
        rvUsers.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rvUsers.setAdapter(usersAdapter);
    }

  /*  @Override
    public void sendId(Integer id) {
        callback.onUserSelected(id);
    }*/

   /* @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.destroy(); //should be called from usersAdapter
    }*/

}
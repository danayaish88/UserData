package mainPackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.userdata.R;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import DataModels.User;
import adapters.TabAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import contract.UserFragmentContract;
import presenter.UserPresenter;

import static adapters.TabAdapter.DETAILS_FRAGMENT_INDEX;


public class MainActivity extends AppCompatActivity implements UserListFragment.OnHeadlineSelectedListener,
        UserFragmentContract.View {

    public static final String ERROR_MSG = "Error getting Data";
    private static final int USER_DETAILS_INDEX = 1;
    private TabAdapter pagerAdapter;

    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setPresenter();
    }

    private void setPresenter() {
        UserPresenter presenter = UserPresenter.getIntance();
        presenter.setView(this);
        presenter.loadUsers();
    }

    @Override
    public void onUserSelected(Integer id) {
        goToSelectedUser(id);
        pager.setCurrentItem(USER_DETAILS_INDEX);
    }

    private void goToSelectedUser(Integer id) {
        // TODO : instead of creating one and assiging it  , get the fragment from the tab adapter and then ask it to display detail .
        ParentDetailsFragment parentDetailsFragment = (ParentDetailsFragment) pagerAdapter.getItem(DETAILS_FRAGMENT_INDEX);
        parentDetailsFragment.goToUserId(id);
    }

    @Override
    public void onListReady(List<User> users) {
        setPagerAdapter(users);
        tabLayout.setupWithViewPager(pager);
    }

    private void setPagerAdapter(List<User> users) {
        pagerAdapter = new TabAdapter(getSupportFragmentManager(), users);
        pager.setAdapter(pagerAdapter);
    }

    @Override
    public void showError(String message) {
        Toast toast = Toast.makeText(this,
                ERROR_MSG, //TODO : all texts must be constatnts
                Toast.LENGTH_SHORT);

        toast.show();
    }

    @Override
    public void setFav(Integer id) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(String.valueOf(id), true);
        editor.apply();
    }

    @Override
    public boolean checkisFav(int id) {
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
          return sharedPref.getBoolean(String.valueOf(id), false);
    }
}
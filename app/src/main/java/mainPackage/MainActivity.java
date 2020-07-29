package mainPackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

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
    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    private TabAdapter pagerAdapter;
    private List<User> users;

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


    private void setPagerAdapter(List<User> users) {
        pagerAdapter = new TabAdapter(getSupportFragmentManager(), users);
        pager.setAdapter(pagerAdapter);
    }

    @Override
    public void onUserSelected(Integer id) {
        // TODO : instead of creating one and assiging it  , get the fragment from the tab adapter and then ask it to display detail .
        ParentDetailsFragment parentDetailsFragment = (ParentDetailsFragment) pagerAdapter.getItem(DETAILS_FRAGMENT_INDEX);
        parentDetailsFragment.goToUserId();
        pager.setCurrentItem(USER_DETAILS_INDEX);
    }

    @Override
    public void onListReady(List<User> users) {
        this.users = users;
        setPagerAdapter(users);
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    public void showError(String message) {
        Toast toast = Toast.makeText(this,
                ERROR_MSG, //TODO : all texts must be constatnts
                Toast.LENGTH_SHORT);

        toast.show();
    }
}
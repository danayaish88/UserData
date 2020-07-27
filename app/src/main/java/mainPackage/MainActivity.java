package mainPackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.userdata.R;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import DataModels.User;
import adapters.TabAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import contract.UserFragmentContract;
import presenter.UserPresenter;


public class MainActivity extends AppCompatActivity implements UserListFragment.OnHeadlineSelectedListener,
        UserFragmentContract.ActivityView {

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
        presenter.setActivityView(this);
        presenter.loadUsers();
    }


    private void setPagerAdapter(List<User> users) {
        pagerAdapter = new TabAdapter(getSupportFragmentManager(), users);
        pager.setAdapter(pagerAdapter);
    }

    @Override
    public void onUserSelected(Integer id) {
        ParentDetailsFragment parentDetailsFragment = ParentDetailsFragment.getInstance(id, users);
        parentDetailsFragment.goToUserId();
        pager.setCurrentItem(USER_DETAILS_INDEX);
    }

    @Override
    public void onListReady(List<User> users) {
        this.users = users;
        setPagerAdapter(users);
        tabLayout.setupWithViewPager(pager);
    }
}
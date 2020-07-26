package mainPackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.userdata.R;
import com.google.android.material.tabs.TabLayout;

import adapters.TabAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

import static mainPackage.UserDetailsFragment.ARG_USER_ID;


public class MainActivity extends AppCompatActivity implements UserListFragment.OnHeadlineSelectedListener{

    private static final int USER_DETAILS_INDEX = 1;
    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    private TabAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        pagerAdapter = new TabAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(pager);

    }

    @Override
    public void onUserSelected(Integer id) {
        UserDetailsFragment userDetailsFragment = UserDetailsFragment.getInstance();
        setSelectedId(id, userDetailsFragment);
        pager.setCurrentItem(USER_DETAILS_INDEX);
    }

    private void setSelectedId(Integer id, UserDetailsFragment userDetailsFragment) {
        Bundle args = new Bundle();
        args.putInt(ARG_USER_ID, id);
        userDetailsFragment.setArguments(args);
        userDetailsFragment.setId();
    }
}
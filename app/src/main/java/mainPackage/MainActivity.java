package mainPackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.example.userdata.R;
import com.google.android.material.tabs.TabLayout;

import adapters.TabAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;


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

        pagerAdapter = new TabAdapter(getSupportFragmentManager(), 2);
        pager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(pager);

    }

    @Override
    public void onArticleSelected(Integer id) {
        UserDetailsFragment userDetailsFragment = UserDetailsFragment.getInstance();
        userDetailsFragment.setId(id);
        pager.setCurrentItem(USER_DETAILS_INDEX);
    }
}
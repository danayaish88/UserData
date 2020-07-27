package mainPackage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

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

        setPagerAdapter();

        tabLayout.setupWithViewPager(pager);
    }

    private void setPagerAdapter() {
        pagerAdapter = new TabAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
    }

    @Override
    public void onUserSelected(Integer id) {
        ParentDetailsFragment parentDetailsFragment = ParentDetailsFragment.getInstance(id);
        parentDetailsFragment.goToUserId();
        pager.setCurrentItem(USER_DETAILS_INDEX);
    }
}
package mainPackage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.userdata.R;

import java.util.List;

import DataModels.User;
import adapters.DetailsPagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import presenter.UserPresenter;


public class ParentDetailsFragment extends Fragment {


    public static ParentDetailsFragment singleFragment = null;
    private static final String USER_ID = "userId" ;

    private Integer id = 1;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    public ParentDetailsFragment() {
        // Required empty public constructor
    }

    public static ParentDetailsFragment getInstance(Integer id) {
        if(singleFragment == null){
            singleFragment = new ParentDetailsFragment();
        }

        Bundle args = new Bundle();
        args.putInt(USER_ID, id);
        singleFragment.setArguments(args);
        return singleFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.id = getUserId();

    }

    private Integer getUserId() {
        if(getArguments() != null){
            id = getArguments().getInt(USER_ID);
        }
        return id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_parent_details, container, false);
        ButterKnife.bind(this, view);

        viewPager.setAdapter(buildAdapter());
        viewPager.setCurrentItem(id-1);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private PagerAdapter buildAdapter() {
        return new DetailsPagerAdapter(getChildFragmentManager());
    }

    public void goToUserId() {
        Integer id = getUserId();
        viewPager.setCurrentItem(id-1);
    }

}
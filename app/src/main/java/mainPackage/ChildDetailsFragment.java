package mainPackage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.userdata.R;

import DataModels.Address;
import DataModels.Company;
import DataModels.User;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ChildDetailsFragment extends Fragment {


    public static final String ARG_USER_ID = "userId";
    private int id = 1;

    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    @BindView(R.id.name)
    TextView nameTV;
    @BindView(R.id.username)
    TextView usernameTV;
    @BindView(R.id.email)
    TextView emailTV;
    @BindView(R.id.address)
    TextView addressTV;
    @BindView(R.id.phone)
    TextView phoneTV;
    @BindView(R.id.website)
    TextView websiteTV;
    @BindView(R.id.company)
    TextView companyTV;

    private ChildDetailsFragment() {
        // Required empty constructor
    }

    public static ChildDetailsFragment getInstance(int id) {
         ChildDetailsFragment childDetailsFragment = new ChildDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_USER_ID, id);
        childDetailsFragment.setArguments(bundle);
        return childDetailsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_child_details, container, false);
        ButterKnife.bind(this, view);

        //showValues();

        setId();
        return view;
    }


    private void assignValues(User user) {
        name=user.getName();
        username=user.getUsername();
        email=user.getEmail();
        address=user.getAddress();
        phone=user.getPhone();
        website=user.getWebsite();
        company=user.getCompany();
    }

    private void showValues() {
        nameTV.setText(name);
        usernameTV.setText(username);
        emailTV.setText(email);
        addressTV.setText(address.toString());
        phoneTV.setText(phone);
        websiteTV.setText(website);
        companyTV.setText(company.toString());
    }

    public void setId() {
        if(getArguments() != null){
            Integer id = getArguments().getInt(ARG_USER_ID);
            nameTV.setText(String.valueOf(id));
        }else{
            //default value for id = 1
            nameTV.setText(String.valueOf(id));
        }
    }

    public void setSelectedId(Integer id) {
        Bundle args = new Bundle();
        args.putInt(ARG_USER_ID, id);
        setArguments(args);
        setId();
    }
}
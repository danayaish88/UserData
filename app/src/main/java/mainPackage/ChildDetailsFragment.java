package mainPackage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.userdata.R;

import java.util.List;

import DataModels.Address;
import DataModels.Company;
import DataModels.User;
import butterknife.BindView;
import butterknife.ButterKnife;


public class ChildDetailsFragment extends Fragment {


    public static final String ARG_USER = "user";
    private static List<User> users;

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

    public static ChildDetailsFragment getInstance(int id, List<User> users) {
        ChildDetailsFragment childDetailsFragment = new ChildDetailsFragment();

        if(users != null){
            Bundle bundle = new Bundle();
            bundle.putParcelable(ARG_USER, users.get(id));
            childDetailsFragment.setArguments(bundle);
        }
        return childDetailsFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_child_details, container, false);
        ButterKnife.bind(this, view);

        User u = getUser();
        if(u != null){
            assignValues(u);
            showValues();
        }

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

    public User getUser() {
        User user = null;

        if(getArguments() != null){
            user = getArguments().getParcelable(ARG_USER);
        }
        return user;
    }

}
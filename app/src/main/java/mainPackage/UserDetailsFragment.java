package mainPackage;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
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


public class UserDetailsFragment extends Fragment {


    private static UserDetailsFragment singleFragment = null;
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

    private UserDetailsFragment() {
        // Required empty constructor
    }

    public static UserDetailsFragment getInstance() {
        if(singleFragment == null){
            singleFragment = new UserDetailsFragment();
            //Bundle args = new Bundle();
            //args.putInt(ARG_USER_ID, id);
            //singleFragment.setArguments(args);
        }
        return singleFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getUserId();
        Log.d("TAG", "onCreate: ");
        //assign Values of user with the specified id
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_details, container, false);
        ButterKnife.bind(this, view);
        //showValues();
        nameTV.setText(String.valueOf(id));
        Log.d("TAG", "onCreateView: ");
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

    private int getUserId() {
        if(getArguments() != null){
            return getArguments().getInt(ARG_USER_ID);
        }
        return 1;
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



    public void setId(Integer id) {
        nameTV.setText(String.valueOf(id));
    }
}
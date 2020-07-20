package mainPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.userdata.R;

import DataModels.Address;
import DataModels.Company;
import DataModels.User;
import butterknife.BindView;
import butterknife.ButterKnife;

public class UserDetailsActivity extends AppCompatActivity {

    public static final String KEY_USER ="User" ;

    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    @BindView(R.id.name) TextView nameTV;
    @BindView(R.id.username) TextView usernameTV;
    @BindView(R.id.email) TextView emailTV;
    @BindView(R.id.address) TextView addressTV;
    @BindView(R.id.phone) TextView phoneTV;
    @BindView(R.id.website) TextView websiteTV;
    @BindView(R.id.company) TextView companyTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        ButterKnife.bind(this);

        User user = getUser();
        assignValues(user);
        showValues();

    }

    public static void startScreen(Context callingContext, User user) {
        Intent intent = new Intent(callingContext, UserDetailsActivity.class);
        intent.putExtra(KEY_USER, user); // TODO : by convention its KEY_USER ( use it instead of TAG )
        callingContext.startActivity(intent);
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

    private User getUser() {
        Intent intent = getIntent();
        return intent.getParcelableExtra(KEY_USER);
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
}
package mainPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.userdata.R;

import DataModels.Address;
import DataModels.Company;
import DataModels.User;

public class UserDetails extends AppCompatActivity {

    public static final String TAG ="User" ;

    private String name;
    private String username;
    private String email;
    private Address address;
    private String phone;
    private String website;
    private Company company;

    private TextView nameTV;
    private TextView usernameTV;
    private TextView emailTV;
    private TextView addressTV;
    private TextView phoneTV;
    private TextView websiteTV;
    private TextView companyTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        User user = getUser();
        assignValues(user);
        showValues();

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
        return intent.getParcelableExtra(TAG);
    }

    private void showValues(){
        nameTV=findViewById(R.id.name);
        nameTV.setText(name);
        usernameTV=findViewById(R.id.username);
        usernameTV.setText(username);
        emailTV=findViewById(R.id.email);
        emailTV.setText(email);
        addressTV=findViewById(R.id.address);
        addressTV.setText(address.toString());
        phoneTV=findViewById(R.id.phone);
        phoneTV.setText(phone);
        websiteTV=findViewById(R.id.website);
        websiteTV.setText(website);
        companyTV=findViewById(R.id.company);
        companyTV.setText(company.toString());
    }
}
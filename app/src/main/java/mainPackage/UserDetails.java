package mainPackage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.userdata.R;

import DataModels.User;

public class UserDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        /* TODO : refactor
        *  the code below is a mixture of reading data from intent
        *  , refering to views and assigning values to the views .
        *
        *  a good practice is to split into methods .
        *   for example one to read data from intent .
        *   other for assigning values .
        * */
        Intent intent = getIntent();
        User user = intent.getParcelableExtra("User"); // TODO : define the string as constants .
        String id=user.getId();
        String phone=user.getPhone();
        // TODO : convert into class members instead of lolcal variables and make them reachable else where
        TextView idTextView=findViewById(R.id.textView);
        idTextView.setText(id);
        TextView phoneTextView=findViewById(R.id.textView2);
        phoneTextView.setText(phone);
    }
}
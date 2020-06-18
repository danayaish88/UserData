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
        Intent intent = getIntent();
        User user = intent.getParcelableExtra("User");
        String id=user.getId();
        String phone=user.getPhone();
        TextView idTextView=findViewById(R.id.textView);
        idTextView.setText(id);
        TextView phoneTextView=findViewById(R.id.textView2);
        phoneTextView.setText(phone);
    }
}
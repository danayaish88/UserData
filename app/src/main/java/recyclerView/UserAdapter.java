package recyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import com.example.userdata.R;

import java.security.AccessController;
import java.util.List;
import DataModels.User;
import mainPackage.MainActivity;
import mainPackage.UserDetails;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class UserAdapter extends
        RecyclerView.Adapter<UserAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    // populates data to the views present in the recyclerView.
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        // viewHolder describes and provides access to all the views within each item row.
        public TextView nameTextView;
        public TextView emailTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.user_name);
            emailTextView = (TextView) itemView.findViewById(R.id.user_email);
        }
    }

    // Store a member variable for the contacts
    private List<User> mUsers;

    // Pass in the contact array into the constructor
    public UserAdapter(List<User> users) {
        mUsers = users;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View userView = inflater.inflate(R.layout.item_user, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(userView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(final UserAdapter.ViewHolder viewHolder, final int position) {
        // Get the data model based on position
        final User user = mUsers.get(position);

        // Set item views based on your views and data model
        final TextView textViewName = viewHolder.nameTextView;
        textViewName.setText(user.getName());
        TextView textViewEmail = viewHolder.emailTextView;
        textViewEmail.setText(user.getEmail());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(view.getContext(), UserDetails.class);
                intent.putExtra("User",user);
                view.getContext().startActivity(intent);
            }
        });


    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mUsers.size();
    }

}
package recyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.userdata.R;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.List;
import DataModels.User;
import io.reactivex.disposables.Disposable;
import mainPackage.UserDetails;

import static mainPackage.UserDetails.TAG;

public class UserAdapter extends
        RecyclerView.Adapter<UserAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {//TODO : make this class static .

        public TextView nameTextView;
        public TextView emailTextView;

        public ViewHolder(View itemView) {

            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.user_name);
            emailTextView = (TextView) itemView.findViewById(R.id.user_email);
        }

    }

    private List<User> mUsers;

    public UserAdapter(List<User> users) {
        mUsers = users;
    }

    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View userView = inflater.inflate(R.layout.item_user, parent, false);

        ViewHolder viewHolder = new ViewHolder(userView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final UserAdapter.ViewHolder viewHolder, int position) {
        final User user = mUsers.get(position);

        TextView textViewName = viewHolder.nameTextView;
        textViewName.setText(user.getName());
        TextView textViewEmail = viewHolder.emailTextView;
        textViewEmail.setText(user.getEmail());

        Disposable d =RxView.clicks(viewHolder.itemView)
                            .subscribe(obs->{
                                Intent intent= new Intent(viewHolder.itemView.getContext(), UserDetails.class);
                                intent.putExtra(TAG,user); //TODO : intead of writing key 'Users' as a string , refer it as a constant in teh details screen .
                                viewHolder.itemView.getContext().startActivity(intent);
                            });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }
}
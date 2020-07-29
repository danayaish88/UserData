package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.userdata.R;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.List;

import DataModels.User;
import butterknife.BindView;
import butterknife.ButterKnife;
import presenter.UserPresenter;


public class UsersAdapter extends
        RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private UserPresenter presenter;
    private static List<User> mUsers;

    public UsersAdapter(List<User> users) {
        presenter = UserPresenter.getIntance();
        mUsers = users;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder { //TODO : remove rowView


        @BindView(R.id.user_name) TextView nameTextView;
        @BindView(R.id.user_email) TextView emailTextView;;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setName(String name) {
            nameTextView.setText(name);
        }

        public void setEmail(String email) {
            emailTextView.setText(email);
        }

        public void bind(int position) {
            User user=mUsers.get(position);
            this.setName(user.getName());
            this.setEmail(user.getEmail());
        }
    }

    @Override
    public UsersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View userView = inflater.inflate(R.layout.item_user, parent, false);

        ViewHolder viewHolder = new ViewHolder(userView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final UsersAdapter.ViewHolder viewHolder, int position) {


        viewHolder.bind(position);

        presenter.setDisposable(RxView.clicks(viewHolder.itemView)
                .subscribe(obs -> {

                    //TODO : instead of making the presenter start your activity ,
                    // create a static method in your destination activity .
                    //presenter.sendId(mUsers.get(position).getId());
                }));
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }
}
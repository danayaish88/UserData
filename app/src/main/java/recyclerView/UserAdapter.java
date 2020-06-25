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

import contract.UserActivityContract;
import presenter.UserPresenter;


public class UserAdapter extends
        RecyclerView.Adapter<UserAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder implements UserActivityContract.rowView {//TODO : make this class static .

        public TextView nameTextView;
        public TextView emailTextView;

        public ViewHolder(View itemView) {

            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.user_name);
            emailTextView = (TextView) itemView.findViewById(R.id.user_email);
        }

        @Override
        public void setName(String name) {
            nameTextView.setText(name);
        }

        @Override
        public void setEmail(String email) {
            emailTextView.setText(email);
        }
    }

    private UserPresenter presenter;

    public UserAdapter(UserPresenter mPresenter) {
        presenter=mPresenter;
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

        presenter.onBindRowView(viewHolder,position);
        presenter.setDisposable(RxView.clicks(viewHolder.itemView)
                .subscribe(obs->{
                    presenter.startUserDetailsActivity(position);
                }));

    }

    @Override
    public int getItemCount() {
        return presenter.getItemCount();
    }
}
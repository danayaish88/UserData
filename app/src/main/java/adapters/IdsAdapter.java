package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userdata.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import presenter.UserPresenter;

public class IdsAdapter extends
        RecyclerView.Adapter<IdsAdapter.ViewHolder> {


    private static List<Integer> data;
    private UserPresenter presenter;

    public static class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.user_name)
        TextView nameTextView;
        @BindView(R.id.user_email)
        TextView emailTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setName(Integer id) {
            nameTextView.setText(Integer.toString(id));
        }

        public void setEmail(String email) {
            emailTextView.setText(email);
        }

        public void bind(int position) {
            Integer id =data.get(position);
            this.setName(id);
        }
    }

    public IdsAdapter(UserPresenter mPresenter, List<Integer> data) {
        presenter = mPresenter;
        IdsAdapter.data = data;
    }

    @NonNull
    @Override
    public IdsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View userView = inflater.inflate(R.layout.item_user, parent, false);

        ViewHolder viewHolder = new ViewHolder(userView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.bind(position);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.sendId(data.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}

package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.userdata.R;
import com.jakewharton.rxbinding2.view.RxView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import DataModels.User;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import eventBus.FavEvent;
import presenter.UserPresenter;


public class UsersAdapter extends
        RecyclerView.Adapter<UsersAdapter.ViewHolder> {

    private static List<User> mUsers;

    private static UserPresenter presenter;
    private OnUserSelectedListener callback;


    public interface OnUserSelectedListener {
        void sendId(Integer id);
    }

    public UsersAdapter(Fragment fragment) {
        presenter = UserPresenter.getIntance();
        setCallback(fragment);
    }

    public void setData(List<User> users) {
        mUsers = users;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {//TODO : remove rowView
        public static final boolean FAV = true;
        public static final boolean UNFAV = false;


        @BindView(R.id.user_name) TextView nameTextView;
        @BindView(R.id.user_email) TextView emailTextView;
        @BindView(R.id.favoriteButton) ImageButton favoriteButton;

        private User user;

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
            user=mUsers.get(position);
            this.setName(user.getName());
            this.setEmail(user.getEmail());

            if(presenter.checkIfFav(user.getId()))
                favoriteButton.setBackgroundResource(R.drawable.ic_baseline_star_24_pressed);
        }

        @OnClick(R.id.favoriteButton)
        public void favorited(ImageButton imageButton){
            if(!presenter.checkIfFav(user.getId())){
                favUser(imageButton);
            }
            else{
                unfavUser(imageButton);
            }

        }

        private void unfavUser(ImageButton imageButton) {
            imageButton.setBackgroundResource(R.drawable.ic_baseline_star_24_normal);
            presenter.setFav(user.getId(), UNFAV);
            EventBus.getDefault().post(new FavEvent(UNFAV));
        }

        private void favUser(ImageButton imageButton) {
            imageButton.setBackgroundResource(R.drawable.ic_baseline_star_24_pressed);
            presenter.setFav(user.getId(), FAV);
            EventBus.getDefault().post(new FavEvent(FAV));
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

    private void setCallback(Fragment fragment) {
        if(fragment instanceof OnUserSelectedListener){
            callback = (OnUserSelectedListener) fragment;
        }
    }

    @Override
    public void onBindViewHolder(final UsersAdapter.ViewHolder viewHolder, int position) {

        viewHolder.bind(position);

        presenter.setDisposable(RxView.clicks(viewHolder.itemView)
                .subscribe(obs -> {

                    if(callback != null){
                        callback.sendId(mUsers.get(position).getId());
                    }

                    //TODO : instead of making the presenter start your activity ,
                    // create a static method in your destination activity .
                }));
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void destroy() {
        presenter.destroy();
    }
}
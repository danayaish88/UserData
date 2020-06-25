package contract;

import java.util.List;

import DataModels.User;
import recyclerView.UserAdapter;

public interface UserActivityContract {

    interface View{
        void init();

        void showError(String message);

        void loadDataInList();

        void startUSerDetailsActivity(User user);
    }

    interface rowView{
        void setName(String name);

        void setEmail(String email);
    }


    interface Presenter{
        void start();

        void loadUsers();


        void onBindRowView(UserActivityContract.rowView rowView, int position);

        int getItemCount();

        void startUserDetailsActivity(int position);

        void destroy();
    }

}

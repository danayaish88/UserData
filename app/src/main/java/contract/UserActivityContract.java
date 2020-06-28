package contract;

import java.util.List;

import DataModels.User;
import recyclerView.UserAdapter;

public interface UserActivityContract {

    interface View {
        void init();

        void showError(String message);

        void loadDataInList();

        void startUSerDetailsActivity(User user);
    }

    // TODO : not needed . dont use MVP on inflating row items ( let it be a high level concept ) .
    interface rowView {
        void setName(String name);

        void setEmail(String email);
    }


    //TODO : it might be helpful to have a high level describe of your presenter .
    // but i dont think it has any good value here . just let Presenter class has its own methods
    interface Presenter {
        void start();

        void loadUsers();

        void onBindRowView(UserActivityContract.rowView rowView, int position);

        int getItemCount();

        void startUserDetailsActivity(int position);

        void destroy();
    }

}

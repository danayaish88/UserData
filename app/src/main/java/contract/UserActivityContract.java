package contract;

import java.util.List;

import DataModels.User;

public interface UserActivityContract {

    interface View{
        void init();

        void showError(String message);

        void loadDataInList(List<User> users);

    }


    interface Presenter{
        void start();

        void loadUsers();
    }

}

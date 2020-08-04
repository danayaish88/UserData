package contract;

import java.util.List;

import DataModels.User;

public interface UserFragmentContract {

    interface View {
        void onListReady(List<User> users);

        void showError(String message);

        void setFav(Integer id);

        boolean checkisFav(int id);
    }

}

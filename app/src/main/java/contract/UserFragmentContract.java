package contract;

import java.util.List;

import dataModels.User;

public interface UserFragmentContract {

    interface View {
        void onListReady(List<User> users);

        void showError(String message);

        void setFav(Integer id, Boolean fav);

        boolean checkisFav(int id);

    }

}

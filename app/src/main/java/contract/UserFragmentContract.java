package contract;

import java.util.List;

import DataModels.User;

public interface UserFragmentContract {

    interface View {
        void init();

        void showError(String message);

        void sendId(Integer id);
    }

    interface ActivityView {
        void onListReady(List<User> users);
    }


}

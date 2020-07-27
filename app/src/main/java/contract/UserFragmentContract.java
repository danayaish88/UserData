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

    // TODO : not needed . dont use MVP on inflating row items ( let it be a high level concept ) .

    //TODO : it might be helpful to have a high level describe of your presenter .
    // but i dont think it has any good value here . just let Presenter class has its own methods

}

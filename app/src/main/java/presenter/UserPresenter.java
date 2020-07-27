package presenter;

import java.util.List;

import DataModels.User;
import contract.UserFragmentContract;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import networking.RetrofitC;

public class UserPresenter {

    private static UserPresenter singleInstance = null;

    private UserFragmentContract.View mView;
    private Disposable disposable;
    private List<User> usersList = null;

    public static UserPresenter getIntance() {
        if(singleInstance == null){
            singleInstance = new UserPresenter();
        }
        return singleInstance;
    }

    private UserPresenter() {
    }

    public void setView(UserFragmentContract.View mView) {
        this.mView=mView;
    }

    public void setDisposable(Disposable disposable) {
        this.disposable = disposable;
    }

    public void start() {
        mView.init();
    }

    public void loadUsers() {
        RetrofitC.getInstance()
                .getUsersFromApi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<User> users) {
                        // TODO : please always check if mView is null or not
                        if(mView!=null){
                            mView.loadDataInList(users);
                        }
                        usersList = users;
                         // TODO : no need hold ref if not used later , simply deliver to your view
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void destroy() {
        if(disposable != null) {
            disposable.dispose();
        }
    }


    public void sendId(Integer id) {
        mView.sendId(id);
    }

    public List<User> getUsersList() {
        return usersList;
    }
}

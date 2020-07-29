package presenter;

import android.app.Activity;

import java.util.List;

import DataModels.User;
import contract.UserFragmentContract;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import mainPackage.MainActivity;
import networking.RetrofitC;

public class UserPresenter {

    private static UserPresenter singleInstance = null;

    private UserFragmentContract.View mView;
    private UserFragmentContract.ActivityView activityView;
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
        //TODO : always check if ur mview is not null
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

                        if(activityView != null){
                            activityView.onListReady(users);
                        }

                        usersList = users;

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

    public void setActivityView(UserFragmentContract.ActivityView activityView) {
        this.activityView = activityView;
    }
}

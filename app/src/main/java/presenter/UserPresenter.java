package presenter;

import java.util.List;

import DataModels.User;
import contract.UserActivityContract;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import networking.RetrofitC;

public class UserPresenter implements UserActivityContract.Presenter {
    UserActivityContract.View mView;

    public UserPresenter(UserActivityContract.View mView) {
        this.mView=mView;
    }

    @Override
    public void start() {
        mView.init();
    }

    @Override
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
                        mView.loadDataInList(users);
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
}

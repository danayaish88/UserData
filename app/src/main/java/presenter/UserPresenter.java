package presenter;

import android.util.Log;

import java.util.List;

import DataModels.User;
import contract.UserActivityContract;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import networking.RetrofitC;

public class UserPresenter {
    UserActivityContract.View mView;
    Disposable disposable;

    public UserPresenter(UserActivityContract.View mView) {
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
                        Log.d("TAG", "onSubscribe: ");

                    }

                    @Override
                    public void onNext(List<User> users) {
                        // TODO : please always check if mView is null or not
                        if(mView!=null){
                            mView.loadDataInList(users);
                            Log.d("TAG", "onNext: ");
                        }
                         // TODO : no need hold ref if not used later , simply deliver to your view
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                        Log.d("TAG", "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("TAG", "onComplete: ");

                    }
                });
    }

    public void destroy() {
        if(disposable != null) {
            disposable.dispose();
        }
    }

}

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

    private UserFragmentContract.View view;
    private Disposable disposable;


    public static UserPresenter getIntance() {
        if(singleInstance == null){
            singleInstance = new UserPresenter();
        }
        return singleInstance;
    }

    private UserPresenter() {
    }


    public void setDisposable(Disposable disposable) {
        this.disposable = disposable;
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

                        if(view != null){
                            view.onListReady(users);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(view != null){
                            view.showError(e.getMessage());
                        }
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

    public void setView(UserFragmentContract.View view) {
        this.view = view;
    }
}

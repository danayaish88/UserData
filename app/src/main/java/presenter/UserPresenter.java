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
    List<User> userss;
    Disposable disposable;

    public UserPresenter(UserActivityContract.View mView) {
        this.mView=mView;
    }

    public void setDisposable(Disposable disposable) {
        this.disposable = disposable;
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
                        // TODO : please always check if mView is null or not
                        mView.loadDataInList();
                        userss=users; // TODO : no need hold ref if not used later , simply deliver to your view
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

    @Override
    public void onBindRowView(UserActivityContract.rowView rowView, int position) {
        User user=userss.get(position);
        rowView.setName(user.getName());
        rowView.setEmail(user.getEmail());

    }

    @Override
    public int getItemCount() {
        return userss.size();
    }

    @Override
    public void startUserDetailsActivity(int position) {
        mView.startUSerDetailsActivity(userss.get(position));
    }

    @Override
    public void destroy() {
        disposable.dispose();
    }

}

package adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

import DataModels.User;
import mainPackage.ParentDetailsFragment;

import mainPackage.UserListFragment;

public class TabAdapter extends FragmentStatePagerAdapter {


    private static final int NUM_OF_TABS = 2;
    private static final Integer DEFAULT_USER_ID = 1;
    private final List<User> users;

    public TabAdapter(@NonNull FragmentManager fm, List<User> users) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.users = users;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = UserListFragment.getInstance(users);
                break;
            case 1:
                fragment = ParentDetailsFragment.getInstance(DEFAULT_USER_ID, users);
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return NUM_OF_TABS;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        CharSequence charSequence = "";
        switch (position){
            case 0:
                charSequence = "List";
                break;
            case 1:
                charSequence = "Details";
                break;
            default:
                break;
        }
        return charSequence;
    }

}

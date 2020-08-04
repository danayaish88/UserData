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


    public static final int DETAILS_FRAGMENT_INDEX = 1;
    private static final int NUM_OF_TABS = 2;
    private static final Integer DEFAULT_USER_ID = 1;
    private static final int LIST_FRAGMENT_INDEX = 0;
    private final List<User> users;

    public TabAdapter(@NonNull FragmentManager fm, List<User> users) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.users = users;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = UserListFragment.getInstance(users);
        switch (position){
            //TODO : use constants instead on indecies
            case LIST_FRAGMENT_INDEX:
                fragment = UserListFragment.getInstance(users);
                break;
            case DETAILS_FRAGMENT_INDEX:
                fragment = ParentDetailsFragment.getInstance(DEFAULT_USER_ID, users);
                break;
            default:
                break;
        }

        //TODO : since the method assumes a non0null fragment , start with default return and check
        // upon position .
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
        //TODO : use constants
        switch (position){
            case LIST_FRAGMENT_INDEX:
                charSequence = "Users"; // TODO : rename to  Users
                break;
            case DETAILS_FRAGMENT_INDEX:
                charSequence = "Details";
                break;
            default:
                break;
        }
        return charSequence;
    }

}

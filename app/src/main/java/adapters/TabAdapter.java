package adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import mainPackage.UserDetailsFragment;
import mainPackage.UserListFragment;

public class TabAdapter extends FragmentStatePagerAdapter {

    int tabCount = 0;
    int selectedUserId = 1;

    public TabAdapter(@NonNull FragmentManager fm, int tabCount) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.tabCount = tabCount;
    }

    public TabAdapter(@NonNull FragmentManager fm, int tabCount, int selectedUserId) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.tabCount = tabCount;
        this.selectedUserId = selectedUserId;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = UserListFragment.getInstance();
                break;
            case 1:
                fragment = UserDetailsFragment.getInstance();
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return tabCount;
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

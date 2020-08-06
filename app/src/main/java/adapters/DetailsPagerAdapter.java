package adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

import dataModels.User;
import mainPackage.ChildDetailsFragment;

public class DetailsPagerAdapter extends FragmentStatePagerAdapter {


    private List<User> users;

    public DetailsPagerAdapter(@NonNull FragmentManager fm, List<User> users) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.users = users;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return ChildDetailsFragment.getInstance(position, users);
    }

    @Override
    public int getCount() {
        return users != null? users.size() : 0 ;
    }
}

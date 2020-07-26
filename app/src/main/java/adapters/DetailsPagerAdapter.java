package adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import mainPackage.ChildDetailsFragment;

public class DetailsPagerAdapter extends FragmentStatePagerAdapter {


    private static final int NUM_OF_USERS = 10;

    public DetailsPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return ChildDetailsFragment.getInstance(position);
    }

    @Override
    public int getCount() {
        return NUM_OF_USERS;
    }
}

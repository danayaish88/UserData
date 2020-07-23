package adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import mainPackage.UserDetailsFragment;

public class SlidePagerAdapter extends FragmentStatePagerAdapter {

    private int id;
    private int position;

    public SlidePagerAdapter(@NonNull FragmentManager fm, int id) {
        super(fm);
        this.id = id;
        this.position =id;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        //return UserDetailsFragment.getInstance(id);
        return null;
    }

    @Override
    public int getCount() {
        return 10;
    }
}

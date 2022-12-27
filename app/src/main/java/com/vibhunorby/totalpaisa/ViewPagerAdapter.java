package com.vibhunorby.totalpaisa;
//Sharing project on GitHubb
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> stringList = new ArrayList<>();


    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {


        switch (position) {
            case 0:

                return new CalculationFragment();
            case 1:
                return new DetailsFragment();
            case 2:
                return new HistoryFragment();
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return fragmentList.size();

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return stringList.get(position);
    }

    public void addFragment(Fragment fragment, String title){
        fragmentList.add(fragment);
        stringList.add(title);

    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}

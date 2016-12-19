package com.example.tanmayjha.gdgvitvellore.Entity.Members;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanmayjha.gdgvitvellore.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabbedMemberFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
   // private static int int_items=3;

    public TabbedMemberFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart()
    {
        super.onStart();
        View view=getView();
        tabLayout=(TabLayout)view.findViewById(R.id.tabs_member_details);
        viewPager=(ViewPager)view.findViewById(R.id.viewpager_member_details);

        viewPager.setAdapter(new MembersAdapter(getChildFragmentManager()));
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

    }

    class MembersAdapter extends FragmentStatePagerAdapter {

        public MembersAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0: return new ManagementMemberFragment();
                case 1: return new TechnicalMemberFragment();
                case 2: return new DesignMemberFragment();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0: return "Management";
                case 1: return "Technical";
                case 2: return "Design";
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tabbed_member, container, false);
    }

}

package com.example.tanmayjha.gdgvitvellore.Entity.ContactUs;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanmayjha.gdgvitvellore.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactUsFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;

    public ContactUsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        tabLayout = (TabLayout) view.findViewById(R.id.tabs_contact_us);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_contact_details);
        viewPager.setAdapter(new ContactAdapter(getChildFragmentManager()));
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });
    }
    class ContactAdapter extends FragmentStatePagerAdapter {

        public ContactAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0: return new ContactDetailsFragment();
                case 1: return new ContactMapFragment();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0: return "Contact";
                case 1: return "Map";
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_us, container, false);
    }

}

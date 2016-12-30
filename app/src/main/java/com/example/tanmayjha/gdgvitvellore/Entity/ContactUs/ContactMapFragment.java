package com.example.tanmayjha.gdgvitvellore.Entity.ContactUs;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanmayjha.gdgvitvellore.R;
import com.melnykov.fab.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 */
/*public class ContactMapFragment extends Fragment implements View.OnClickListener{
    SupportMapFragment mMapFragment;
    FloatingActionButton fab;

    public ContactMapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_map, container, false);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        mMapFragment = SupportMapFragment.newInstance();
        fab=(FloatingActionButton)rootView.findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        FragmentTransaction fragmentTransaction =
                getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.maplayout, mMapFragment);
        fragmentTransaction.commit();
        mMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.969129, 79.155787))
                .title("GDG VIT Vellore"));
        LatLng coordinate = new LatLng(12.969129, 79.155787);
        CameraUpdate yourLocation = CameraUpdateFactory.newLatLngZoom(coordinate, 5);
        googleMap.animateCamera(yourLocation);

    }

    @Override
    public void onClick(View v) {
        String geodir="http://www.maps.google.com/maps?"+"daddr="+12.969129+","+79.155787;
        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(geodir));
        startActivity(intent);
    }

}
*/
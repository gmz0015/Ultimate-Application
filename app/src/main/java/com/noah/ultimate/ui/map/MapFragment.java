package com.noah.ultimate.ui.map;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.noah.ultimate.R;
import com.noah.ultimate.ui.setting.SettingsFragment;

public class MapFragment extends Fragment {

    private final static int MAP_TYPE = 1; // 0 - Baidu Map; 1 - Google Map

    private FragmentManager mParentFragManager;
    private static Activity mActivity;
    private static BaiduMapFragment mBaiduMapFragment = new BaiduMapFragment();
    private static GoogleMapFragment mGoogleMapFragment = new GoogleMapFragment();
    private View view;

    public static MapFragment newInstance() {
        return new MapFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MapFragment", "onCreate()");
        mParentFragManager = getFragmentManager();
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.i("MapFragment", "onCreateView()");

        view = inflater.inflate(R.layout.map_overview_fragment, container, false);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("MapFragment", "onActivityCreated()");

        if (savedInstanceState == null) {
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.mapContainer, mGoogleMapFragment)
                    .commitNow();
        }
    }





    /**
     * Set toolbar to have menu, search and setting options
     *
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.i("GoogleMapFragment", "onCreateOptionsMenu()");

        // Clear the previous menu
        menu.clear();

        // Inflate the menu with main_menu.xml
        inflater.inflate(R.menu.map_menu, menu);

        // Set the home icon is menu
        ActionBar actionbar = ((AppCompatActivity) mActivity).getSupportActionBar();
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        actionbar.setTitle("My Map");

    }


    /**
     * Check to see which action the user selected.
     * If the method does not recognize the user's action, it invokes the superclass method
     *
     * @param item
     * @return
     */
//    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.BaiduMap:
                Log.i("MapFragment", "Baidu Map Switch");
                getChildFragmentManager().beginTransaction()
                        .replace(R.id.mapContainer, mBaiduMapFragment)
                        .commitNow();
                return true;

            case R.id.GoogleMap:
                Log.i("MapFragment", "Google Map Switch");
                getChildFragmentManager().beginTransaction()
                        .replace(R.id.mapContainer, mGoogleMapFragment)
                        .commitNow();
                return true;

            case R.id.action_settings:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                SettingsFragment settingsFragment = new SettingsFragment();
                FragmentTransaction fragmentTransaction = mParentFragManager.beginTransaction();
                fragmentTransaction.hide(getFragment());
                fragmentTransaction.addToBackStack("Google Map Fragment").add(R.id.basicContainer, settingsFragment, "Settings").commit();
                return true;

            case android.R.id.home:
                DrawerLayout mDrawerLayout = mActivity.findViewById(R.id.drawer_layout);
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private Fragment getFragment() { return this; }
}

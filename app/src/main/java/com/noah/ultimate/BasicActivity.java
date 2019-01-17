package com.noah.ultimate;

import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.noah.ultimate.ui.TODO.TODOFragment;
import com.noah.ultimate.ui.basic.BasicFragment;
import com.noah.ultimate.ui.map.BaiduMapFragment;
import com.noah.ultimate.ui.map.GoogleMapFragment;
import com.noah.ultimate.ui.map.MapFragment;
import com.noah.ultimate.ui.setting.AboutFragment;

public class BasicActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private static Activity mActivity;
    private static BasicFragment mBasicFragment = new BasicFragment();
    private static TODOFragment mTODOFragment = new TODOFragment();
    private static MapFragment mMapFragment = new MapFragment();

    @Override
    protected void onResume(){
        super.onResume();
        ActionBar actionbar = getSupportActionBar();
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        Log.i("BasicActivity", "onResume()");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_activity);

        mActivity = this;

        // Set the toolbar as the app bar for the activity
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar actionbar = getSupportActionBar();

        // Enable the Up button
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        Log.i("BasicActivity", "onNavigationItemSelected");

                        // set item as selected to persist highlight
                        switch (menuItem.getItemId()) {
                            case R.id.nav_grid_view:
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.basicContainer, mBasicFragment)
                                        .commitNow();
                                break;
                            case R.id.nav_map_view:
                                getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.basicContainer, mMapFragment)
                                        .commitNow();
                                break;
                            default:
//                                getSupportFragmentManager().beginTransaction()
//                                        .replace(R.id.container, GridFragment.newInstance())
//                                        .commitNow();
                        }
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.basicContainer, mBasicFragment)
                    .commitNow();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.basis_menu, menu);
        return true;
    }
}

package com.noah.ultimate.ui.basic;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.noah.ultimate.R;
import com.noah.ultimate.ui.setting.AboutFragment;
import com.noah.ultimate.ui.setting.SettingsFragment;

public class BasicFragment extends Fragment {

    private BasicViewModel mViewModel;

    private static Activity mActivity;
    private FragmentManager mParentFragManager;


    public static BasicFragment newInstance() {
        return new BasicFragment();
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        mActivity = (Activity)context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get the handle of parent fragment
        mParentFragManager = getFragmentManager();

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.basic_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        mViewModel = ViewModelProviders.of(this).get(BasicViewModel.class);
        // TODO: Use the ViewModel
    }



    /**
     * Set toolbar
     *
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.i("BasicFragment", "onCreateOptionsMenu()");

        // Clear the previous menu
        menu.clear();

        // Inflate the menu with main_menu.xml
        inflater.inflate(R.menu.basis_menu, menu);

        // Set the home icon is menu
        ActionBar actionbar = ((AppCompatActivity) mActivity).getSupportActionBar();
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
//        actionbar.setTitle("My Photo");

    }



    /**
     * Check to see which action the user selected.
     * If the method does not recognize the user's action, it invokes the superclass method
     *
     * @param item the handle of the selected menu item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                SettingsFragment settingsFragment = new SettingsFragment();
                FragmentTransaction fragmentTransaction = mParentFragManager.beginTransaction();
                fragmentTransaction.hide(getFragment());
                fragmentTransaction.addToBackStack("Basic Fragment").add(R.id.basicContainer, settingsFragment, "Settings").commit();
                return true;

            case android.R.id.home:
                DrawerLayout mDrawerLayout = mActivity.findViewById(R.id.drawer_layout);
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.

                //the superclass can expand the action view
                return super.onOptionsItemSelected(item);

        }
    }

    private Fragment getFragment() { return this;}

}

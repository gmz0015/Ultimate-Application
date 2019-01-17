package com.noah.ultimate.ui.setting;

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
import android.widget.TextView;

import com.noah.ultimate.R;

public class SettingsFragment extends Fragment {

    private static Activity mActivity;
    private FragmentManager mParentFragManager;

    private TextView aboutTextView;


    public static SettingsFragment newInstance() {
        return new SettingsFragment();
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

        View view = inflater.inflate(R.layout.settings_fragment, container, false);

        aboutTextView =view.findViewById(R.id.aboutTextView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        aboutTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutFragment aboutFragment = new AboutFragment();
                FragmentTransaction fragmentTransaction = mParentFragManager.beginTransaction();
                fragmentTransaction.hide(getFragment());
                fragmentTransaction.addToBackStack("Settings Fragment").add(R.id.basicContainer, aboutFragment, "About").commit();
            }
        });
    }



    /**
     * Set toolbar
     *
     * @param menu
     * @param inflater
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        Log.i("SettingsFragment", "onCreateOptionsMenu()");

        // Clear the previous menu
        menu.clear();

        // Inflate the menu with main_menu.xml
        inflater.inflate(R.menu.settings_memu, menu);

        // Set the home icon is menu
        ActionBar actionbar = ((AppCompatActivity) mActivity).getSupportActionBar();

        // Enable the Up button
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        actionbar.setTitle("Settings");

        super.onCreateOptionsMenu(menu, inflater);
    }



    /**
     * Check to see which action the user selected.
     * If the method does not recognize the user's action, it invokes the superclass method
     *
     * @param item the handle of the selected menu item
     * @return
     */
//    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().getSupportFragmentManager().popBackStack();
            Log.i("SettingsFragment", "onOptionItemSelected() - back");
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

    private Fragment getFragment() { return this;}
}

package com.noah.ultimate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.noah.ultimate.ui.basic.BasicFragment;

public class BasicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.basic_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.basicContainer, BasicFragment.newInstance())
                    .commitNow();
        }
    }
}

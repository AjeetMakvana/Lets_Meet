package com.example.letsmeet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.letsmeet.Fragments.HistoryFragment;
import com.example.letsmeet.Fragments.HomeFragment;
import com.example.letsmeet.Fragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class DashboardActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView_dash);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListner);

        getSupportFragmentManager().beginTransaction().replace(R.id.container_view , new HomeFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                     Fragment selectedFragment = null;

                     switch(item.getItemId()){
                         case R.id.nav_home :
                             selectedFragment = new HomeFragment();
                             break;

                         case R.id.nav_history :
                             selectedFragment = new HistoryFragment();
                             break;

                         case R.id.nav_settings :
                             selectedFragment = new SettingsFragment();
                             break;

                         case R.id.nav_logout :
                             FirebaseAuth.getInstance().signOut();
                             Intent intent = new Intent(DashboardActivity.this , LoginActivity.class);
                             intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                             startActivity(intent);
                             finish();
                             break;
                     }

                     getSupportFragmentManager().beginTransaction().replace(R.id.container_view , selectedFragment).commit();
                     return true;
                }
            };
}
package com.example.teambeta;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.io.File;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    // fab button init
    FloatingActionButton fab , fab1 , fab2;
    Animation fabOpen ,fabClose , rotateForward, rotateBackward;
    boolean isOpen = false; //by default it is false
    // end of the fab button init
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if(savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
        // ***********fab button************
        fab = (FloatingActionButton) findViewById(R.id.fab_button_share);
        fab1 = (FloatingActionButton) findViewById(R.id.fab_button_app);
        fab2 = (FloatingActionButton) findViewById(R.id.fab_button_git);

        // animations for fab button
        fabOpen = AnimationUtils.loadAnimation(this,R.anim.from_buttom_anim);
        fabClose = AnimationUtils.loadAnimation(this,R.anim.to_buttom_anime);
        rotateForward = AnimationUtils.loadAnimation(this,R.anim.rotate_open_anim);
        rotateBackward = AnimationUtils.loadAnimation(this,R.anim.rotate_close_anim);

        fab.setOnClickListener(view -> animateFab());
        fab1.setOnClickListener(view -> {
            animateFab();
            Toast.makeText(MainActivity.this, "sharing the apk file", Toast.LENGTH_SHORT).show();
            // ************ share option enable ********
            Intent intent =new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String Body = "Download this App";
            String Sub = "https://playstorelink.com";
            intent.putExtra(Intent.EXTRA_TEXT,Body);
            intent.putExtra(Intent.EXTRA_TEXT,Sub);
            startActivity(Intent.createChooser(intent,"Share using"));
            //**************end of the share option *******

        });

        fab2.setOnClickListener(view -> {
            animateFab();
            Toast.makeText(MainActivity.this, "sharing the raw file", Toast.LENGTH_SHORT).show();
            // ************ share option enable ********
            Intent intent =new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String Body = "Download this App";
            String Sub = "https://gituplink.com";
            intent.putExtra(Intent.EXTRA_TEXT,Body);
            intent.putExtra(Intent.EXTRA_TEXT,Sub);
            startActivity(Intent.createChooser(intent,"Share using"));
            //**************end of the share option *******

        });
        // **** end of fab *****


    }
    // ***** fab button animate  ********
    private void animateFab()
    {
       if (isOpen){
           fab.startAnimation(rotateForward);
           fab1.startAnimation(fabClose);
           fab2.startAnimation(fabClose);
           fab1.setClickable(false);
           fab2.setClickable(false);
           isOpen = false;

       }
       else {
           fab.startAnimation(rotateBackward);
           fab1.startAnimation(fabOpen);
           fab2.startAnimation(fabOpen);
           fab1.setClickable(true);
           fab2.setClickable(true);
           isOpen = true;

       }
    }
    // ****end of fab button animate ********

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HomeFragment()).commit();
                break;
            case R.id.nav_login:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new LoginFragment()).commit();
                break;
            case R.id.nav_signup:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new signupFragment()).commit();
                break;
            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new aboutFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }
}
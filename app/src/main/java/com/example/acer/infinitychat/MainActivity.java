package com.example.acer.infinitychat;

import android.content.Intent;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Toolbar mToolbar;

    private ViewPager mViewPager;
    private SectionsPagerAdapter mSectionsPagerAdapter;

    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth=FirebaseAuth.getInstance();

        mToolbar=(Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Infinity Chat");

        //tabs
        mViewPager=(ViewPager) findViewById(R.id.main_tabPager);
        mSectionsPagerAdapter=new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(mSectionsPagerAdapter);

        mTabLayout=(TabLayout) findViewById(R.id.main_tabs);
        mTabLayout.setupWithViewPager(mViewPager);

    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser==null){
            sentToStart();
        }
    }

    private void sentToStart() {
        Intent startIntent=new Intent(MainActivity.this,StartActivity.class);
        startActivity(startIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.main_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId()==R.id.main_logout_btn){
            FirebaseAuth.getInstance().signOut();
            sentToStart();
        }
        if(item.getItemId()==R.id.main_setting_btn){
            Intent settingsIntent=new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(settingsIntent);
        }
        if (item.getItemId()==R.id.main_all_btn){
            Intent usersActivityIntent=new Intent(MainActivity.this,UsersActivity.class);
            startActivity(usersActivityIntent);
        }

        return true;
    }
}

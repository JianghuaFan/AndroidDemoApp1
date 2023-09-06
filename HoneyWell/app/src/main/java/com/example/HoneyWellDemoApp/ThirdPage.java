package com.example.HoneyWellDemoApp;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.rokid.glass.instruct.InstructLifeManager;
import com.rokid.glass.instruct.entity.EntityKey;
import com.rokid.glass.instruct.entity.IInstructReceiver;
import com.rokid.glass.instruct.entity.InstructEntity;

public class ThirdPage extends AppCompatActivity {
    Button Object1Button, goBackButton, Call1Button, Call2Button;

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    public InstructLifeManager mLifeManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_page);
        Object1Button = findViewById(R.id.Object1);
        goBackButton =  findViewById(R.id.goBackButton);
        Call1Button = findViewById(R.id.Call1);
        Call2Button = findViewById(R.id.Call2);
        configInstruct();

        Object1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the Information Button");

            }
        });

        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the Start Demo Button");
                goBack();
            }
        });

        Call1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence text = "Call1 button has been clicked!";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(ThirdPage.this, text, duration);
                toast.show();
            }
        });
        Call2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence text = "Call2 button has been clicked!";
                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(ThirdPage.this, text, duration);
                toast.show();
            }
        });

        // Initialize the drawer layout and the ActionBarDrawerToggle
        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Handle navigation item clicks
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle navigation item clicks here
                int itemId = item.getItemId();

                if (itemId == R.id.nav_item1) {
                    // Handle click for Item 1
                    Log.d("TAG", "1");
                    goToFirstPage();
                } else if (itemId == R.id.nav_item2) {
                    // Handle click for Item 2
                    Log.d("TAG", "2");
                    goToSecondPage();

                }else if(itemId == R.id.nav_item3){
                    // Handle click for Item 3
                    Log.d("TAG", "3");
                    goToThirdPage();
                }
                // Add more if-else blocks for other items as needed
                // Close the navigation drawer after handling the click
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);

                return true; // Return true to indicate the item has been selected
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void goBack(){
        Intent intent1 = new Intent(ThirdPage.this, SecondPage.class);
        startActivity(intent1);
    }

    private void goToFirstPage(){
        Intent intent1 = new Intent(ThirdPage.this, MainActivity.class);
        startActivity(intent1);
    }
    private void goToSecondPage() {
        Intent intent2 = new Intent(ThirdPage.this, SecondPage.class);
        startActivity(intent2);
    }

    private void goToThirdPage() {
        Intent intent3 = new Intent(ThirdPage.this, ThirdPage.class);
        startActivity(intent3);
    }
    public void configInstruct(){
        mLifeManager = new InstructLifeManager(this, getLifecycle(), mInstructLifeListener);
        mLifeManager.addInstructEntity(
                new InstructEntity()
                        .addEntityKey(R.id.goBackButton)
                        .addEntityKey(new EntityKey(EntityKey.Language.en,"Go Back"))
                        .addEntityKey(new EntityKey(EntityKey.Language.en,"select item 3"))
                        .setShowTips(true)
                        .setCallback(new IInstructReceiver() {
                            @Override
                            public void onInstructReceive(Activity act, String key, InstructEntity instruct) {
                                Log.d(TAG,"Go Back 觸發");
                                goToSecondPage();
                            }
                        })
        );

    }

    private InstructLifeManager.IInstructLifeListener mInstructLifeListener = new InstructLifeManager.IInstructLifeListener() {
        @Override
        public boolean onInterceptCommand(String command) {
            if ("需要拦截的指令".equals(command)) {
                return true;
            }
            return false;
        }
        @Override
        public void onTipsUiReady() {
            Log.d("AudioAi", "onTipsUiReady Call ");
        }

        @Override
        public void onHelpLayerShow(boolean show) {

        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

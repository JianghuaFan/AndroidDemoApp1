package com.example.HoneyWellDemoApp;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.app.AlertDialog;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.rokid.glass.instruct.InstructLifeManager;
import com.rokid.glass.instruct.entity.EntityKey;
import com.rokid.glass.instruct.entity.IInstructReceiver;
import com.rokid.glass.instruct.entity.InstructEntity;



public class MainActivity extends AppCompatActivity  implements ExampleFragment.ButtonClickListener {
    public MainActivity(){
        super(R.layout.activity_main);
    }
    private Snackbar snackbar;
    // Create and show the dialog for information
    private AlertDialog alertDialog = null;
    Button btnStartDemo, btnInformation,openMenu, closeMenu,buttonYes, buttonNo,fragmentButton;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    public InstructLifeManager mLifeManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStartDemo = findViewById(R.id.startDemoButton);
        btnInformation =  findViewById(R.id.informationButton);
        configInstruct();

        // Create an instance of your fragment
        ExampleFragment myFragment = new ExampleFragment();

        // Get the FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Start a FragmentTransaction
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Replace the fragment_container with your fragment
        transaction.replace(R.id.fragment_container_view, myFragment);

        // Commit the transaction
        transaction.commit();


        // deal with information, get id from information.xml, so we can select yes or no to dismiss the snackBar
        final LayoutInflater factoryInformation = getLayoutInflater();
        final View informationView = factoryInformation.inflate(R.layout.information,null);

        buttonYes = informationView.findViewById(R.id.buttonYes);
        buttonNo = informationView.findViewById(R.id.buttonNo);

        // approach 1 : Handle the button click from the fragment,
//        View fragmentView = factoryInformation.inflate(R.layout.example_fragment,null);
//        fragmentButton = fragmentView.findViewById(R.id.myButton);
//        fragmentButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("BUTTONS", "User tapped the Information Button");
////                showCustomSnackBar(v);
//                showDialog(v);
//            }
//        });



        // Initialize the drawer layout and the ActionBarDrawerToggle
        drawerLayout = findViewById(R.id.drawer_layout);
        openMenu = findViewById(android.R.id.home);
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

        btnInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the Information Button");
//                showCustomSnackBar(v);
                showDialog(v);
            }
        });

        btnStartDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the Start Demo Button");
                goToSecondPage();
            }
        });
    }

    private void goToFirstPage(){
        Intent intent1 = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent1);
    }
    private void goToSecondPage() {
        Intent intent2 = new Intent(MainActivity.this, SecondPage.class);
        startActivity(intent2);
    }

    private void goToThirdPage() {
        Intent intent3 = new Intent(MainActivity.this, ThirdPage.class);
        startActivity(intent3);
    }

    // override the onOptionsItemSelected()
    // function to implement
    // the item click listener callback
    // to open and close the navigation
    // drawer when the icon is clicked
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void configInstruct(){
        mLifeManager = new InstructLifeManager(this, getLifecycle(), mInstructLifeListener);
        mLifeManager.addInstructEntity(
                new InstructEntity()
                        .addEntityKey(R.id.informationButton)
                        .addEntityKey(new EntityKey(EntityKey.Language.en,"information"))
                        .setShowTips(true)
                        .setCallback(new IInstructReceiver() {
                            @Override
                            public void onInstructReceive(Activity act, String key, InstructEntity instruct) {
                                Log.d(TAG,"信息 觸發");
//                                showCustomSnackBar(findViewById(android.R.id.content).getRootView());
                                showDialog(findViewById(android.R.id.content).getRootView());
                            }
                        })
        ).addInstructEntity(
                new InstructEntity()
                        .addEntityKey(R.id.informationButton)
                        .addEntityKey(new EntityKey(EntityKey.Language.en,"select item 4"))
                        .setShowTips(true)
                        .setCallback(new IInstructReceiver() {
                            @Override
                            public void onInstructReceive(Activity act, String key, InstructEntity instruct) {
                                Log.d(TAG,"信息 觸發");
//                                showCustomSnackBar(findViewById(android.R.id.content).getRootView());
                                showDialog(findViewById(android.R.id.content).getRootView());
                            }
                        })
        );
        mLifeManager.addInstructEntity(
                new InstructEntity()
                        .addEntityKey(R.id.startDemoButton)
                        .addEntityKey(new EntityKey(EntityKey.Language.en,"start demo"))
                        .setShowTips(true)
                        .setCallback(new IInstructReceiver() {
                            @Override
                            public void onInstructReceive(Activity act, String key, InstructEntity instruct) {
                                Log.d(TAG,"demo 觸發");
                                goToSecondPage();
                            }
                        })
        )
                .addInstructEntity(
                new InstructEntity()
                        .addEntityKey(R.id.startDemoButton)
                        .addEntityKey(new EntityKey(EntityKey.Language.en,"select item 3"))
                        .setShowTips(true)
                        .setCallback(new IInstructReceiver() {
                            @Override
                            public void onInstructReceive(Activity act, String key, InstructEntity instruct) {
                                Log.d(TAG,"demo 觸發");
                                goToSecondPage();
                            }
                        })
        );
        mLifeManager.addInstructEntity(
                new InstructEntity()
                        .addEntityKey(R.id.drawer_layout)
//                        .addEntityKey(android.R.id.home)
                        .addEntityKey(new EntityKey(EntityKey.Language.en,"select menu"))
                        .setShowTips(true)
                        .setCallback(new IInstructReceiver() {
                            @Override
                            public void onInstructReceive(Activity act, String key, InstructEntity instruct) {
                                Log.d(TAG,"menu 觸發");
                                if (!drawerLayout.isDrawerOpen(GravityCompat.START)) {
                                    drawerLayout.openDrawer(GravityCompat.START);
                                }
                            }
                        })
        ).addInstructEntity(
                new InstructEntity()
                        .addEntityKey(R.id.drawer_layout)
                        .addEntityKey(new EntityKey(EntityKey.Language.en,"select back"))
                        .setShowTips(true)
                        .setCallback(new IInstructReceiver() {
                            @Override
                            public void onInstructReceive(Activity act, String key, InstructEntity instruct) {
                                Log.d(TAG,"back 觸發");
                                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                                    drawerLayout.closeDrawer(GravityCompat.START);
                                }
                            }
                        })
        );
        mLifeManager.addInstructEntity(
                new InstructEntity()
                        .addEntityKey(R.id.nav_item1)
                        .addEntityKey(new EntityKey(EntityKey.Language.en,"First Page"))
                        .setShowTips(true)
                        .setCallback(new IInstructReceiver() {
                            @Override
                            public void onInstructReceive(Activity act, String key, InstructEntity instruct) {
                                Log.d(TAG,"First Page 觸發");
                                goToFirstPage();
                            }
                        })
        ).addInstructEntity(
                new InstructEntity()
                        .addEntityKey(R.id.nav_item2)
                        .addEntityKey(new EntityKey(EntityKey.Language.en,"Second Page"))
                        .setShowTips(true)
                        .setCallback(new IInstructReceiver() {
                            @Override
                            public void onInstructReceive(Activity act, String key, InstructEntity instruct) {
                                Log.d(TAG,"Second Page 觸發");
                                goToSecondPage();
                            }
                        })
        ).addInstructEntity(
                new InstructEntity()
                        .addEntityKey(R.id.nav_item3)
                        .addEntityKey(new EntityKey(EntityKey.Language.en,"Third Page"))
                        .setShowTips(true)
                        .setCallback(new IInstructReceiver() {
                            @Override
                            public void onInstructReceive(Activity act, String key, InstructEntity instruct) {
                                Log.d(TAG,"Third Page 觸發");
                                goToThirdPage();
                            }
                        })
        );
        mLifeManager.addInstructEntity(
                new InstructEntity()
                        .addEntityKey(R.id.buttonYes)
                        .addEntityKey(new EntityKey(EntityKey.Language.en,"select yes"))
                        .setShowTips(true)
                        .setCallback(new IInstructReceiver() {
                            @Override
                            public void onInstructReceive(Activity act, String key, InstructEntity instruct) {
                                Log.d(TAG,"yes 觸發");
//                                if(snackbar != null){
//                                    snackbar.dismiss();
//                                }
                                if(alertDialog != null){
                                    alertDialog.dismiss();
                                }
                            }
                        })
        ).addInstructEntity(
                new InstructEntity()
                        .addEntityKey(R.id.buttonYes)
                        .addEntityKey(new EntityKey(EntityKey.Language.en,"select item 1"))
                        .setShowTips(true)
                        .setCallback(new IInstructReceiver() {
                            @Override
                            public void onInstructReceive(Activity act, String key, InstructEntity instruct) {
                                Log.d(TAG,"yes 觸發");
//                                if(snackbar != null){
//                                    snackbar.dismiss();
//                                }
                                if(alertDialog != null){
                                    alertDialog.dismiss();
                                }
                            }
                        })
        ).addInstructEntity(
                new InstructEntity()
                        .addEntityKey(R.id.buttonNo)
                        .addEntityKey(new EntityKey(EntityKey.Language.en,"select no"))
                        .setShowTips(true)
                        .setCallback(new IInstructReceiver() {
                            @Override
                            public void onInstructReceive(Activity act, String key, InstructEntity instruct) {
                                Log.d(TAG,"no 觸發");
//                                if(snackbar != null){
//                                    snackbar.dismiss();
//                                }
                                if(alertDialog != null){
                                    alertDialog.dismiss();
                                }
                            }
                        })
        ).addInstructEntity(
                new InstructEntity()
                        .addEntityKey(R.id.buttonNo)
                        .addEntityKey(new EntityKey(EntityKey.Language.en,"select item 2"))
                        .setShowTips(true)
                        .setCallback(new IInstructReceiver() {
                            @Override
                            public void onInstructReceive(Activity act, String key, InstructEntity instruct) {
                                Log.d(TAG,"no 觸發");
//                                if(snackbar != null){
//                                    snackbar.dismiss();
//                                }
                                if(alertDialog != null){
                                    alertDialog.dismiss();
                                }
                            }
                        })
        );
        mLifeManager.addInstructEntity(
                new InstructEntity()
                        .addEntityKey(R.id.myButton)
                        .addEntityKey(new EntityKey(EntityKey.Language.en,"On Click"))
                        .setShowTips(true)
                        .setCallback(new IInstructReceiver() {
                            @Override
                            public void onInstructReceive(Activity act, String key, InstructEntity instruct) {
                                Log.d(TAG,"信息 觸發");
                                showDialog(findViewById(android.R.id.content).getRootView());
                            }
                        })
        );

    }

    private void showDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.information,null);
        builder.setView(dialogView);
        alertDialog = builder.create();
//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//        lp.copyFrom(alertDialog.getWindow().getAttributes());
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.height =  WindowManager.LayoutParams.FILL_PARENT;
//        int width = WindowManager.LayoutParams.MATCH_PARENT;
//        int height =  WindowManager.LayoutParams.FILL_PARENT;
//        alertDialog.getWindow().setLayout(width, height);
        alertDialog.show();
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
    public void onButtonClicked() {
        // Handle the button click within MainActivity's lifecycle
        // You can perform any necessary actions here

        // For example, if you want to log a message similar to your onClick logic:
        Log.d("BUTTONS", "User tapped the Information Button");

        // You can also call your existing showDialog method if needed
        showDialog(findViewById(android.R.id.content).getRootView());

        // Add any other code you want to execute when the button is clicked here
    }

//    private void showCustomSnackBar(View view){
//
//        snackbar = Snackbar.make(view, "", Snackbar.LENGTH_INDEFINITE);
//
//        // Inflate custom layout
//        View customSnackBarView = getLayoutInflater().inflate(R.layout.information, null);
//        // Add custom layout to SnackBar
//        Snackbar.SnackbarLayout snackBarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
//        snackBarLayout.addView(customSnackBarView, 0);
//        snackbar.show();
//    }

//    public void informationDismiss(View view) {
//        // Handle the button click here.
//        // You can define your desired behavior when the button is clicked.
//        // Close the Toast
//        if (snackbar != null) {
//            // Close the Toast
//            snackbar.dismiss();
//        }
//    }
}
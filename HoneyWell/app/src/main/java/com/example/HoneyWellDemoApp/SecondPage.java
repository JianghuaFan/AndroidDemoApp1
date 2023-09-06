package com.example.HoneyWellDemoApp;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.rokid.glass.instruct.InstructLifeManager;
import com.rokid.glass.instruct.entity.EntityKey;
import com.rokid.glass.instruct.entity.IInstructReceiver;
import com.rokid.glass.instruct.entity.InstructEntity;


public class SecondPage extends AppCompatActivity  {
    private TextView title,tips;
    private  Button object_1,object_2,object_3,object_4,object_5,object_6,informationButton,buttonYes,buttonNo;
    private Snackbar snackbar;
    // Create and show the dialog for information
    private AlertDialog alertDialog = null;
    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    public InstructLifeManager mLifeManager;
    public ScrollView scrollView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_page);
        tips=findViewById(R.id.tips);
        title=findViewById(R.id.textViewTitle);
        informationButton=findViewById(R.id.informationButton);
        object_1=findViewById(R.id.button1);
        object_2=findViewById(R.id.button2);
        object_3=findViewById(R.id.button3);
        object_4=findViewById(R.id.button4);
        object_5=findViewById(R.id.button5);
        object_6=findViewById(R.id.button6);
        scrollView=findViewById(R.id.scrollView);
        configInstruct();

        // deal with information, get id from information.xml, so we can select yes or no to dismiss the snackBar
        final LayoutInflater factory = getLayoutInflater();
        final View informationView = factory.inflate(R.layout.information,null);

        buttonYes = informationView.findViewById(R.id.buttonYes);
        buttonNo = informationView.findViewById(R.id.buttonNo);
        object_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // This code will be executed when the Button is clicked
                // Replace this with the actions you want to perform on click
                Intent intent2 = new Intent(SecondPage.this, ThirdPage.class);
                startActivity(intent2);
            }
        });

        informationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the Information Button");
//                showCustomSnackBar(v);
                showDialog(v);
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

    public void showCustomSnackBar(View view) {
        snackbar = Snackbar.make(view, "", Snackbar.LENGTH_INDEFINITE);

        // Inflate custom layout
        View customSnackbarView = getLayoutInflater().inflate(R.layout.information, null);
        // Add custom layout to Snackbar
        Snackbar.SnackbarLayout snackbarLayout = (Snackbar.SnackbarLayout) snackbar.getView();
        snackbarLayout.addView(customSnackbarView, 0);
        snackbar.show();
    }
    // use dialog to show information
    private void showDialog(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.information,null);
        builder.setView(dialogView);
        alertDialog = builder.create();
        alertDialog.show();
    }
    public void onButtonClick(View view) {
        // Handle the button click here.
        // You can define your desired behavior when the button is clicked.
        // Close the Toast
        if (snackbar != null) {
            // Close the Toast
            snackbar.dismiss();
        }
    }

    private void goToFirstPage(){
        Intent intent1 = new Intent(SecondPage.this, MainActivity.class);
        startActivity(intent1);
    }
    private void goToSecondPage() {
        Intent intent2 = new Intent(SecondPage.this, SecondPage.class);
        startActivity(intent2);
    }

    private void goToThirdPage() {
        Intent intent3 = new Intent(SecondPage.this, ThirdPage.class);
        startActivity(intent3);
    }
    public void configInstruct(){
        mLifeManager = new InstructLifeManager(this, getLifecycle(), mInstructLifeListener);
        mLifeManager.addInstructEntity(
                new InstructEntity()
                        .addEntityKey(R.id.scrollView)
                        .addEntityKey(new EntityKey(EntityKey.Language.en,"scroll down"))
                        .setShowTips(true)
                        .setCallback(new IInstructReceiver() {
                            @Override
                            public void onInstructReceive(Activity act, String key, InstructEntity instruct) {
                                Log.d(TAG,"向下 觸發");
                                scrollView.pageScroll(View.FOCUS_DOWN);
                            }
                        })
        ).addInstructEntity(
                new InstructEntity()
                        .addEntityKey(R.id.scrollView)
                        .addEntityKey(new EntityKey(EntityKey.Language.en,"scroll up"))
                        .setShowTips(true)
                        .setCallback(new IInstructReceiver() {
                            @Override
                            public void onInstructReceive(Activity act, String key, InstructEntity instruct) {
                                Log.d(TAG,"向上 觸發");
                                scrollView.pageScroll(View.FOCUS_UP);
                            }
                        })
        );
        mLifeManager.addInstructEntity(
                new InstructEntity()
                        .addEntityKey(R.id.informationButton)
                        .addEntityKey(new EntityKey(EntityKey.Language.en,"select item 7"))
                        .setShowTips(true)
                        .setCallback(new IInstructReceiver() {
                            @Override
                            public void onInstructReceive(Activity act, String key, InstructEntity instruct) {
                                Log.d(TAG,"信息 觸發");
                                showCustomSnackBar(findViewById(android.R.id.content).getRootView());
                            }
                        })
        ).addInstructEntity(
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
                        .addEntityKey(R.id.button2)
                        .addEntityKey(new EntityKey(EntityKey.Language.en,"Object1"))
                        .setShowTips(true)
                        .setCallback(new IInstructReceiver() {
                            @Override
                            public void onInstructReceive(Activity act, String key, InstructEntity instruct) {
                                Log.d(TAG,"Object2 觸發");
                                goToThirdPage();
                            }
                        })
        ).addInstructEntity(
                new InstructEntity()
                        .addEntityKey(R.id.button1)
                        .addEntityKey(new EntityKey(EntityKey.Language.en,"select item 3"))
                        .setShowTips(true)
                        .setCallback(new IInstructReceiver() {
                            @Override
                            public void onInstructReceive(Activity act, String key, InstructEntity instruct) {
                                Log.d(TAG,"Object1 觸發");
                                goToThirdPage();
                            }
                        })
        );
        mLifeManager.addInstructEntity(
                new InstructEntity()
                        .addEntityKey(R.id.button2)
                        .addEntityKey(new EntityKey(EntityKey.Language.en,"select item 4"))
                        .setShowTips(true)
                        .setCallback(new IInstructReceiver() {
                            @Override
                            public void onInstructReceive(Activity act, String key, InstructEntity instruct) {
                                Log.d(TAG,"Object2 觸發");
                                goToSecondPage();
                            }
                        })
        ).addInstructEntity(
                new InstructEntity()
                        .addEntityKey(R.id.button2)
                        .addEntityKey(new EntityKey(EntityKey.Language.en,"Object2"))
                        .setShowTips(true)
                        .setCallback(new IInstructReceiver() {
                            @Override
                            public void onInstructReceive(Activity act, String key, InstructEntity instruct) {
                                Log.d(TAG,"Object2 觸發");
                                goToSecondPage();
                            }
                        })
        );
        mLifeManager.addInstructEntity(
                new InstructEntity()
                        .addEntityKey(R.id.button3)
                        .addEntityKey(new EntityKey(EntityKey.Language.en,"select item 5"))
                        .setShowTips(true)
                        .setCallback(new IInstructReceiver() {
                            @Override
                            public void onInstructReceive(Activity act, String key, InstructEntity instruct) {
                                Log.d(TAG,"Object3 觸發");
                                goToThirdPage();
                            }
                        })
        )
                .addInstructEntity(
                        new InstructEntity()
                            .addEntityKey(R.id.button3)
                            .addEntityKey(new EntityKey(EntityKey.Language.en,"Object3"))
                            .setShowTips(true)
                            .setCallback(new IInstructReceiver() {
                                @Override
                                public void onInstructReceive(Activity act, String key, InstructEntity instruct) {
                                    Log.d(TAG,"Object3 觸發");
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
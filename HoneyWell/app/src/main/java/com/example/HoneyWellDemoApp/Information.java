package com.example.HoneyWellDemoApp;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.rokid.glass.instruct.InstructLifeManager;
import com.rokid.glass.instruct.entity.EntityKey;
import com.rokid.glass.instruct.entity.IInstructReceiver;
import com.rokid.glass.instruct.entity.InstructEntity;

public class Information extends AppCompatActivity {
    public Button yes, no;
    public InstructLifeManager mLifeManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);
        configInstruct();
        yes = findViewById(R.id.buttonYes);
        no = findViewById(R.id.buttonNo);
    };

    public void configInstruct() {
        mLifeManager = new InstructLifeManager(this, getLifecycle(), mInstructLifeListener);
        mLifeManager.addInstructEntity(
                new InstructEntity()
                        .addEntityKey(R.id.buttonYes)
                        .addEntityKey(new EntityKey(EntityKey.Language.en, "yes"))
                        .addEntityKey(new EntityKey(EntityKey.Language.en, "select item 1"))
                        .setShowTips(true)
                        .setCallback(new IInstructReceiver() {
                            @Override
                            public void onInstructReceive(Activity act, String key, InstructEntity instruct) {
                                Log.d(TAG, "yes 觸發");

                            }
                        })
        );

        mLifeManager.addInstructEntity(
                new InstructEntity()
                        .addEntityKey(R.id.buttonNo)
                        .addEntityKey(new EntityKey(EntityKey.Language.en, "no"))
                        .addEntityKey(new EntityKey(EntityKey.Language.en, "select item 2"))
                        .setShowTips(true)
                        .setCallback(new IInstructReceiver() {
                            @Override
                            public void onInstructReceive(Activity act, String key, InstructEntity instruct) {
                                Log.d(TAG, "no 觸發");

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

package com.example.HoneyWellDemoApp;

import android.app.Activity;
import android.app.Application;

import com.rokid.glass.instruct.VoiceInstruction;
import com.rokid.glass.instruct.entity.EntityKey;
import com.rokid.glass.instruct.entity.IInstructReceiver;
import com.rokid.glass.instruct.entity.InstructEntity;

public class InstructionApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化语音指令SDK，App运行时默认关闭百灵鸟
        VoiceInstruction.init(this, false);
        VoiceInstruction.init(this);
        // alternatively, use the following mixing mode with both the plugin and the lark
        // VoiceInstruction.init(this, false);


        // set global instructions; you can delete the following code if there is no global instruction
        // e.g. the instruction Return
        VoiceInstruction.getInstance().addGlobalInstruct(
                new InstructEntity()
                        .setGlobal(true)
                        .addEntityKey(new EntityKey("返回", "fan hui"))
                        .addEntityKey(new EntityKey(EntityKey.Language.en, "back last page"))
                        .setCallback(new IInstructReceiver() {
                            @Override
                            public void onInstructReceive(Activity act, String key, InstructEntity instruct) {
                                try {
                                    if (act != null) {
                                        act.finish();
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }));
    }

}

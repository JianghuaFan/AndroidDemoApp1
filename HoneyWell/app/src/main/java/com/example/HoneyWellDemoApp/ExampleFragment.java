package com.example.HoneyWellDemoApp;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.rokid.glass.instruct.InstructLifeManager;
import com.rokid.glass.instruct.entity.EntityKey;
import com.rokid.glass.instruct.entity.IInstructReceiver;
import com.rokid.glass.instruct.entity.InstructEntity;

public class ExampleFragment extends Fragment {

    // Declare an interface to define a callback
    public interface ButtonClickListener {
        void onButtonClicked();
    }
    private ButtonClickListener buttonClickListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        // Ensure that the activity implements the interface
        if (context instanceof ButtonClickListener) {
            buttonClickListener = (ButtonClickListener) context;
        } else {
            throw new ClassCastException(context.toString() + " must implement ButtonClickListener");
        }
    }
//    private AlertDialog alertDialog = null;
//    public InstructLifeManager mLifeManager;

    public ExampleFragment() {
        // Required empty public constructor
    }
    private View view; // Declare the View here
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.example_fragment, container, false);

        // Find the button by its ID and set a click listener
        Button myButton = view.findViewById(R.id.myButton);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the callback method when the button is clicked
                if (buttonClickListener != null) {
                    buttonClickListener.onButtonClicked();
                }
            }
        });
//        configInstruct();
        return view;
    }

//    public void configInstruct(){
//        mLifeManager = new InstructLifeManager(getContext(), getLifecycle(), mInstructLifeListener);
//        mLifeManager.addInstructEntity(
//                new InstructEntity()
//                        .addEntityKey(R.id.myButton)
//                        .addEntityKey(new EntityKey(EntityKey.Language.en,"On Click"))
//                        .setShowTips(true)
//                        .setCallback(new IInstructReceiver() {
//                            @Override
//                            public void onInstructReceive(Activity act, String key, InstructEntity instruct) {
//                                Log.d(TAG,"信息 觸發");
//                                showDialog(view.findViewById(android.R.id.content).getRootView());
//                            }
//                        })
//        );
//    }
//    private void showDialog(View view) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        LayoutInflater inflater = getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.information,null);
//        builder.setView(dialogView);
//        alertDialog = builder.create();
////        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
////        lp.copyFrom(alertDialog.getWindow().getAttributes());
////        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
////        lp.height =  WindowManager.LayoutParams.FILL_PARENT;
////        int width = WindowManager.LayoutParams.MATCH_PARENT;
////        int height =  WindowManager.LayoutParams.FILL_PARENT;
////        alertDialog.getWindow().setLayout(width, height);
//        alertDialog.show();
//    }
//    private InstructLifeManager.IInstructLifeListener mInstructLifeListener = new InstructLifeManager.IInstructLifeListener() {
//        @Override
//        public boolean onInterceptCommand(String command) {
//            if ("需要拦截的指令".equals(command)) {
//                return true;
//            }
//            return false;
//        }
//        @Override
//        public void onTipsUiReady() {
//            Log.d("AudioAi", "onTipsUiReady Call ");
//        }
//
//        @Override
//        public void onHelpLayerShow(boolean show) {
//
//        }
//    };
}
package com.bijgepast.quissteling.util;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.bijgepast.quissteling.HomeActivity.HomeActivity;
import com.bijgepast.quissteling.R;
import com.bijgepast.quissteling.quiz.QuizActivity;

import java.time.LocalDateTime;


public class PopUpClass {
    private final View view;
    private final int resource;
    private View.OnClickListener okOnClickListener;
    private PopupWindow popupWindow;

    public PopUpClass(View view, int resource) {
        this.view = view;
        this.resource = resource;
    }

    public PopUpClass(View view, int resource, Context context, View.OnClickListener okOnClickListener) {
        this(view, resource);
        this.okOnClickListener = okOnClickListener;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void show(Context context) {
        //Create a View object.
        LayoutInflater inflater = (LayoutInflater) this.view.getContext().getSystemService(this.view.getContext().LAYOUT_INFLATER_SERVICE);
        /*
         * In de resource kan je de 2 buttons aanmaken. Deze buttons moeten altijd hetzelfde id hebben
         * id/closeButton
         * id/okButton
         */
        View popupView = inflater.inflate(resource, null);

        //Specify the length and width
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        //Make inactive items outsite of popupwindow
        boolean focusable = true;

        //create a window with our parameters
        this.popupWindow = new PopupWindow(popupView, width, height, focusable);

        //set location of popup
        popupWindow.showAtLocation(this.view, Gravity.CENTER, 0, 0);

        //init buttons
        Button okButton = popupView.findViewById(R.id.okButton);
        Button closeButton = popupView.findViewById(R.id.closeButton);

        //Onclick listeners
        if (closeButton != null) {
            closeButton.setOnClickListener(v -> popupWindow.dismiss());
        }

        if (this.okOnClickListener == null) {
            this.okOnClickListener = v -> {
                checkCode(popupView, context);
            };
        }

        if (okButton != null) {
            okButton.setOnClickListener(okOnClickListener);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void checkCode(View popupView, Context context){
        EditText code = popupView.findViewById(R.id.editTextNumber);

        if (code.getText().toString().length() != 4 || code.getText().toString().substring(2).equals("00")) {
            Toast.makeText(context, context.getString(R.string.toast_wrong_code),
                    Toast.LENGTH_LONG).show();
        } else {
            try {
                InitQuestion.quiz.setId(code.getText().toString());
                Intent intent = new Intent(context, QuizActivity.class);

                UserSetting userSetting = new UserSetting(context);
                if (userSetting.getLastDate() != null) {
                    if (LocalDateTime.now().isAfter(userSetting.getLastDate())) {
                        context.startActivity(intent);
                        userSetting.setLastDate(LocalDateTime.now());
                        popupWindow.dismiss();
                        ((HomeActivity) context).finish();

                    } else {
                        Toast.makeText(context, context.getString(R.string.toast_location_already_used), Toast.LENGTH_LONG).show();
                    }
                } else {
                    userSetting.setLastDate(LocalDateTime.now());
                    context.startActivity(intent);
                    popupWindow.dismiss();
                    ((HomeActivity) context).finish();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(context, context.getString(R.string.toast_wrong_code), Toast.LENGTH_LONG).show();
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void show(View view, Context context) {
        //Create a View object.
        /**
         * In de resource kan je de 2 buttons aanmaken. Deze buttons moeten altijd hetzelfde id hebben
         * id/closeButton
         * id/okButton
         */
        View popupView = view;

        //Specify the length and width
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;

        //Make inactive items outsite of popupwindow
        boolean focusable = true;

        //create a window with our parameters
        this.popupWindow = new PopupWindow(popupView, width, height, focusable);

        //set location of popup
        popupWindow.showAtLocation(this.view, Gravity.CENTER, 0, 0);

        //init buttons
        Button okButton = popupView.findViewById(R.id.okButton);
        Button closeButton = popupView.findViewById(R.id.closeButton);

        //Onclick listeners
        if (closeButton != null) {
            closeButton.setOnClickListener(v -> popupWindow.dismiss());
        }

        if (this.okOnClickListener == null) {
            this.okOnClickListener = v -> {
                EditText code = popupView.findViewById(R.id.editTextNumber);

                if (code.getText().toString().length() != 4) {
                    Toast.makeText(context, context.getString(R.string.toast_code_not_correct),
                            Toast.LENGTH_LONG).show();
                } else {
                    try {
                        UserSetting userSetting = new UserSetting(context);
                        if (LocalDateTime.now().isAfter(userSetting.getLastDate())) {
                            Intent intent = new Intent(context, QuizActivity.class);
                            context.startActivity(intent);
                            userSetting.setLastDate(LocalDateTime.now());
                        } else {
                            Toast.makeText(context, context.getString(R.string.toast_location_already_used), Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(context, context.getString(R.string.toast_wrong_code), Toast.LENGTH_LONG).show();
                    }
                }
            };
        }

        if (okButton != null) {
            okButton.setOnClickListener(okOnClickListener);
        }
    }

    public void dismiss(){
        this.popupWindow.dismiss();
    }
}

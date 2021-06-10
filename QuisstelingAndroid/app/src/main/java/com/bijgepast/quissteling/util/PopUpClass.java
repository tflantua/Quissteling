package com.bijgepast.quissteling.util;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bijgepast.quissteling.R;
import com.bijgepast.quissteling.quiz.QuizActivity;

public class PopUpClass {
    private final View view;
    private final int resource;
    private final Context context;
    private View.OnClickListener okOnClickListener;

    public PopUpClass(View view, int resource, Context context) {
        this.view = view;
        this.resource = resource;
        this.context = context;
    }

    public PopUpClass(View view, int resource, Context context, View.OnClickListener okOnClickListener) {
        this(view, resource, context);
        this.okOnClickListener = okOnClickListener;
    }

    public void show() {
        //Create a View object.
        LayoutInflater inflater = (LayoutInflater) this.view.getContext().getSystemService(this.view.getContext().LAYOUT_INFLATER_SERVICE);
        /**
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
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

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
                    Toast.makeText(context, "De lengte van de cijferreeks is niet correct",
                            Toast.LENGTH_LONG).show();
                } else {
                    try {
                        InitQuestion.quiz.setId(code.getText().toString());
                        Intent intent = new Intent(context, QuizActivity.class);
                        context.startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(context, "Dit is niet de juiste code", Toast.LENGTH_LONG).show();
                    }
                }
            };
        }

        if (okButton != null) {
            okButton.setOnClickListener(okOnClickListener);
        }
    }

    public void show(View view) {
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
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        //set location of popup
        popupWindow.showAtLocation(this.view, Gravity.CENTER, 0, 0);

        //init buttons
        Button okButton = popupView.findViewById(R.id.okButton);
        Button closeButton = popupView.findViewById(R.id.closeButton);

        if (this.okOnClickListener == null) {
            this.okOnClickListener = view1 -> {

            };
        }

        //Onclick listeners
        if (closeButton != null) {
            closeButton.setOnClickListener(v -> popupWindow.dismiss());
        }

        if (this.okOnClickListener == null) {
            this.okOnClickListener = v -> {
                EditText code = popupView.findViewById(R.id.editTextNumber);

                if (code.getText().toString().length() != 4) {
                    Toast.makeText(context, "De lengte van de cijferreeks is niet correct",
                            Toast.LENGTH_LONG).show();
                } else {
                    try {
                        InitQuestion.quiz.setId(code.getText().toString());
                        Intent intent = new Intent(context, QuizActivity.class);
                        context.startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(context, "Dit is niet de juiste code", Toast.LENGTH_LONG).show();
                    }
                }
            };
        }

        if (okButton != null) {
            okButton.setOnClickListener(okOnClickListener);
        }
    }
}

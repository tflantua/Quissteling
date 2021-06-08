package com.bijgepast.quissteling;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

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

        if (okButton != null) {
            okButton.setOnClickListener(okOnClickListener);
        }
    }
}

package com.bijgepast.quissteling.secondScreen.ui.MyPrizes;

import androidx.appcompat.app.AppCompatActivity;

import com.bijgepast.quissteling.R;
import com.bijgepast.quissteling.secondScreen.Price;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class MyPrizesDetailView extends AppCompatActivity {
    private static final String LOGTAG = MyPrizesDetailView.class.getName();

    public static final String EXTRA_PRICE_ID = "priceId";
    Price price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_prizes_detail_view);

        Log.d(LOGTAG,"Placing " + EXTRA_PRICE_ID + " in Detail");

        price = (Price) getIntent().getSerializableExtra("priceId");

        ImageView pricePhoto = (ImageView) findViewById(R.id.prijsImage);
        pricePhoto.setImageResource(price.getImageResourceId());

        TextView priceName = (TextView) findViewById(R.id.prijsNaam);
        priceName.setText(price.getPriceName());

        TextView priceDescription = (TextView) findViewById(R.id.prijsDescriptie);
        priceDescription.setText(price.getPriceDescription());

        TextView pricePlaceNeeded = (TextView) findViewById(R.id.prijsPlaats);
        pricePlaceNeeded.setText(price.getPlaceNeededString());
    }
}
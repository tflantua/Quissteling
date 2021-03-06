package com.bijgepast.quissteling.secondScreen.ui.MyPrizes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bijgepast.quissteling.R;
import com.bijgepast.quissteling.secondScreen.Price;
import com.bijgepast.quissteling.secondScreen.PriceAdapter;
import com.bijgepast.quissteling.util.UserSetting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MyPrizesFragment extends Fragment implements PriceAdapter.OnItemClickListener {
    private static final String LOGTAG = MyPrizesFragment.class.getName();


    private ArrayList<Price> priceList;
    private RecyclerView recyclerView;
    private PriceAdapter priceAdapter;
    private ConstraintLayout constraintLayout;

    private UserSetting userSetting;
    private Context context;
    private MyPrizesViewModel myPrizesViewModel;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myPrizesViewModel =
                new ViewModelProvider(this).get(MyPrizesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_myprizes, container, false);

        this.priceList = new ArrayList<>();
        this.context = root.getContext();
        this.userSetting = new UserSetting(root.getContext());

        this.recyclerView = root.findViewById(R.id.priceViewLeader);
        this.constraintLayout = root.findViewById(R.id.listconstraint);

        this.priceAdapter = new PriceAdapter(root.getContext(), this.priceList, this);
        this.recyclerView.setAdapter(this.priceAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));


        priceList.add(new Price(getString(R.string.prize_keychain), getString(R.string.prize_keychain_description), 4, R.drawable.esstelingsleutelhangerroodkapje, userSetting.getPrize1() ));
        priceList.add(new Price(getString(R.string.prize_stuffedAnimal), getString(R.string.pirze_stuffedAnimal_description), 3, R.drawable.esstelingknuffel, userSetting.getPrize2()));
        priceList.add(new Price(getString(R.string.prize_ticketDiscount), getString(R.string.prize_ticketDiscount_description), 2, R.drawable.esstelingkorting, userSetting.getPrize3()));
        priceList.add(new Price(getString(R.string.prize_restaurantDiscount), getString(R.string.pirze_stuffedAnimal_description), 1, R.drawable.esstelingvoedselkortingklein, userSetting.getPrize4()));
        priceList.add(new Price(getString(R.string.prize_fastLane), getString(R.string.prize_fastlane_description), 0, R.drawable.esstelingfastlane, userSetting.getPrize5()));


        return root;
    }

    @Override
    public void onItemClick(int clickedPosition) {
        Log.i(LOGTAG, "onItemClick() called for position " + clickedPosition);
        navigateToPriceDetailActivity(clickedPosition);
    }

    private void navigateToPriceDetailActivity(int position){
        Intent intent = new Intent(this.context, MyPrizesDetailView.class);
        intent.putExtra(MyPrizesDetailView.EXTRA_PRICE_ID, priceList.get(position));
        startActivity(intent);
    }
}
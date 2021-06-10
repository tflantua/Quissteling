package com.bijgepast.quissteling.secondScreen.ui.MyPrizes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bijgepast.quissteling.R;
import com.bijgepast.quissteling.secondScreen.Price;
import com.bijgepast.quissteling.secondScreen.PriceAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MyPrizesFragment extends Fragment {

    private ArrayList<Price> priceList;
    private RecyclerView recyclerView;
    private PriceAdapter priceAdapter;

    private MyPrizesViewModel myPrizesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        myPrizesViewModel =
                new ViewModelProvider(this).get(MyPrizesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_myprizes, container, false);

        this.priceList = new ArrayList<>();

        this.recyclerView = root.findViewById(R.id.priceViewLeader);
        this.priceAdapter = new PriceAdapter(root.getContext(), this.priceList, new PriceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int clickedPosition) {

            }
        });

        this.recyclerView.setAdapter(this.priceAdapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));

        Collections.addAll(priceList, Price.getPrices());

//        for (Price price : Price.getPrices()){
//            priceList.add(price);
//        }
//
//        priceList.add(new Price("Essteling Roodkapje Sleutelhanger", "Een sleutelhanger van Roodkapje uit het sprookje van Roodkapje.", 4, R.drawable.esstelinglogo ));
//        priceList.add(new Price("Essteling Roodkapje Knuffel", "Een knuffel van Roodkapje uit het sprookje van Roodkapje.", 3, R.drawable.esstelinglogo));
//        priceList.add(new Price("Essteling Ticket Korting Bon", "Een eenmalige korting bon op je volgende Efteling ticket.", 2, R.drawable.esstelinglogo));
//        priceList.add(new Price("Essteling Restaurant Korting Bon", "Een eenmalige korting bon op je volgende bestelling bij een van onze restaurants.", 1, R.drawable.esstelinglogo));
//        priceList.add(new Price("Essteling fast lane pass", "Krijg eenmalig voorang in een wachtrij bij een attractie naar keuze.", 0, R.drawable.esstelinglogo));


        return root;
    }
}
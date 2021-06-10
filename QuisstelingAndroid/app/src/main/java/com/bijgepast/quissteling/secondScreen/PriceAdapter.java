package com.bijgepast.quissteling.secondScreen;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.bijgepast.quissteling.BR;
import com.bijgepast.quissteling.R;

import java.util.List;

public class PriceAdapter extends RecyclerView.Adapter<PriceAdapter.PriceViewHolder> {
   private static final String LOGTAG = PriceAdapter.class.getName();

   private Context appContext;
   private OnItemClickListener clickListener;
   private List<Price> priceList;

   public class PriceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final ViewDataBinding binding;

       public PriceViewHolder(ViewDataBinding binding) {
           super(binding.getRoot());
           this.binding = binding;
       }

       public void bind(Object obj) {
           this.binding.setVariable(BR.data, obj);
           this.binding.executePendingBindings();
       }

       @Override
       public void onClick(View view) {
           int clickedPosition = getAdapterPosition();
           Log.i(LOGTAG, "Opleiding " + clickedPosition + " clicked");
           clickListener.onItemClick(clickedPosition);
       }
   }

    public interface OnItemClickListener {
        void onItemClick(int clickedPosition);
    }

    public PriceAdapter(Context context, List<Price> prices, OnItemClickListener clickListener) {
        appContext = context;
        this.priceList = prices;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public PriceAdapter.PriceViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d(LOGTAG, "onCreateViewHolder() called");
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.price_item, viewGroup, false);
        return new PriceViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PriceAdapter.PriceViewHolder priceViewHolder, int i) {
        Log.d(LOGTAG, "onBindViewHolder() called for item " + i);
        Log.d(LOGTAG, "Received: " + priceList.get(i).getPriceName());

        priceViewHolder.bind(priceList.get(i));
    }

    @Override
    public int getItemCount() {
        return priceList.size();
    }
}

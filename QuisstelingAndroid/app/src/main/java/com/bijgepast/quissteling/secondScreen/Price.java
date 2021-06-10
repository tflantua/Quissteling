package com.bijgepast.quissteling.secondScreen;

import com.bijgepast.quissteling.R;
import com.bijgepast.quissteling.util.UserSetting;

import java.io.Serializable;

public class Price implements Serializable {

    private final String priceName;
    private final String priceDescription;
    private final int placeNeeded;
    private final int imageResourceId;

    private int lock;
    // 0 = unlocked
    // 1 = locked

    public Price(String priceName, String priceDescription, int imageResourceId, Boolean lockCheck) {
        this.priceName = priceName;
        this.priceDescription = priceDescription;
        this.placeNeeded = 0;
        this.imageResourceId = imageResourceId;
        if (lockCheck){
            this.lock = 0;
        } else{
            this.lock = 1;
        }
    }

    public Price(String priceName, String priceDescription, int placeNeeded, int imageResourceId, Boolean lockCheck) {
        this.priceName = priceName;
        this.priceDescription = priceDescription;
        this.placeNeeded = placeNeeded;
        this.imageResourceId = imageResourceId;
        if (lockCheck){
            this.lock = 0;
        } else{
            this.lock = 1;
        }
    }

    public String getPriceName() {
        return priceName;
    }

    public String getPriceDescription() {
        return priceDescription;
    }

    public String getPlaceNeededString(){
        if (getPlaceNeeded() == 0){
            return "N.V.T";
        }
        else{
            return "" + placeNeeded;
        }
    }

    public int getLock() {
        return lock;
    }

    public void setLock(int lock) {
        this.lock = lock;
    }

    public int getPlaceNeeded() {
        return placeNeeded;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }



}
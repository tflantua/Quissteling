package com.bijgepast.quissteling.secondScreen;

import com.bijgepast.quissteling.R;

import java.io.Serializable;

public class Price implements Serializable {

    private final String priceName;
    private final String priceDescription;
    private final int placeNeeded;
    private final int imageResourceId;

    public Price(String priceName, String priceDescription, int imageResourceId) {
        this.priceName = priceName;
        this.priceDescription = priceDescription;
        this.placeNeeded = 0;
        this.imageResourceId = imageResourceId;
    }

    public Price(String priceName, String priceDescription, int placeNeeded, int imageResourceId) {
        this.priceName = priceName;
        this.priceDescription = priceDescription;
        this.placeNeeded = placeNeeded;
        this.imageResourceId = imageResourceId;
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

    public int getPlaceNeeded() {
        return placeNeeded;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    private static final Price[] prices ={
            new Price("Essteling Roodkapje Sleutelhanger", "Een sleutelhanger van Roodkapje uit het sprookje van Roodkapje.", 4, R.drawable.esstelingsleutelhangerroodkapje),
            new Price("Essteling Roodkapje Knuffel", "Een knuffel van Roodkapje uit het sprookje van Roodkapje.", 3, R.drawable.esstelingknuffel),
            new Price("Essteling Ticket Korting Bon", "Een eenmalige korting bon op je volgende Efteling ticket.", 2, R.drawable.esstelingkorting),
            new Price("Essteling Restaurant Korting Bon", "Een eenmalige korting bon op je volgende bestelling bij een van onze restaurants.", 1, R.drawable.esstelingvoedselkortingklein),
            new Price("Essteling fast lane pass", "Krijg eenmalig voorang in een wachtrij bij een attractie naar keuze.", R.drawable.esstelingfastlane)
    };

    public static Price[] getPrices(){
        return prices;
    }

    public static Price getPrice(int id){
        return prices[id];
    }


}
package com.example.nathaniel.a1up;

public class Inventory {

    private String number_Entered, name_Entered, price_Entered, quantity_Entered, date_Entered;

    public Inventory(String nameEntered, String numberEntered, String quantityEntered,
                     String priceEntered, String dateEntered) {

        name_Entered = nameEntered;
        number_Entered = numberEntered;
        quantity_Entered = quantityEntered;
        price_Entered = priceEntered;
        date_Entered = dateEntered;}


    public String getNameEntered() {
        return name_Entered;
    }
    public String getNumberEntered() {
        return number_Entered;
    }
    public String getQuantityEntered() {
        return quantity_Entered;
    }
    public String getPriceEntered() {
        return price_Entered;
    }
    public String getDateEntered() {
        return date_Entered;
    }
}

package com.example.memorygame;

import android.widget.ImageView;

public class Card {
    int imgId;
    ImageView itemId;
    boolean isOpen;

    public Card(int ImgId,ImageView ItemId){
        this.imgId = ImgId;
        this.itemId = ItemId;
        this.isOpen = false;
    }

    public void SetCardIsOpen(boolean status){
        this.isOpen = status;
    }
}

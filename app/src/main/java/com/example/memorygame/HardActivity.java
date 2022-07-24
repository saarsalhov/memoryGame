package com.example.memorygame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class HardActivity extends AppCompatActivity {
    private View decorView;
    private ArrayList<Integer> myImageList = new ArrayList<>();
    ArrayList<ImageView> button = new ArrayList<>();
    int movesCounter = 0;
    Card firstImageClicked = null;
    Card secondImageClicked = null;
    int firstImage;
    int secondImage;
    ArrayList<Card> myCardsList = new ArrayList<>();
    TextView triesNumber;
    TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard);

        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == 0) {
                    decorView.setSystemUiVisibility(HideSystemBars());
                }
            }
        });
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String passedUser = extras.getString("name");
            username = (TextView)findViewById(R.id.hardUserName);
            username.setText(passedUser);
        }
        myImageList.add(R.drawable.pizza);
        myImageList.add(R.drawable.carrot);
        myImageList.add(R.drawable.sushi);
        myImageList.add(R.drawable.falafel);
        myImageList.add(R.drawable.icecream);
        myImageList.add(R.drawable.pasta);
        myImageList.add(R.drawable.shawarma);
        myImageList.add(R.drawable.pancake);
        myImageList.addAll(myImageList);
        Collections.shuffle(myImageList);
        button.add(findViewById(R.id.hardCard1));
        button.add(findViewById(R.id.hardCard2));
        button.add(findViewById(R.id.hardCard3));
        button.add(findViewById(R.id.hardCard4));
        button.add(findViewById(R.id.hardCard5));
        button.add(findViewById(R.id.hardCard6));
        button.add(findViewById(R.id.hardCard7));
        button.add(findViewById(R.id.hardCard8));
        button.add(findViewById(R.id.hardCard9));
        button.add(findViewById(R.id.hardCard10));
        button.add(findViewById(R.id.hardCard11));
        button.add(findViewById(R.id.hardCard12));
        button.add(findViewById(R.id.hardCard13));
        button.add(findViewById(R.id.hardCard14));
        button.add(findViewById(R.id.hardCard15));
        button.add(findViewById(R.id.hardCard16));


        for (int i = 0; i < 16; i++) {
            Card card = new Card(myImageList.get(i),button.get(i));
            myCardsList.add(card);
        }
        movesCounter = 0;

//        triesNumber = (TextView)findViewById(R.id.hardTries);
//        triesNumber.setText(movesCounter);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            decorView.setSystemUiVisibility(HideSystemBars());

        }
    }

    private int HideSystemBars() {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }

    public void onImageclicked(View view) throws InterruptedException {
        if (firstImageClicked == null){
            for (Card card: myCardsList){
                if(card.itemId == (ImageView) view && !card.isOpen){
                    ((ImageView) view).setImageResource(card.imgId);
                    firstImageClicked = card;
                    firstImage = card.imgId;
                    card.SetCardIsOpen(true);
                    movesCounter++;
//                    triesNumber.setText("moves: " + movesCounter);
                }
            }
        }
        else if (secondImageClicked == null && ((ImageView) view).getId() != firstImageClicked.itemId.getId()){
            for (Card card: myCardsList){
                if(card.itemId == (ImageView) view && !card.isOpen){
                    ((ImageView) view).setImageResource(card.imgId);
                    secondImageClicked = card;
                    secondImage = card.imgId;
                    card.SetCardIsOpen(true);
                    movesCounter++;
//                    triesNumber.setText("moves: " + movesCounter);
                }
            }
        }
        if (firstImageClicked != null && secondImageClicked != null){
            if(firstImage != secondImage){
                firstImageClicked.itemId.setImageResource(R.drawable.card);
                secondImageClicked.itemId.setImageResource(R.drawable.card);
                firstImageClicked.SetCardIsOpen(false);
                secondImageClicked.SetCardIsOpen(false);
                Toast.makeText(getApplicationContext(),"Wrong cards", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getApplicationContext(),"Its a match!", Toast.LENGTH_SHORT).show();
            }
            firstImageClicked = null;
            secondImageClicked = null;
        }
        if (IsWin()){
            Toast.makeText(getApplicationContext(),"You Win!!!!", Toast.LENGTH_LONG).show();
        }
    }

    private boolean IsWin(){
        boolean isWin = true;
        for (Card card : myCardsList){
            if(!card.isOpen){
                isWin = false;
            }
        }
        return  isWin;
    }

}
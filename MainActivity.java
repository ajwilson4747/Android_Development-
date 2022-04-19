package com.example.fibonacciflower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList <ImageView> allPetals;
    private LayoutInflater layoutInflater;

    private Button pinkBtn;
    private Button goldBtn;
    private Button clearBtn;
    private RelativeLayout relativeLayout;

    Flower myFlower;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myFlower = new Flower();
        allPetals = new ArrayList <ImageView> ();

        //Initialize generation of the Fibonacci artwork
        initialize();

        //create a layout interface to add petals to relative layout
        layoutInflater = (LayoutInflater) getSystemService (Context.LAYOUT_INFLATER_SERVICE);

        relativeLayout = (RelativeLayout) findViewById (R.id.relativeLayout1);
        pinkBtn = (Button) findViewById (R.id.button);
        goldBtn = (Button) findViewById (R.id.button2);
        clearBtn = (Button) findViewById (R.id.button3);

        pinkBtn.setOnClickListener(addPetal);
        goldBtn.setOnClickListener(addPetal);
        clearBtn.setOnClickListener(clearPetals);

        //set up the center coordinate
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        myFlower.set_xCenter(metrics.widthPixels / 2);
        myFlower.set_yCenter(metrics.heightPixels/2);
    }

    private void initialize(){
        //task 1: initialize all the settings for the first petal
        myFlower.setRotate(0);
        myFlower.setScaleX((float) .3);
        myFlower.setScaleY((float) .3);
        myFlower.setDegenerate((float) 1.001);
        myFlower.initializeAngle();
    }
    //On Click button handlers for adding a flower to the screen
    private View.OnClickListener addPetal = new View.OnClickListener() {
        public void onClick (View view){
            //task 1: instantiate a view to store a petal graphic
            ImageView petal;

            //Inflate the correct petal
            String buttonText = ( (Button) view).getText().toString();
            if (buttonText.equals("Add Pink"))
                petal = (ImageView) layoutInflater.inflate(R.layout.petal_pink, null);
            else
                petal = (ImageView) layoutInflater.inflate(R.layout.petal_gold, null);
            //task 2: set up the visual properties of the petal
            petal.setX(myFlower.get_xCenter());
            petal.setY(myFlower.get_yCenter());
            petal.setPivotY(0);
            petal.setPivotX(100);
            petal.setScaleX(myFlower.getScaleX());
            petal.setScaleY(myFlower.getScaleY());
            petal.setRotation(myFlower.getRotate());

            //task 3: place the inflated imageview in the main layout
            relativeLayout.addView(petal);

            //Task 4: add the imageview of the petal to the arraylist
            allPetals.add(petal);

            //Task 5: update the angle and scale
            // for the next petal to be added
            myFlower.updatePetalValues();
        }
    };

    //on click button handler to clear all petals on the screen
    private View.OnClickListener clearPetals = new View.OnClickListener(){
        public void onClick(View view){
            //Task 1: Remove all the petal image view from the layout
            for (int i =0; i<allPetals.size(); i++){
                ImageView petal = allPetals.get(i);
                relativeLayout.removeView(petal);
            }

            //Task 2: clear the arraylist and reset all variables
            allPetals.clear();
            initialize();
        }
    };
}
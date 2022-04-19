package com.example.shippingcalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Data Model for ship item
    private ShipItem shipItem;

    //View Objects for Layout UI Reference
    private EditText weightET;
    private TextView baseCostTV;
    private TextView addedCostTV;
    private TextView totalCostTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Task 2: Create a data model for storing an item to be shipped
        shipItem = new ShipItem();

        //Task 3: Establish the references to Input weight element
        weightET = (EditText) findViewById(R.id.editText1);

        //Tasked 4: Establish the References to Output Elements
        baseCostTV = (TextView) findViewById(R.id.textView5);
        addedCostTV= (TextView) findViewById(R.id.textView6);
        totalCostTV= (TextView) findViewById(R.id.textView7);

        //Task 5: Register the Listener Event For Weight Input
        weightET.addTextChangedListener(weightTextWatcher);
    }

    private TextWatcher weightTextWatcher = new TextWatcher(){
        //the input element is attached to an editable,
        //therefore these methods are called when the text is changed

        public void onTextChanged(CharSequence s, int start, int before, int count){
            try{
                shipItem.setWeight((int) Double.parseDouble(s.toString()));
            }
            catch (NumberFormatException e){
                shipItem.setWeight(0);
            }
            displayShipping();
        }

        public void afterTextChanged (Editable s){}
        public void beforeTextChanged (CharSequence s, int start, int count, int after){}
    };

    private void displayShipping(){
        //display the base cost, addedcost, and total cost
        baseCostTV.setText("$" + String.format("%.02f",shipItem.getBaseCount()));
        addedCostTV.setText("$" + String.format("%.02f",shipItem.getAddedCost()));
        totalCostTV.setText("$" + String.format("%.02f",shipItem.getTotalCost()));

    }
}
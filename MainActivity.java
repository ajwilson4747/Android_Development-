package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Button;



public class MainActivity extends AppCompatActivity {

        //instantiate the variables

        private BMI myBMI;
        private TextView quanHeight;
        private TextView quanWeight;
        private TextView totalSummary;
        private TextView whParameter1;
        private TextView whParameter2;
        private RadioGroup converterType;

        private RadioButton selection1;
        private RadioButton selection2;

        private Button calculate;

        private boolean usedMetrics=false;
        private boolean usedStandard=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myBMI = new BMI();

        quanHeight= findViewById(R.id.textView5);
        quanWeight= findViewById(R.id.textView6);

        totalSummary = findViewById(R.id.textView7);

        converterType = (RadioGroup) findViewById(R.id.radioGroup);

        selection2 = (RadioButton) findViewById(R.id.radioButton2);

        whParameter1 = findViewById(R.id.textView8);
        whParameter2 = findViewById(R.id.textView9);

        calculate = (Button) findViewById(R.id.button6);



        converterType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) findViewById(checkedId);
                switch (checkedId){
                    case R.id.radioButton:
                        whParameter1.setText("Centimeters");
                        whParameter2.setText("Kilograms");
                        usedMetrics = true;
                        break;
                    case R.id.radioButton2:
                        whParameter1.setText("Inches");
                        whParameter2.setText("Pounds");
                        usedStandard=true;
                        break;
                }
            }
        });

        calculate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (usedMetrics ==true){
                    double yourBMIResults = myBMI.computeMetric();
                    totalSummary.setText("Your BMI Weight is:" + " "+ Double.toString(yourBMIResults));
                }
                else{
                    double yourBMIResults = myBMI.computeStandard();
                    totalSummary.setText("Your BMI Weight is:" + " " + Double.toString(yourBMIResults));
                }
            }
        }
        );


    }


    public void incrementHeight(View view){
        myBMI.incrementHeight();
        quanHeight.setText(myBMI.getQuantityHeight());
    }

    public void decrementHeight(View view){
        myBMI.decrementHeight();
        quanHeight.setText(myBMI.getQuantityHeight());
    }

    public void incrementWeight(View view){
        myBMI.incrementWeight();;
        quanWeight.setText(myBMI.getQuantityWeight());
    }

    public void decrementWeight(View view){
        myBMI.decrementWeight();
        quanWeight.setText(myBMI.getQuantityWeight());
    }
}
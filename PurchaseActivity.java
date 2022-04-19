package com.example.autopurchase;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;

public class PurchaseActivity extends AppCompatActivity {

    //The auto object cotains the unformation
    //about the vehicle being purchased

    Auto mAuto;

    //data to be passed to the LoanActivity

    String loanReport;
    String monthlyPayment;

    //layout to input references

    private EditText carPriceET;
    private EditText downPayET;
    private RadioGroup loanTermRG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase_layout);
        //establish references to editable text fields and radio buttons

        carPriceET = (EditText) findViewById (R.id.editText1);
        downPayET = (EditText) findViewById (R.id.editText2);
        loanTermRG = (RadioGroup) findViewById (R.id.radioButton1);

        //create an automobile object to store auto data
        mAuto = new Auto();
    }
    private void collectAutoInputData (){
        //Task 1: Set the car price
        mAuto.setPrice( (double) Integer.valueOf(carPriceET.getText().toString()));

        //Task 2: set the down payment
        mAuto.setDownPayment ( (double) Integer.valueOf(downPayET.getText().toString()));

        //Task 3: set the loan term
        Integer radioId = loanTermRG.getCheckedRadioButtonId();
        RadioButton term = (RadioButton) findViewById (radioId);
        mAuto.setLoanTerm(term.getText().toString());
    }

    private void buildLoanReport(){
        //1: construct the monthly payment
        Resources res = getResources();
        monthlyPayment = res.getString(R.string.report_line1) +
        String.format("%.02f", mAuto.monthlyPayment());

        //2: construct the loan Report
        loanReport += res.getString(R.string.report_line6) + String.format("%10.02f", mAuto.getPrice());
        loanReport += res.getString(R.string.report_line7) + String.format("%10.02f", mAuto.getDownPayment());
        loanReport += res.getString (R.string.report_line9) + String.format("%18.02f", mAuto.taxAmount());
        loanReport += res.getString (R.string.report_line10) + String.format("%18.02f", mAuto.totalCost());
        loanReport += res.getString (R.string.report_line11) + String.format("%12.02f", mAuto.borrowedAmount());
        loanReport += res.getString (R.string.report_line12) + String.format("%12.02f", mAuto.interestAmount());

        loanReport += "\n\n" + res.getString (R.string.report_line8) + String.format("%12.02f", mAuto.getLoanTerm() + " years.");
        loanReport += "\n\n" + res.getString (R.string.report_line2);
        loanReport += res.getString (R.string.report_line3);
        loanReport += res.getString (R.string.report_line4);
        loanReport += res.getString (R.string.report_line5);
    }
        public void activateLoanSummary(View view){
        //1: Build A Loan Report From the Input Data
            collectAutoInputData();
            buildLoanReport();

        //2: create an intent to display the loan summary activity
        Intent launchReport = new Intent(this,LoanSummaryActivity.class);

        //3: pass the loan summary activity two pieces of data
            //the loan report containing Loan Details
            //the monthly payment
            launchReport.putExtra("LoanReport", loanReport);
            launchReport.putExtra("MonthlyReport", monthlyPayment);

        //4: Start the Loan activity
        startActivity(launchReport);
        }
}
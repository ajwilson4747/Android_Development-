package com.example.bmicalculator;

public class BMI {
    private double BMI;
    //include data variables
    private Integer numHeight;
    private Integer numWeight;

    private final int max = 500;
    private final int min = 0;

    public BMI (){
        numHeight=0;
        numWeight=0;
    }

    public void incrementHeight (){
        if (numHeight < max){
            numHeight++;
        }

    }

    public void decrementHeight (){
        if (numHeight > min){
            numHeight--;
        }
    }

    public void incrementWeight(){
        if(numWeight < max){
            numWeight++;
        }
    }

    public void decrementWeight(){
        if(numWeight > min){
            numWeight--;
        }
    }

    public String getQuantityHeight(){
        return numHeight.toString();
    }

    public String getQuantityWeight(){
        return numWeight.toString();
    }

    public double computeStandard(){
        BMI = ((double) numWeight / (double) (numHeight * numHeight)) * 703.0;
       return BMI;
    }
    public double computeMetric(){
        BMI = ((double) numWeight / (double) (numHeight*numHeight)) * 10000.0;
        return BMI;
    }
}

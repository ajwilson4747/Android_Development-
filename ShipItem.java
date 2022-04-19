package com.example.shippingcalculator;

public class ShipItem {
    //Shipping constants
    static final Double BASE= 3.00;
    static final Double ADDED = .50;
    static final int BASE_WEIGHT = 16;
    static final double EXTRA_OUNCES = 4.0;

    //Data members
    private Integer mWeight;
    private Double mBaseCost;
    private Double mAddedCost;
    private Double mTotalCost;

    public ShipItem(){
        mWeight=0;
        mBaseCost= BASE;
        mAddedCost=0.0;
        mTotalCost=0.0;
    }

    public void setWeight(int weight){
        mWeight = weight;
        computeCosts();
    }

    public void computeCosts(){
        mAddedCost = 0.0;
        mBaseCost = BASE;

        if (mWeight <=0)
            mBaseCost = 0.0;
        else if (mWeight > BASE_WEIGHT)
            mAddedCost = Math.ceil((double)
        (mWeight - BASE_WEIGHT) / EXTRA_OUNCES) * ADDED;

        mTotalCost = mBaseCost + mAddedCost;
    }

    public Double getBaseCount(){
        return mBaseCost;
    }

    public Double getAddedCost(){
        return mAddedCost;
    }

    public Double getTotalCost(){
        return mTotalCost;
    }

}

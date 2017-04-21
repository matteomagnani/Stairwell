package com.matteomagnani;

/**
 * Created by mmagnani on 21/04/2017.
 */
public class StairwellResult {

    private String resultName;
    private int minStrides;

    public StairwellResult() {
    }

    public String getResultName() {
        return resultName;
    }

    public void setResultName(String resultName) {
        this.resultName = resultName;
    }

    public int getResultAmount() {
        return minStrides;
    }

    public void setResultAmount(int resultAmount) {
        this.minStrides = resultAmount;
    }
}

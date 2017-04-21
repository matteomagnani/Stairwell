package com.matteomagnani;

/**
 * Created by mmagnani on 20/04/2017.
 */
public class StairwellObject {

    private String[] flights;
    private int steps;
    public static final int MIN_FLIGHTS = 1;
    public static final int MAX_FLIGHTS = 30;
    public static final int MAX_STEPS = 20;
    public static final int MIN_STRIDE = 1;
    public static final int MAX_STRIDE = 30;
    public static final int STEPS_TO_TURN = 2;

    public String[] getFlights() {
        return flights;
    }

    public void setFlights(String[] flights) {
        this.flights = flights;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }
}

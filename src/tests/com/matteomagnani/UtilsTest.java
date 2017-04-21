package com.matteomagnani;

import org.junit.Assert;

/**
 * Created by mmagnani on 21/04/2017.
 */
public class UtilsTest {
    @org.junit.Test
    public void getData() throws Exception {
        String input = "1234";
        Assert.assertEquals("1234", input);
        Assert.assertNotNull(input);
        Assert.assertNotEquals("", input);
    }

    @org.junit.Test
    public void checkInput() throws Exception {
        String input = "data=1234";
        String[] inMap = input.split("=");
        Assert.assertEquals("data", inMap[0]);
        Assert.assertEquals("1234", inMap[1]);
    }

    @org.junit.Test
    public void calculateMinStrides() throws Exception {
        int result = 0;
        StairwellObject so = new StairwellObject();
        so.setFlights(new String[]{ "17", "17" });
        so.setSteps(3);

        for (int i = 0; i < so.getFlights().length; i++) {
            result += (Integer.parseInt(so.getFlights()[i]) / so.getSteps()) + (so.getSteps() - (Integer.parseInt(so.getFlights()[i]) % so.getSteps()));
        }

        if (so.getFlights().length > 1) {
            if (so.getFlights().length % 2 == 0) {
                result += StairwellObject.STEPS_TO_TURN * (so.getFlights().length / 2);

            } else {
                result += StairwellObject.STEPS_TO_TURN * ((so.getFlights().length / 2) - 1);

            }
        }

        Assert.assertEquals(14, result);
    }

    @org.junit.Test
    public void validateStaircaseObject() throws Exception {
        StairwellObject so = new StairwellObject();
        so.setFlights(new String[]{ "17", "17" });
        so.setSteps(3);

        Assert.assertTrue(so.getSteps() < 4);
        Assert.assertTrue(so.getSteps() > 1);
        Assert.assertTrue(so.getFlights().length < 30);
        Assert.assertTrue(Integer.parseInt(so.getFlights()[0]) < 20);

    }

}
package com.matteomagnani;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import javassist.bytecode.stackmap.TypeData;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;


/**
 * Created by mmagnani on 19/04/2017.
 */
public class Utils {

    private static final Logger logger = Logger.getLogger(TypeData.ClassName.class.getName());
    private static JsonParser parser = new JsonParser();
    public static final String INPUT_PARAM_NAME = "data";
    public static final int INVALID_STEPS_RANGE = 9999;
    public static final int INVALID_FLIGHTS_AMOUNT = 9998;

    public final String getData(String input) throws UnsupportedEncodingException {
        String[] inMap = input.split("=");
        String staircase = base64UrlDecode(inMap[1]);

        try {
            if(isValidJson(staircase)){
                logger.info("valid json");
                return staircase;
            }
        } catch (Exception e) {
            logger.severe("error in decoding the base64 input" + e);
        }

        return "";
    }

    private static String base64UrlDecode(String input) {
        String result;
        Base64 decoder = new Base64(true);
        byte[] decodedBytes = decoder.decode(input);
        result = new String(decodedBytes);
        return result;
    }

    public final Boolean checkInput(String in){
        String[] inMap = in.split("=");
        Boolean isCorrectParam = false;
        logger.info("input = " + inMap[0]);

        if (inMap[0].equals(INPUT_PARAM_NAME)){
            isCorrectParam = true;
            logger.info("input param name = " + inMap[0]);
        } else {
            isCorrectParam = false;
            logger.severe("not equals = " + inMap[0] + " / " + INPUT_PARAM_NAME);
        }

        return isCorrectParam;
    }

    private Boolean isValidJson(String in) throws Exception {

        JsonElement je;
        try {
            je = parser.parse(in);
        } catch (Exception e) {
            throw new Exception("error validating the json" + e);
        }

        return je.isJsonObject();
    }

    public int calculateMinStrides(StairwellObject so) {
        int result = 0;

        for (int i = 0; i < so.getFlights().length; i++) {
            result += (Integer.parseInt(so.getFlights()[i]) / so.getSteps()) + (so.getSteps() - (Integer.parseInt(so.getFlights()[i]) % so.getSteps()));
        }

        if (so.getFlights().length > 1) {
            if (so.getFlights().length % 2 == 0) {
                result += StairwellObject.STEPS_TO_TURN * (so.getFlights().length / 2);
                logger.info("pari: " + result);
            } else {
                result += StairwellObject.STEPS_TO_TURN * ((so.getFlights().length / 2) - 1);
                logger.info("dispari: " + result);
            }
        }

        return result;

    }

    public boolean validateStaircaseObject(StairwellObject so) {
        if (so.getFlights().length > 30 || so.getFlights().length < 1){
            logger.severe(String.format("Invalid amount of flights%d", so.getFlights().length));
            return false;
        } else if (so.getSteps() > 4 && so.getSteps() < 1) {
            logger.severe(String.format("Invalid range of steps%d", so.getSteps()));
            return false;
        } else {
            return true;
        }
    }
}

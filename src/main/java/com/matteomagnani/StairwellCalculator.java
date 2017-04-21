package com.matteomagnani;

import com.google.gson.Gson;
import javassist.bytecode.stackmap.TypeData;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;


/**
 * Created by mmagnani on 19/04/2017.
 */

@Path("stairwell/")
public class StairwellCalculator {

    private static final Logger logger = Logger.getLogger(TypeData.ClassName.class.getName());

    @POST
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED + ";charset=utf-8")
    public Response calculateSteps (String in) throws Exception {


        in = java.net.URLDecoder.decode(in, "UTF-8");
        logger.info("Got a request with input: " + in);

        int result;
        String message;
        Utils utils = new Utils();

        if(in.isEmpty() || in == null){
            logger.severe("input empty");
            return Response.serverError().entity("ko: input empty or null").build();
        }

        try {
            if (!utils.checkInput(in)) {
                logger.severe("Error: expected input param name: " + Utils.INPUT_PARAM_NAME);
                return Response.serverError().entity("Error: expected input param name: " + Utils.INPUT_PARAM_NAME).build();
            }

            try {
                String parsedInput = utils.getData(in);
                Gson gson = new Gson();
                StairwellObject so = gson.fromJson(parsedInput, StairwellObject.class);

                if (!utils.validateStaircaseObject(so)) {
                    logger.severe("Incorrect amount of flights or steps range");
                    return Response.serverError().entity("ko: Incorrect amount of flights or steps range").build();
                }

                try {
                    result = utils.calculateMinStrides(so);
                } catch (Exception e){
                    logger.severe("Error calculating the number of strides " + e);
                    return Response.serverError().entity("Error calculating the number of strides").build();
                }

                StairwellResult sr = new StairwellResult();
                sr.setResultAmount(result);

                message = gson.toJson(sr);

                logger.info("ok result : " + result);
                return Response.ok(message, MediaType.APPLICATION_JSON).build();

            } catch (Exception e) {
                logger.severe("Input not in base64 " + e);
                return Response.serverError().entity("Input not in base64.").build();

            }
        } catch (Exception e){
            logger.severe("input not in base64 " + e);
            return Response.serverError().entity("error calculating the solution").build();
        }
    }
}
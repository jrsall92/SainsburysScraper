package com.jsalliaris;

import com.jsalliaris.Utils.LogUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsalliaris.domain.Response;
import com.jsalliaris.service.ResponseService;

import java.net.MalformedURLException;
import java.net.URL;

public class Application {

    public static void main(String[] args) {
        checkArgs(args);
        ResponseService responseService = new ResponseService();
        Response response = responseService.getResponse(args[0]);
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(json);
    }

    private static void checkArgs(String[] args){
        LogUtil.setDebug(true);

        if(args.length == 0){
            LogUtil.logError("You need to provide at least one argument with the initial url page");
            System.exit(-1);
        }

        try {
            URL url = new URL(args[0]);
        } catch (MalformedURLException e) {
            LogUtil.logError("The provided first argument is not a valid URL");
            System.exit(-1);
        }

        if(args.length > 2){
            LogUtil.logError("You can provide up to two arguments. The first is the URL of the initial page " +
                    "and the second is true/false to enable/disable logging");
            System.exit(-1);
        }

        if(args.length == 2 && !args[1].equals("true") && !args[1].equals("false")){
            LogUtil.logError("The second argument needs to be 'true' or 'false'");
            System.exit(-1);
        }

        if(args.length == 2){
            LogUtil.setDebug(Boolean.parseBoolean(args[1]));
        }

    }

}

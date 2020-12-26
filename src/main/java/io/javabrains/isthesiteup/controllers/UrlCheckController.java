package io.javabrains.isthesiteup.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {

    private final String SITE_IS_UP = "Site is up!";
    private final String SITE_IS_DOWN = "Site is down!";
    private final String INCORRECT_URL = "URL is incorrect!";

    @GetMapping("/check")
    public String getUrlStatusMessage(@RequestParam String url) {
        System.out.println("url passed : " + url);
        String returnMessage = "";
        System.out.println("returnMessage : " + returnMessage);
        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            System.out.println("Response Code : " + conn.getResponseCode());
            int responseCodeCategory = conn.getResponseCode() / 100;
            System.out.println("responseCodeCategory : " + responseCodeCategory);
            if(responseCodeCategory != 2 && responseCodeCategory != 3) {
                returnMessage = SITE_IS_DOWN;
            } else {
                returnMessage = SITE_IS_UP;
            }
            System.out.println("returnMessage : " + returnMessage);
		} catch (MalformedURLException e) {
            System.err.println("MalformedURLException occurred");
            e.printStackTrace();
            returnMessage = INCORRECT_URL;
		} catch (IOException e) {
            returnMessage = SITE_IS_DOWN;
            System.err.println("IOException occurred");
            e.printStackTrace();            
        }
        System.out.println("returnMessage : " + returnMessage);
        return returnMessage;
    }
    
}

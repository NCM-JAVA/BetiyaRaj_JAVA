package com.bor.rcms.enums;
import java.io.IOException;

import okhttp3.*;
import java.io.IOException;
import java.util.Random;  // Import Random class

import okhttp3.*;
import okhttp3.*;
import okhttp3.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class Test {
 

    public static void main(String[] args) {
        // Define the parameters
        String apiKey = "5c20a6b499570";  // Example API key
        String sender = "NCMSMS";  // Sender ID
        String mobileNo = "8287482288";  // Recipient mobile number
        String otp="1292";
        String text = otp+ " is mobile number verification OTP. Do not share with others Team JDA (NETCREATIVEMIND SOLUTIONS).";  // SMS content

        // Build the request URL with dynamic parameters
        String url = String.format(
            "https://www.mysmsapp.in/api/push.json?apikey=%s&sender=%s&mobileno=%s&text=%s",
            apiKey, sender, mobileNo, text
        );

        // Call the method to send SMS
        sendSms(url);
    }

    private static void sendSms(String url) {
        OkHttpClient client = new OkHttpClient();

        // Create the request body (empty in this case, since the data is in the URL)
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");

        // Build the request
        Request request = new Request.Builder()
            .url(url)
            .method("POST", body)
            .build();

        // Execute the request
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("SMS sent successfully!");
            } else {
                System.out.println("Error sending SMS: " + response.message());
            }
        } catch (Exception e) {
            System.out.println("Request failed: " + e.getMessage());
        }
    
}
}

package com.bor.rcms.ExceptionHand;

import java.util.HashMap;
import java.util.Map;

public class Responsedata {
    private static final Map<Integer, String> statusMap = new HashMap<>();

    static {
        statusMap.put(200, "OK");
        statusMap.put(201, "Created");
        statusMap.put(400, "Bad Request");
        statusMap.put(401, "Unauthorized");
        statusMap.put(404, "Not Found");
        statusMap.put(500, "Internal Server Error");
        // Add more as needed
    }

    public static String getMessage(int code) {
        return statusMap.getOrDefault(code, "Unknown Status Code");
    }

}
